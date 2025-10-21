package com.aimercet.advcontainer.loot.trigger;

public interface ILootTrigger
{
    String getLootTriggerID();
    String getLootTriggerLang();
    default float getLootRateMulti(){return 1F;}
}
