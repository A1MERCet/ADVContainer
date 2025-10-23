package com.aimercet.advcontainer.container;

import com.aimercet.advcontainer.container.backpack.equipment.ContainerHandlerEquipment;
import com.aimercet.advcontainer.container.handler.ContainerHandlerGeneral;
import com.aimercet.advcontainer.container.handler.IContainerHandler;
import com.aimercet.advcontainer.container.handler.source.HandleSourceConfig;
import com.aimercet.advcontainer.container.handler.source.HandleSourceSystem;
import com.aimercet.advcontainer.container.source.*;
import com.aimercet.advcontainer.item.ItemManager;
import com.aimercet.brlib.Options;
import com.aimercet.brlib.log.Logger;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ContainerManager
{
    public static ContainerManager instance;

    private static ContainerIndex<Location> locationCache = new ContainerIndex<>();
    private static ContainerIndex<Entity> entityCache = new ContainerIndex<>();

    public static void updateCache(IContainer container)
    {
        if(container==null)return;
        ISource inventorySource = container.getInventorySource();

        if(inventorySource==null){

            removeCache(container);

        }else if(container.getInventorySource() instanceof SourceLocation) {

            SourceLocation source = (SourceLocation) container.getInventorySource();
            if(source.getSourceLocation()!=null)
                locationCache.put(source.getSourceLocation(),container);

        }else if(container.getInventorySource() instanceof SourceEntity){

            SourceEntity source = (SourceEntity) container.getInventorySource();
            if(source.getEntity()!=null)
                entityCache.put(source.getEntity(),container);

        }
    }
    public static void removeCache(IContainer container)
    {
        locationCache.remove(container);
        entityCache.remove(container);
    }


    private static HashMap<String, IContainerHandler> containerHandlerMap = new HashMap<String, IContainerHandler>();
    public static HashMap<String, IContainerHandler> getContainerHandlerMap() {return containerHandlerMap;}
    public static  IContainerHandler getHandler(String id) {return containerHandlerMap.get(id);}
    public static  void registerHandler(IContainerHandler handler) {containerHandlerMap.put(handler.getHandlerID(), handler);}

    private final List<IContainer> containerList = new ArrayList<>();
    private final HashMap<String, IContainer> containerMap = new HashMap<>();

    public SourceSystem sourceSystem;
    public ContainerHandlerGeneral handlerGeneral;
    public ContainerHandlerEquipment handlerEquipment;
    public HandleSourceSystem handleSourceSystem;
    public HandleSourceConfig handleSourceConfig;

    private long maxInteractDistance = 5L;

    public ContainerManager()
    {
        instance = this;

        handleSourceSystem = new HandleSourceSystem();
        handleSourceConfig = new HandleSourceConfig();
        sourceSystem = new SourceSystem();
        handlerGeneral = new ContainerHandlerGeneral();
        handlerEquipment = new ContainerHandlerEquipment();

        registerHandler(handlerGeneral);
        registerHandler(handlerEquipment);

        ContainerFactory.register(new ContainerFactory());
    }

    protected void onRegister(IContainer container)
    {
        Logger.info("注册容器: "+container);
    }
    protected void onUnRegister(IContainer container)
    {
        Logger.info("注销容器: "+container);
    }

    public boolean register(IContainer container)
    {
        if(container==null || containerMap.containsKey(container.getUUID()))return false;

        containerMap.put(container.getUUID(), container);
        containerList.add(container);

        onRegister(container);
        return true;
    }
    public boolean unRegister(Container container){return container==null?false:unRegister(container.getUUID());}
    public boolean unRegister(String uuid)
    {
        IContainer container = containerMap.get(uuid);
        if(container!=null)return false;

        containerMap.remove(uuid);
        containerList.remove(container);
        removeCache(container);

        if(container!=null)onUnRegister(container);
        return container!=null;
    }

    public IContainer getFromItem(ItemStack isk,boolean loadFromFile)
    {
        if(isk==null)return null;
        String uuid = ItemManager.getContainer(isk);
        IContainer container = get(uuid);
        if(loadFromFile && container==null) container = loadContainer(uuid);
        return container;
    }

    public ItemStack bindToItem(ItemStack isk,IContainer container)
    {
        if(isk==null || container==null)return null;
        ItemManager.setContainer(isk,container);
        container.setInventorySource(new SourceItemStack(isk));
        return isk;
    }

    public IContainer loadContainer(ItemStack isk)
    {
        if(isk==null)return null;
        String uuid = ItemManager.getContainer(isk);
        IContainer container = loadContainer(uuid);
        if(container!=null) container.setInventorySource(new SourceItemStack(isk));
        return container;
    }

    public IContainer loadContainer(String uuid) {return loadContainer(uuid,false);}
    public IContainer loadContainer(String uuid,boolean overwrite) {
        if(uuid==null) return null;
        IContainer registry = containerMap.get(uuid);
        if(registry!=null)return registry;
        return loadContainer(new File(getFilePath()+uuid+".yml"),overwrite);
    }
    public IContainer loadContainer(File file){return loadContainer(file,false);}
    public IContainer loadContainer(File file,boolean overwrite)
    {
        if(file==null || !file.exists())return null;
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        ConfigurationSection section = cfg.getConfigurationSection("container");
        if(section==null)return null;
        return loadContainer(section,overwrite);
    }

    public IContainer loadContainer(ConfigurationSection section){return loadContainer(section,false);}
    public IContainer loadContainer(ConfigurationSection section , boolean overwrite){return loadContainer(section,null,overwrite);}
    public IContainer loadContainer(ConfigurationSection section, String clz){return loadContainer(section,clz,false);}
    public IContainer loadContainer(ConfigurationSection section, String clz,boolean overwrite)
    {
        String cfgClz = section.getString("class");
        if(cfgClz==null || (clz!=null && !cfgClz.equals(clz)))return null;
        String uuid = section.getString("uuid");

        IContainer registry = containerMap.get(uuid);
        if(registry!=null && !overwrite)return registry;

        Logger.info("加载容器["+cfgClz+" - "+uuid+"]");
        IContainer container = ContainerFactory.Create(cfgClz, uuid);
        container.load(section);
        return container;
    }

    public String getFilePath(){return Options.Instance().configPath+"/cache/container/";}

    public HashMap<String, IContainer> getContainerMap()    {return containerMap;}
    public IContainer get(String uuid)                      {return containerMap.get(uuid);}
    public IContainer get(int index)                        {return containerMap.get(index);}
    public long getMaxInteractDistance()                    {return maxInteractDistance;}
    public ContainerManager setMaxInteractDistance(long maxInteractDistance) {this.maxInteractDistance = maxInteractDistance;return this;
    }
}
