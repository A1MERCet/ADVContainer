package com.aimercet.advcontainer.loot.container;

import com.aimercet.advcontainer.container.ContainerManager;
import com.aimercet.advcontainer.container.IContainer;
import com.aimercet.advcontainer.loot.ILoot;
import com.aimercet.advcontainer.loot.trigger.ILootTrigger;

public class ContainerLootState implements IContainerLootState
{
    public final IContainerLoot loot;
    public final IContainer container;

    public ContainerLootState(IContainerLoot loot)
    {
        this.loot = loot;
        container = loot.getContainerTemplate().create(ContainerManager.instance.handlerGeneral, String.valueOf(hashCode()));
    }

    @Override
    public void onRefresh(ILootTrigger trigger, ILoot loot) {
        IContainerLootState.super.onRefresh(trigger, loot);
    }

    @Override public ILoot getLoot() {return loot;}
    @Override public IContainer getContainer() {return container;}
}
