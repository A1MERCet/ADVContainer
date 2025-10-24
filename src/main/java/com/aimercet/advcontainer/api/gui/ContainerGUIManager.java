package com.aimercet.advcontainer.api.gui;

import com.aimercet.advcontainer.api.gui.container.IPartContainer;

public class ContainerGUIManager
{
    public static ContainerGUIManager instance;

    private IContainerGUIFactory guiFactory;

    public ContainerGUIManager()
    {
        instance = this;
    }

    public IContainerGUIFactory getGuiFactory() {return guiFactory;}
    public ContainerGUIManager setGuiFactory(IContainerGUIFactory guiFactory) {this.guiFactory = guiFactory;return this;}
}
