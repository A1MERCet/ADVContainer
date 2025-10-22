package com.aimercet.advcontainer.container.source;

import com.aimercet.advcontainer.container.slotitem.ISlotItem;

public class SourceSlotItem implements ISource
{
    public final ISlotItem item;
    public SourceSlotItem(ISlotItem item)
    {
        this.item = item;
    }

    @Override public String getSourceID()   {return item ==null?"none": item.getSlotItemID();}
    @Override public String getSourceLang() {return item==null?"none":item.getSlotItemLang();}
}
