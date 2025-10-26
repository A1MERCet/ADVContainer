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


    public BRLibBridge onLoad()
    {
        PlayerModuleManager.instance.registerPreModule(ModuleBackpack.class);
        PlayerModuleManager.instance.registerPreModule(ModuleContainerState.class);
        return this;
    }
}
