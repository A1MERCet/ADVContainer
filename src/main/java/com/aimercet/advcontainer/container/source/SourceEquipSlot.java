package com.aimercet.advcontainer.container.source;


import com.aimercet.advcontainer.container.backpack.equipment.SlotEquip;

public class SourceEquipSlot extends SourceSlotItem
{
    public final SlotEquip slot;

    public SourceEquipSlot(SlotEquip slot)
    {
        super(slot.item);
        this.slot = slot;
    }
}
