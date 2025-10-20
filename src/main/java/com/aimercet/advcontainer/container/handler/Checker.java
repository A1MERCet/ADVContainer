package com.aimercet.advcontainer.container.handler;

import com.aimercet.advcontainer.container.slotitem.ISlotItem;
import com.aimercet.advcontainer.item.ItemType;

import java.util.ArrayList;
import java.util.List;

public class Checker implements IChecker , Cloneable
{

    public final List<ItemType> allowTypes = new ArrayList<ItemType>();
    public final List<ItemType> blacklistTypes = new ArrayList<ItemType>();

    public final List<String> allowID = new ArrayList<>();
    public final List<String> blacklistID = new ArrayList<>();

    public Checker()
    {

    }

    public Checker allow(ItemType type) {allowTypes.add(type);return this;}
    public Checker deny(ItemType type)  {blacklistTypes.add(type);return this;}

    public Checker allow(String id)     {allowID.add(id);return this;}
    public Checker deny(String id)      {blacklistID.add(id);return this;}

    public Checker clear()              {allowTypes.clear();blacklistTypes.clear();allowID.clear();blacklistID.clear();return this;}

    @Override
    protected Object clone() throws CloneNotSupportedException {
        try {
            Checker clone = (Checker) super.clone();
            clone.allowTypes.addAll(allowTypes);
            clone.blacklistTypes.addAll(blacklistTypes);
            clone.allowID.addAll(allowID);
            clone.blacklistID.addAll(blacklistID);
            return clone;
        }catch (CloneNotSupportedException e){throw new AssertionError();}
    }

    @Override
    public IChecker.Type check(ISlotItem item)
    {
        String id = item.getSlotItemID();
        ItemType type = item.getTypeItem().type;

        if(allowID.contains(id))            return IChecker.Type.SUCCESS;

        if(blacklistID.contains(id))        return IChecker.Type.BLACKLIST_ID;
        if(blacklistTypes.contains(type))   return IChecker.Type.BLACKLIST_TYPE;

        if(allowTypes.contains(type))       return IChecker.Type.SUCCESS;

        return IChecker.Type.TYPE;
    }
}
