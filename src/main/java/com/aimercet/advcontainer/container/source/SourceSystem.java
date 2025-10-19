package com.aimercet.advcontainer.container.source;

public class SourceSystem implements IInventorySource
{
    public final String inventorySourceID;
    public final String inventorySourceLang;

    public SourceSystem()
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

