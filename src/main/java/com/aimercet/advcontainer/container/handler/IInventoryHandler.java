package com.aimercet.advcontainer.container.handler;

import com.aimercet.advcontainer.container.IContainer;
import com.aimercet.advcontainer.container.ISlot;
import com.aimercet.advcontainer.container.IStock;
import com.aimercet.advcontainer.container.slotitem.ISlotItem;
import com.aimercet.advcontainer.container.handler.source.IHandleSource;
import com.aimercet.advcontainer.util.Coord;
import com.aimercet.advcontainer.util.SizeInt;
import com.aimercet.advcontainer.util.Util;
import com.aimercet.brlib.log.Logger;

import java.util.ArrayList;
import java.util.List;

public interface IInventoryHandler
{

    default List<RemoveResult> clear(IHandleSource handleSource,IStock stock)
    {
        List<RemoveResult> results = new ArrayList<RemoveResult>();
        for (int y = 0; y < stock.getSizeY(); y++)
            for (int x = 0; x < stock.getSizeX(); x++)
                results.add(remove(handleSource,new ItemSource(stock,new Coord(x,y))));
        return results;
    }

    default List<RemoveResult> clear(IHandleSource handleSource,IContainer container)
    {
        List<RemoveResult> results = new ArrayList<RemoveResult>();
        for (IStock stock : container.getStockList())
            for (int y = 0; y < stock.getSizeY(); y++)
                for (int x = 0; x < stock.getSizeX(); x++)
                    results.add(remove(handleSource,new ItemSource(stock,new Coord(x,y))));
        return results;
    }

    default PlaceResult place(IHandleSource handleSource, ISlotItem item, IContainer container)
    {
        PlaceResult result = new PlaceResult(handleSource,item,PlaceResult.Type.NO_SPACE,null);
        if(container.isFull()) return result;

        for (IStock stock : container.getStockList())
        {
            result = place(handleSource, item, stock);
            if(result.type == PlaceResult.Type.SUCCESS) return result;
        }

        return result;
    }
    default PlaceResult place(IHandleSource handleSource, ISlotItem item, IStock stock)
    {
        PlaceResult result = new PlaceResult(handleSource,item,PlaceResult.Type.NO_SPACE,null);
        if(stock.getSlots()==null || stock.getSize().size()<=0) return new PlaceResult(handleSource,item, PlaceResult.Type.COORD_NULL,null);
        if(stock.isFull())                                      return result;

        SizeInt size = item.getSize();
        for (int y = 0; y < stock.getSizeY(); y++)
            for (int x = 0; x < stock.getSizeX(); x++)
            {
                result = place(handleSource, item, new ItemSource(stock, new Coord(x, y)),false);
                if(result.type==PlaceResult.Type.SUCCESS) return result;
                if(size.height!=size.width)
                {
                    result = place(handleSource, item, new ItemSource(stock, new Coord(x, y)),true);
                    if(result.type==PlaceResult.Type.SUCCESS) return result;
                }
            }

        return result;
    }

    default PlaceResult place(IHandleSource handleSource, ISlotItem item, ItemSource coord,boolean rotate)
    {
        if(Util.checkNull(coord))                               return new PlaceResult(handleSource,item, PlaceResult.Type.COORD_NULL,coord);

        if(coord.get().isOccupied())                            return new PlaceResult(handleSource,item, PlaceResult.Type.NO_SPACE,coord);

        SizeInt size = item.getSize();
        if(size==null || size.size()<=0)                        return new PlaceResult(handleSource,item, PlaceResult.Type.ITEM_SIZE,coord);
        if(size.size() > coord.stock.getEmpty())                return new PlaceResult(handleSource,item, PlaceResult.Type.FULL,coord);
        if(rotate)size = size.swap();

        SizeInt stockSize = coord.stock.getSize();
        if(stockSize==null || stockSize.size()<=0)              return new PlaceResult(handleSource,item, PlaceResult.Type.STOCK_SIZE,coord);

        int startX = coord.coord.x,startY = coord.coord.y;
        int endX = coord.coord.x + size.width,endY = coord.coord.y + size.height;
        if(endX>stockSize.width || endY> stockSize.height)      return new PlaceResult(handleSource,item, PlaceResult.Type.BOUND,coord);

        if(Util.isOccupied(coord.stock, coord.coord,size))      return new PlaceResult(handleSource,item, PlaceResult.Type.NO_SPACE,coord);

        Util.fillSource(coord.stock,coord.coord,size);
        coord.stock.getSlots()[startX][startY].setItem(item);
        coord.stock.getSlots()[startX][startY].setRotate(rotate);

        PlaceResult result = new PlaceResult(handleSource, item, PlaceResult.Type.SUCCESS, coord);
        onPlace(result);
        return result;
    }

    default RemoveResult remove(IHandleSource handlerSource, ItemSource coord)
    {
        ISlotItem item = coord.stock.get(coord.coord).getItem();
        if(item==null)                                          return new RemoveResult(handlerSource,item, RemoveResult.Type.NO_ITEM,coord);
        if(Util.checkNull(coord))                               return new RemoveResult(handlerSource,item, RemoveResult.Type.COORD_NULL,coord);

        SizeInt size = item.getSize();
        if(size==null || size.size()<=0)                        return new RemoveResult(handlerSource,item, RemoveResult.Type.ITEM_SIZE,coord);
        if(coord.get().isRotate()) size = size.swap();

        SizeInt stockSize = coord.stock.getSize();
        if(stockSize==null || stockSize.size()<=0)              return new RemoveResult(handlerSource,item, RemoveResult.Type.STOCK_SIZE,coord);

        Util.clearSource(coord.stock,coord.coord,size);
        coord.stock.getSlots()[coord.coord.x][coord.coord.y].setItem(null);

        RemoveResult result = new RemoveResult(handlerSource, item, RemoveResult.Type.SUCCESS, coord);
        onRemove(result);
        return result;
    }

    default void onPlace(PlaceResult result)
    {
        result.handleSource.getInventoryHandleHistory().add(result);
        result.coord.stock.getContainer().getInventoryHandleHistory().add(result);
    }
    default void onRemove(RemoveResult result)
    {
        result.handleSource.getInventoryHandleHistory().add(result);
        result.coord.stock.getContainer().getInventoryHandleHistory().add(result);
    }
}
