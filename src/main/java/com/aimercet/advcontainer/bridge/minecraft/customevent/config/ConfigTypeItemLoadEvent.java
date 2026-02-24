package com.aimercet.advcontainer.bridge.minecraft.customevent.config;

import com.aimercet.advcontainer.item.item.TypeItem;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ConfigTypeItemLoadEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    public static HandlerList getHandlerList() {return handlers;}

    public final TypeItem typeItem;

    public ConfigTypeItemLoadEvent(TypeItem typeItem)
    {
        this.typeItem = typeItem;
    }

    @Override public HandlerList getHandlers() {return handlers;}
}
