package com.aimercet.advcontainer.container.handler;

import com.aimercet.advcontainer.container.ContainerManager;
import com.aimercet.advcontainer.container.slotitem.ISlotItem;
import com.aimercet.advcontainer.container.handler.source.IHandleSource;

public class PlaceResult extends HandleResult
{
    public enum Type
    {
        REFUSE,
        SUCCESS,
        FULL,
        BOUND,
        COORD_NULL,
        SIZE_ERROR,
        STOCK_SIZE,
        NO_SPACE,
        TYPE,
        ITEM_NULL,
        CHECK_FAIL
        ;

    }

    public final SlotSource source;
    public final boolean rotate;

    public PlaceResult(IHandleSource handleSource, ISlotItem item, Type type)
    {
        super(handleSource,type,null,item);
        this.source = null;
        this.rotate = false;
    }

    public PlaceResult(IHandleSource handleSource, ISlotItem item, Type type, SlotSource source, SlotSource target, boolean rotate)
    {
        super(handleSource,type,target,item);
        this.source = source;
        this.rotate = rotate;
    }

    public PlaceResult(IHandleSource handleSource, ISlotItem item, Type type, SlotSource target, boolean rotate)
    {
        super(handleSource,type,target,item);
        this.source = null;
        this.rotate = rotate;
    }

    public PlaceResult(ISlotItem item, Type type, SlotSource target, boolean rotate)
    {
        super(ContainerManager.instance.handleSourceSystem, type,target,item);
        this.source = null;
        this.rotate = rotate;
    }

    @Override
    public String toString() {
        String s = super.toString().replace("HandleResult","PlaceResult ").replace("]","");
        return s+", Source: "+source+", Rotate: "+rotate+"]";
    }
}
