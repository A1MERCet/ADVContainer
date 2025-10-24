package com.aimercet.advcontainer.command;

import com.aimercet.advcontainer.bridge.minecraft.container.SlotItemStack;
import com.aimercet.advcontainer.container.ContainerManager;
import com.aimercet.advcontainer.container.IContainer;
import com.aimercet.advcontainer.container.ISlot;
import com.aimercet.advcontainer.container.IStock;
import com.aimercet.advcontainer.container.handler.*;
import com.aimercet.advcontainer.container.handler.source.IHandleSource;
import com.aimercet.advcontainer.item.ItemManager;
import com.aimercet.advcontainer.item.item.TypeItem;
import com.aimercet.advcontainer.player.modules.ModuleContainerState;
import com.aimercet.advcontainer.util.Coord;
import com.aimercet.advcontainer.util.Util;
import com.aimercet.advcontainer.util.UtilCommand;
import com.aimercet.brlib.command.CMDBasic;
import com.aimercet.brlib.log.Logger;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.regex.Pattern;

public class CMDContainerPlayer extends CMDBasic
{
    public boolean log = true;

    public CMDContainerPlayer()
    {
        super("conp", CMDContainerPlayer.class);
    }



    @CommandArgs(
            describe = "重置指针状态",
            args = {"cursor","clear"},
            types = {ArgType.DEPEND,ArgType.BOOLEAN},
            needOP = false
    )
    public void resetCursor(CommandSender sender,boolean rotate)
    {
        if(!(sender instanceof Player)) return;
        ModuleContainerState module = ModuleContainerState.get((Player)sender);
        if(module == null) return;

        module.actionState.getCursor().clear();
    }

    @CommandArgs(
            describe = "设置当前指针物品的旋转",
            args = {"cursor","rotate","[true/false]"},
            types = {ArgType.DEPEND,ArgType.DEPEND,ArgType.BOOLEAN},
            needOP = false
    )
    public void setCursorRotate(CommandSender sender,boolean rotate)
    {
        if(!(sender instanceof Player)) return;
        ModuleContainerState module = ModuleContainerState.get((Player)sender);
        if(module == null) return;

        module.actionState.getCursor().setRotate(rotate);
    }

    @CommandArgs(
            describe = "设置当前指针的物品",
            args = {"cursor","click","容器UUID","库存下标","x","y"},
            types = {ArgType.DEPEND,ArgType.DEPEND,ArgType.STRING,ArgType.INTEGER,ArgType.INTEGER,ArgType.INTEGER},
            needOP = false
    )
    public void setCursor(CommandSender sender,String uuid,int stockIndex,int x,int y)
    {
        if(!(sender instanceof Player)) return;
        ModuleContainerState module = ModuleContainerState.get((Player)sender);
        if(module == null) return;

        IContainer container = ContainerManager.instance.get(uuid);
        if(container == null) {info(sender,"容器["+uuid+"]不存在");return;}

        IStock stock = container.getStock(stockIndex);
        if(stock == null) return;

        ISlot slot = stock.get(x, y);
        if(slot == null) return;

        module.actionState.getCursor().setSource(new ItemSource(stock,slot.getCoord()));

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
