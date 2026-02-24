package com.aimercet.advcontainer.container.backpack.equipment;

import com.aimercet.advcontainer.container.IContainer;
import com.aimercet.advcontainer.container.IStock;
import com.aimercet.advcontainer.container.Slot;
import com.aimercet.advcontainer.util.Coord;

import java.util.List;

public class SlotEquip extends Slot
{
    public StockEquip stockEquip;
    public IContainer containerEquip;

    public SlotEquip(IStock stock, Coord coord) {
        super(stock, coord);
        stockEquip = (StockEquip) stock;
        containerEquip = stockEquip.getContainer();
        getGUIStyle().setStyle("equip");
    }

    public StockEquip getStockEquip() {return stockEquip;}
    public IContainer getContainerEquip() {return containerEquip;}
    public List<EquipType> getEquipTypes() {return stockEquip==null?null:stockEquip.getEquipTypes();}
}
