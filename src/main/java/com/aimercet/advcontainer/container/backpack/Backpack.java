package com.aimercet.advcontainer.container.backpack;

import com.aimercet.advcontainer.container.ContainerFactory;
import com.aimercet.advcontainer.container.IStock;
import com.aimercet.advcontainer.container.backpack.equipment.ContainerEquip;
import com.aimercet.advcontainer.container.backpack.equipment.EquipType;
import com.aimercet.advcontainer.container.slotitem.ISlotItem;
import com.aimercet.advcontainer.container.source.ISource;
import com.aimercet.advcontainer.item.ItemType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Backpack
{
    public final ISource inventorySource;
    public final List<EquipType> allowedEquipSlots = new ArrayList<>();
    public final HashMap<EquipType,List<ItemType>> allowedTypes = new HashMap<>();
    private ContainerEquip containerEquip;

    public Backpack(ISource inventorySource)
    {
        this.inventorySource = inventorySource;
        allowedEquipSlots.addAll(EquipType.values);
        containerEquip = (ContainerEquip) ContainerFactory.Create("equip",inventorySource.getSourceID()+"_backpack");
        containerEquip.setInventorySource(inventorySource);
    }

    public boolean equip(ISlotItem slotItem)
    {
        return false;
    }

    public boolean unEquip(ISlotItem slotItem)
    {
        return false;
    }
}
