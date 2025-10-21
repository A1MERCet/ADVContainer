package com.aimercet.advcontainer;

import com.aimercet.advcontainer.bridge.BRLibBridge;
import com.aimercet.advcontainer.command.CMDContainer;
import com.aimercet.advcontainer.command.CMDItem;
import com.aimercet.advcontainer.container.ContainerManager;
import com.aimercet.advcontainer.event.PlayerEventHandler;
import com.aimercet.advcontainer.item.ItemManager;
import com.aimercet.advcontainer.loot.LootManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ADVContainer extends JavaPlugin {

    public CMDContainer cmdContainer;
    public CMDItem cmdItem;

    public ItemManager itemManager;
    public ContainerManager containerManager;
    public LootManager lootManager;

    @Override
    public void onEnable()
    {
        itemManager = new ItemManager();
        containerManager = new ContainerManager();
        lootManager = new LootManager();

        new BRLibBridge().init();

        registerCMD();
        registerEvent();
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
    }
}
