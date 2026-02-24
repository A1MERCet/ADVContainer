package com.aimercet.advcontainer.item.item.property;

public class PropertyString extends Property<String>{
    @Override public void parse(String str) {setValue(str);}
    @Override public String geType() {return "string";}
    @Override public String getDefaultValue() {return "";}
}
