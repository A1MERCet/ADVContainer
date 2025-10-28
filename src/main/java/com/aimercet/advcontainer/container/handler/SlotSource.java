package com.aimercet.advcontainer.container.handler;

import com.aimercet.advcontainer.container.ContainerManager;
import com.aimercet.advcontainer.container.IContainer;
import com.aimercet.advcontainer.container.ISlot;
import com.aimercet.advcontainer.container.IStock;
import com.aimercet.advcontainer.container.slotitem.ISlotItem;
import com.aimercet.advcontainer.util.Coord;

import java.util.Objects;

public class SlotSource
{
    public final IContainer container;
    public final String uuid;
    public final int stock;
    public final Coord coord;

    public SlotSource(ISlot slot)
    {
        this.container = slot.getContainer();
        this.uuid  = container==null?null:container.getUUID();
        this.stock = slot.getStock().getIndex();
        this.coord = slot.getCoord();
    }

    public SlotSource(IContainer container,int stock, Coord coord)
    {
        this.container = container;
        this.uuid  = container==null?null:container.getUUID();
        this.stock = stock;
        this.coord = coord;
    }

    public SlotSource(IStock stock, Coord coord)
    {
        this.container = stock.getContainer();
        this.uuid  = container==null?null:container.getUUID();
        this.stock = stock.getIndex();
        this.coord = coord;
    }

    public SlotSource(String uuid,int stock, Coord coord)
    {
        this.container = null;
        this.uuid  = uuid;
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
        IContainer con = getContainer();
        if(con==null)return null;
        IStock stock = con.getStock(this.stock);
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
        return coord.equals(slot.getCoord()) && stock == slot.getStock().getIndex() && Objects.equals(uuid, slot.getContainer().getUUID());
    }
    public IContainer getContainer() {return container==null? ContainerManager.instance.get(uuid):container;}
    public IStock getStock() {IContainer con = getContainer();return con==null?null:con.getStock(this.stock);}

    @Override
    public String toString() {
        return "ItemSource[" +
                uuid+"-"+
                +stock+
                ", " + coord + "]";
    }
}
