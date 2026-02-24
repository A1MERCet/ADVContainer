package com.aimercet.advcontainer.bridge.minecraft.container;

import com.aimercet.advcontainer.container.Container;
import org.bukkit.inventory.Inventory;

public class ContainerVanilla extends Container
{
    public Inventory inventory;

    protected ContainerVanilla(Inventory inventory)
    {
        super(inventory.hashCode()+"");
    }
}
