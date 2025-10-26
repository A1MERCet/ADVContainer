package com.aimercet.advcontainer.api.gui.data;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class DataContainer
{
    public final String uuid;
    private List<DataStock> stocks = new ArrayList<DataStock>();
    public String style;

    public DataContainer(String uuid)
    {
        this.uuid = uuid;
    }

    public List<DataStock> getStocks() {return stocks;}
    public DataContainer addStock(DataStock s) {stocks.add(s);return this;}

    public String getStyle() {return style;}
    public DataContainer setStyle(String style) {this.style = style;return this;}

    public String toJson()
    {
        return new Gson().toJson(this);
    }


    @Override
    public String toString() {
        return getClass().getSimpleName()+"[" +
                "uuid=" + uuid +
                ", stocks=" + stocks.size() +
                "]";
    }
}
