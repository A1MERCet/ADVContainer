package com.aimercet.advcontainer.container;

import com.aimercet.advcontainer.container.handler.IInventoryHandler;
import com.aimercet.advcontainer.container.source.IInventorySource;
import com.aimercet.brlib.util.MapBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ContainerFactory
{
    public static HashMap<String,Class<? extends IContainer>> loadClasses = new MapBuilder<String,Class<? extends IContainer>>()
            .put("default",Container.class)
            .map;

    public static ContainerFactory instance;
    private static List<ContainerFactory> factoryList = new ArrayList<>();
    public static List<ContainerFactory> getFactoryList() {return factoryList;}
    public static ContainerFactory register(ContainerFactory f){if(!factoryList.contains(f))factoryList.add(f); return f;}

    public static IContainer Create(String clzName , IInventorySource source , IInventoryHandler handler, String uuid)
    {
        for (ContainerFactory f : factoryList) {
            IContainer c = f.create(clzName, source, handler, uuid);
            if(c!=null) return c;
        }
        return null;
    }


    public ContainerFactory()
    {

    }

    protected IContainer create(String clzName , IInventorySource source , IInventoryHandler handler, String uuid)
    {
        Class<? extends IContainer> clz = loadClasses.get(clzName);

        if(clz == Container.class){
            return new Container(source, handler, uuid);
        }
        return null;
    }
}
