package com.aimercet.advcontainer.loot.trigger;

import com.aimercet.brlib.localization.Localization;

public class LootTriggerSystem implements ILootTrigger
{
    @Override public String getLootTriggerID()          {return "system";}
    @Override public String getLootTriggerLang()        {return Localization.register("loot.trigger.system","system");}
}
