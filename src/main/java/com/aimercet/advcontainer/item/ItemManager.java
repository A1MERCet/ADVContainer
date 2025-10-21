package com.aimercet.advcontainer.item;

import com.aimercet.advcontainer.bridge.minecraft.container.SlotItemStack;
import com.aimercet.advcontainer.container.IContainer;
import com.aimercet.advcontainer.container.slotitem.ISlotItem;
import com.aimercet.advcontainer.item.item.TypeItem;
import com.aimercet.advcontainer.item.item.TypeItemContainer;
import com.aimercet.brlib.Options;
import com.aimercet.brlib.localization.Localization;
import com.aimercet.brlib.log.LogBuilder;
import com.aimercet.brlib.log.Logger;
import com.aimercet.brlib.util.MapBuilder;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemManager
{
    public static HashMap<String,Class<? extends ISlotItem>> slotItemClass = new MapBuilder<String,Class<? extends ISlotItem>>()
            .put(SlotItemStack.CLASS_NAME, SlotItemStack.class)
            .map;
    public static Class<? extends ISlotItem> getSlotItemClass(String id){return slotItemClass.get(id);}
    public static void registerSlotItemClass(String id , ISlotItem slotItem){slotItemClass.put(id, slotItem.getClass());}

    public static HashMap<String,Material> materialsCache = new HashMap<>();
    static {
        for (Material material : Material.values())
            materialsCache.put(material.name().toUpperCase(), material);
    }

    public static HashMap<String,Class<? extends TypeItem>> typeItemClass = new MapBuilder<String,Class<? extends TypeItem>>()
            .put("default",TypeItem.class)
            .put("container", TypeItemContainer.class)
            .map;

    public static ItemManager instance;
    public static TypeItem Get(String id){return instance.items.get(id);}
    public static TypeItem Get(ItemStack isk){return isk==null?null:instance.items.get(isk.getType().name());}

    private HashMap<String,TypeItem> items = new HashMap();
    private HashMap<ItemType,List<TypeItem>> typeMap = new HashMap();

    public TypeItem get(String id){return items.get(id);}
    public TypeItem get(ItemStack isk){return items.get(isk.getType().name());}
    public HashMap<String,TypeItem> getAll(){return items;}
    public HashMap<ItemType,List<TypeItem>> getTypeMap(){return typeMap;}

    private void add(TypeItem item)
    {
        if(item==null) {Logger.warn("register failed - item is null");return;}

        ItemType type = item.getType();
        if(type==null) Logger.warn("item["+item.id+"] type is null");

        List<TypeItem> list = typeMap.getOrDefault(type, new ArrayList<>());
        list.add(item);
        typeMap.put(type, list);

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
        File directory = new File(path);

        if(directory.exists() && directory.isDirectory()) {
            for(File f : directory.listFiles())
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
                    Class<? extends TypeItem> clz = typeItemClass.get(clzName.toLowerCase());
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

                    Logger.info("已加载物品: "+item.id+"["+ clzName+"/"+Localization.get(item.type.lang) +"]");
                }
            }
        }else {Logger.warn("没有找到任何物品注册配置文件");}

    }

    public static ISlotItem loadSlotItem(ConfigurationSection section)
    {
        if(section==null)return null;

        Class<? extends ISlotItem> clz = ItemManager.getSlotItemClass(section.getString("class"));
        if(clz == null){
            Logger.error("slot item class["+section.getString("class")+"] not found");return null;}

        if(clz == SlotItemStack.class){
            ItemStack isk = section.getItemStack("content");
            if(isk==null)return null;

            ISlotItem instance = null;

            try {
                instance = clz.getConstructor(ItemStack.class).newInstance(isk);
                instance.load(section);
            } catch (Exception e) {e.printStackTrace();}
            return instance;
        }
        return null;
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
    public static String getContainer(ItemStack isk)
    {
        return getValue(isk,"container");
    }
    public static ItemStack setContainer(ItemStack isk , IContainer container)
    {
        return setLore(isk,"container",container.getUUID());
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
