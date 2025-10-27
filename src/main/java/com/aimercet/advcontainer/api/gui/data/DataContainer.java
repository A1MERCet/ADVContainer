package com.aimercet.advcontainer.api.gui.data;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataContainer
{
    public final String uuid;
    private List<DataStock> stocks = new ArrayList<DataStock>();
    public String style;
    public HashMap<String,String> customData = new HashMap<>();
    public String bindPart;

    public DataContainer(String uuid)
    {
        this.uuid = uuid;
    }

    public List<DataStock> getStocks() {return stocks;}
    public DataContainer addStock(DataStock s) {stocks.add(s);return this;}

    public String getStyle() {return style;}
    public DataContainer setStyle(String style) {this.style = style;return this;}

    public HashMap<String, String> getCustomData() {return customData;}
    public DataContainer setCustomData(HashMap<String, String> customData) {this.customData = customData;return this;}
    public DataContainer addCustomData(String k,String v){customData.put(k,v);return this;}
    public String getBindPart() {return bindPart;}
    public DataContainer setBindPart(String bindPart) {this.bindPart = bindPart;return this;}

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
