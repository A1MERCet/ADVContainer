package com.aimercet.advcontainer.command;

import com.aimercet.advcontainer.container.*;
import com.aimercet.advcontainer.container.handler.*;
import com.aimercet.advcontainer.container.handler.source.IHandleSource;
import com.aimercet.advcontainer.bridge.minecraft.container.SlotItemStack;
import com.aimercet.advcontainer.item.ItemManager;
import com.aimercet.advcontainer.item.item.TypeItem;
import com.aimercet.advcontainer.util.Coord;
import com.aimercet.advcontainer.util.Util;
import com.aimercet.advcontainer.util.UtilCommand;
import com.aimercet.brlib.command.CMDBasic;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.regex.Pattern;

public class CMDContainerAdmin extends CMDBasic
{
    public CMDContainerAdmin()
    {
        super("con", CMDContainerAdmin.class);
    }

    @CommandArgs(
            describe = "打印所有已注册的容器",
            args = {"list"},
            types = {ArgType.DEPEND}
    )
    public void printList(CommandSender sender)
    {
        StringBuilder b = new StringBuilder().append("已注册的容器["+ContainerManager.instance.getContainerMap().size()).append("个]\n");
        ContainerManager.instance.getContainerMap().values().forEach(container -> b.append("    ").append(container.toString()).append("\n"));
        b.append("已注册的容器["+ContainerManager.instance.getContainerMap().size()).append("个]\n");
        sender.sendMessage(b.toString());
    }

    @CommandArgs(
            describe = "查找物品",
            args = {"search","item","容器UUID","物品ID","数量"},
            types = {ArgType.DEPEND,ArgType.DEPEND,ArgType.STRING,ArgType.STRING,ArgType.INTEGER}
    )
    public void searchItem(CommandSender sender,String uuid,String itemID,int amount)
    {
        TypeItem item = ItemManager.Get(itemID);
        if(item == null) {sender.sendMessage("物品["+itemID+"]不存在");return;}

        IContainer container = ContainerManager.instance.get(uuid);
        if(container == null) {sender.sendMessage("容器["+uuid+"]不存在");return;}

        StringBuilder b = new StringBuilder().append("查找容器["+uuid+"]内容["+itemID+"*"+amount+"]\n");

        GetResult serchResult = Util.serch(container, new ItemDetailed().add(new ItemDetailed.Entry(item, amount)));
        b.append(serchResult.toString());

        sender.sendMessage(b.toString());
    }

    @CommandArgs(
            describe = "打印所有已容器内容[ I = 物品 P = 占位]",
            args = {"show","容器UUID","打印物品名[true,false]"},
            types = {ArgType.DEPEND,ArgType.STRING,ArgType.BOOLEAN}
    )
    public void showContent(CommandSender sender,String uuid,boolean printItemName)
    {
        IContainer container = ContainerManager.instance.get(uuid);
        if(container == null) {sender.sendMessage("容器["+uuid+"]不存在");return;}

        StringBuilder b = new StringBuilder().append("容器["+uuid+"]\n");
        b.append("库存\n");
        container.getStockList().forEach(stock -> b.append("    [").append(stock.getSize()).append(", Full=").append(stock.isFull()).append(", ItemCount=").append(stock.getItems().size()).append("]\n"));
        b.append("\n");
        b.append("内容\n");

        for (int i = 0; i < container.getStockList().size(); i++) {
            IStock stock = container.getStockList().get(i);
            b.append("Stock-"+i+"\n");
            for (int y = 0; y < stock.getSizeY(); y++)
            {
                StringBuilder b2 = new StringBuilder().append("  ");
                for (int x = 0; x < stock.getSizeX(); x++)
                {
                    ISlot slot = stock.getSlots()[x][y];
                    b2.append("[").append(slot.hasItem() ? (printItemName?slot.getItem().getSlotItemID():"I") : slot.isPlaceholder() ? "P" : " ").append("]  ");
                }
                b.append(b2.append("\n\n").toString());
            }
        }
        sender.sendMessage(b.toString());
    }

    @CommandArgs(
            describe = "从容器中移除指定坐标的物品",
            args = {"action","容器UUID","remove","库存下标","x","y"},
            types = {ArgType.DEPEND,ArgType.STRING,ArgType.DEPEND,ArgType.INTEGER,ArgType.INTEGER,ArgType.INTEGER}
    )
    public void actionRemove(CommandSender sender,String uuid,int stock,int x,int y)
    {
        IHandleSource handleSource = UtilCommand.parseSender(sender);
        if(handleSource == null) {sender.sendMessage("操作者无效["+sender.getName()+"]");return;}

        IContainer container = ContainerManager.instance.get(uuid);
        if(container == null) {sender.sendMessage("容器["+uuid+"]不存在");return;}

        if(stock<0 || stock>=container.getStockList().size()) {sender.sendMessage("库存下标["+stock+"]超出索引范围");return;}
        RemoveResult remove = container.getHandler().remove(handleSource, new ItemSource(container.getStockList().get(stock), new Coord(x, y)));
        sender.sendMessage("操作结果: "+remove);
    }

    @CommandArgs(
            describe = "移动容器1指定的槽位物品至容器2的指定槽位",
            args = {"action","容器1UUID","库存1下标","x1","y1","容器2UUID","库存2下标","x2","y2","是否旋转[true,false]"},
            types = {ArgType.DEPEND,ArgType.STRING,ArgType.INTEGER,ArgType.INTEGER,ArgType.INTEGER,ArgType.STRING,ArgType.INTEGER,ArgType.INTEGER,ArgType.INTEGER,ArgType.BOOLEAN}
    )
    public void transform(CommandSender sender,String uuid1 ,int stock1index,int x1 , int y1,String uuid2 ,int stock2index,int x2 , int y2,boolean rotate)
    {
        IHandleSource handleSource = UtilCommand.parseSender(sender);
        if(handleSource == null) {sender.sendMessage("操作者无效["+sender.getName()+"]");return;}

        IContainer container1 = ContainerManager.instance.get(uuid1);
        if(container1 == null) {sender.sendMessage("容器["+uuid1+"]不存在");return;}

        IContainer container2 = ContainerManager.instance.get(uuid2);
        if(container2 == null) {sender.sendMessage("容器["+uuid2+"]不存在");return;}

        if(stock1index >= container1.getStockList().size()) {sender.sendMessage("容器1["+stock1index+"]下标超出索引范围");return;}
        if(stock2index >= container2.getStockList().size()) {sender.sendMessage("容器2["+stock2index+"]下标超出索引范围");return;}

        IStock stock1 = container1.getStockList().get(stock1index);
        IStock stock2 = container2.getStockList().get(stock2index);

        RemoveResult result1 = container1.getHandler().remove(handleSource, new ItemSource(stock1, new Coord(x1, y1)));
        if(result1.type!=RemoveResult.Type.SUCCESS){sender.sendMessage("操作失败: "+result1);return;}

        RemoveResult result2 = container2.getHandler().remove(handleSource, new ItemSource(stock2, new Coord(x2, y2)));
        if(result2.type!=RemoveResult.Type.SUCCESS){sender.sendMessage("操作失败: "+result2);return;}

        sender.sendMessage("操作结果: \n"+result1+"\n"+result2);
    }

    @CommandArgs(
            describe = "添加物品至容器[库存下标-1为不指定(有空位就放) 旋转,x,y传false和-1就行]",
            args = {"action","容器UUID","add","物品ID","数量","库存下标","是否旋转[true,false]","x","y"},
            types = {ArgType.DEPEND,ArgType.STRING,ArgType.DEPEND,ArgType.STRING,ArgType.INTEGER,ArgType.INTEGER,ArgType.BOOLEAN,ArgType.INTEGER,ArgType.INTEGER}
    )
    public void actionAddItem(CommandSender sender,String uuid,String itemID,int amount,int stock,boolean rotate,int x,int y)
    {
        IHandleSource handleSource = UtilCommand.parseSender(sender);
        if(handleSource == null) {sender.sendMessage("操作者无效["+sender.getName()+"]");return;}

        TypeItem item = ItemManager.instance.get(itemID);
        if(item==null){sender.sendMessage("物品ID不存在["+itemID+"]");return;}

        IContainer container = ContainerManager.instance.get(uuid);
        if(container == null) {sender.sendMessage("容器["+uuid+"]不存在");return;}

        if(stock>=container.getStockList().size()) {sender.sendMessage("库存下标["+stock+"]超出索引范围");return;}
        SlotItemStack slotItemStack = new SlotItemStack(ItemManager.setAmount(item.createItem(),amount));

        PlaceResult place;
        if(stock<0) {
            place = container.getHandler().place(handleSource, slotItemStack, container);
        } else {
            Coord coord = new Coord(x, y);
            if(coord.x<0 || coord.y<0){sender.sendMessage("坐标["+coord+"]超出索引范围");return;}
            place = container.getHandler().place(handleSource, slotItemStack, new ItemSource(container.getStockList().get(stock),coord),rotate);
        }

        sender.sendMessage("操作结果: "+place);
    }

    @CommandArgs(
            describe = "清空容器物品",
            args = {"action","容器UUID","clear","库存下标[-1为所有]"},
            types = {ArgType.DEPEND,ArgType.STRING,ArgType.DEPEND,ArgType.INTEGER}
    )
    public void actionClear(CommandSender sender,String uuid,int stockIndex)
    {
        IHandleSource handleSource = UtilCommand.parseSender(sender);
        if(handleSource == null) {sender.sendMessage("操作者无效["+sender.getName()+"]");return;}

        IContainer container = ContainerManager.instance.get(uuid);
        if(container == null) {sender.sendMessage("容器["+uuid+"]不存在");return;}

        List<RemoveResult> resultList;

        if(stockIndex<0){
            resultList = container.getHandler().clear(handleSource, container);
        }else {
            if(stockIndex>=container.getStockList().size()) {sender.sendMessage("库存下标["+stockIndex+"]超出索引范围");return;}
            resultList = container.getHandler().clear(handleSource, container.getStockList().get(stockIndex));
        }

        StringBuilder b = new StringBuilder().append("清空容器["+uuid+"] 库存["+stockIndex+"]\n");
        resultList.forEach(e->b.append(e.toString()).append("\n"));

        sender.sendMessage("操作结果完成");
    }



    /**
     * 寻找指定id的已注册容器(UUID或者指定下标)
     */
    public IContainer parseContainer(String id)
    {
        if(Pattern.matches("^[0-9]+$", id)){
            return ContainerManager.instance.get(id);
        }else{
            return ContainerManager.instance.get(id);
        }
    }
}
