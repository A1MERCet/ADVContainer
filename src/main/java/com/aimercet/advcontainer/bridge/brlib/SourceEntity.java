package com.aimercet.advcontainer.bridge.brlib;

import com.aimercet.advcontainer.container.source.ISource;
import com.aimercet.brlib.localization.Localization;
import org.bukkit.entity.Entity;

public class SourceEntity implements ISource
{
    public final Entity entity;
    public final String inventorySourceID;
    public final String inventorySourceLang;

    public SourceEntity(Entity entity)
    {
        this.entity = entity;
        this.inventorySourceID = "entity_"+entity.getName();
        this.inventorySourceLang = Localization.has(inventorySourceID)?inventorySourceID:Localization.register(inventorySourceID,entity.getName());
    }

    @Override public String getSourceID() {return inventorySourceID;}
    @Override public String getSourceLang() {return inventorySourceLang;}

    @Override public String toString() {return getClass().getSimpleName()+"[ID="+inventorySourceID+"]";}
}
