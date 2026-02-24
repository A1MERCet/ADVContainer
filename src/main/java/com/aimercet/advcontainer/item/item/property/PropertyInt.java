package com.aimercet.advcontainer.item.item.property;

public class PropertyInt extends Property<Integer>{
    @Override public void parse(String str) {setValue(Integer.valueOf(str));}
    @Override public String geType() {return "int";}
    @Override public Integer getDefaultValue() {return 0;}
}
