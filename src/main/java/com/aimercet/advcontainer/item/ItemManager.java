package com.aimercet.advcontainer.item;

import com.aimercet.advcontainer.item.item.TypeItem;
import com.aimercet.brlib.Options;
import com.aimercet.brlib.localization.Localization;
import com.aimercet.brlib.log.LogBuilder;
import com.aimercet.brlib.log.Logger;
import com.aimercet.brlib.util.MapBuilder;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ItemManager
{
    public static HashMap<String,Material> materialsCache = new HashMap<>();
    static {
        for (Material material : Material.values())
            materialsCache.put(material.name().toUpperCase(), material);
    }

    public static HashMap<String,Class<TypeItem>> loadClasses = new MapBuilder<String,Class<TypeItem>>()
            .put("default",TypeItem.class)
            .map;

    public static ItemManager instance;
    public static TypeItem Get(String id){return instance.items.get(id);}
    public static TypeItem Get(ItemStack isk){return instance.items.get(isk.getType().name());}

    private HashMap<String,TypeItem> items = new HashMap();

    public TypeItem get(String id){return items.get(id);}
    public TypeItem get(ItemStack isk){return items.get(isk.getType().name());}
    public HashMap<String,TypeItem> getAll(){return new HashMap<>(items);}

    private void add(TypeItem item)
    {
        items.put(item.id, item);
    }

    public static String loader_item_repeated          = Localization.register("loader_item_repeated","$i has been registered multiple times");
    public static String loader_item_start             = Localization.register("loader_item_start","Item config loading");
    public static String loader_item_class             = Localization.register("loader_item_class","Item Class not fount");
    public static String loader_item_initfailed        = Localization.register("loader_item_initfailed","Initialization process failed");
    public static String loader_item_materialnotfound  = Localization.register("loader_item_materialnotfound","Material[$s] not found");

    public ItemManager()
    {
        instance = this;
        load();
    }

    public ItemStack create(Material material){return create(material.name());}
    public ItemStack create(String id)
    {
        TypeItem i = get(id);
        return i.createItem();
    }

    public void load()
    {
        items.clear();

        Logger.info(Localization.get(loader_item_start));
        String path = Options.Instance().configPath+"/items/";
        for(File f : Objects.requireNonNull(new File(path).listFiles()))
        {
            YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
            if(yml.getConfigurationSection("item")==null)continue;
            for(String pathID : yml.getConfigurationSection("item").getKeys(false))
            {
                String id = pathID.toUpperCase();

                if(items.containsKey(id)){
                    Logger.error(Localization.get(loader_item_repeated,"$s",id));
                    continue;
                }

                if(!materialsCache.containsKey(id)){
                    Logger.error(Localization.get(loader_item_materialnotfound,"$s",id));
                    continue;
                }

                String clzName = yml.getString("item."+pathID+".class");
                Class<TypeItem> clz = loadClasses.get(clzName.toLowerCase());
                if(clz==null){
                    LogBuilder.Text(id+" ").lang(loader_item_class).error();
                    continue;
                }

                TypeItem item = null;
                try {item = clz.getConstructor(String.class).newInstance(id);} catch (Exception e) {e.printStackTrace();}
                if(item==null) {
                    LogBuilder.Text(id+" ").lang(loader_item_initfailed).error();
                    continue;
                }

                item.load(yml.getConfigurationSection("item."+pathID));
                add(item);

                Logger.info("Loaded item: "+item.id+"["+ clzName+"/"+Localization.get(item.type.lang) +"]");
            }
        }
    }

    public static ItemStack increaseDuration(ItemStack isk , float amount)
    {
        float source = getAmount(isk);
        return setDuration(isk, source + amount);
    }
    public static ItemStack reduceDuration(ItemStack isk , float amount)
    {
        float source = getAmount(isk);
        return setDuration(isk, source - amount);
    }
    public static ItemStack increaseAmount(ItemStack isk , long amount)
    {
        long source = getAmount(isk);
        return setAmount(isk, source + amount);
    }
    public static ItemStack reduceAmount(ItemStack isk , long amount)
    {
        long source = getAmount(isk);
        return setAmount(isk, source - amount);
    }
    public static ItemStack setAmount(ItemStack isk , long amount)
    {
        if(amount<=0)Logger.warn(Localization.get("item amount less than zero "+isk.getType().name()));
        TypeItem typeItem = ItemManager.Get(isk);
        if(typeItem!=null)amount = Math.min(typeItem.maxAmount,amount);

        return setLore(isk,"amount",String.valueOf(amount));
    }
    public static long getAmount(ItemStack isk)
    {
        return getLong(isk,"amount");
    }
    public static ItemStack setDuration(ItemStack isk , float amount)
    {
        return setLore(isk,"duration",String.valueOf(amount));
    }
    public static float getDuration(ItemStack isk)
    {
        return getFloat(isk,"duration");
    }
    public static ItemStack setFIR(ItemStack isk , boolean fir)
    {
        return setLore(isk,"FIR",String.valueOf(fir));
    }
    public static boolean getFIR(ItemStack isk)
    {
        return getBoolean(isk,"FIR");
    }


    public static List<String> getLore(ItemStack isk)
    {
        ItemMeta meta = isk.getItemMeta();
        if(meta!=null) {
            List<String> lore = meta.getLore();
            return lore==null?new ArrayList<>():lore;
        }
        return new ArrayList<>();
    }
    public static ItemStack setLore(ItemStack isk, List<String> lore)
    {
        ItemMeta meta = isk.getItemMeta();
        meta.setLore(lore);
        isk.setItemMeta(meta);
        return isk;
    }
    public static ItemStack setLore(ItemStack isk, String key , String value)
    {
        List<String> lore = getLore(isk);

        lore.removeIf(e->e.contains("$"+key+"$"));
        lore.add("$"+key+"$"+value);

        setLore(isk, lore);
        return isk;
    }
    public static String getValue(ItemStack isk , String key)
    {
        List<String> lore = getLore(isk);
        for (String l : lore)
            if(l.contains("$"+key+"$"))
                return l.replace("$"+key+"$","");
        return null;
    }
    public static int getInt(ItemStack isk , String key)
    {
        String v = getValue(isk , key);
        if(v!=null)return Integer.parseInt(v);
        return 0;
    }
    public static long getLong(ItemStack isk , String key)
    {
        String v = getValue(isk , key);
        if(v!=null)return Long.parseLong(v);
        return 0L;
    }
    public static float getFloat(ItemStack isk , String key)
    {
        String v = getValue(isk , key);
        if(v!=null)return Float.parseFloat(v);
        return 0F;
    }
    public static double getDouble(ItemStack isk , String key)
    {
        String v = getValue(isk , key);
        if(v!=null)return Double.parseDouble(v);
        return 0D;
    }
    public static boolean getBoolean(ItemStack isk , String key)
    {
        String v = getValue(isk , key);
        if(v!=null)return Boolean.parseBoolean(v);
        return false;
    }
    public static ItemStack setValue(ItemStack isk , String key , int value)
    {
        List<String> lore = getLore(isk);
        lore.removeIf(e->e.contains("$"+key+"$"));
        lore.add("$"+key+"$"+value);
        setLore(isk, lore);
        return isk;
    }
    public static ItemStack setValue(ItemStack isk , String key , long value)
    {
        List<String> lore = getLore(isk);
        lore.removeIf(e->e.contains("$"+key+"$"));
        lore.add("$"+key+"$"+value);
        setLore(isk, lore);
        return isk;
    }
    public static ItemStack setValue(ItemStack isk , String key , float value)
    {
        List<String> lore = getLore(isk);
        lore.removeIf(e->e.contains("$"+key+"$"));
        lore.add("$"+key+"$"+value);
        setLore(isk, lore);
        return isk;
    }
    public static ItemStack setValue(ItemStack isk , String key , double value)
    {
        List<String> lore = getLore(isk);
        lore.removeIf(e->e.contains("$"+key+"$"));
        lore.add("$"+key+"$"+value);
        setLore(isk, lore);
        return isk;
    }
    public static ItemStack setValue(ItemStack isk , String key , boolean value)
    {
        List<String> lore = getLore(isk);
        lore.removeIf(e->e.contains("$"+key+"$"));
        lore.add("$"+key+"$"+value);
        setLore(isk, lore);
        return isk;
    }


}
