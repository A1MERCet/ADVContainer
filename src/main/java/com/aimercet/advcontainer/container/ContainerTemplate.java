package com.aimercet.advcontainer.container;

import com.aimercet.advcontainer.api.gui.GPartStyle;
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
        public GPartStyle style = new GPartStyle();

        public StockTemplate() {}

        public StockTemplate(SizeInt size)
        {
            this.size = size;
        }

        public StockTemplate setStyle(GPartStyle style) {this.style = style;return this;}

        public StockTemplate load(ConfigurationSection section)
        {
            if(section == null) return this;
            size = new SizeInt(section.getInt("width"), section.getInt("height"));
            style.load(section.getConfigurationSection("style"));
            return this;
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

    public IContainer create(String uuid) {return create(null, uuid);}
    public IContainer create(IContainerHandler handler, String uuid)
    {
        IContainer container = ContainerFactory.Create(clzName,uuid);
        for (StockTemplate s : stockTemplateList)
        {
            IStock stock = container.createStock().setSize(s.size);

            container.getStockList().add(stock);

            stock.initStock();
        }

        if(handler != null)container.setHandler(handler);
        container.initContainer();
        ContainerManager.instance.register(container);
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
