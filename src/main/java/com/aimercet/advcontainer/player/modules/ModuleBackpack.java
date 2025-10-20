package com.aimercet.advcontainer.player.modules;

import com.aimercet.advcontainer.bridge.brlib.ContainerSourcePlayer;
import com.aimercet.advcontainer.container.backpack.Backpack;
import com.aimercet.brlib.player.PlayerModule;
import com.aimercet.brlib.player.PlayerState;

public class ModuleBackpack extends PlayerModule
{
    public static String MODULE_NAME = "backpack";

    public final Backpack backpack;
    protected ModuleBackpack(PlayerState playerState)
    {
        super(MODULE_NAME, playerState, true);
        backpack = new Backpack(ContainerSourcePlayer.fromPlayer(playerState));
    }

    @Override
    public void onRegister()
    {
        super.onRegister();
    }

    @Override
    public void onUnRegister()
    {
        super.onUnRegister();
    }
}
