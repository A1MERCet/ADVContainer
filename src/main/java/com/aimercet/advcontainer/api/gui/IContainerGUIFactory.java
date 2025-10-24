package com.aimercet.advcontainer.api.gui;

import com.aimercet.advcontainer.api.gui.container.IPartContainer;
import com.aimercet.advcontainer.api.gui.container.IPartSlot;
import com.aimercet.advcontainer.api.gui.container.IPartStock;
import com.aimercet.advcontainer.container.IContainer;
import com.aimercet.advcontainer.container.ISlot;
import com.aimercet.advcontainer.container.IStock;

public interface IContainerGUIFactory
{
    IPartSlot createSlot(ISlot slot);
    IPartStock createStock(IStock source);
    IPartContainer createContainer(IContainer container);
}
