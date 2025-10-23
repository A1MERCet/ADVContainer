package com.aimercet.advcontainer.container.handler.source;

import com.aimercet.advcontainer.container.ContainerIndex;
import com.aimercet.advcontainer.container.IContainer;
import com.aimercet.advcontainer.container.handler.HandleResult;
import com.aimercet.brlib.log.Logger;

public class HandleSourceSystem implements IHandleSource
{
    public final InventoryHandleHistory history;
    public final ContainerIndex<String> allowedContainers = new ContainerIndex<String>();

    public HandleSourceSystem()
    {
        this.history = new InventoryHandleHistory();
    }

    @Override public String getHandlerName() {return "system";}
    @Override public InventoryHandleHistory getInventoryHandleHistory() {return history;}
    @Override public void addHistory(HandleResult result) {
        this.history.add(result);
        getAllowedContainers().clear();
    }
    @Override public ContainerIndex<String> getAllowedContainers() {return allowedContainers;}
    @Override public boolean canHandle(IContainer container) {
        if(container==null){return false;}
        getAllowedContainers().put(container.getUUID(),container);
        return true;
    }
}
