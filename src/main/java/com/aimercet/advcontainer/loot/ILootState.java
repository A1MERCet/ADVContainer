package com.aimercet.advcontainer.loot;

import com.aimercet.advcontainer.loot.trigger.ILootTrigger;

public interface ILootState
{
    ILoot getLoot();
    default void onRefresh(ILootTrigger trigger, ILoot loot) {}
}
