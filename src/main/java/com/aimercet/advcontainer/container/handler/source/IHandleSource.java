package com.aimercet.advcontainer.container.handler.source;

import com.aimercet.advcontainer.container.handler.HandleResult;

public interface IHandleSource
{
    String getHandlerName();
    InventoryHandleHistory getInventoryHandleHistory();
    void AddHistory(HandleResult result);

}
