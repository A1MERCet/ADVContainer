package com.aimercet.advcontainer.bridge.minecraft.container;

import com.aimercet.advcontainer.container.handler.IContainerHandler;

public class ContainerHandlerVanilla implements IContainerHandler {

    private final String id;

    public ContainerHandlerVanilla()
    {
        this.id = "vanilla";
    }


    @Override
    public String getHandlerID() {return id;}
}
