package com.aimercet.advcontainer.command;

import com.aimercet.advcontainer.api.ContainerAPI;
import com.aimercet.advcontainer.api.gui.GUIActionState;
import com.aimercet.advcontainer.container.ContainerManager;
import com.aimercet.advcontainer.container.IContainer;
import com.aimercet.advcontainer.container.ISlot;
import com.aimercet.advcontainer.container.IStock;
import com.aimercet.advcontainer.container.handler.*;
import com.aimercet.advcontainer.player.modules.ModuleContainerState;
import com.aimercet.brlib.command.CMDBasic;
import com.aimercet.brlib.log.Logger;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.regex.Pattern;

public class CMDContainerPlayer extends CMDBasic
{
    public boolean log = true;

    public CMDContainerPlayer()
    {
        super("conp", CMDContainerPlayer.class);
    }

    @CommandArgs(
            describe = "触发按下槽位逻辑判断(GUI专用)\n只用于GUI内槽位的点击 比如点击槽位A 再点击槽位B 会自动判断槽位A是否有物品及判定和执行A物品转移到B槽位",
            args = {"trigger","click","容器UUID","库存下标","x","y"},
            types = {ArgType.DEPEND,ArgType.DEPEND,ArgType.STRING,ArgType.INTEGER,ArgType.INTEGER,ArgType.INTEGER},
            needOP = false,
            playerOnly = true
    )
    public void triggerClick(CommandSender sender,String uuid,int stockIndex,int x,int y)
    {
        IContainer container = ContainerManager.instance.get(uuid);
        if(container == null) {info(sender,"容器["+uuid+"]不存在");return;}

        IStock stock = container.getStock(stockIndex);
        if(stock == null) return;

        ISlot slot = stock.get(x, y);
        if(slot == null) return;
        SlotSource target = slot.toSource();

        GUIActionState.Cursor cursor = ContainerAPI.instance.getCursor((Player)sender);

        if(cursor.getSource()==null){
            cursor.setSource(target);
            cursor.setRotate(target.isRotate());
            Logger.debug("Set cursor source");
        }else{
            TransferResult result = ContainerAPI.instance.doPlayerTransfer((Player) sender, cursor.getSource(), target, cursor.isRotate(), true);
            Logger.debug("TransferResult:"+(result==null?"null":result.toString()));
        }
    }

    @CommandArgs(
            describe = "重置指针状态",
            args = {"cursor","clear"},
            types = {ArgType.DEPEND,ArgType.DEPEND},
            needOP = false,
            playerOnly = true
    )
    public void resetCursor(CommandSender sender)
    {
        if(!(sender instanceof Player)) return;
        ModuleContainerState module = ModuleContainerState.get((Player)sender);
        if(module == null) return;

        module.actionState.getCursor().clear();
        Logger.debug("Reset cursor");
    }

    @CommandArgs(
            describe = "旋转当前指针物品",
            args = {"cursor","rotate"},
            types = {ArgType.DEPEND,ArgType.DEPEND},
            needOP = false,
            playerOnly = true
    )
    public void cursorRotate(CommandSender sender)
    {
        if(!(sender instanceof Player)) return;
        GUIActionState.Cursor cursor = ContainerAPI.instance.getCursor((Player) sender);
        if(cursor == null) return;

        cursor.setRotate(!cursor.isRotate());
    }

    @CommandArgs(
            describe = "设置当前指针物品的旋转",
            args = {"cursor","rotate","[true/false]"},
            types = {ArgType.DEPEND,ArgType.DEPEND,ArgType.BOOLEAN},
            needOP = false,
            playerOnly = true
    )
    public void setCursorRotate(CommandSender sender,boolean rotate)
    {
        if(!(sender instanceof Player)) return;
        GUIActionState.Cursor cursor = ContainerAPI.instance.getCursor((Player) sender);
        if(cursor == null) return;

        cursor.setRotate(rotate);
    }

    @CommandArgs(
            describe = "设置当前指针的物品",
            args = {"cursor","click","容器UUID","库存下标","x","y"},
            types = {ArgType.DEPEND,ArgType.DEPEND,ArgType.STRING,ArgType.INTEGER,ArgType.INTEGER,ArgType.INTEGER},
            needOP = false,
            playerOnly = true
    )
    public void setCursor(CommandSender sender,String uuid,int stockIndex,int x,int y)
    {
        if(!(sender instanceof Player)) return;
        GUIActionState.Cursor cursor = ContainerAPI.instance.getCursor((Player) sender);
        if(cursor==null)return;

        IContainer container = ContainerManager.instance.get(uuid);
        if(container == null) {info(sender,"容器["+uuid+"]不存在");return;}

        IStock stock = container.getStock(stockIndex);
        if(stock == null) return;

        ISlot slot = stock.get(x, y);
        if(slot == null) return;

        cursor.setSource(new SlotSource(stock,slot.getCoord()));

    }

    public IContainer parseContainer(String id)
    {
        if(Pattern.matches("^[0-9]+$", id)){
            return ContainerManager.instance.get(id);
        }else{
            return ContainerManager.instance.get(id);
        }
    }

    private void info(CommandSender player , String v)  {if(!log)return;Logger.info("["+player.getName()+"] "+v);}
    private void warn(CommandSender player , String v)  {if(!log)return;Logger.warn("["+player.getName()+"] "+v);}
    private void error(CommandSender player , String v) {if(!log)return;Logger.error("["+player.getName()+"] "+v);}

}
