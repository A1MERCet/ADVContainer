package com.aimercet.advcontainer.loot;

import com.aimercet.advcontainer.item.Quality;
import com.aimercet.advcontainer.loot.item.ILootItem;
import com.aimercet.advcontainer.loot.trigger.ILootTrigger;
import org.bukkit.inventory.ItemStack;

public class RandomResult
{
    public final ILootTrigger trigger;
    public final float random;
    public final Quality quality;
    public final ILootItem lootItem;
    public final ItemStack itemStack;

    public RandomResult(ILootTrigger trigger, float random, Quality quality, ILootItem lootItem, ItemStack itemStack)
    {
        this.trigger = trigger;
        this.random = random;
        this.quality = quality;
        this.lootItem = lootItem;
        this.itemStack = itemStack;
    }
}
