package com.aimercet.advcontainer.container.source;

import org.bukkit.Location;
import org.bukkit.block.Block;

public class SourceLocation implements ISource , ISourceLocation
{
    public final Location location;

    public SourceLocation(Location location)
    {
        this.location = location;
    }

    @Override public String getSourceID() {return "["+location.getX()+","+location.getY()+","+location.getZ()+"]";}
    @Override public String getSourceLang() {return getSourceID();}
    @Override public String toString() {return getClass().getSimpleName()+"[ID="+getSourceID()+"]";}
    @Override public Location getSourceLocation() {return location;}
}
