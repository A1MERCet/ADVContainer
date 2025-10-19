package com.aimercet.advcontainer.container;

import com.aimercet.advcontainer.container.slotitem.ISlotItem;
import com.aimercet.advcontainer.util.Coord;

public class Slot implements ISlot
{
    public IStock stock;
    public Coord sorceCoord;
    public final Coord coord;
    public ISlotItem item;
    private boolean rotate = false;

    public Slot(IStock stock,Coord coord)
    {
        this.stock = stock;
        this.coord = coord;
    }

    @Override public IStock getStock() {return stock;}
    @Override public Coord getCoord() {return coord;}
    @Override public Coord getSourceCoord() {return sorceCoord;}
    @Override public void setSourceCoord(Coord source) {this.sorceCoord = source;}

    @Override public ISlotItem getItem() {return item;}
    @Override public void setItem(ISlotItem item) {this.item = item;}
    @Override public boolean isRotate() {return rotate;}
    @Override public void setRotate(boolean rotate) {this.rotate=rotate;}

    @Override
    public String toString() {
        return getClass().getSimpleName()+"["+
                (getSourceCoord()==null?"":("Source="+getSourceCoord())+", ")+
                "Stock="+(stock==null?"null":stock.getClass().getSimpleName())+
                ", Coord="+coord+
                ", Item="+item
                +"]";
    }
}
