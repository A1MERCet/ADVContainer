package com.aimercet.advcontainer.item.item;

import com.aimercet.advcontainer.container.ContainerTemplate;
import org.bukkit.configuration.ConfigurationSection;

public class TypeItemContainer extends TypeItem
{
    public ContainerTemplate containerTemplate = new ContainerTemplate();
    public TypeItemContainer(String id)
    {
        super(id);
    }

    @Override
    public void load(ConfigurationSection section)
    {
        super.load(section);
        if(section.getConfigurationSection("container")!=null) containerTemplate.load(section.getConfigurationSection("container"));
    }
}
