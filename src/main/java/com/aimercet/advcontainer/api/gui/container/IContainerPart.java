package com.aimercet.advcontainer.api.gui.container;

import com.aimercet.advcontainer.container.IContainer;
import com.aimercet.brlib.player.PlayerState;

public interface IContainerPart
{
    String getPartStyle();
    IContainer getContainer();
    PlayerState getPlayerState();
}
