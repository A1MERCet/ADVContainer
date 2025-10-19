package com.aimercet.advcontainer.container;

import com.aimercet.advcontainer.container.handler.IInventoryHandler;
import com.aimercet.advcontainer.container.source.IInventorySource;
import com.aimercet.advcontainer.util.SizeInt;

import java.util.ArrayList;
import java.util.List;

public class ContainerTemplate
{
    public class StockTemplate
    {
        public SizeInt size;

        public StockTemplate(SizeInt size)
        {
            this.size = size;
        }
    }

    public final List<StockTemplate> stockTemplateList = new ArrayList<StockTemplate>();

    public String clzName = "default";
    public ContainerTemplate(String clzName)
    {
        this.clzName = clzName;
    }
    public ContainerTemplate()
    {
    }

    public IContainer create(IInventorySource source , IInventoryHandler handler, String uuid)
    {
        IContainer container = ContainerFactory.Create(clzName,source,handler,uuid);
        for (StockTemplate s : stockTemplateList)
            container.addStock(s.size);
        return container;
    }

    public ContainerTemplate addStock(StockTemplate s){stockTemplateList.add(s);return this;}
    public ContainerTemplate addStock(SizeInt s){stockTemplateList.add(new StockTemplate(s));return this;}
}
