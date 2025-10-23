package com.aimercet.advcontainer.bridge;

import com.aimercet.advcontainer.player.modules.ModuleBackpack;
import com.aimercet.advcontainer.player.modules.ModuleContainerState;
import com.aimercet.brlib.player.PlayerModuleManager;

public class BRLibBridge
{
    public static BRLibBridge instance;
    public  BRLibBridge()
    {
        instance = this;
    }

    private boolean init = false;
    public BRLibBridge init()
    {
        if (init) return this;
        init = true;

        PlayerModuleManager.instance.registerPreModule(ModuleBackpack.class);
        PlayerModuleManager.instance.registerPreModule(ModuleContainerState.class);
        return this;
    }
}
