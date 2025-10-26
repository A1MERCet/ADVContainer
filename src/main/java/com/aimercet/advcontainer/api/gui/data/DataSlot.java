package com.aimercet.advcontainer.api.gui.data;

import java.lang.reflect.Field;
import java.util.HashMap;

public class DataSlot
{
    public String container;
    public int stock;
    public int x;
    public int y;
    public String style;

    public DataSlot() {}

    public String getContainer() {return container;}
    public DataSlot setContainer(String container) {this.container = container;return this;}
    public int getStock() {return stock;}
    public DataSlot setStock(int stock) {this.stock = stock;return this;}
    public int getX() {return x;}
    public DataSlot setX(int x) {this.x = x;return this;}
    public int getY() {return y;}
    public DataSlot setCoord(int x, int y) {setX(x);setY(y);return this;}
    public DataSlot setY(int y) {this.y = y;return this;}
    public String getStyle() {return style;}
    public DataSlot setStyle(String style) {this.style = style;return this;}

    public HashMap<String,String> toMap()
    {
        HashMap<String,String> map = new HashMap<>();

        for (Field field : this.getClass().getFields())
            try {
                field.setAccessible(true);
                Object value = field.get(this);
                map.put(field.getName(), value==null?"":value.toString());
            } catch (IllegalAccessException e) {e.printStackTrace();}

        return map;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()+"[x="+x+", y="+y+"]";
    }
}
