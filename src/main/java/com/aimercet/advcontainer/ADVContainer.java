package com.aimercet.advcontainer;

import com.aimercet.advcontainer.api.ContainerAPI;
import com.aimercet.advcontainer.bridge.BRLibBridge;
import com.aimercet.advcontainer.bridge.minecraft.InventoryWatcher;
import com.aimercet.advcontainer.bridge.minecraft.container.SlotItemStack;
import com.aimercet.advcontainer.bridge.minecraft.event.PlayerEventContainer;
import com.aimercet.advcontainer.bridge.protocollib.ProtocolLibManager;
import com.aimercet.advcontainer.command.CMDContainerAdmin;
import com.aimercet.advcontainer.command.CMDContainerPlayer;
import com.aimercet.advcontainer.command.CMDItem;
import com.aimercet.advcontainer.container.ContainerManager;
import com.aimercet.advcontainer.container.ContainerTemplate;
import com.aimercet.advcontainer.container.IContainer;
import com.aimercet.advcontainer.container.handler.PlaceResult;
import com.aimercet.advcontainer.event.PlayerEventHandler;
import com.aimercet.advcontainer.item.ItemManager;
import com.aimercet.advcontainer.loot.LootManager;
import com.aimercet.advcontainer.timer.LootRunnable;
import com.aimercet.advcontainer.util.SizeInt;
import com.aimercet.brlib.log.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public final class ADVContainer extends JavaPlugin {

    public static ADVContainer instance;

    public CMDContainerAdmin cmdContainerAdmin;
    public CMDContainerPlayer cmdContainerPlayer;
    public CMDItem cmdItem;

    ProtocolLibManager protocolLibManager;
    public ItemManager itemManager;
    public ContainerManager containerManager;
    public LootManager lootManager;

    public LootRunnable lootRunnable;
    public InventoryWatcher inventoryWatcher;
    public BRLibBridge libBridge;

    public ContainerAPI containerAPI;

    @Override
    public void onLoad()
    {
        super.onLoad();
        libBridge = new BRLibBridge().onLoad();
    }

    @Override
    public void onEnable()
    {
        super.onEnable();
        instance = this;
        protocolLibManager = new ProtocolLibManager();
        itemManager = new ItemManager();
        containerManager = new ContainerManager();
        lootManager = new LootManager();
        containerAPI = new ContainerAPI();

        registerCMD();
        registerEvent();

        lootRunnable =  new LootRunnable();
        lootRunnable.runTaskTimerAsynchronously(this,0L,1L);

        inventoryWatcher =  new InventoryWatcher();
        inventoryWatcher.runTaskTimer(this,0L,1L);

        ContainerTemplate t1 = new ContainerTemplate().addStock(new SizeInt(4,4));
        ContainerTemplate t2 = new ContainerTemplate().addStock(new SizeInt(6,8));
        ContainerTemplate t3 = new ContainerTemplate().addStock(new SizeInt(6,6)).addStock(new SizeInt(2,2));
        IContainer c1 = t1.create(ContainerManager.instance.handlerGeneral, UUID.randomUUID().toString()).setInventorySource(ContainerManager.instance.sourceSystem);
        IContainer c2 = t2.create(ContainerManager.instance.handlerGeneral, UUID.randomUUID().toString()).setInventorySource(ContainerManager.instance.sourceSystem);
        IContainer c3 = t3.create(ContainerManager.instance.handlerGeneral, "test").setInventorySource(ContainerManager.instance.sourceSystem);

        PlaceResult r1 = c3.getHandler().place(ContainerManager.instance.handleSourceSystem, new SlotItemStack(ItemManager.Get("STONE").createItem()),c3);
        Logger.info(r1.toString());
    }

    @Override
    public void onDisable()
    {
        super.onDisable();
    }
    private void registerCMD()
    {
        cmdContainerAdmin = new CMDContainerAdmin();
        Bukkit.getPluginCommand(cmdContainerAdmin.name).setExecutor(cmdContainerAdmin);

        cmdContainerPlayer = new CMDContainerPlayer();
        Bukkit.getPluginCommand(cmdContainerPlayer.name).setExecutor(cmdContainerPlayer);

        cmdItem = new CMDItem();
        Bukkit.getPluginCommand(cmdItem.name).setExecutor(cmdItem);
    }
    private void registerEvent()
    {
        Bukkit.getPluginManager().registerEvents(new PlayerEventHandler(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerEventContainer(), this);
    }
}
