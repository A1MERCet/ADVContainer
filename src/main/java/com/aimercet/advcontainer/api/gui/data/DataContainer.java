package com.aimercet.advcontainer.api.gui.data;

import java.util.List;

public class DataContainer
{
    public final String uuid;
    private List<DataStock>  stocks;
    public DataContainer(String uuid)
    {
        this.uuid = uuid;
    }

    public List<DataStock> getStocks() {return stocks;}
    public DataContainer addStock(DataStock s) {stocks.add(s);return this;}

    @Override
    public String toString() {
        return getClass().getSimpleName()+"[" +
                "uuid=" + uuid +
                ", stocks=" + stocks.size() +
                "]";
    }
}
