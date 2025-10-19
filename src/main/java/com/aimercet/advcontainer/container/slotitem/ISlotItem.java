package com.aimercet.advcontainer.container.slotitem;

import com.aimercet.advcontainer.item.item.TypeItem;
import com.aimercet.advcontainer.util.SizeInt;

public interface ISlotItem
{
    long getAmount();
    TypeItem getTypeItem();
    String getSlotItemID();
    String getSlotItemLang();
    SizeInt getSize();
}
