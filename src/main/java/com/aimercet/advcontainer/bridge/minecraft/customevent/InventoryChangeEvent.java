package com.aimercet.advcontainer.bridge.minecraft.customevent;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryChangeEvent extends Event implements Cancellable
{
    private static final HandlerList handlers = new HandlerList();
    public static HandlerList getHandlerList() {return handlers;}

    public final Inventory inventory;
    public final int index;
    public final ItemStack source;
    public final ItemStack target;

    public InventoryChangeEvent(Inventory inventory, int index, ItemStack source, ItemStack target)
    {
        this.inventory = inventory;
        this.index = index;
        this.source = source;
        this.target = target;
    }

    @Override public HandlerList getHandlers() {return handlers;}
    private boolean cancelled = false;
    @Override public boolean isCancelled() {return cancelled;}
    @Override public void setCancelled(boolean b) {cancelled=b;}
}
