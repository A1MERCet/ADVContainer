package com.aimercet.advcontainer.util;

import com.aimercet.advcontainer.container.handler.source.HandleSourcePlayer;
import com.aimercet.advcontainer.container.ContainerManager;
import com.aimercet.advcontainer.container.handler.source.IHandleSource;
import com.aimercet.brlib.player.PlayerManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UtilCommand
{
    public static IHandleSource parseSender(CommandSender sender)
    {
        if(sender instanceof Player) return HandleSourcePlayer.fromPlayer(PlayerManager.Get((Player)sender));
        else return ContainerManager.instance.handleSourceSystem;
    }
}
