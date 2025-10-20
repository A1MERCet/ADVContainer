package com.aimercet.advcontainer.bridge.brlib;

import com.aimercet.advcontainer.container.source.IContainerSource;
import com.aimercet.brlib.localization.Localization;
import org.bukkit.entity.Entity;

public class ContainerSourceEntity implements IContainerSource
{
    public final Entity entity;
    public final String inventorySourceID;
    public final String inventorySourceLang;

    public ContainerSourceEntity(Entity entity)
    {
        this.entity = entity;
        this.inventorySourceID = "entity."+entity.getName();
        String lang = "container.source."+entity.getName();
        this.inventorySourceLang = Localization.has(lang)?lang:Localization.register(lang,entity.getName());
    }

    @Override public String getInventorySourceID() {return inventorySourceID;}
    @Override public String getInventorySourceLang() {return inventorySourceLang;}

    @Override public String toString() {return getClass().getSimpleName()+"[ID="+inventorySourceID+"]";}
}
