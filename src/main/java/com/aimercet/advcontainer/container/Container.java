package com.aimercet.advcontainer.container;

import com.aimercet.advcontainer.api.gui.GPartStyle;
import com.aimercet.advcontainer.container.handler.IContainerHandler;
import com.aimercet.advcontainer.container.handler.source.IHandleSource;
import com.aimercet.advcontainer.container.handler.source.InventoryHandleHistory;
import com.aimercet.advcontainer.container.source.ISource;
import com.aimercet.brlib.player.PlayerState;

import java.util.ArrayList;
import java.util.List;

public class Container implements IContainer
{
    public static final String CLASS_NAME = "default";

    private ISource source;
    public final String uuid;
    public final InventoryHandleHistory handleHistory = new InventoryHandleHistory();

    public String defaultHandler = ContainerManager.instance.handlerGeneral.getHandlerID();
    public IContainerHandler handler = ContainerManager.instance.handlerGeneral;

    private final List<IStock> stockList = new ArrayList<>();
    private final List<IHandleSource> users = new ArrayList<>();

    private GPartStyle guiStyle = new GPartStyle();

    public Container(String uuid)
    {
        this.uuid = uuid;
        ContainerManager.instance.register(this);
    }

    @Override public IContainerHandler getDefaultHandler()                  {return ContainerManager.getHandler(defaultHandler);}
    @Override public IContainerHandler getHandler()                         {return handler==null?getDefaultHandler():handler;}
    @Override public void setHandler(IContainerHandler handler)             {this.handler = handler;}
    @Override public List<IStock> getStockList()                            {return stockList;}
    @Override public ISource getInventorySource()                           {return source;}
    @Override public GPartStyle getGUIStyle() {return guiStyle;}
    public Container setGuiStyle(GPartStyle guiStyle) {this.guiStyle = guiStyle;return this;}
    @Override public IContainer setInventorySource(ISource source)          {this.source = source;return IContainer.super.setInventorySource(source);}
    @Override public InventoryHandleHistory getInventoryHandleHistory()     {return handleHistory;}

    @Override public String getUUID()                                       {return uuid;}
    @Override public String getClassName()                                  {return CLASS_NAME;}
    @Override public boolean handlerMissing()                               {return this.handler==null;}
    @Override public List<IHandleSource> getUsers()                         {return users;}

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        stockList.forEach(s -> b.append(s.toString()).append(" "));
        return getClass().getSimpleName()+"[UUID="+uuid+", "+source+", Stock[Count="+stockList.size()+"]="+b.toString()+"]";
    }
}
