package com.aimercet.advcontainer.event.container;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ContainerEvent extends Event implements Cancellable
{
    private static final HandlerList handlers = new HandlerList();
    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override public HandlerList getHandlers() {
        return handlers;
    }


    private boolean cancelled = false;
    @Override public boolean isCancelled() {
        return cancelled;
    }
    @Override public void setCancelled(boolean b) {
        cancelled=b;
    }
}
