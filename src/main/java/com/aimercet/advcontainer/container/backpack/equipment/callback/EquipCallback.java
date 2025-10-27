package com.aimercet.advcontainer.container.backpack.equipment.callback;


import com.aimercet.advcontainer.container.backpack.equipment.SlotEquip;
import com.aimercet.advcontainer.event.container.ActionStage;

public abstract class EquipCallback
{
    abstract void onEquip(SlotEquip slot, ActionStage stage);
    abstract void onUnEquip(SlotEquip slot, ActionStage stage);
}
