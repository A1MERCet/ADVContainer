package com.aimercet.advcontainer.player.modules;

import com.aimercet.advcontainer.container.backpack.Backpack;
import com.aimercet.advcontainer.container.handler.source.HandleSourcePlayer;
import com.aimercet.brlib.player.PlayerManager;
import com.aimercet.brlib.player.PlayerModule;
import com.aimercet.brlib.player.PlayerState;
import org.bukkit.entity.Player;

public class ModuleBackpack extends PlayerModule
{
    public static ModuleBackpack get(Player p)          {return get(PlayerManager.Get(p));}
    public static ModuleBackpack get(PlayerState ps)    {return ps==null?null:(ModuleBackpack) ps.getModule(MODULE_NAME);}

    public static String MODULE_NAME = "backpack";

    public Backpack backpack;
    protected ModuleBackpack(PlayerState playerState)
    {
        super(MODULE_NAME, playerState, true);
    }

    @Override
    public void onRegister()
    {
        super.onRegister();

        backpack = new Backpack(HandleSourcePlayer.fromPlayer(playerState));
    }

    @Override
    public void onUnRegister()
    {
        super.onUnRegister();
    }
}
