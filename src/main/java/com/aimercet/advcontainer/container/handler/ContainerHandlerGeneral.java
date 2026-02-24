package com.aimercet.advcontainer.container.handler;

public class ContainerHandlerGeneral implements IContainerHandler {

    private final String id;

    public ContainerHandlerGeneral()
    {
        this.id = "general";
    }


    @Override
    public String getHandlerID() {return id;}
}
