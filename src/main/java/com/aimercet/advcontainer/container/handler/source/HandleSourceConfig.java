package com.aimercet.advcontainer.container.handler.source;

import com.aimercet.advcontainer.container.handler.HandleResult;

public class HandleSourceConfig implements IHandleSource
{
    public final InventoryHandleHistory history;

    public HandleSourceConfig()
    {
        this.history = new InventoryHandleHistory();
    }

    @Override public String getHandlerName() {return "config";}
    @Override public InventoryHandleHistory getInventoryHandleHistory() {return history;}
    @Override public void AddHistory(HandleResult result) {this.history.add(result);}
}
