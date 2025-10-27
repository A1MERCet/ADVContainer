package com.aimercet.advcontainer.container.slotitem;

import com.aimercet.advcontainer.item.item.TypeItem;
import com.aimercet.advcontainer.util.SizeInt;
import org.bukkit.configuration.ConfigurationSection;

public interface ISlotItem
{
    String getClassName();
    long getAmount();
    TypeItem getTypeItem();
    String getSlotItemID();
    String getSlotItemLang();
    SizeInt getSize();
    String getContainer();

    void load(ConfigurationSection section);
    void save(ConfigurationSection section);
}