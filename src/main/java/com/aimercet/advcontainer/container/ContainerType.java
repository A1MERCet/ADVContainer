package com.aimercet.advcontainer.container;

import com.aimercet.brlib.localization.Localization;

public enum ContainerType
{
    GENERAL(Localization.register("container_type_general")),
    SHOWCASE(Localization.register("container_type_general")),
    ;

    public final String lang;

    ContainerType(String lang)
    {
        this.lang = lang;
    }
}
