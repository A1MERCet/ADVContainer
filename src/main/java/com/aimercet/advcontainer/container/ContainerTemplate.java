package com.aimercet.advcontainer.container;

import com.aimercet.advcontainer.container.handler.IContainerHandler;
import com.aimercet.advcontainer.util.SizeInt;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

public class ContainerTemplate
{
    public class StockTemplate
    {
        public SizeInt size;

        public StockTemplate() {}

        public StockTemplate(SizeInt size)
        {
            this.size = size;
        }

        public void load(ConfigurationSection section)
        {
            size = new SizeInt(section.getInt("width"), section.getInt("height"));
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

    public IContainer create(IContainerHandler handler, String uuid) {return create(handler.getHandlerID(), uuid);}
    public IContainer create(String handler, String uuid)
    {
        IContainer container = ContainerFactory.Create(clzName,uuid);
        for (StockTemplate s : stockTemplateList)
        {
            container.addStock(s.size);
        }
        return container;
    }

    public void load(ConfigurationSection section)
    {
        clzName = section.getString("clz");

        ConfigurationSection stockSection = section.getConfigurationSection("stock");
        if(stockSection != null)
            for (String key : stockSection.getKeys(false))
            {
                StockTemplate stock = new StockTemplate();
                stock.load(section.getConfigurationSection("stock."+key));
                addStock(stock);
            }
    }

    public ContainerTemplate addStock(StockTemplate s){stockTemplateList.add(s);return this;}
    public ContainerTemplate addStock(SizeInt s){stockTemplateList.add(new StockTemplate(s));return this;}
}
