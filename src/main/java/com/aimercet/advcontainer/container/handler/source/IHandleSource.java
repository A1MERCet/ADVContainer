package com.aimercet.advcontainer.container.handler.source;

import com.aimercet.advcontainer.container.ContainerIndex;
import com.aimercet.advcontainer.container.ContainerManager;
import com.aimercet.advcontainer.container.IContainer;
import com.aimercet.advcontainer.container.handler.HandleResult;
import com.aimercet.advcontainer.container.source.ISource;

public interface IHandleSource extends ISource
{
    String getHandlerName();
    InventoryHandleHistory getInventoryHandleHistory();
    default void addHistory(HandleResult result)
    {
        getInventoryHandleHistory().add(result);
    }

    ContainerIndex<String> getAllowedContainers();
    default void setContainerAllow(IContainer c)
    {
        if(c==null) return;
        getAllowedContainers().put(c.getUUID(), c);
        if(!c.getUsers().contains(this)) c.getUsers().add(this);
    }
    default void setContainerDeny(IContainer c)
    {
        if(c==null) return;
        getAllowedContainers().remove(c.getUUID());
        c.getUsers().remove(this);
    }
    default void setContainerDeny(String c)
    {
        IContainer container = ContainerManager.instance.get(c);
        getAllowedContainers().remove(c);
        if(container!=null) container.getUsers().remove(this);
    }

    default boolean canHandle(IContainer container){return getAllowedContainers().containsValue(container);}

    @Override default String getSourceID(){return getHandlerName();}
    @Override default String getSourceLang(){return getHandlerName();}
}
