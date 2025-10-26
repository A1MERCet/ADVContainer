package com.aimercet.advcontainer.player.modules;

import com.aimercet.advcontainer.api.gui.GUIActionState;
import com.aimercet.advcontainer.container.handler.source.HandleSourcePlayer;
import com.aimercet.brlib.player.PlayerManager;
import com.aimercet.brlib.player.PlayerModule;
import com.aimercet.brlib.player.PlayerState;
import org.bukkit.entity.Player;

public class ModuleContainerState extends PlayerModule
{
    public static ModuleContainerState get(Player p)          {return get(PlayerManager.Get(p));}
    public static ModuleContainerState get(PlayerState ps)    {return ps==null?null:(ModuleContainerState) ps.getModule(MODULE_NAME);}

    public static String MODULE_NAME = "container_state";

    public HandleSourcePlayer handleSource;
    public GUIActionState actionState = new GUIActionState();

    protected ModuleContainerState(PlayerState playerState)
    {
        super(MODULE_NAME, playerState, false);
        handleSource = new HandleSourcePlayer(playerState);
    }
}
