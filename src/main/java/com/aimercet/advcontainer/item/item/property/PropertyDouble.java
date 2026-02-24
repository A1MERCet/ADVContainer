package com.aimercet.advcontainer.item.item.property;

public class PropertyDouble extends Property<Double>{
    @Override public void parse(String str) {setValue(Double.valueOf(str));}
    @Override public String geType() {return "double";}
    @Override public Double getDefaultValue() {return 0D;}
}
