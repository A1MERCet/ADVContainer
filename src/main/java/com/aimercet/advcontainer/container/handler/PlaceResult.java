package com.aimercet.advcontainer.container.handler;

import com.aimercet.advcontainer.container.ContainerManager;
import com.aimercet.advcontainer.container.slotitem.ISlotItem;
import com.aimercet.advcontainer.container.handler.source.IHandleSource;

public class PlaceResult extends HandleResult
{
    public enum Type
    {
        SUCCESS,
        FULL,
        BOUND,
        COORD_NULL,
        SIZE_ERROR,
        STOCK_SIZE,
        NO_SPACE,
        TYPE,
        REFUSE,
        ITEM_NULL,
        CHECK_FAIL
        ;

    }

    public final ItemSource source;

    public PlaceResult(IHandleSource handleSource, ISlotItem item, Type type, ItemSource source, ItemSource target)
    {
        super(handleSource,type,target,item);
        this.source = source;
    }

    public PlaceResult(IHandleSource handleSource, ISlotItem item, Type type, ItemSource target)
    {
        super(handleSource,type,target,item);
        this.source = null;
    }

    public PlaceResult(ISlotItem item, Type type, ItemSource target)
    {
        super(ContainerManager.instance.handleSourceSystem, type,target,item);
        this.source = null;
    }

    @Override
    public String toString() {
        String s = super.toString().replace("HandleResult","PlaceResult").replace("]","");
        return s+", Source: "+source+"]";
    }
}
