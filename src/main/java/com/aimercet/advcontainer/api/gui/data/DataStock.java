package com.aimercet.advcontainer.api.gui.data;

public class DataStock
{
    private DataItem[][] items;

    public DataStock()
    {

    }

    public DataStock set(DataItem item , int x, int y)
    {
        if(items.length == 0 || x<0 || y<0 || x>= items.length || y>= items[0].length)return this;
        items[x][y] = item;
        return this;
    }
    public DataStock remove(int x, int y)
    {
        if(items.length == 0 || x<0 || y<0 || x>= items.length || y>= items[0].length)return this;
        items[x][y] = null;
        return this;
    }
    public DataStock setSize(int width,int height){items = new DataItem[height][width];return this;}
    public DataItem[][] getItems() {return items;}
    public DataStock setItems(DataItem[][] items) {this.items = items;return this;}

    @Override
    public String toString() {
        return getClass().getSimpleName()+"[" +
                "size="+(items.length+"*"+(items.length==0?"0":items[0].length))+
                ']';
    }
}
