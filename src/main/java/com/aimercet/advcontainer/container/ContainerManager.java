package com.aimercet.advcontainer.container;

import com.aimercet.advcontainer.container.handler.InventoryHandlerGeneral;
import com.aimercet.advcontainer.container.handler.source.HandleSourceSystem;
import com.aimercet.advcontainer.container.source.SourceSystem;
import com.aimercet.brlib.log.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ContainerManager
{
    public static ContainerManager instance;


    private final List<IContainer> containerList = new ArrayList<>();
    private final HashMap<String, IContainer> containerMap = new HashMap<>();

    public SourceSystem sourceSystem;
    public InventoryHandlerGeneral handlerGeneral;
    public HandleSourceSystem handleSourceSystem;

    public ContainerManager()
    {
        instance = this;

        handleSourceSystem = new HandleSourceSystem();
        sourceSystem = new SourceSystem();
        handlerGeneral = new InventoryHandlerGeneral();

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

        Logger.info("注册容器: "+container);
        onRegister(container);
        return true;
    }
    public boolean unRegister(Container container)
    {
        if(container==null)return false;
        IContainer v = containerMap.remove(container.getUUID());
        containerList.remove(v);

        if(v!=null)onUnRegister(v);
        return v!=null;
    }
    public boolean unRegister(String uuid)
    {
        IContainer container = containerMap.get(uuid);
        containerMap.remove(uuid);
        containerList.remove(container);

        if(container!=null)onUnRegister(container);
        return container!=null;
    }
    public boolean unRegister(int index)
    {
        if(index>=containerList.size()||index<0)return false;
        IContainer container = containerList.get(index);
        containerList.remove(index);
        if(container!=null)containerMap.remove(container.getUUID());

        if(container!=null)onUnRegister(container);
        return container!=null;
    }

    public HashMap<String, IContainer> getContainerMap()    {return containerMap;}
    public IContainer get(String uuid)                      {return containerMap.get(uuid);}
    public IContainer get(int index)                        {return containerMap.get(index);}
}
