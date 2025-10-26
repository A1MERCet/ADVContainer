package com.aimercet.advcontainer.api.gui.data;

import com.aimercet.advcontainer.api.gui.GPartStyle;
import com.google.gson.Gson;

public class DataStock
{
    private String container;
    private int index;
    private DataItem[][] items;
    public String style;
    public int styleX;
    public int styleY;
    public int w;
    public int h;

    public DataStock()
    {

    }

    public DataStock set(DataItem item , int x, int y)
    {
        if(items.length == 0 || x<0 || y<0 || x>= items.length || y>= items[0].length)return this;
        items[x][y] = item
                .setCoord(x, y)
                .setContainer(container)
                .setStock(index)
        ;
        return this;
    }
    public DataStock remove(int x, int y)
    {
        if(items.length == 0 || x<0 || y<0 || x>= items.length || y>= items[0].length)return this;
        items[x][y] = null;
        return this;
    }
    public DataStock setSize(int width,int height){items = new DataItem[height][width];w=width;h=height;return this;}
    public DataItem[][] getItems() {return items;}
    public DataStock setItems(DataItem[][] items) {this.items = items;return this;}
    public int getIndex() {return index;}
    public DataStock setIndex(int index) {this.index = index;return this;}
    public String getContainer() {return container;}
    public DataStock setContainer(String container) {this.container = container;return this;}

    public String getStyle() {return style;}
    public DataStock setStyle(String style) {this.style = style;return this;}
    public int getStyleX() {return styleX;}
    public DataStock setStyleX(int styleX) {this.styleX = styleX;return this;}
    public int getStyleY() {return styleY;}
    public DataStock setStyleY(int styleY) {this.styleY = styleY;return this;}
    public DataStock setStyle(GPartStyle style)
    {
        setStyle(style.getStyle());
        setStyleX(style.getX());
        setStyleY(style.getY());
        return this;
    }

    public String toJson()
    {
        return new Gson().toJson(this);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()+"[" +
                "size="+(items.length+"*"+(items.length==0?"0":items[0].length))+
                ']';
    }
}
