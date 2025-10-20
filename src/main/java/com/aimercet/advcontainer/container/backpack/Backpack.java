package com.aimercet.advcontainer.container.backpack;

import com.aimercet.advcontainer.container.ContainerFactory;
import com.aimercet.advcontainer.container.backpack.equipment.ContainerEquip;
import com.aimercet.advcontainer.container.backpack.equipment.EquipType;
import com.aimercet.advcontainer.container.source.IContainerSource;

import java.util.ArrayList;
import java.util.List;

public class Backpack
{
    public final IContainerSource inventorySource;
    public final List<EquipType> allowEquipSlots = new ArrayList<>();
    private ContainerEquip containerEquip;

    public Backpack(IContainerSource inventorySource)
    {
        this.inventorySource = inventorySource;
        allowEquipSlots.addAll(EquipType.values);
        containerEquip = (ContainerEquip) ContainerFactory.Create("equip",inventorySource.getInventorySourceID()+"_backpack");
        containerEquip.setInventorySource(inventorySource);
    }
}
