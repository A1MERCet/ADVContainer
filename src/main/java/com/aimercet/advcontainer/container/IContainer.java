package com.aimercet.advcontainer.container;

import com.aimercet.advcontainer.container.handler.IInventoryHandler;
import com.aimercet.advcontainer.container.handler.source.InventoryHandleHistory;
import com.aimercet.advcontainer.container.source.IInventorySource;
import com.aimercet.advcontainer.util.Coord;
import com.aimercet.advcontainer.util.SizeInt;

import java.util.List;

public interface IContainer
{
    String getUUID();
    IInventoryHandler getHandler();
    void setHandler(IInventoryHandler handler);
    List<IStock> getStockList();
    IInventorySource getInventorySource();
    InventoryHandleHistory getInventoryHandleHistory();

    default IStock addStock(SizeInt size){getStockList().add(new Stock(this).setSize(size));return getStockList().get(getStockList().size()-1);}
    default ISlot createSlot(IStock stock, Coord coord){return new Slot(stock, coord);}


    default boolean isFull()
    {
        for (IStock stock : getStockList())
            if(!stock.isFull()) return false;
        return true;
    }
}
