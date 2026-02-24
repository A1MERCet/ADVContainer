package com.aimercet.advcontainer.container.backpack.equipment;

import com.aimercet.advcontainer.container.IContainer;
import com.aimercet.advcontainer.container.ISlot;
import com.aimercet.advcontainer.container.handler.IContainerHandler;
import com.aimercet.advcontainer.container.handler.PlaceResult;
import com.aimercet.advcontainer.container.handler.RemoveResult;
import com.aimercet.advcontainer.container.slotitem.ISlotItem;
import com.aimercet.advcontainer.util.SizeInt;

public class ContainerHandlerEquipment implements IContainerHandler {

    private final String id;
    private IContainer inventory;

    public ContainerHandlerEquipment()
    {
        this.id = "equipment";
    }

    @Override public SizeInt getItemSize(ISlotItem item, boolean rotate) {return new SizeInt(1,1);}

    @Override
    public void onPlace(PlaceResult result)
    {
        IContainerHandler.super.onPlace(result);

        ISlot slot = result.coord.get();
        SlotEquip slotEquip = slot instanceof SlotEquip ? (SlotEquip)slot : null;
        ISlotItem item = result.coord.getItem();
        if(result.type == PlaceResult.Type.SUCCESS && item !=null && slotEquip!=null)
            item.onEquip(slotEquip);
    }

    @Override
    public void onRemove(RemoveResult result)
    {
        IContainerHandler.super.onRemove(result);

        ISlot slot = result.coord.get();
        SlotEquip slotEquip = slot instanceof SlotEquip ? (SlotEquip)slot : null;
        if(result.type == PlaceResult.Type.SUCCESS && slotEquip!=null)
            result.item.onUnEquip(slotEquip);
    }

    @Override public String getHandlerID() {return id;}
}
