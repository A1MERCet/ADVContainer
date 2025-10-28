package com.aimercet.advcontainer.container.backpack.equipment;

import com.aimercet.advcontainer.container.IContainer;
import com.aimercet.advcontainer.container.IStock;
import com.aimercet.advcontainer.container.Slot;
import com.aimercet.advcontainer.util.Coord;

public class SlotEquip extends Slot
{
    public StockEquip stockEquip;
    public IContainer containerEquip;

    public SlotEquip(IStock stock, Coord coord) {
        super(stock, coord);
        stockEquip = (StockEquip) stock;
        getGUIStyle().setStyle("equip");
    }

    public StockEquip getStockEquip() {return stockEquip;}
    public IContainer getContainerEquip() {return containerEquip;}
}
