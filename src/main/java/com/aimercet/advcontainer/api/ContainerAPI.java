package com.aimercet.advcontainer.api;

import com.aimercet.advcontainer.api.gui.GUIActionState;
import com.aimercet.advcontainer.api.gui.container.IPartContainer;
import com.aimercet.advcontainer.api.gui.data.DataContainer;
import com.aimercet.advcontainer.api.gui.data.DataItem;
import com.aimercet.advcontainer.api.gui.data.DataStock;
import com.aimercet.advcontainer.container.IContainer;
import com.aimercet.advcontainer.container.ISlot;
import com.aimercet.advcontainer.container.IStock;
import com.aimercet.advcontainer.container.handler.ItemSource;
import com.aimercet.advcontainer.container.handler.PlaceResult;
import com.aimercet.advcontainer.container.handler.RemoveResult;
import com.aimercet.advcontainer.container.handler.TransferResult;
import com.aimercet.advcontainer.container.slotitem.ISlotItem;
import com.aimercet.advcontainer.item.ItemManager;
import com.aimercet.advcontainer.item.item.TypeItem;
import com.aimercet.advcontainer.player.modules.ModuleContainerState;
import com.aimercet.brlib.log.Logger;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ContainerAPI
{

    public static ContainerAPI instance;

    public ContainerAPI()
    {
        this.instance = this;
    }

    /**
     * 设置玩家与容器交互的交互权限
     */
    public void allow(Player player , IContainer container)
    {
        ModuleContainerState module = ModuleContainerState.get(player);
        if(module==null || container == null) return;
        module.handleSource.setContainerAllow(container);
    }

    /**
     * 移除玩家与容器的交互权限
     */
    public void deny(Player player , String containerUUID)
    {
        ModuleContainerState module = ModuleContainerState.get(player);
        if(module==null || containerUUID == null) return;
        module.handleSource.setContainerDeny(containerUUID);
    }

    /**
     * 从指针槽位的物品转移到目标槽位
     * @param clearCursor 转移成功后重置指针状态
     */
    public TransferResult doPlayerTransfer(Player player , ItemSource source , ItemSource target , boolean rotate , boolean clearCursor)
    {
        if(source==null || target==null)return null;

        ModuleContainerState module = ModuleContainerState.get(player);
        if(module==null || module.handleSource==null || source.getContainer()==null || target.getContainer()==null) return null;

        ISlotItem item = source.getItem();
        if(item==null) return null;

        if(target.getContainer().getHandler().checkPlace(module.handleSource, item, target, rotate) == PlaceResult.Type.SUCCESS){
            RemoveResult removeResult = source.getContainer().getHandler().remove(module.handleSource, source);
            if(removeResult.type == RemoveResult.Type.SUCCESS)
            {
                PlaceResult placeResult = target.getContainer().getHandler().place(module.handleSource, item, target, rotate, true);
                if(clearCursor)module.actionState.getCursor().clear();
                return new TransferResult(removeResult,placeResult);
            }
        }
        return null;
    }

    public GUIActionState getPlayerActionState(Player player)
    {
        ModuleContainerState module = ModuleContainerState.get(player);
        if(module==null)return null;

        return module.actionState;
    }

    public GUIActionState.Cursor getPlayerClick(Player player)
    {
        ModuleContainerState module = ModuleContainerState.get(player);
        if(module==null)return null;

        return module.actionState.getCursor();
    }

    public boolean setPlayerClick(Player player, ISlot slot,boolean rotate)
    {
        if(player==null || slot==null)return false;

        ModuleContainerState module = ModuleContainerState.get(player);
        if(module==null)return false;

        module.actionState.getCursor().set(new ItemSource(slot.getStock(), slot.getCoord()),rotate);

        return true;
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
