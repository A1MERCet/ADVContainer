package com.aimercet.advcontainer.item.item.property;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;

public abstract class Property<T>
{
    private T value;

    public Property() {}
    public Property(T value)           {setValue(value);}
    public T getValue()                {return value;}
    public void setValue(T value)      {this.value = value;}
    @Override public String toString() {return value.toString();}
    public abstract void parse(String str);
    public abstract String geType();
    public abstract T getDefaultValue();

    public void save(ConfigurationSection section)
    {
        if(section==null) return;
        section.set("type", geType());
        section.set("value", toString());
    }
    public void load(ConfigurationSection section)
    {
        if(section==null) return;
        String v = section.getString("value", "");
        try {
            parse(v);
        }catch (Exception e) {
            Bukkit.getLogger().severe(v);
            e.printStackTrace();
            setValue(getDefaultValue());
        }
    }
}