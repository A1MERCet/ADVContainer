package com.aimercet.advcontainer.container.source;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class SourceEntity implements ISource , ISourceLocation
{
    public final Entity entity;

    public SourceEntity(Entity entity)
    {
        this.entity = entity;
    }

    @Override public String getSourceID() {return entity.getName();}
    @Override public String getSourceLang() {return "entity."+entity.getName();}

    @Override public String toString() {return getClass().getSimpleName()+"[ID="+ entity.getName() +"]";}
    @Override public Location getSourceLocation() {return entity ==null?null: entity.getLocation();}
    public Entity getEntity() {return entity;}
}
