package com.aimercet.advcontainer.container;

import com.aimercet.advcontainer.container.handler.IInventoryHandler;
import com.aimercet.advcontainer.container.handler.source.InventoryHandleHistory;
import com.aimercet.advcontainer.container.source.IInventorySource;

import java.util.ArrayList;
import java.util.List;

public class Container implements IContainer
{
    public final IInventorySource source;
    public final String uuid;
    public final InventoryHandleHistory handleHistory = new InventoryHandleHistory();
    public IInventoryHandler handler;

    private final List<IStock> stockList = new ArrayList<>();

    public Container(IInventorySource source , IInventoryHandler handler, String uuid)
    {
        this.source = source;
        this.handler = handler;
        this.uuid = uuid;
        ContainerManager.instance.register(this);
    }

    @Override public IInventoryHandler getHandler()                     {return handler;}
    @Override public void setHandler(IInventoryHandler handler)         {this.handler = handler;}
    @Override public List<IStock> getStockList()                        {return stockList;}
    @Override public IInventorySource getInventorySource()              {return source;}
    @Override public InventoryHandleHistory getInventoryHandleHistory() {return handleHistory;}

    @Override public String getUUID()                           {return uuid;}

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        stockList.forEach(s -> b.append(s.toString()).append(" "));
        return getClass().getSimpleName()+"[UUID="+uuid+", "+source+", Stock[Count="+stockList.size()+"]="+b+"]";
    }
}
