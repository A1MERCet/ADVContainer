package com.aimercet.advcontainer.util;

import com.aimercet.advcontainer.bridge.minecraft.container.SlotItemStack;
import com.aimercet.advcontainer.container.slotitem.ISlotItem;
import org.bukkit.inventory.ItemStack;

public class UtilItem
{
    public static ISlotItem toSlotItem(ItemStack itemStack)
    {
        return new SlotItemStack(itemStack);
    }
}
