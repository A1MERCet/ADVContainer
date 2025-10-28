package com.aimercet.advcontainer.container.handler;

import com.aimercet.advcontainer.container.slotitem.ISlotItem;

public interface IChecker
{
    public enum Type
    {
        SUCCESS,TYPE,BLACKLIST_TYPE,BLACKLIST_ID,ITEM_NULL
    }
    Type check(ISlotItem item);
}
