package com.aimercet.advcontainer.container.handler.source;

import com.aimercet.advcontainer.container.ContainerIndex;
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
    default boolean canHandle(IContainer container){return getAllowedContainers().containsValue(container);}
}
