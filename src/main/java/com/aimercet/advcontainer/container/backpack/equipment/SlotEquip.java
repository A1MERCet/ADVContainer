package com.aimercet.advcontainer.container.backpack.equipment;

import com.aimercet.advcontainer.container.IContainer;
import com.aimercet.advcontainer.container.IStock;
import com.aimercet.advcontainer.container.Slot;
import com.aimercet.advcontainer.container.slotitem.ISlotItem;
import com.aimercet.advcontainer.util.Coord;
import com.aimercet.brlib.log.Logger;

public class SlotEquip extends Slot
{
    public StockEquip stockEquip;
    public IContainer containerEquip;

    public EquipType equipType;

    public SlotEquip(IStock stock, Coord coord) {
        super(stock, coord);
        stockEquip = (StockEquip) stock;
    }

    @Override
    public void setItem(ISlotItem item)
    {
        if(equipType == null) Logger.warn("EquipType is null["+toString()+"]");
        super.setItem(item);
    }

    public StockEquip getStockEquip() {return stockEquip;}
    public IContainer getContainerEquip() {return containerEquip;}

    public void setEquipType(EquipType equipType) {this.equipType = equipType;}
    public EquipType getEquipType() {return equipType;}

    @Override public String toString() {return super.toString().replace("]","")+", equipType="+equipType.toString()+"]";}
}
