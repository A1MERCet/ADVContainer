package com.aimercet.advcontainer.api.gui.container;

import com.aimercet.advcontainer.container.IContainer;
import com.aimercet.advcontainer.container.ISlot;

public interface IPartSlot
{
    ISlot getSlot();
    IPartStock getStockPart();

    default IContainer getContainer(){return getSlot()==null?null:getSlot().getStock()==null?null:getSlot().getStock().getContainer();}
    default IPartContainer getContainerPart(){return getStockPart()==null?null:getStockPart().getContainerPart();}
}
