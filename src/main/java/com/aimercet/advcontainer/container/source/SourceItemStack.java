package com.aimercet.advcontainer.container.source;

import com.aimercet.advcontainer.bridge.minecraft.vanilla.UtilVanillaInventory;
import org.bukkit.inventory.ItemStack;

public class SourceItemStack extends SourceSlotItem
{
    public final ItemStack itemStack;
    public SourceItemStack(ItemStack itemStack)
    {
        super(UtilVanillaInventory.toSlotItem(itemStack));
        this.itemStack = itemStack;
    }
}
