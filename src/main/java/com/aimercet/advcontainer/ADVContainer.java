package com.aimercet.advcontainer;

import com.aimercet.advcontainer.bridge.BRLibBridge;
import com.aimercet.advcontainer.bridge.minecraft.event.PlayerEventContainer;
import com.aimercet.advcontainer.command.CMDContainer;
import com.aimercet.advcontainer.command.CMDItem;
import com.aimercet.advcontainer.container.ContainerManager;
import com.aimercet.advcontainer.event.PlayerEventHandler;
import com.aimercet.advcontainer.item.ItemManager;
import com.aimercet.advcontainer.loot.LootManager;
import com.aimercet.advcontainer.timer.LootRunnable;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ADVContainer extends JavaPlugin {

    public CMDContainer cmdContainer;
    public CMDItem cmdItem;

    public ItemManager itemManager;
    public ContainerManager containerManager;
    public LootManager lootManager;

    public LootRunnable lootRunnable;

    @Override
    public void onEnable()
    {
        itemManager = new ItemManager();
        containerManager = new ContainerManager();
        lootManager = new LootManager();

        new BRLibBridge().init();

        registerCMD();
        registerEvent();

        lootRunnable =  new LootRunnable();
        lootRunnable.runTaskTimerAsynchronously(this,0L,1L);
    }

    public void handleBRLib()
    {

    }

    @Override
    public void onDisable()
    {
    }
    private void registerCMD()
    {
        cmdContainer = new CMDContainer();
        Bukkit.getPluginCommand(cmdContainer.name).setExecutor(cmdContainer);
        cmdItem = new CMDItem();
        Bukkit.getPluginCommand(cmdItem.name).setExecutor(cmdItem);
    }
    private void registerEvent()
    {
        Bukkit.getPluginManager().registerEvents(new PlayerEventHandler(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerEventContainer(), this);
    }
}
