package com.aimercet.advcontainer.container;

import com.aimercet.advcontainer.container.handler.IContainerHandler;
import com.aimercet.advcontainer.container.handler.PlaceResult;
import com.aimercet.advcontainer.container.handler.RemoveResult;
import com.aimercet.advcontainer.container.handler.source.InventoryHandleHistory;
import com.aimercet.advcontainer.container.source.ISource;
import com.aimercet.advcontainer.util.Coord;
import com.aimercet.advcontainer.util.SizeInt;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

public interface IContainer
{
    String getUUID();
    String getClassName();
    String getDefaultHandler();
    IContainerHandler getHandler();
    void setHandler(IContainerHandler handler);
    List<IStock> getStockList();
    ISource getInventorySource();

    default IContainer setInventorySource(ISource source)
    {
        ContainerManager.updateCache(this);
        return this;
    }
    InventoryHandleHistory getInventoryHandleHistory();

    default IStock addStock(SizeInt size){IStock s = createStock(size);getStockList().add(s);return s;}
    default IStock createStock(SizeInt size){return new Stock(this).setSize(size);}
    default ISlot createSlot(IStock stock, Coord coord){return new Slot(stock, coord);}

    default boolean isFull()
    {
        for (IStock stock : getStockList())
            if(!stock.isFull()) return false;
        return true;
    }

    default void onPlace(PlaceResult result){}
    default void onRemove(RemoveResult result){}

    default void load(ConfigurationSection section)
    {
        for (int i = 0; i < getStockList().size(); i++)
        {
            ConfigurationSection s = section.getConfigurationSection("Content.Stock"+i);
            if(s!=null) getStockList().get(i).load(s);
        }
    }
    default void save(ConfigurationSection section)
    {
        section.set("uuid",getUUID());
        section.set("class",getClassName());
        for (int i = 0; i < getStockList().size(); i++)
            getStockList().get(i).save(section.createSection("Content.Stock"+i));
    }
}
