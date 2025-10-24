package com.aimercet.advcontainer.container.handler.source;

import com.aimercet.advcontainer.container.ContainerIndex;
import com.aimercet.advcontainer.container.ContainerManager;
import com.aimercet.advcontainer.container.IContainer;
import com.aimercet.advcontainer.container.handler.HandleResult;

public interface IHandleSource
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
    }
    default void setContainerAllow(String uuid)
    {
        IContainer c = ContainerManager.instance.get(uuid);
        if(c==null) return;
        getAllowedContainers().put(uuid, c);
    }
    default void setContainerDeny(String uuid)
    {
        getAllowedContainers().remove(uuid);
    }

    default boolean canHandle(IContainer container){return getAllowedContainers().containsValue(container);}
}
