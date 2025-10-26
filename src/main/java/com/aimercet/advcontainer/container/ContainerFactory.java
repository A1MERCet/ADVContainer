package com.aimercet.advcontainer.container;

import com.aimercet.advcontainer.container.backpack.equipment.ContainerEquip;
import com.aimercet.brlib.log.Logger;
import com.aimercet.brlib.util.MapBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ContainerFactory
{
    public static HashMap<String,Class<? extends IContainer>> loadClasses = new MapBuilder<String,Class<? extends IContainer>>()
            .put(Container.CLASS_NAME,Container.class)
            .put(ContainerEquip.CLASS_NAME,ContainerEquip.class)
            .map;

    public static ContainerFactory instance;
    private static List<ContainerFactory> factoryList = new ArrayList<>();
    public static List<ContainerFactory> getFactoryList() {return factoryList;}
    public static ContainerFactory register(ContainerFactory f){if(!factoryList.contains(f))factoryList.add(f); return f;}

    public static IContainer Create(String clzName ,  String uuid)
    {
        for (ContainerFactory f : factoryList) {
            IContainer c = f.create(clzName, uuid);
            if(c!=null) return c;
        }
        Logger.warn("没有在注册表中找到类型为["+clzName+"]的容器");
        return null;
    }


    public ContainerFactory()
    {

    }

    protected IContainer create(String clzName , String uuid)
    {
        Class<? extends IContainer> clz = loadClasses.get(clzName);

        switch (clzName){
            case Container.CLASS_NAME:   return new Container(uuid);
            case ContainerEquip.CLASS_NAME: return new ContainerEquip(uuid);
        }
        return null;
    }
}
