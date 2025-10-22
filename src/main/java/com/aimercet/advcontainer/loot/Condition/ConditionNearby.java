package com.aimercet.advcontainer.loot.Condition;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConditionNearby implements ISpawnCondition
{
    public enum Type
    {
        PLAYER,ENTITY
    }

    private Type type;
    private Location location;
    private double distance;

    public ConditionNearby()
    {
    }

    @Override
    public boolean check()
    {
        switch (type){
            case PLAYER:{
                AtomicBoolean v = new AtomicBoolean(false);
                location.getWorld().getNearbyEntities(location,distance,distance,distance).forEach((entity)->{
                    if(entity instanceof Player)
                    {
                        v.set(true);
                        return;
                    }
                });
                return v.get();
            }
            case ENTITY:{
                Collection<Entity> entities = location.getWorld().getNearbyEntities(location, distance, distance, distance);
                return !entities.isEmpty();
            }
        }
        return false;
    }

    public ConditionNearby set(Type type,Location location,double dist) {setType(type);setLocation(location);setDistance(dist);return this;}
    public Location getLocation()                               {return location;}
    public ConditionNearby setLocation(Location location)       {this.location = location;return this;}
    public double getDistance() {return distance;}
    public ConditionNearby setDistance(double distance)         {this.distance = distance;return this;}
    public Type getType()                                       {return type;}
    public ConditionNearby setType(Type type)                   {this.type = type;return this;}
}
