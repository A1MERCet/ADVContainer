package com.aimercet.advcontainer.container.source;

import com.aimercet.advcontainer.loot.ILootState;

public class SourceLoot implements ISource
{
    public final ILootState lootState;

    public SourceLoot(ILootState lootState)
    {
        this.lootState = lootState;
    }

    @Override public String getSourceID()      {return lootState.getID();}
    @Override public String getSourceLang()    {return lootState.getLoot().getLootLang();}
    @Override public String toString() {return getClass().getSimpleName()+"[ID="+ lootState.getLoot().getLootID()+"]";}
}
