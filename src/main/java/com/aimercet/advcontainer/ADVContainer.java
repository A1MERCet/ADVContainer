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
import com.aimercet.advcontainer.version.AdapterFactory;
import com.aimercet.advcontainer.version.VersionAdapter;
import com.aimercet.brlib.log.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class ADVContainer extends JavaPlugin {

    public static VersionAdapter GetVersionAdapter(){return adapterFactory.getAdapter();}
    public static String BukkitVersion(){return bukkitVersion;}

    private static AdapterFactory adapterFactory;
    private static String bukkitVersion;
    private static String version = "0.1";

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
        //PRE
        String bktPckName = Bukkit.getServer().getClass().getPackage().getName();
        bukkitVersion = bktPckName.substring(bktPckName.lastIndexOf('.') + 1);
        Bukkit.getLogger().info("ADVC加载中 插件版本["+ version +"]  Bukkit版本["+Bukkit.getBukkitVersion()+"] NMS版本["+ bukkitVersion +"]");

        adapterFactory = new AdapterFactory(bukkitVersion);

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

        //Post
        Bukkit.getLogger().info("ADVC已启用");

        //DEBUG
        ContainerTemplate t1 = new ContainerTemplate().addStock(new SizeInt(4,4));
        ContainerTemplate t2 = new ContainerTemplate().addStock(new SizeInt(6,8));
        ContainerTemplate t3 = new ContainerTemplate().addStock(new SizeInt(6,6)).addStock(new SizeInt(2,2));
        IContainer c1 = t1.create(ContainerManager.instance.handlerGeneral, UUID.randomUUID().toString()).setInventorySource(ContainerManager.instance.sourceSystem);
        IContainer c2 = t2.create(ContainerManager.instance.handlerGeneral, UUID.randomUUID().toString()).setInventorySource(ContainerManager.instance.sourceSystem);
        IContainer c3 = t3.create(ContainerManager.instance.handlerGeneral, "test").setInventorySource(ContainerManager.instance.sourceSystem);

        PlaceResult r1 = c3.getHandler().place(ContainerManager.instance.handleSourceSystem, new SlotItemStack(ItemManager.Get("STONE").createItemStack()),c3);
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

    private static HashMap<String,Class<?>> nmsClassCache = new HashMap<>();
    private static HashMap<String,Class<?>> craftClassCache = new HashMap<>();

    public static Class<?> getNMSClass(String className) {
        String path1 = "net.minecraft.server." + bukkitVersion + "." + className;
        String path2 = "net.minecraft." + className;

        if(nmsClassCache.containsKey(path1))   { return nmsClassCache.get(path1); }
        if(craftClassCache.containsKey(path1)) { return craftClassCache.get(path1); }


        try {
            Class<?> clz = Class.forName(path1);
            nmsClassCache.put(path1, clz);
            return clz;
        } catch (ClassNotFoundException e) {
            try {
                Class<?> clz = Class.forName(path2);
                craftClassCache.put(path2, clz);
                return clz;
            } catch (ClassNotFoundException ex) {
                Bukkit.getLogger().severe("NMS ["+className+"] not found \n"+ex.getMessage());
            }
        }
        return null;
    }

    public static Class<?> getCraftClass(String className) {
        String path = "org.bukkit.craftbukkit." + bukkitVersion + "." + className;
        try {
            Class<?> clz = Class.forName(path);
            craftClassCache.put(path, clz);
            return clz;
        } catch (ClassNotFoundException e) {
            Bukkit.getLogger().severe("Craft ["+className+"] not found \n"+e.getMessage());
        }
        return null;
    }
}
