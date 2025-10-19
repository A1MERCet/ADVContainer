package com.aimercet.advcontainer.container.slotitem;

import com.aimercet.advcontainer.item.ItemManager;
import com.aimercet.advcontainer.item.item.TypeItem;
import com.aimercet.advcontainer.util.SizeInt;
import org.bukkit.inventory.ItemStack;

public class SlotItemStack implements ISlotItem
{
    public final TypeItem typeItem;
    public final ItemStack item;

    public SlotItemStack(ItemStack item)
    {
        this.item = item;
        this.typeItem = ItemManager.Get(item);
    }

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

    @Override public String toString() {return getClass().getSimpleName()+"["+getSlotItemID()+"]";}
}
