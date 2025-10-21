package com.aimercet.advcontainer.bridge.minecraft.container;

import com.aimercet.advcontainer.container.slotitem.ISlotItem;
import com.aimercet.advcontainer.item.ItemManager;
import com.aimercet.advcontainer.item.item.TypeItem;
import com.aimercet.advcontainer.util.SizeInt;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

public class SlotItemStack implements ISlotItem
{
    public static String CLASS_NAME = "default";
    private final TypeItem typeItem;
    private final ItemStack item;

    public SlotItemStack(ItemStack item)
    {
        this.item = item;
        this.typeItem = ItemManager.Get(item);
    }

    public ItemStack getItemStack() {return item;}
    @Override public String getClassName() {return CLASS_NAME;}
    @Override public long getAmount() {return ItemManager.getAmount(item);}
    @Override public TypeItem getTypeItem() {return typeItem;}
    @Override public String getSlotItemID() {return item==null?"null":item.getType().name();}
    @Override public String getSlotItemLang() {return item==null?"null":item.getType().name();}

    @Override
    public SizeInt getSize()
    {
        if(item == null)return null;
        TypeItem typeItem = ItemManager.Get(item.getType().name());
        return typeItem==null?null:typeItem.getSize(item);
    }

    @Override
    public void load(ConfigurationSection section)
    {
    }

    @Override
    public void save(ConfigurationSection section)
    {
        section.set("content",item);
    }

    @Override public String toString() {return getClass().getSimpleName()+"["+getSlotItemID()+"]";}
}
