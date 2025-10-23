package com.aimercet.advcontainer.container.backpack.equipment;

import com.aimercet.advcontainer.container.IContainer;
import com.aimercet.advcontainer.container.Stock;
import com.aimercet.advcontainer.container.handler.Checker;
import com.aimercet.advcontainer.item.ItemType;

public class StockEquip extends Stock
{
    private ContainerEquip containerEquip;

    public StockEquip(IContainer container)
    {
        super(container);
        containerEquip = (ContainerEquip) container;
    }

    public ContainerEquip getContainerEquip() {return containerEquip;}
}
