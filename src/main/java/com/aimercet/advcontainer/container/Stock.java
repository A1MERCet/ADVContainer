package com.aimercet.advcontainer.container;

import com.aimercet.advcontainer.api.gui.GPartStyle;
import com.aimercet.advcontainer.container.handler.Checker;
import com.aimercet.advcontainer.container.handler.IChecker;
import com.aimercet.advcontainer.item.ItemType;

public class Stock implements IStock
{
    private final IContainer container;
    private ISlot[][] slotsAry;
    private IChecker checker;
    private GPartStyle guiStyle = new GPartStyle();

    public Stock(IContainer container)
    {
        this.container = container;
        this.checker = new Checker().allow(ItemType.values);
    }

    @Override
    public void initStock()
    {
    }


    @Override public IChecker getChecker() {return checker;}
    @Override public void setChecker(IChecker c) {this.checker = c;}

    @Override public IContainer getContainer() {return container;}
    @Override public ISlot[][] getSlots() {return slotsAry;}
    @Override public void setSlots(ISlot[][] ary) {this.slotsAry = ary;}
    @Override public GPartStyle getGUIStyle() {return guiStyle;}
    @Override public String toString() {return getClass().getSimpleName()+"["+ getSize()+", Container="+container.getUUID()+"]";}
    @Override public void setGUIStyle(GPartStyle guiStyle) {this.guiStyle = guiStyle;}
}
