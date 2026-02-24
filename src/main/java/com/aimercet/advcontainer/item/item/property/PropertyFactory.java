package com.aimercet.advcontainer.item.item.property;

import com.aimercet.brlib.util.MapBuilder;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;

public class PropertyFactory
{
    private static HashMap<String, Class<? extends Property<?>>> types = new MapBuilder<String, Class<? extends Property<?>>>()
            .put("int",PropertyInt.class)
            .put("boolean",PropertyBoolean.class)
            .put("double",PropertyDouble.class)
            .put("float",PropertyFloat.class)
            .put("long",PropertyLong.class)
            .put("string",PropertyString.class)
            .map;


    public static Property<?> parseYaml(ConfigurationSection section)
    {
        String value = section.getString("value", "");
        Property<?> property = null;

        switch (section.getString(".type","string")) {
            case "int":     property = new PropertyInt();       break;
            case "boolean": property = new PropertyBoolean();   break;
            case "double":  property = new PropertyDouble();    break;
            case "float":   property = new PropertyFloat();     break;
            case "long":    property = new PropertyLong();      break;
            case "string":  property = new PropertyString();    break;
            default:        return null;
        }
        property.load(section);
        return property;
    }
}
