package com.aimercet.advcontainer.api.gui.cursor;

import com.aimercet.advcontainer.container.handler.SlotSource;
import com.aimercet.advcontainer.container.slotitem.ISlotItem;

public class CursorSourceADVC implements ICursorSource
{
    private final SlotSource slotSource;

    public CursorSourceADVC(SlotSource slotSource)
    {
        this.slotSource = slotSource;
    }

    @Override
    public ISlotItem getItem() {
        return slotSource.getItem();
    }
}
