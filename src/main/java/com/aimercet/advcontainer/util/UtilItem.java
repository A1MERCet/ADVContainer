package com.aimercet.advcontainer.util;

import com.aimercet.advcontainer.bridge.minecraft.container.SlotItemStack;
import com.aimercet.advcontainer.container.slotitem.ISlotItem;
import com.aimercet.advcontainer.item.ItemManager;
import org.bukkit.inventory.ItemStack;

public class UtilItem
{
    public static ISlotItem toSlotItem(ItemStack itemStack)
    {
        if(ItemManager.Get(itemStack)==null)return null;
        return new SlotItemStack(itemStack);
    }
}
