package com.aimercet.advcontainer.container.backpack.equipment;

import com.aimercet.advcontainer.container.IContainer;
import com.aimercet.advcontainer.container.Stock;
import com.aimercet.advcontainer.container.handler.IChecker;

import java.util.List;

public class StockEquip extends Stock
{
    private ContainerEquip containerEquip;
    private List<EquipType> equipTypes;

    public StockEquip(IContainer container)
    {
        super(container);
        getGUIStyle().setStyle("equip");
        containerEquip = (ContainerEquip) container;
    }

    public ContainerEquip getContainerEquip() {return containerEquip;}
    public StockEquip setContainerEquip(ContainerEquip containerEquip) {this.containerEquip = containerEquip;return this;}
    public List<EquipType> getEquipTypes() {return equipTypes;}
    public StockEquip setEquipTypes(List<EquipType> equipTypes)
    {
        IChecker sourceChecker = getChecker();

        if(equipTypes == null && sourceChecker instanceof CheckerEquip){
            ((CheckerEquip) sourceChecker).getAllowEquipTypes().clear();
            return this;
        }

        this.equipTypes = equipTypes;


        if(!(sourceChecker instanceof CheckerEquip)){
            setChecker(new CheckerEquip().addEquipType(equipTypes));
        }else {
            List<EquipType> list = ((CheckerEquip) sourceChecker).getAllowEquipTypes();
            list.clear();
            list.addAll(equipTypes);
        }

        return this;
    }

    public StockEquip addEquipTypes(EquipType equipType)
    {
        if(equipTypes == null)return this;
        this.equipTypes.add(equipType);

        IChecker sourceChecker = getChecker();

        if(!(sourceChecker instanceof CheckerEquip)){
            setChecker(new CheckerEquip().addEquipType(equipType));
        }else {
            ((CheckerEquip) sourceChecker).getAllowEquipTypes().add(equipType);
        }

        return this;
    }
}
