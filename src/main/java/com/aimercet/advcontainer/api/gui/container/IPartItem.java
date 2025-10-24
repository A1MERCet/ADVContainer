package com.aimercet.advcontainer.api.gui.container;

import com.aimercet.advcontainer.container.IContainer;
import com.aimercet.advcontainer.container.ISlot;

public interface IPartItem
{
    IPartSlot getSlotPart();
    default ISlot getSlot(){return getSlotPart()==null?null:getSlotPart().getSlot();}

    default IPartStock getStockPart(){return getSlotPart()==null?null:getSlotPart().getStockPart();}
    default IContainer getContainer(){return getSlot()==null?null:getSlot().getStock()==null?null:getSlot().getStock().getContainer();}
    default IPartContainer getContainerPart(){return getStockPart()==null?null:getStockPart().getContainerPart();}
}
