package com.aimercet.advcontainer.container.source;

import com.aimercet.advcontainer.util.UtilItem;
import org.bukkit.inventory.ItemStack;

public class SourceItemStack extends SourceSlotItem
{
    public final ItemStack itemStack;
    public SourceItemStack(ItemStack itemStack)
    {
        super(UtilItem.toSlotItem(itemStack));
        this.itemStack = itemStack;
    }
}
