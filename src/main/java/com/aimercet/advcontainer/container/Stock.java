package com.aimercet.advcontainer.container;

public class Stock implements IStock
{
    private final IContainer container;
    private ISlot[][] slotsAry;

    public Stock(IContainer container)
    {
        this.container = container;
    }

    @Override public IContainer getContainer() {return container;}
    @Override public ISlot[][] getSlots() {return slotsAry;}
    @Override public void setSlots(ISlot[][] ary) {this.slotsAry = ary;}

    @Override public String toString() {return getClass().getSimpleName()+"["+ getSize()+", Container="+container.getUUID()+"]";}
}
