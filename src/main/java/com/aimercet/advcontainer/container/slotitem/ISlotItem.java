package com.aimercet.advcontainer.container.slotitem;

import com.aimercet.advcontainer.container.ContainerManager;
import com.aimercet.advcontainer.container.backpack.equipment.SlotEquip;
import com.aimercet.advcontainer.container.handler.PlaceResult;
import com.aimercet.advcontainer.container.source.SourceEquipSlot;
import com.aimercet.advcontainer.item.item.TypeItem;
import com.aimercet.advcontainer.util.SizeInt;
import org.bukkit.configuration.ConfigurationSection;

public interface ISlotItem
{
    String getClassName();
    long getAmount();
    TypeItem getTypeItem();
    String getSlotItemID();
    String getSlotItemLang();
    SizeInt getSize();
    String getContainerUUID();

    default void onEquip(SlotEquip slot) {
        String containerUUID = getContainerUUID();
        if(containerUUID != null) {
            slot.containerEquip = ContainerManager.instance.loadContainer(this);
            if(slot.containerEquip != null) slot.containerEquip.setInventorySource(new SourceEquipSlot(slot));
        }
    }
    default void onUnEquip(SlotEquip slot) {
        if(slot.containerEquip != null) slot.containerEquip.setInventorySource(null);
        slot.containerEquip = null;
    }

    void load(ConfigurationSection section);
    void save(ConfigurationSection section);
}