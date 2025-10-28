package com.aimercet.advcontainer.container.backpack;

import com.aimercet.advcontainer.container.*;
import com.aimercet.advcontainer.container.backpack.equipment.ContainerEquip;
import com.aimercet.advcontainer.container.backpack.equipment.EquipType;
import com.aimercet.advcontainer.container.backpack.equipment.SlotEquip;
import com.aimercet.advcontainer.container.handler.SlotSource;
import com.aimercet.advcontainer.container.handler.PlaceResult;
import com.aimercet.advcontainer.container.handler.source.IHandleSource;
import com.aimercet.advcontainer.container.slotitem.ISlotItem;
import com.aimercet.advcontainer.container.source.ISource;
import com.aimercet.advcontainer.item.ItemType;
import com.aimercet.advcontainer.player.modules.ModuleBackpack;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Backpack
{
    public static Backpack get(Player player)
    {
        ModuleBackpack module = ModuleBackpack.get(player);
        return module==null?null:module.backpack;
    }

    public final ISource inventorySource;
    public final List<EquipType> allowedEquipSlots = new ArrayList<>();
    public final HashMap<EquipType,List<ItemType>> allowedTypes = new HashMap<>();
    private ContainerEquip container;

    public Backpack(ISource inventorySource)
    {
        this.inventorySource = inventorySource;

        allowedEquipSlots.addAll(EquipType.values());

        container = (ContainerEquip) ContainerFactory.Create(ContainerEquip.CLASS_NAME,inventorySource.getSourceID()+"_backpack");
        container.setInventorySource(inventorySource);
        container.setEquipSizeSingal(allowedEquipSlots.size(), allowedEquipSlots);
        container.initContainer();
        ContainerManager.instance.register(container);
    }

    public IHandleSource getHandler()
    {
        if(inventorySource instanceof IHandleSource) return (IHandleSource)inventorySource;
        return null;
    }

    public PlaceResult equip(ISlotItem slotItem) {return equip(getHandler(),slotItem);}
    public PlaceResult equip(IHandleSource handler , ISlotItem slotItem) {return container.getHandler().place(handler, slotItem, container);}

    public PlaceResult addItem(ISlotItem slotItem){return addItem(getHandler(),slotItem);}
    public PlaceResult addItem(IHandleSource handler , ISlotItem slotItem)
    {
        for (IContainer container : getEquipContainers())
        {
            PlaceResult result = container.getHandler().place(handler, slotItem, container);
            if(result.type == PlaceResult.Type.SUCCESS)
                return result;
        }
        return new PlaceResult(handler,slotItem, PlaceResult.Type.REFUSE);
    }

    public List<IContainer> getEquipContainers()
    {
        List<IContainer> list = new ArrayList<>();
        for (SlotSource item : container.getItems()) {
            ISlot slot = item.get();
            if(slot instanceof SlotEquip) {
                IContainer container = ((SlotEquip) slot).getContainerEquip();
                if(container != null) list.add(container);
            }
        }
        return list;
    }


    public ISource getInventorySource() {return inventorySource;}
    public List<EquipType> getAllowedEquipSlots() {return allowedEquipSlots;}
    public HashMap<EquipType, List<ItemType>> getAllowedTypes() {return allowedTypes;}
    public ContainerEquip getContainerEquip() {return container;}
}
