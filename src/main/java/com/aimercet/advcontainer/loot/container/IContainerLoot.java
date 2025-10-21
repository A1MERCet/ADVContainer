package com.aimercet.advcontainer.loot.container;

import com.aimercet.advcontainer.container.ContainerTemplate;
import com.aimercet.advcontainer.loot.ILoot;

public interface IContainerLoot extends ILoot
{
    ContainerTemplate getContainerTemplate();
    IContainerLootState createContainerLootState();
}
