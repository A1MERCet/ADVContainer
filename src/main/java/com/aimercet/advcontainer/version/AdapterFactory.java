package com.aimercet.advcontainer.version;

import com.aimercet.advcontainer.version.versions.v1_12.AdapterV1_12;
import com.aimercet.advcontainer.version.versions.v1_20.AdapterV1_20;

public class AdapterFactory
{
    private VersionAdapter adapter;

    public AdapterFactory(String version)
    {
        init(version);
    }

    private void init(String version)
    {
        adapter = getAdapter(version);
    }

    public VersionAdapter getAdapter(String version) {

        VersionAdapter adapter = null;

        int ver = Integer.parseInt(version.split("_")[1]);

        if (ver >= 20)    adapter = new AdapterV1_20();
        else              adapter = new AdapterV1_12();

        return adapter;
    }


    public VersionAdapter getAdapter() {return adapter;}
}
