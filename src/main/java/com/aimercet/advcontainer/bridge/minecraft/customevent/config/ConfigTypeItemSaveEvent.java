package com.aimercet.advcontainer.bridge.minecraft.customevent.config;

import com.aimercet.advcontainer.item.item.TypeItem;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ConfigTypeItemSaveEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    public static HandlerList getHandlerList() {return handlers;}

    public final TypeItem typeItem;

    public ConfigTypeItemSaveEvent(TypeItem typeItem)
    {
        this.typeItem = typeItem;
    }

    @Override public HandlerList getHandlers() {return handlers;}
}
