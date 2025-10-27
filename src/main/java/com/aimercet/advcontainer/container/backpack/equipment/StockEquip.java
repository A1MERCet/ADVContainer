package com.aimercet.advcontainer.container.backpack.equipment;

import com.aimercet.advcontainer.container.IContainer;
import com.aimercet.advcontainer.container.ISlot;
import com.aimercet.advcontainer.container.IStock;
import com.aimercet.advcontainer.container.Stock;
import com.aimercet.advcontainer.util.SizeInt;
import com.aimercet.brlib.log.Logger;

import java.util.List;

public class StockEquip extends Stock
{
    private ContainerEquip containerEquip;

    public StockEquip(IContainer container)
    {
        super(container);
        containerEquip = (ContainerEquip) container;
    }

    @Override
    public void initStock()
    {
        List<EquipType> types = containerEquip.allowedEquipSlots;
        SizeInt size = getSize();
        int index = 0;

        for (int y = 0; y < size.height; y++)
            for (int x = 0; x < size.width; x++)
            {
                ISlot slot = getSlots()[x][y];
                if(slot instanceof SlotEquip)
                    if(index<=types.size()-1)
                    {
                        ((SlotEquip) slot).setEquipType(types.get(index));
                        Logger.info("Set slot equip type: " + types.get(index).id);
                    }
                index++;
            }
    }

    @Override
    public IStock setSize(SizeInt size)
    {
        return super.setSize(new SizeInt(containerEquip.allowedEquipSlots.size(),1));
    }

    public ContainerEquip getContainerEquip() {return containerEquip;}
}
