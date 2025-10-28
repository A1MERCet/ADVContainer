package com.aimercet.advcontainer.container;

import com.aimercet.advcontainer.api.gui.GPartStyle;
import com.aimercet.advcontainer.container.handler.SlotSource;
import com.aimercet.advcontainer.container.slotitem.ISlotItem;
import com.aimercet.advcontainer.util.Coord;
import org.bukkit.configuration.ConfigurationSection;

public interface ISlot
{
    IStock getStock();
    default IContainer getContainer(){return getStock()==null?null:getStock().getContainer();};
    Coord getCoord();

    Coord getSourceCoord();
    void setSourceCoord(Coord source);
    ISlotItem getItem();
    void setItem(ISlotItem item);
    GPartStyle getGUIStyle();
    void setGUIStyle(GPartStyle guiStyle);

    /**
     * 只返回是否含有物品 不判断作为占位的情况
     */
    default boolean hasItem()               {return getItem()!=null;}

    /**
     * 是否被其他格子的物品占位 多格子的物品通过设置SourceCoord为被占用
     */
    default boolean isPlaceholder()         {return getSourceCoord()!=null;}

    /**
     * 是否被占用 此格子含有物品或者被其他格子物品占位
     */
    default boolean isOccupied()            {return hasItem() || isPlaceholder();}

    default SlotSource toSource()           {return new SlotSource(this);}

    boolean isRotate();
    void setRotate(boolean rotate);

    void save(ConfigurationSection section);
    void load(ConfigurationSection section);
}