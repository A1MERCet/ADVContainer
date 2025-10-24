package com.aimercet.advcontainer.container.handler;

import com.aimercet.advcontainer.bridge.brlib.HandleSourcePlayer;
import com.aimercet.advcontainer.container.ContainerManager;
import com.aimercet.advcontainer.container.IContainer;
import com.aimercet.advcontainer.container.ISlot;
import com.aimercet.advcontainer.container.IStock;
import com.aimercet.advcontainer.container.slotitem.ISlotItem;
import com.aimercet.advcontainer.container.handler.source.IHandleSource;
import com.aimercet.advcontainer.container.source.ISourceLocation;
import com.aimercet.advcontainer.util.Coord;
import com.aimercet.advcontainer.util.SizeInt;
import com.aimercet.advcontainer.util.Util;
import com.aimercet.brlib.log.Logger;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public interface IContainerHandler
{
    default boolean checkHandler(IHandleSource handler , IContainer target)
    {
        if(handler==null ||  target==null)return false;

        if(!handler.canHandle(target))return false;

        if(handler instanceof HandleSourcePlayer && target.getInventorySource() instanceof ISourceLocation)
        {
            HandleSourcePlayer handlerPlayer = (HandleSourcePlayer) handler;
            ISourceLocation sourceLocation = (ISourceLocation) target.getInventorySource();
            Location location = sourceLocation.getSourceLocation();

            Player player = handlerPlayer.playerState.getPlayer();
            if(player==null||!player.isOnline()||location==null)return false;

            return location.distance(player.getLocation()) <= ContainerManager.instance.getMaxInteractDistance();
        }

        return true;
    }

    String getHandlerID();
    default List<RemoveResult> clear(IHandleSource handleSource,IStock stock)
    {
        if(checkHandler(handleSource,stock==null?null:stock.getContainer()))return null;

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
                {
                    ISlot slot = stock.get(x, y);
                    if(slot!=null && slot.hasItem())
                        results.add(remove(handleSource,new ItemSource(stock,new Coord(x,y))));
                }
        return results;
    }

    default PlaceResult place(IHandleSource handleSource, ISlotItem item, IContainer container)
    {
        if(!checkHandler(handleSource,container))return new PlaceResult(handleSource,item,PlaceResult.Type.REFUSE,null,false);

        PlaceResult result = new PlaceResult(handleSource,item,PlaceResult.Type.NO_SPACE,null,false);
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
        if(!checkHandler(handleSource,stock==null?null:stock.getContainer()))return new PlaceResult(handleSource,item, PlaceResult.Type.REFUSE,null,false);

        PlaceResult result = new PlaceResult(handleSource,item,PlaceResult.Type.NO_SPACE,null,false);
        if(stock.getSlots()==null || stock.getSize().size()<=0) return new PlaceResult(handleSource,item, PlaceResult.Type.COORD_NULL,null,false);
        if(stock.isFull())                                      return result;

        SizeInt size = item.getSize();
        for (int y = 0; y < stock.getSizeY(); y++)
            for (int x = 0; x < stock.getSizeX(); x++)
            {
                result = place(handleSource, item, new ItemSource(stock, new Coord(x, y)),false,false);
                if(result.type==PlaceResult.Type.SUCCESS) return result;
                if(size.height!=size.width)
                {
                    result = place(handleSource, item, new ItemSource(stock, new Coord(x, y)),true,false);
                    if(result.type==PlaceResult.Type.SUCCESS) return result;
                }
            }

        return result;
    }

    /**
     * 前置条件检查
     */
    default PlaceResult.Type preCheck(IHandleSource handleSource, ISlotItem item, ItemSource coord,boolean rotate)
    {
        if(Util.checkNull(coord))                                   return PlaceResult.Type.COORD_NULL;
        if(!checkHandler(handleSource,coord.stock.getContainer()))  return PlaceResult.Type.REFUSE;


        if(coord.get().isOccupied())                                return PlaceResult.Type.NO_SPACE;

        if(item.getTypeItem()==null)                                return PlaceResult.Type.ITEM_NULL;
        if(coord.stock.getChecker()!=null) {
            Enum<?> check = coord.stock.getChecker().check(item);
            if(check != IChecker.Type.SUCCESS)                      return PlaceResult.Type.CHECK_FAIL;
        }else{
            Logger.warn("Stock["+coord.stock.getContainer().getUUID()+" - "+coord.stock.getContainer().getStockList().indexOf(coord.stock)+"]'s checker is null");
        }
        return PlaceResult.Type.SUCCESS;
    }

    /**
     * 空间检查
     */
    default PlaceResult.Type spaceCheck(IHandleSource handleSource, ISlotItem item, ItemSource coord,boolean rotate)
    {
        if(!checkHandler(handleSource,coord.stock.getContainer()))  return PlaceResult.Type.REFUSE;

        SizeInt size = getItemSize(item,coord,rotate);
        if(size==null || size.size()<=0)                            return PlaceResult.Type.SIZE_ERROR;
        if(size.size() > coord.stock.getEmpty())                    return PlaceResult.Type.FULL;

        SizeInt stockSize = coord.stock.getSize();
        if(stockSize==null || stockSize.size()<=0)                  return PlaceResult.Type.STOCK_SIZE;

        int endX = coord.coord.x + size.width,endY = coord.coord.y + size.height;
        if(endX>stockSize.width || endY> stockSize.height)          return PlaceResult.Type.BOUND;

        if(Util.isOccupied(coord.stock, coord.coord,size))          return PlaceResult.Type.NO_SPACE;

        return PlaceResult.Type.SUCCESS;
    }

    /**
     * 综合检查
     */
    default PlaceResult.Type checkPlace(IHandleSource handleSource, ISlotItem item, ItemSource coord , boolean rotate)
    {
        if(!checkHandler(handleSource,coord.stock.getContainer()))return PlaceResult.Type.REFUSE;

        PlaceResult.Type preCheck = preCheck(handleSource,item, coord,rotate);
        if(preCheck!= PlaceResult.Type.SUCCESS)                 return preCheck;

        PlaceResult.Type spaceCheck = spaceCheck(handleSource,item, coord,rotate);
        if(spaceCheck!= PlaceResult.Type.SUCCESS)               return spaceCheck;

        return PlaceResult.Type.SUCCESS;
    }

    /**
     * 获取并处理物品大小
     */
    default SizeInt getItemSize(ISlotItem item , ItemSource coord , boolean rotate)
    {
        SizeInt size = item.getSize();
        if(rotate)size = size.swap();
        return size;
    }

    default PlaceResult place(IHandleSource handleSource, ISlotItem item, ItemSource coord,boolean rotate){return place(handleSource, item, coord, rotate,false);}
    default PlaceResult place(IHandleSource handleSource, ISlotItem item, ItemSource coord,boolean rotate , boolean bypass)
    {
        if(!bypass){
            PlaceResult.Type check = checkPlace(handleSource, item, coord, rotate);
            if(check != PlaceResult.Type.SUCCESS) return new  PlaceResult(handleSource,item,check,coord,rotate);
        }

        SizeInt size =  getItemSize(item,coord,rotate);

        Util.fillSource(coord.stock,coord.coord,size);
        coord.stock.getSlots()[coord.coord.x][coord.coord.y].setItem(item);
        coord.stock.getSlots()[coord.coord.x][coord.coord.y].setRotate(rotate);

        PlaceResult result = new PlaceResult(handleSource, item, PlaceResult.Type.SUCCESS, coord,rotate);
        onPlace(result);
        return result;
    }

    default RemoveResult remove(IHandleSource handleSource, ItemSource coord)
    {
        ISlotItem item = coord.getItem();;
        if(item==null)                                              return new RemoveResult(handleSource,item, RemoveResult.Type.NO_ITEM,coord);
        if(Util.checkNull(coord))                                   return new RemoveResult(handleSource,item, RemoveResult.Type.COORD_NULL,coord);
        if(!checkHandler(handleSource,coord.stock.getContainer()))  return new RemoveResult(handleSource,item, RemoveResult.Type.REFUSE,coord);

        SizeInt stockSize = coord.stock.getSize();
        if(stockSize==null || stockSize.size()<=0)                  return new RemoveResult(handleSource,item, RemoveResult.Type.STOCK_SIZE,coord);

        SizeInt size = getItemSize(item,coord,coord.get().isRotate());
        if(size==null || size.size()<=0)                            return new RemoveResult(handleSource,item, RemoveResult.Type.SIZE_ERROR,coord);

        Util.clearSource(coord.stock,coord.coord,size);
        coord.stock.getSlots()[coord.coord.x][coord.coord.y].setItem(null);

        RemoveResult result = new RemoveResult(handleSource, item, RemoveResult.Type.SUCCESS, coord);
        onRemove(result);
        return result;
    }

    default void onPlace(PlaceResult result)
    {
        result.handleSource.addHistory(result);
        result.coord.stock.getContainer().addHistory(result);
        result.coord.stock.getContainer().onPlace(result);
    }
    default void onRemove(RemoveResult result)
    {
        result.handleSource.addHistory(result);
        result.coord.stock.getContainer().addHistory(result);
        result.coord.stock.getContainer().onRemove(result);
    }

}
