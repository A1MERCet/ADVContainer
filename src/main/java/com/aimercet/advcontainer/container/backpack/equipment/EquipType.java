package com.aimercet.advcontainer.container.backpack.equipment;

import com.aimercet.brlib.localization.Localization;
import com.aimercet.brlib.log.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EquipType
{
    public static List<EquipType> values = new ArrayList<EquipType>();
    public static HashMap<String, EquipType> map = new HashMap<String, EquipType>();

    public static EquipType valueOf(String id){return map.get(id);}
    public static boolean contains(String id){return map.containsKey(id);}

    public static EquipType HEAD = new EquipType("HEAD",null,new ArrayList<>());
    public static EquipType UPPER_BODY = new EquipType("UPPER_BODY",null,new ArrayList<>());
    public static EquipType LOWER_BODY = new EquipType("LOWER_BODY",null,new ArrayList<>());
    public static EquipType BOOT = new EquipType("BOOT",null,new ArrayList<>());

    public static EquipType HELMET = new EquipType("HELMET",null,new ArrayList<>());
    public static EquipType ARMOR = new EquipType("ARMOR",null,new ArrayList<>());
    public static EquipType VEST = new EquipType("VEST",null,new ArrayList<>());
    public static EquipType BACKPACK = new EquipType("BACKPACK",null,new ArrayList<>());
    public static EquipType BADGE = new EquipType("BADGE",null,new ArrayList<>());
    public static EquipType BELT = new EquipType("BELT",null,new ArrayList<>());

    public static EquipType SAFE_CASE = new EquipType("SAFE_CASE",null,new ArrayList<>());

    ;
    public final String id;
    public final String lang;

    public final EquipType parent;
    public final List<EquipType> childs = new ArrayList<EquipType>();

    EquipType(String id, EquipType parent, List<EquipType> childs)
    {
        this.id = id;
        this.lang = "equip_type_"+id.toLowerCase();
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

        EquipType other = (EquipType) obj;
        return this.id.equals(other.id);
    }
    @Override public int hashCode() {return id.hashCode();}
    @Override public String toString() {return id;}
}
