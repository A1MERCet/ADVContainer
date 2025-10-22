package com.aimercet.advcontainer.loot;

import com.aimercet.advcontainer.container.source.ISource;
import com.aimercet.advcontainer.loot.Condition.ISpawnCondition;

public class LootState implements ILootState
{
    public final String id;
    public final ILoot loot;
    private ISpawnCondition condition;
    private ISource source;

    private long prevRefreshTime = -1L;
    private long nextRefreshTime = -1L;

    public LootState(String id, ILoot loot)
    {
        this.id = id;
        this.loot = loot;
    }

    @Override public String getID() {return id;}
    @Override public ILoot getLoot() {return loot;}
    @Override public long getPrevRefreshTime() {return prevRefreshTime;}
    @Override public long getNextRefreshTime() {return nextRefreshTime;}
    @Override public void setPrevRefreshTime(long v) {prevRefreshTime=v;}
    @Override public void setNextRefreshTime(long v) {nextRefreshTime=v;}
    @Override public ISpawnCondition getSpawnCondition() {return condition;}
    @Override public void setSpawnCondition(ISpawnCondition condition) {this.condition=condition;}
    @Override public ILootState SetSource(ISource source) {this.source=source;return this;}
    @Override public ISource getSource() {return source;}

}
