package com.aimercet.advcontainer.item.item;

import com.aimercet.advcontainer.bridge.minecraft.customevent.config.ConfigTypeItemLoadEvent;
import com.aimercet.advcontainer.bridge.minecraft.customevent.config.ConfigTypeItemSaveEvent;
import com.aimercet.advcontainer.container.backpack.equipment.EquipType;
import com.aimercet.advcontainer.container.backpack.equipment.SlotEquip;
import com.aimercet.advcontainer.container.source.ISource;
import com.aimercet.advcontainer.container.source.ISourceEntity;
import com.aimercet.advcontainer.item.ItemType;
import com.aimercet.advcontainer.item.Quality;
import com.aimercet.advcontainer.item.item.property.Property;
import com.aimercet.advcontainer.item.item.property.PropertyFactory;
import com.aimercet.advcontainer.loot.item.LootItem;
import com.aimercet.advcontainer.util.SizeInt;
import com.aimercet.brlib.localization.Localization;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class TypeItem
{
    public static String loader_item_size             = Localization.register("loader_item_size","The size of item $s is not standardized");

    public final String id;
    public final String fullLang;
    public final String shortLang;
    public final String descriptionLang;

    public ItemType type;
    public Quality quality;
    public EquipType equipType;

    public int maxAmount;
    public long systemPrice;

    public float maxDuration;
    public boolean durationBreak = false;

    public SizeInt defaultSize = new SizeInt(1, 1);

    public LootItem loot;

    private HashMap<String, Property<?>> property = new HashMap();

    public void addProperty(String name, Property<?> property) {this.property.put(name,property);}
    public void removeProperty(String name) {this.property.remove(name);}
    public Property<?> getProperty(String name) {return this.property.get(name);}

    public TypeItem(String id)
    {
        this.id = id;
        this.loot = new LootItem(this);
        this.fullLang = Localization.register("item_full_"+id,id);
        this.shortLang = Localization.register("item_short_"+id,id);
        this.descriptionLang = Localization.register("item_descr_"+id,id);
    }

    public void onHandOn(ISourceEntity entity , ISource source)
    {

    }
    public void onHandOff(ISourceEntity entity , ISource source)
    {

    }
    public void onEquip(ISourceEntity entity , SlotEquip slot)
    {

    }
    public void onUnEquip(ISourceEntity entity , SlotEquip slot)
    {

    }

    public ItemStack createItem()
    {
        return new ItemStack(Material.valueOf(id));
    }

    public SizeInt getSize(ItemStack isk)
    {
        return defaultSize;
    }

    public boolean hasDuration(){return maxDuration>0F;}

    public void load(ConfigurationSection section)
    {
        type            = ItemType.valueOf(section.getString("type"));
        quality         = Quality.fromLevel(section.getInt("quality",0));
        equipType       = EquipType.valueOf(section.getString("equipType"));

        maxAmount       = section.getInt("maxAmount",1);
        systemPrice     = section.getLong("systemPrice",-1L);

        maxDuration     = (float)section.getDouble("maxDuration",-1D);
        durationBreak   = section.getBoolean("durationBreak",false);

        defaultSize     = new SizeInt(section.getInt("sizeWidth",1), section.getInt("sizeHeight",1));

        loot.rate       = (float)section.getDouble("loot.rate",1D);
        loot.minAmount  = section.getInt("loot.minAmount",1);
        loot.maxAmount  = section.getInt("loot.maxAmount",1);

        ConfigurationSection pro = section.getConfigurationSection("property");
        if(pro!=null)
            for (String key : pro.getKeys(false))
            {
                Property<?> parse = PropertyFactory.parseYaml(pro.getConfigurationSection(key));
                if(parse!=null) addProperty(key,parse);
            }

        Bukkit.getPluginManager().callEvent(new ConfigTypeItemLoadEvent(this));
    }

    public void save(ConfigurationSection section) {
        Bukkit.getPluginManager().callEvent(new ConfigTypeItemSaveEvent(this));
        //todo
    }

    public String getID()                   {return id;}
    public ItemType getType()               {return type;}
    public Quality getQuality()             {return quality;}
    public int getMaxAmount()               {return maxAmount;}
    public long getSystemPrice()            {return systemPrice;}
    public float getMaxDuration()           {return maxDuration;}
    public boolean isDurationBreak()        {return durationBreak;}
    public SizeInt getDefaultSize()         {return defaultSize;}
    public LootItem getDefaultLoot()        {return loot.clone();}
    public EquipType getEquipType()         {return equipType;}
    public HashMap<String, Property<?>> getProperty() {return property;}

    @Override public String toString() {return getClass().getSimpleName()+"[ID="+getID()+", Type:"+type+", "+getDefaultSize()+"]";}
}
