package com.aimercet.advcontainer.item;

import com.aimercet.brlib.localization.Localization;
import com.aimercet.brlib.log.Logger;
import com.aimercet.brlib.util.UtilColor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Quality
{
    public static List<Quality> values = new ArrayList<>();
    public static HashMap<String,Quality> map = new HashMap<>();

    public static Quality valueOf(String id) {return map.get(id);}
    public static boolean contains(String id){return map.containsKey(id);}

    public static Quality Q1 = new Quality("LOW",0, UtilColor.GRAY,1F);
    public static Quality Q2 = new Quality("MEDIUM",1, UtilColor.BLUE,0.2F);
    public static Quality Q3 = new Quality("HIGH",2, UtilColor.PURPLE,0.08F);
    public static Quality Q4 = new Quality("ULTRA",3, UtilColor.ORANGE,0.03F);
    public static Quality Q5 = new Quality("ULTIMATE",4, UtilColor.RED,0.005F);

    public final String id;
    public final int level;
    public final String lang;

    public final float globalRate;
    public final long color;

    public static HashMap<Integer,Quality> levelMap = null;

    Quality(String id,int level, long color,float globalRate)
    {
        this.id=id;
        this.level = level;
        this.color = color;
        this.lang = Localization.register("quality_type_"+id);
        this.globalRate = globalRate;

        register(this);
    }

    private Quality register(Quality quality)
    {
        String id = quality.id;
        if(map.containsKey(id)) Logger.error("Duplicate item type: "+id);
        values.add(this);
        values.sort(Comparator.comparingInt(o -> o.level));
        map.put(this.id, this);
        return quality;
    }

    public static Quality fromLevel(int level) {return levelMap.get(level);}

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Quality other = (Quality) obj;
        return this.id.equals(other.id);
    }
    @Override public int hashCode() {return id.hashCode();}
    @Override public String toString() {return id;}

}
