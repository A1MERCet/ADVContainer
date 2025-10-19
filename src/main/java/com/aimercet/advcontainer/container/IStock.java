package com.aimercet.advcontainer.container;

import com.aimercet.advcontainer.container.handler.ItemSource;
import com.aimercet.advcontainer.util.Coord;
import com.aimercet.advcontainer.util.SizeInt;

import java.util.ArrayList;
import java.util.List;

public interface IStock
{
    IContainer getContainer();
    ISlot[][] getSlots();
    void setSlots(ISlot[][] ary);

    default ISlot get(Coord coord) {return get(coord.x, coord.y);}
    default ISlot get(int x,int y)
    {
        SizeInt size = getSize();
        if(x >= size.width || y >= size.height)return null;
        return getSlots()[x][y];
    }

    default IStock setSize(SizeInt size) {
        setSlots(new ISlot[size.width][size.height]);

        for (int x = 0; x < size.width; x++)
            for (int y = 0; y < size.height; y++) {
                getSlots()[x][y]= getContainer().createSlot(this,new Coord(x,y));
            }

        return this;
    }

    default int getEmpty()
    {
        int v = 0;
        for (int x = 0; x < getSlots().length; x++) {
            for (int y = 0; y < getSlots()[x].length; y++) {
                ISlot slot = get(x,y);
                if(slot!=null && !slot.isOccupied())v++;
            }
        }
        return v;
    }

    default boolean isFull()
    {
        for (int x = 0; x < getSlots().length; x++) {
            for (int y = 0; y < getSlots()[x].length; y++) {
                ISlot slot = get(x,y);
                if(slot!=null && !slot.isOccupied())return false;
            }
        }
        return true;
    }

    default List<ItemSource> getItems()
    {
        List<ItemSource> list = new ArrayList<ItemSource>();

        for (int x = 0; x < getSlots().length; x++)
            for (int y = 0; y < getSlots()[x].length; y++)
            {
                ISlot slot = get(x, y); if(slot==null || !slot.hasItem())continue;
                list.add(new ItemSource(this,new Coord(x,y)));
            }

        return list;
    }

    default SizeInt getSize() {return new SizeInt(getSizeX(), getSizeY());}
    default int getSizeX(){
        if(getSlots().length==0 || getSlots()[0].length==0)return 0;
        return getSlots().length;
    }
    default int getSizeY(){
        if(getSlots().length==0)return 0;else return getSlots()[0].length;
    }
}
