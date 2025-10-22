package com.aimercet.advcontainer.container.backpack.equipment;

import com.aimercet.advcontainer.container.*;
import com.aimercet.advcontainer.container.backpack.Backpack;
import com.aimercet.advcontainer.container.handler.PlaceResult;
import com.aimercet.advcontainer.container.handler.RemoveResult;
import com.aimercet.advcontainer.util.Coord;
import com.aimercet.advcontainer.util.SizeInt;

public class ContainerEquip extends Container
{
    public static final String CLASS_NAME = "EQUIP";
    private Backpack backpack;

    public ContainerEquip(String uuid)
    {
        super(uuid);
        this.handler = ContainerManager.instance.handlerEquipment;
        this.defaultHandler = handler.getHandlerID();
    }

    @Override
    public void onPlace(PlaceResult result)
    {
        super.onPlace(result);
    }

    @Override
    public void onRemove(RemoveResult result)
    {
        super.onRemove(result);
    }

    @Override public IStock createStock(SizeInt size) {return new StockEquip(this).setSize(size);}
    @Override public IStock addStock(SizeInt size) {
        return super.addStock(size);
    }
    @Override public ISlot createSlot(IStock stock, Coord coord) {
        return super.createSlot(stock, coord);
    }

    public Backpack getBackpack()                           {return backpack;}
    public ContainerEquip setBackpack(Backpack backpack)    {this.backpack = backpack;return this;}
}
