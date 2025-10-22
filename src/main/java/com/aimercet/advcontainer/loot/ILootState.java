package com.aimercet.advcontainer.loot;

import com.aimercet.advcontainer.container.source.ISource;
import com.aimercet.advcontainer.loot.Condition.ISpawnCondition;
import com.aimercet.advcontainer.loot.trigger.ILootTrigger;

public interface ILootState
{
    String getID();
    ILoot getLoot();
    ISpawnCondition  getSpawnCondition();
    void setSpawnCondition(ISpawnCondition condition);
    ILootState SetSource(ISource source);
    ISource getSource();

    default void onRefresh(ILootTrigger trigger, ILoot loot)
    {
        setNextRefreshTime(System.currentTimeMillis() + loot.getRefreshTime());
    }

    long getPrevRefreshTime();
    long getNextRefreshTime();
    void setPrevRefreshTime(long v);
    void setNextRefreshTime(long v);
}
