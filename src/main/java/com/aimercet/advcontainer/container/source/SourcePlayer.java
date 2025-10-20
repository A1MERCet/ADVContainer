package com.aimercet.advcontainer.container.source;

public class SourcePlayer implements IContainerSource
{
    public final String inventorySourceID;
    public final String inventorySourceLang;

    public SourcePlayer()
    {
        this.inventorySourceID = "system";
        this.inventorySourceLang = "container.source.system";
    }

    @Override public String getInventorySourceID() {return inventorySourceID;}
    @Override public String getInventorySourceLang() {return inventorySourceLang;}

    @Override
    public String toString() {
        return "SourceSystem[ID="+inventorySourceID+"]";
    }
}
