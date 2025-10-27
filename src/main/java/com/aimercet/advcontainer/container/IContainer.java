package com.aimercet.advcontainer.container;

import com.aimercet.advcontainer.api.gui.GPartStyle;
import com.aimercet.advcontainer.container.handler.*;
import com.aimercet.advcontainer.container.handler.source.IHandleSource;
import com.aimercet.advcontainer.container.handler.source.InventoryHandleHistory;
import com.aimercet.advcontainer.container.source.ISource;
import com.aimercet.advcontainer.util.Coord;
import com.aimercet.advcontainer.util.SizeInt;
import com.aimercet.brlib.player.PlayerState;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

public interface IContainer
{

    String getUUID();
    String getClassName();
    IContainerHandler getDefaultHandler();
    IContainerHandler getHandler();
    boolean handlerMissing();
    void setHandler(IContainerHandler handler);
    List<IStock> getStockList();
    ISource getInventorySource();
    GPartStyle getGUIStyle();

    List<IHandleSource> getUsers();

    void initContainer();

    default IContainer setInventorySource(ISource source)
    {
        ContainerManager.updateCache(this);
        return this;
    }
    InventoryHandleHistory getInventoryHandleHistory();
    default void addHistory(HandleResult result)
    {
        getInventoryHandleHistory().add(result);
    }

    default IStock createStock(){return new Stock(this);}
    default ISlot  createSlot(IStock stock, Coord coord){return new Slot(stock, coord);}

    default boolean isFull()
    {
        for (IStock stock : getStockList())
            if(!stock.isFull()) return false;
        return true;
    }
    default IStock getStock(int index)
    {
        if(index < 0 || index >= getStockList().size()) return null;
        return getStockList().get(index);
    }

    default List<ItemSource> getItems()
    {
        List<ItemSource> list = new ArrayList<>();

        for (IStock stock : getStockList())
            list.addAll(stock.getItems());

        return list;
    }

    default List<ISlot> getSlots()
    {
        List<ISlot> list = new ArrayList<>();

        for (IStock stock : getStockList())
        {
            ISlot[][] slots = stock.getSlots();
            for (int i = 0; i < slots.length; i++)
                for (int j = 0; j < slots[i].length; j++)
                    list.add(slots[i][j]);
        }

        return list;
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
