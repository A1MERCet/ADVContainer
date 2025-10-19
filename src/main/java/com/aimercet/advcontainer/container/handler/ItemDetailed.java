package com.aimercet.advcontainer.container.handler;

import com.aimercet.advcontainer.container.slotitem.ISlotItem;
import com.aimercet.advcontainer.item.item.TypeItem;

import java.util.ArrayList;
import java.util.List;

public class ItemDetailed
{
    public static class Entry
    {
        public final TypeItem item;
        public final long required;

        public Entry(TypeItem item, long required)
        {
            this.item = item;
            this.required = required;
        }

        @Override public String toString() {return "[" + item.getID() + "*" + required + "]";}
    }

    public final List<Entry> entries = new ArrayList<Entry>();

    public ItemDetailed()
    {

    }

    public ItemDetailed add(Entry e){this.entries.add(e);return this;}

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder().append(getClass().getSimpleName());
        entries.forEach(b::append);
        return b.toString();
    }
}
