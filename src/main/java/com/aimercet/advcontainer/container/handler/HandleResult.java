package com.aimercet.advcontainer.container.handler;

import com.aimercet.advcontainer.container.slotitem.ISlotItem;
import com.aimercet.advcontainer.container.handler.source.IHandleSource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class HandleResult
{
    public String uuid;
    public final IHandleSource handleSource;
    public final Enum<?> type;
    public final SlotSource coord;
    public final ISlotItem item;
    public final Date date;

    public HandleResult(IHandleSource handleSource , Enum<?> type, SlotSource coord, ISlotItem item)
    {
        this.uuid = UUID.randomUUID().toString();
        this.handleSource = handleSource;
        this.type = type;
        this.coord = coord;
        this.item = item;
        this.date = new Date();
    }

    @Override
    public String toString() {
        return "HandleResult[Date: "+new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date) +", "+(handleSource==null?"null":handleSource.getHandlerName())+", T="+type.toString()+", C="+coord+", I="+(item==null?"null":item.getSlotItemID())+", UUID="+uuid+"]";
    }
}
