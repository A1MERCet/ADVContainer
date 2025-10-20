package com.aimercet.advcontainer.container.handler;

import com.aimercet.advcontainer.container.IContainer;

public class ContainerHandlerGeneral implements IContainerHandler {

    private final String id;
    private IContainer inventory;

    public ContainerHandlerGeneral()
    {
        this.id = "general";
    }


    @Override
    public String getHandlerID() {return id;}
}
