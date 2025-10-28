package com.aimercet.advcontainer.container.backpack.equipment;

import com.aimercet.advcontainer.container.handler.Checker;
import com.aimercet.advcontainer.container.handler.IChecker;
import com.aimercet.advcontainer.container.slotitem.ISlotItem;
import com.aimercet.advcontainer.item.item.TypeItem;

import java.util.ArrayList;
import java.util.List;

public class CheckerEquip extends Checker
{
    public final List<EquipType> allowEquipTypes = new ArrayList<EquipType>();

    @Override
    public Type check(ISlotItem item)
    {
        if(item==null) return IChecker.Type.ITEM_NULL;
        TypeItem typeItem = item.getTypeItem();
        if(typeItem==null) return IChecker.Type.ITEM_NULL;

        if(!allowID.contains(typeItem.id) && !allowEquipTypes.contains(typeItem.equipType)) return Type.TYPE;

        return super.check(item);
    }

    @Override
    protected CheckerEquip clone() throws CloneNotSupportedException
    {
        CheckerEquip clone = (CheckerEquip)super.clone();
        clone.allowEquipTypes.addAll(allowEquipTypes);
        return clone;
    }

    public CheckerEquip addEquipType(EquipType type) {allowEquipTypes.add(type);return this;}
    public CheckerEquip addEquipType(List<EquipType> type) {allowEquipTypes.addAll(type);return this;}
    public List<EquipType> getAllowEquipTypes() {return allowEquipTypes;}
}
