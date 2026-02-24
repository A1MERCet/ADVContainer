package com.aimercet.advcontainer.loot;

import com.aimercet.advcontainer.container.slotitem.ISlotItem;
import com.aimercet.advcontainer.item.Quality;
import com.aimercet.advcontainer.loot.item.ILootItem;
import com.aimercet.advcontainer.loot.trigger.ILootTrigger;

public class RandomResult
{
    public final ILootTrigger trigger;
    public final float random;
    public final Quality quality;
    public final ILootItem lootItem;
    public final ISlotItem item;

    public RandomResult(ILootTrigger trigger, float random, Quality quality, ILootItem lootItem, ISlotItem item)
    {
        this.trigger = trigger;
        this.random = random;
        this.quality = quality;
        this.lootItem = lootItem;
        this.item = item;
    }
}
