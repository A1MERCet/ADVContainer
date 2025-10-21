package com.aimercet.advcontainer.item;

import com.aimercet.brlib.localization.Localization;
import com.aimercet.brlib.log.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemType
{
    public static List<ItemType> values = new ArrayList<ItemType>();
    private static HashMap<String,ItemType> map = new HashMap<String,ItemType>();

    public static ItemType valueOf(String id){return map.get(id);}
    public static boolean contains(String id){return map.containsKey(id);}

    public static ItemType MISC = new ItemType("MISC",null,new ArrayList<>());
    public static ItemType TOOL = new ItemType("TOOL",null,new ArrayList<>());

    public static ItemType WEAPON = new ItemType("WEAPON",null,new ArrayList<>());
    public static ItemType BULLET = new ItemType("BULLET",null,new ArrayList<>());
    public static ItemType MAGAZINE = new ItemType("MAGAZINE",null,new ArrayList<>());

    public static ItemType ARMOR = new ItemType("ARMOR",null,new ArrayList<>());

    public static ItemType MEDICAL = new ItemType("MEDICAL",null,new ArrayList<>());

    public static ItemType EDIBLE = new ItemType("EDIBLE",null,new ArrayList<>());
    ;
    public final String id;
    public final String lang;

    public final ItemType parent;
    public final List<ItemType> childs = new ArrayList<ItemType>();

    ItemType(String id, ItemType parent, List<ItemType> childs)
    {
        this.id = id;
        this.lang = "item_type_"+id.toLowerCase();
        Localization.register(this.lang,id);
        this.parent = parent;
        this.childs.addAll(childs);
        if(this.parent!=null) this.parent.childs.add(this);

        register(this);
    }

    private ItemType register(ItemType type)
    {
        String id = type.id;
        if(map.containsKey(id)) Logger.error("Duplicate item type: "+id);
        values.add(this);
        map.put(this.id, this);
        return type;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        ItemType other = (ItemType) obj;
        return this.id.equals(other.id);
    }
    @Override public int hashCode() {return id.hashCode();}
    @Override public String toString() {return id;}
}
