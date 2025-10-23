package com.aimercet.advcontainer.util;

import com.aimercet.brlib.log.Logger;
import org.bukkit.configuration.ConfigurationSection;

public class UtilFile
{
    public static void printSection(ConfigurationSection section)
    {
        StringBuilder b = new StringBuilder();
        b.append("print section:\n");
        b.append(section.toString()).append("\n");
        if(section != null){
            b.append(getSection(section,0));
        }else{
            b.append("  no section\n");
        }
        Logger.info(b.toString());
    }
    private static String getSection(ConfigurationSection section,int spaceCount)
    {
        if(section==null)return "";
        StringBuilder b = new StringBuilder();
        String space = "";
        for (int i = 0; i < spaceCount; i++) space+="  ";

        for (String key : section.getKeys(false))
        {
            b.append(space).append(key).append(": ").append(section.getString(key)).append("\n");
            b.append(getSection(section.getConfigurationSection(key),spaceCount+1));
        }

        return b.toString();
    }
}
