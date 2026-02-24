package com.aimercet.advcontainer.item.item.property;

public class PropertyFloat extends Property<Float>{
    @Override public void parse(String str) {setValue(Float.valueOf(str));}
    @Override public String geType() {return "float";}
    @Override public Float getDefaultValue() {return 0F;}
}
