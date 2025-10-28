package com.aimercet.advcontainer.item.item;

import com.aimercet.advcontainer.container.backpack.equipment.EquipType;
import com.aimercet.advcontainer.item.ItemType;
import com.aimercet.advcontainer.item.Quality;
import com.aimercet.advcontainer.loot.item.LootItem;
import com.aimercet.advcontainer.util.SizeInt;
import com.aimercet.brlib.localization.Localization;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

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


    public TypeItem(String id)
    {
        this.id = id;
        this.loot = new LootItem(this);
        this.fullLang = Localization.register("item_full_"+id,id);
        this.shortLang = Localization.register("item_short_"+id,id);
        this.descriptionLang = Localization.register("item_descr_"+id,id);
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

    @Override public String toString() {return getClass().getSimpleName()+"[ID="+getID()+", Type:"+type+", "+getDefaultSize()+"]";}
}
