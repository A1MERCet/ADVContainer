package com.aimercet.advcontainer.container.backpack.equipment;

import com.aimercet.advcontainer.container.*;
import com.aimercet.advcontainer.container.backpack.Backpack;
import com.aimercet.advcontainer.container.backpack.equipment.callback.EquipCallback;
import com.aimercet.advcontainer.container.backpack.equipment.callback.EquipCallbackList;
import com.aimercet.advcontainer.container.handler.PlaceResult;
import com.aimercet.advcontainer.container.handler.RemoveResult;
import com.aimercet.advcontainer.container.source.ISource;
import com.aimercet.advcontainer.util.Coord;
import com.aimercet.advcontainer.util.SizeInt;

import java.util.ArrayList;
import java.util.List;

public class ContainerEquip extends Container
{
    public static final String CLASS_NAME = "equip";

    public List<EquipType> allowedEquipSlots = new ArrayList<>();
    private final EquipCallbackList callbackEquip = new EquipCallbackList();
    private Backpack backpack;

    protected ContainerEquip(String uuid)
    {
        super(uuid);
        this.handler = ContainerManager.instance.handlerEquipment;
        this.defaultHandler = handler.getHandlerID();
    }

    @Override
    public void initContainer()
    {
        super.initContainer();
    }

    @Override
    public void onPlace(PlaceResult result)
    {
        super.onPlace(result);
    }

    @Override public void onRemove(RemoveResult result)
    {
        super.onRemove(result);
    }

    @Override public StockEquip createStock() {return new StockEquip(this);}
    @Override public ISlot createSlot(IStock stock, Coord coord) {return super.createSlot(stock, coord);}

    public Backpack getBackpack()                           {return backpack;}
    public ContainerEquip setBackpack(Backpack backpack)    {this.backpack = backpack;return this;}

    public EquipCallbackList getCallbackEquip() {return callbackEquip;}
    public ContainerEquip registerCallbackEquip(EquipCallback callback) {callbackEquip.add(callback);return this;}
    public ContainerEquip unCallbackEquip(EquipCallback callback)       {callbackEquip.remove(callback);return this;}
}
