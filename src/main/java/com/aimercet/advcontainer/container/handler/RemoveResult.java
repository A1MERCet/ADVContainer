package com.aimercet.advcontainer.container.handler;

import com.aimercet.advcontainer.container.slotitem.ISlotItem;
import com.aimercet.advcontainer.container.handler.source.IHandleSource;

public class RemoveResult extends HandleResult
{
    public enum Type
    {
        SUCCESS,REFUSE,COORD_NULL,ITEM_NULL, SIZE_ERROR,STOCK_SIZE,NO_ITEM
    }


    public RemoveResult(IHandleSource handleSource, ISlotItem item,Type type, ItemSource coord)
    {
        super(handleSource,type,coord,item);
    }

    @Override
    public String toString() {
        return super.toString().replace("HandleResult","RemoveResult");
    }
}
