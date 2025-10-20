package com.aimercet.advcontainer.bridge;

import com.aimercet.advcontainer.player.modules.ModuleBackpack;
import com.aimercet.brlib.player.PlayerModuleManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BRLibBridge
{
    public static BRLibBridge instance;
    public  BRLibBridge()
    {
        instance = this;
    }

    private boolean init = false;
    public void init()
    {
        if (init) return;
        init = true;

        PlayerModuleManager.instance.registerPreModule(ModuleBackpack.class);
    }
}
