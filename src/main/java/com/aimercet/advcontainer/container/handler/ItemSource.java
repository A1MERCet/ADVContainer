package com.aimercet.advcontainer.container.handler;

import com.aimercet.advcontainer.container.ISlot;
import com.aimercet.advcontainer.container.IStock;
import com.aimercet.advcontainer.container.slotitem.ISlotItem;
import com.aimercet.advcontainer.util.Coord;

public class ItemSource
{
    public final IStock stock;
    public final Coord coord;

    public ItemSource(IStock stock, Coord coord)
    {
        this.stock = stock;
        this.coord = coord;
    }

    public boolean isRotate()
    {
        ISlot slot = get();
        if(slot!=null) return slot.isRotate();
        return false;
    }
    public ISlot get()
    {
        if(stock==null)return null;
        return stock.get(coord);
    }
    public ISlotItem getItem()
    {
        ISlot slot = get();
        return slot==null?null:slot.getItem();
    }
    public boolean is(ISlot slot)
    {
        if(slot==null)return false;
        return coord.equals(slot.getCoord()) && stock.equals(slot.getStock());
    }

    @Override
    public String toString() {
        return "ItemSource[" +
                stock.getContainer().getUUID()+"-"+
                +stock.getContainer().getStockList().indexOf(stock)+
                ", " + coord + "]";
    }
}
