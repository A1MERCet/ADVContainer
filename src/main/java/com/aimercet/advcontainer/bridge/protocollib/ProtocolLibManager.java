package com.aimercet.advcontainer.bridge.protocollib;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;

public class ProtocolLibManager
{
    public static ProtocolLibManager instance;
    public final ProtocolManager pm;

    public ProtocolLibManager()
    {
        instance = this;
        pm = ProtocolLibrary.getProtocolManager();;

        init();
    }

    private ProtocolLibManager init()
    {


        return this;
    }
}
