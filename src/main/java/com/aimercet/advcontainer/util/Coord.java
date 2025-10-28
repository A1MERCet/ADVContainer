package com.aimercet.advcontainer.util;

import com.aimercet.advcontainer.container.IStock;
import com.aimercet.advcontainer.container.handler.SlotSource;

public class Coord implements Cloneable
{
    public final int x;
    public final int y;

    public Coord(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public SlotSource toSource(IStock stock)
    {
        return new SlotSource(stock.getContainer(),stock.getIndex(),this);
    }

    @Override
    public Coord clone() {
        return new Coord(x,y);
    }

    @Override public String toString() {return "["+x+","+y+"]";}

    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof Coord){
            Coord o = (Coord) obj;
            return o.x==x && o.y==y;
        }else {
            return super.equals(obj);
        }
    }
}
