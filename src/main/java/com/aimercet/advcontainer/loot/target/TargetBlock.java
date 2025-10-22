package com.aimercet.advcontainer.loot.target;

import org.bukkit.block.Block;

public class TargetBlock implements ILootBindTarget
{
    private Block  block;

    public TargetBlock(Block block)
    {
        this.block = block;
    }

    public Block getBlock()                     {return block;}
    public TargetBlock setBlock(Block block)    {this.block = block;return this;}

    @Override public String getBindTargetID()   {return block==null?"none":block.getType().name();}
    @Override public String getBindTargetLang() {return "block_"+getBindTargetID();}

    @Override public boolean isAvailable()      {return block!=null;}
}
