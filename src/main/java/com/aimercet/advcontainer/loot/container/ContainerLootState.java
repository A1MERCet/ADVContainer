package com.aimercet.advcontainer.loot.container;

import com.aimercet.advcontainer.container.ContainerManager;
import com.aimercet.advcontainer.container.IContainer;
import com.aimercet.advcontainer.container.source.ISource;
import com.aimercet.advcontainer.container.source.SourceLoot;
import com.aimercet.advcontainer.loot.Condition.ISpawnCondition;
import com.aimercet.advcontainer.loot.ILoot;
import com.aimercet.advcontainer.loot.ILootState;
import com.aimercet.advcontainer.loot.trigger.ILootTrigger;

public class ContainerLootState implements IContainerLootState
{
    public final String id;
    public final IContainerLoot loot;
    public final IContainer container;
    private ISpawnCondition condition;
    private ISource source;

    private long prevRefreshTime = -1L;
    private long nextRefreshTime = -1L;

    public ContainerLootState(String id,IContainerLoot loot)
    {
        this.id = id;
        this.loot = loot;
        this.container = loot.getContainerTemplate().create(ContainerManager.instance.handlerGeneral, String.valueOf(hashCode()));
        this.container.setInventorySource(new SourceLoot(this));
    }

    @Override
    public void onRefresh(ILootTrigger trigger, ILoot loot)
    {
        IContainerLootState.super.onRefresh(trigger, loot);
    }

    @Override public ILoot getLoot() {return loot;}
    @Override public IContainer getContainer() {return container;}
    @Override public String getID() {return id;}
    @Override public long getPrevRefreshTime() {return prevRefreshTime;}
    @Override public long getNextRefreshTime() {return nextRefreshTime;}
    @Override public void setPrevRefreshTime(long v) {prevRefreshTime=v;}
    @Override public void setNextRefreshTime(long v) {nextRefreshTime=v;}
    @Override public ISpawnCondition getSpawnCondition() {return condition;}
    @Override public void setSpawnCondition(ISpawnCondition condition) {this.condition=condition;}
    @Override public ILootState SetSource(ISource source) {this.source = source;return this;}
    @Override public ISource getSource() {return source;}
}
