package com.aimercet.advcontainer.util;

import com.aimercet.advcontainer.container.IStock;
import com.aimercet.advcontainer.container.handler.ItemSource;

public class Coord
{
    public final int x;
    public final int y;

    public Coord(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public ItemSource toSrouce(IStock stock)
    {
        return new ItemSource(stock,this);
    }

    @Override
    public String toString() {
        return "["+x+","+y+"]";
    }

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
