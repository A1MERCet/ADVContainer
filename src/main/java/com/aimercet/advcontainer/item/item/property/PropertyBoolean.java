package com.aimercet.advcontainer.item.item.property;

public class PropertyBoolean extends Property<Boolean>{
    @Override public void parse(String str) {setValue(Boolean.valueOf(str));}
    @Override public String geType() {return "boolean";}
    @Override public Boolean getDefaultValue() {return false;}
}
