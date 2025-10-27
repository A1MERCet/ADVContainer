package com.aimercet.advcontainer.container.backpack.equipment.callback;

import com.aimercet.advcontainer.container.backpack.equipment.SlotEquip;
import com.aimercet.advcontainer.event.container.ActionStage;

import java.util.ArrayList;

public class EquipCallbackList extends ArrayList<EquipCallback>
{
    public void onEquip(SlotEquip slot, ActionStage stage)
    {
        for (EquipCallback callback : this)
            callback.onEquip(slot,stage);
    }
    public void onUnEquip(SlotEquip slot, ActionStage stage)
    {
        for (EquipCallback callback : this)
            callback.onUnEquip(slot,stage);
    }
}
