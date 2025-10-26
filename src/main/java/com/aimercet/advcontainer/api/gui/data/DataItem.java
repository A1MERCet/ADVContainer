package com.aimercet.advcontainer.api.gui.data;

import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.HashMap;


public class DataItem
{
    public String id;
    public String name;
    public int x;
    public int y;
    public int w;
    public int h;
    public boolean rotate;
    public String image;
    public long color;
    public long amount;
    public String style;

    public int stock;
    public String container;

    public DataItem() {
    }

    public String getID() {return id;}
    public DataItem setID(String id) {this.id = id;return this;}
    public String getName() {return name;}
    public DataItem setName(String name) {this.name = name;return this;}
    public String getImage() {return image;}
    public DataItem setImage(String image) {this.image = image;return this;}
    public long getColor() {return color;}
    public DataItem setColor(long color) {this.color = color;return this;}
    public long getAmount() {return amount;}
    public DataItem setAmount(long amount) {this.amount = amount;return this;}
    public int getX() {return x;}
    public DataItem setX(int x) {this.x = x;return this;}
    public int getY() {return y;}
    public DataItem setY(int y) {this.y = y;return this;}
    public DataItem setCoord(int x,int y) {setX(x);setY(y);return this;}
    public int getW() {return w;}
    public DataItem setW(int w) {this.w = w;return this;}
    public int getH() {return h;}
    public DataItem setH(int h) {this.h = h;return this;}
    public DataItem setSize(int w,int h){setW(w);setH(h);return this;}
    public boolean isRotate() {return rotate;}
    public DataItem setRotate(boolean rotate) {this.rotate = rotate;return this;}
    public String getStyle() {return style;}
    public DataItem setStyle(String style) {this.style = style;return this;}
    public int getStock() {return stock;}
    public DataItem setStock(int stock) {this.stock = stock;return this;}
    public String getContainer() {return container;}
    public DataItem setContainer(String container) {this.container = container;return this;}
    public String toJson() {return new Gson().toJson(this);}

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
        return getClass().getSimpleName()+"[" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                ']';
    }
}
