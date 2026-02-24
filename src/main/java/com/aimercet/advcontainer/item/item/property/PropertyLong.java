package com.aimercet.advcontainer.item.item.property;

public class PropertyLong extends Property<Long>{
    @Override public void parse(String str) {setValue(Long.valueOf(str));}
    @Override public String geType() {return "long";}
    @Override public Long getDefaultValue() {return 0L;}
}
