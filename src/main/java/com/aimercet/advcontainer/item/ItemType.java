package com.aimercet.advcontainer.item;

import com.aimercet.brlib.localization.Localization;
import com.aimercet.brlib.log.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemType
{
    public static List<ItemType> values = new ArrayList<ItemType>();
    public static HashMap<String,ItemType> map = new HashMap<String,ItemType>();

    public static ItemType valueOf(String id){return map.get(id);}
    public static boolean contains(String id){return map.containsKey(id);}

    public static ItemType MISC = new ItemType(Localization.register("MISC","Misc"),null,new ArrayList<>());
    public static ItemType TOOL = new ItemType(Localization.register("TOOL","Tool"),null,new ArrayList<>());

    public static ItemType WEAPON = new ItemType(Localization.register("WEAPON","Weapon"),null,new ArrayList<>());
    public static ItemType BULLET = new ItemType(Localization.register("BULLET","Bullet"),null,new ArrayList<>());
    public static ItemType MAGAZINE = new ItemType(Localization.register("MAGAZINE","Magazine"),null,new ArrayList<>());

    public static ItemType ARMOR = new ItemType(Localization.register("ARMOR","Armor"),null,new ArrayList<>());

    public static ItemType MEDICAL = new ItemType(Localization.register("MEDICAL","Medical"),null,new ArrayList<>());

    public static ItemType EDIBLE = new ItemType(Localization.register("EDIBLE","Edible"),null,new ArrayList<>());
    ;
    public final String id;
    public final String lang;

    public final ItemType parent;
    public final List<ItemType> childs = new ArrayList<ItemType>();

    ItemType(String id, ItemType parent, List<ItemType> childs)
    {
        this.id = id;
        this.lang = "item_type_"+id;
        Localization.register(this.lang,id);
        this.parent = parent;
        this.childs.addAll(childs);
        if(this.parent!=null) this.parent.childs.add(this);

        if(map.containsKey(id)) Logger.error("Duplicate item type: "+id);
        values.add(this);
        map.put(this.id, this);
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
