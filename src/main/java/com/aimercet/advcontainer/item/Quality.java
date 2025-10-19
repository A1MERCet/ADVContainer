package com.aimercet.advcontainer.item;

import com.aimercet.brlib.localization.Localization;
import com.aimercet.brlib.util.UtilColor;

import java.util.HashMap;

public enum Quality
{
    LOW(0, Localization.register("quality_low"), UtilColor.GRAY),
    MEDIUM(1, Localization.register("quality_medium"),UtilColor.BLUE),
    HIGH(2, Localization.register("quality_high"),UtilColor.PURPLE),
    ULTRA(3, Localization.register("quality_ultra"),UtilColor.ORANGE),
    ;
    private final int level;
    private final String lang;
    private final long color;

    private static HashMap<Integer,Quality> map = null;
    public static HashMap<Integer,Quality> getLevelMap()
    {
        if(map==null){
            map = new HashMap<Integer,Quality>();
            for (Quality value : Quality.values())
                map.put(value.level, value);
        }
        return map;
    }

    Quality(int level, String lang, long color)
    {
        this.level = level;
        this.lang = lang;
        this.color = color;
    }

    public static Quality fromLevel(int level) {return getLevelMap().get(level);}
}
