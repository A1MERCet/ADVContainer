package com.aimercet.advcontainer.api.gui;

import com.aimercet.advcontainer.api.gui.container.IContainerPart;
import com.aimercet.advcontainer.api.gui.data.DataContainer;
import com.aimercet.advcontainer.api.gui.data.DataItem;
import com.aimercet.advcontainer.api.gui.data.DataStock;
import com.aimercet.advcontainer.container.IContainer;
import com.aimercet.advcontainer.container.IStock;
import com.aimercet.advcontainer.container.handler.ItemSource;
import com.aimercet.advcontainer.container.slotitem.ISlotItem;
import com.aimercet.advcontainer.item.ItemManager;
import com.aimercet.advcontainer.item.item.TypeItem;
import com.aimercet.brlib.log.Logger;
import com.aimercet.brlib.player.PlayerState;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class ContainerGUIManager
{
    public static ContainerGUIManager instance;
    public HashMap<String, GUIActionState> playerStateMap = new HashMap<>();

    public ContainerGUIManager()
    {
        this.instance = this;
    }

    public boolean open(PlayerState ps , IContainer container)
    {
        IContainerPart gui = container.createGUIPart(ps);
        return false;
    }

    public DataContainer parseContainer(IContainer container)
    {
        if(container==null)return null;
        DataContainer dataContainer = new DataContainer(container.getUUID());

        for (IStock stock : container.getStockList())
        {
            DataStock dataStock = new DataStock();
            dataStock.setSize(stock.getSizeX(),stock.getSizeY());

            for (ItemSource item : stock.getItems())
            {
                if(item==null||item.getItem()==null)continue;
                DataItem dataItem = parseItem(item.getItem());
                dataStock.set(dataItem,item.coord.x,item.coord.y);
            }

            dataContainer.addStock(dataStock);
        }

        Logger.info(dataContainer.toString());
        return dataContainer;
    }


    public DataItem parseItem(ISlotItem item)
    {
        if(item==null)return null;

        TypeItem typeItem = item.getTypeItem();
        if(typeItem==null)return null;

        DataItem dataItem = new DataItem();
        parseItemBase(dataItem,typeItem);

        dataItem
                .setAmount(item.getAmount())
        ;

        return dataItem;
    }
    public DataItem parseItem(ItemStack isk)
    {
        if(isk==null)return null;

        TypeItem typeItem = ItemManager.Get(isk);
        if(typeItem==null)return null;

        DataItem dataItem = new DataItem();
        parseItemBase(dataItem,typeItem);

        dataItem
                .setAmount(ItemManager.getAmount(isk))
                ;

        return dataItem;
    }
    public DataItem parseItem(TypeItem item)
    {
        if(item==null)return null;

        DataItem dataItem = new DataItem();
        parseItemBase(dataItem,item);

        return dataItem;
    }

    private DataItem parseItemBase(DataItem data , TypeItem item)
    {
        data
                .setID(item.getID())
                .setName(item.shortLang)
                .setImage(item.getID())
        ;

        return data;
    }
}
