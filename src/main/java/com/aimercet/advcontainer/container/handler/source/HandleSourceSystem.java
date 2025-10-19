package com.aimercet.advcontainer.container.handler.source;

import com.aimercet.advcontainer.container.handler.HandleResult;

public class HandleSourceSystem implements IHandleSource
{
    public final InventoryHandleHistory history;

    public HandleSourceSystem()
    {
        this.history = new InventoryHandleHistory();
    }

    @Override public String getHandlerName() {return "system";}
    @Override public InventoryHandleHistory getInventoryHandleHistory() {return history;}
    @Override public void AddHistory(HandleResult result) {this.history.add(result);}
}
