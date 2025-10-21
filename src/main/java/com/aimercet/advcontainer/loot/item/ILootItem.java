package com.aimercet.advcontainer.loot.item;

import com.aimercet.advcontainer.item.Quality;
import com.aimercet.advcontainer.item.item.TypeItem;
import com.aimercet.advcontainer.loot.trigger.ILootTrigger;
import org.bukkit.inventory.ItemStack;

public interface ILootItem
{
    TypeItem getItem();
    float getRate();
    long getMinAmount();
    long getMaxAmount();
    Quality getQualityOverlap();
    ItemStack random(ILootTrigger trigger);
}
