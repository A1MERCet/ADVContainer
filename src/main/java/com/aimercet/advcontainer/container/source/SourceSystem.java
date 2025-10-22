package com.aimercet.advcontainer.container.source;

import com.aimercet.brlib.localization.Localization;

public class SourceSystem implements ISource
{
    public final String sourceID;
    public final String sourceLang;

    public SourceSystem()
    {
        this.sourceID = "system";
        this.sourceLang = Localization.register("container.source.system","系统");
    }

    @Override public String getSourceID() {return sourceID;}
    @Override public String getSourceLang() {return sourceLang;}

    @Override public String toString() {return getClass().getSimpleName()+"[ID="+ sourceID +"]";}
}

