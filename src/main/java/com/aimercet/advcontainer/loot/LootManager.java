package com.aimercet.advcontainer.loot;

import com.aimercet.advcontainer.loot.trigger.LootTriggerSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LootManager
{
    public static LootManager instance;

    public final LootTriggerSystem triggerSystem;

    public final List<ILootState> lootStates = new ArrayList<>();
    public final HashMap<String,ILootState> lootStateMap = new HashMap<>();

    public LootManager()
    {
        instance = this;

        triggerSystem = new LootTriggerSystem();
    }

    public ILootState getLootState(String id) {return lootStateMap.get(id);}

    public boolean registerLootState(ILootState state)
    {
        if(state==null || lootStateMap.containsKey(state.getID()))return false;
        lootStates.add(state);
        lootStateMap.put(state.getID(),state);
        return true;
    }

    public ILootState unRegisterLootState(String id){return unRegisterLootState(lootStateMap.get(id));}
    public ILootState unRegisterLootState(ILootState state)
    {
        if(state==null)return null;
        ILootState lootState = lootStateMap.get(state.getID());
        if(lootState!=null)
        {
            lootStates.remove(lootState);
            lootStateMap.remove(state.getID());
        }
        return lootState;
    }
}
