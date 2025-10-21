package com.aimercet.advcontainer.loot;

import com.aimercet.advcontainer.loot.trigger.LootTriggerSystem;

public class LootManager
{
    public static LootManager instance;

    public LootTriggerSystem triggerSystem;

    public LootManager()
    {
        instance = new LootManager();

        triggerSystem = new LootTriggerSystem();
    }
}
