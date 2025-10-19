package com.aimercet.advcontainer.util;

import com.aimercet.advcontainer.container.IContainer;
import com.aimercet.advcontainer.container.ISlot;
import com.aimercet.advcontainer.container.IStock;
import com.aimercet.advcontainer.container.handler.GetResult;
import com.aimercet.advcontainer.container.handler.ItemDetailed;
import com.aimercet.advcontainer.container.handler.ItemSource;
import com.aimercet.brlib.log.Logger;

import java.util.List;

public class Util
{
    public static GetResult serch(IContainer container, ItemDetailed detailed)
    {
        GetResult result = new GetResult();

        for (ItemDetailed.Entry entry : detailed.entries)
        {
            if(entry.item ==null)continue;
            long left = entry.required;
            for (IStock stock : container.getStockList())
            {
                List<ItemSource> stockItems = stock.getItems();
                for (ItemSource item : stockItems) {
                    if(item.getItem()==null)continue;
                    if(!item.getItem().getSlotItemID().equals(entry.item.getID()))continue;

                    long amount = item.getItem().getAmount();
                    if(amount>=left){
                        result.add(new GetResult.Entry(item,left,left));
                    }else {
                        left -= amount;
                        result.add(new GetResult.Entry(item,left,amount));
                    }

                }
            }
            if(left>0) result.addMissing(entry,left);
        }

        return result;
    }
    public static boolean checkNull(ItemSource source)
    {
        if(source.stock == null || source.coord==null || source.stock.getContainer() ==null)return true;
        return false;
    }

    public static boolean fillSource(IStock stock , Coord coord , SizeInt size)
    {
        if(stock==null)return false;

        for (int x = coord.x; x < coord.x+size.width; x++)
            for (int y = coord.y; y < coord.y+size.height; y++) {
                ISlot slot = stock.get(x, y);
                if(slot!=null && !slot.hasItem())
                    slot.setSourceCoord(coord);
            }
        return true;
    }

    public static boolean isOccupied(IStock stock , Coord coord , SizeInt size)
    {
        if(stock==null)return false;

        for (int x = coord.x; x < coord.x+size.width; x++)
            for (int y = coord.y; y < coord.y+size.height; y++) {
                ISlot slot = stock.get(x, y);
                if(slot.isOccupied())return true;
            }
        return false;
    }
    public static boolean clearSource(IStock stock , Coord coord , SizeInt size)
    {
        if(stock==null)return false;

        for (int x = coord.x; x < coord.x+size.width; x++)
            for (int y = coord.y; y < coord.y+size.height; y++) {
                ISlot slot = stock.get(x, y);
                if(slot!=null)slot.setSourceCoord(null);
            }
        return true;
    }
}
