package com.aimercet.advcontainer.api.gui.container;

import com.aimercet.advcontainer.container.IContainer;
import com.aimercet.advcontainer.container.IStock;

public interface IPartStock
{
    IStock getStock();
    default IContainer getContainer(){return getStock()==null?null:getStock().getContainer();}
    IPartSlot[][] getSlotParts();

    IPartContainer getContainerPart();
}
