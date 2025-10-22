package com.aimercet.advcontainer.container.source;

import org.bukkit.Location;
import org.bukkit.block.Block;

public class SourceBlock extends SourceLocation
{
    public final Block block;

    public SourceBlock(Block block)
    {
        super(block.getLocation());
        this.block = block;
    }

    @Override public String getSourceID() {return block.getType().name();}
    @Override public String getSourceLang() {return "block_"+getSourceID();}
    @Override public String toString() {return getClass().getSimpleName()+"[ID="+getSourceID()+"]";}
    @Override public Location getSourceLocation() {return block==null?super.getSourceLocation():block.getLocation();}
}
