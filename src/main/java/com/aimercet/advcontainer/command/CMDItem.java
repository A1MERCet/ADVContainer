package com.aimercet.advcontainer.command;

import com.aimercet.advcontainer.container.ContainerManager;
import com.aimercet.advcontainer.container.ContainerTemplate;
import com.aimercet.advcontainer.item.ItemManager;
import com.aimercet.advcontainer.util.SizeInt;
import com.aimercet.brlib.command.CMDBasic;
import org.bukkit.command.CommandSender;

import java.util.UUID;

public class CMDItem extends CMDBasic
{
    public CMDItem()
    {
        super("itm", CMDItem.class);
    }

    @CommandArgs(
            describe = "打印所有已注册的物品",
            args = {"list"},
            types = {ArgType.DEPEND}
    )
    public void printList(CommandSender sender)
    {
        StringBuilder b = new StringBuilder();
        b.append("已注册的物品["+ItemManager.instance.getAll().size()+"]\n");
        ItemManager.instance.getAll().values().forEach((e)->b.append("  ").append(e).append("\n"));
        b.append("已注册的物品["+ItemManager.instance.getAll().size()+"]\n");
        sender.sendMessage(b.toString());
    }
}
