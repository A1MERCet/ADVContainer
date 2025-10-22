package com.aimercet.advcontainer.loot.target;

import org.bukkit.entity.Entity;

public class TargetEntity implements ILootBindTarget
{
    private Entity entity;

    public TargetEntity(Entity entity)
    {
        this.entity = entity;
    }

    public Entity getEntity()                       {return entity;}
    public TargetEntity setEntity(Entity entity)    {this.entity = entity;return this;}
    @Override public String getBindTargetID()       {return entity==null?"none":entity.getType().name();}
    @Override public String getBindTargetLang()     {return getBindTargetID();}
    @Override public boolean isAvailable()          {return entity !=null;}
}
