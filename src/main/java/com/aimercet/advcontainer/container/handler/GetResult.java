package com.aimercet.advcontainer.container.handler;

import com.aimercet.advcontainer.container.IContainer;
import com.aimercet.advcontainer.container.slotitem.ISlotItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GetResult
{
    public static class Entry
    {
        public final SlotSource source;
        public final long required;
        public final long amount;

        public Entry(SlotSource source, long required, long amount)
        {
            this.source = source;
            this.required = required;
            this.amount = amount;
        }

        public ISlotItem getItem(){return source.getItem();}

        @Override
        public String toString() {
            return "[" +
                     source +
                    ", required=" + required +
                    ", amount=" + amount +
                    ']';
        }
    }

    public final List<Entry> entries = new ArrayList<Entry>();
    public HashMap<ItemDetailed.Entry,Long> missing = new HashMap<>();

    public GetResult(List<Entry> entries)
    {
        this.entries.addAll(entries);
    }
    public GetResult()
    {

    }

    public GetResult add(Entry e){entries.add(e);return this;}
    public GetResult addMissing(ItemDetailed.Entry i,long v){missing.put(i,missing.getOrDefault(i,0L)+v);return this;}

    public IContainer getContainer()
    {
        if(entries.isEmpty())return null;
        Entry head = entries.get(0);
        if(head == null || head.source==null)return null;
        return head.source.getContainer();
    }

    public boolean isMissing(){return !missing.isEmpty();}

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder().append(getClass().getSimpleName()).append("\nEntry: \n");
        entries.forEach(entry -> b.append(entry).append("\n"));
        b.append("\nMissing: \n");
        missing.forEach((k,v)->b.append(v).append("\n"));
        return b.toString();
    }
}
