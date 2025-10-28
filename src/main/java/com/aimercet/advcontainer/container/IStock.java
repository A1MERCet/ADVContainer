package com.aimercet.advcontainer.container;

import com.aimercet.advcontainer.api.gui.GPartStyle;
import com.aimercet.advcontainer.container.handler.IChecker;
import com.aimercet.advcontainer.container.handler.SlotSource;
import com.aimercet.advcontainer.container.handler.PlaceResult;
import com.aimercet.advcontainer.container.slotitem.ISlotItem;
import com.aimercet.advcontainer.item.ItemManager;
import com.aimercet.advcontainer.util.Coord;
import com.aimercet.advcontainer.util.SizeInt;
import com.aimercet.brlib.log.Logger;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

public interface IStock
{
    IChecker getChecker();
    void setChecker(IChecker c);
    IContainer getContainer();
    ISlot[][] getSlots();
    void setSlots(ISlot[][] ary);
    GPartStyle getGUIStyle();
    void setGUIStyle(GPartStyle guiStyle);

    void initStock();

    default void save(ConfigurationSection section)
    {
        for (int y = 0; y < getSizeY(); y++)
            for (int x = 0; x < getSizeX(); x++)
            {
                ISlot slot = get(x, y);
                if(slot == null || !slot.hasItem()) continue;
                slot.save(section.createSection(x + "-" + y));
            }
    }
    default void load(ConfigurationSection section)
    {
        for (int y = 0; y < getSizeY(); y++)
            for (int x = 0; x < getSizeX(); x++)
            {
                ConfigurationSection s = section.getConfigurationSection(x+"-"+y+".slotItem");
                if(s!=null) {
                    ISlotItem slotItem = ItemManager.loadSlotItem(s);
                    boolean rotate = section.getBoolean(x+"-"+y+".rotate");

                    if(slotItem!=null){
                        PlaceResult result = getContainer().getHandler().place(ContainerManager.instance.handleSourceConfig, slotItem, new SlotSource(getContainer(),getIndex(), new Coord(x, y)), rotate);
                        if(result.type!=PlaceResult.Type.SUCCESS) Logger.warn("load item fail["+getContainer().getUUID()+" - "+getContainer().getStockList().indexOf(this)+"] in coord["+x+", "+y+"]");
                    }
                }
            }
    }

    default ISlot get(Coord coord) {return get(coord.x, coord.y);}
    default ISlot get(int x,int y)
    {
        SizeInt size = getSize();
        if(x<0 || y<0 || x >= size.width || y >= size.height)return null;
        return getSlots()[x][y];
    }

    default IStock setSize(SizeInt size) {
        setSlots(new ISlot[size.width][size.height]);

        for (int x = 0; x < size.width; x++)
            for (int y = 0; y < size.height; y++) {
                getSlots()[x][y]= getContainer().createSlot(this,new Coord(x,y));
            }

        return this;
    }

    default int getEmpty()
    {
        int v = 0;
        for (int x = 0; x < getSlots().length; x++) {
            for (int y = 0; y < getSlots()[x].length; y++) {
                ISlot slot = get(x,y);
                if(slot!=null && !slot.isOccupied())v++;
            }
        }
        return v;
    }

    default boolean isFull()
    {
        for (int x = 0; x < getSlots().length; x++) {
            for (int y = 0; y < getSlots()[x].length; y++) {
                ISlot slot = get(x,y);
                if(slot!=null && !slot.isOccupied())return false;
            }
        }
        return true;
    }

    default List<SlotSource> getItems()
    {
        List<SlotSource> list = new ArrayList<SlotSource>();

        for (int x = 0; x < getSlots().length; x++)
            for (int y = 0; y < getSlots()[x].length; y++)
            {
                ISlot slot = get(x, y); if(slot==null || !slot.hasItem())continue;
                list.add(new SlotSource(slot));
            }

        return list;
    }

    default SizeInt getSize() {return new SizeInt(getSizeX(), getSizeY());}
    default int getSizeX(){
        if(getSlots()==null || getSlots().length==0 || getSlots()[0].length==0)return 0;
        return getSlots().length;
    }
    default int getSizeY(){
        if(getSlots().length==0)return 0;else return getSlots()[0].length;
    }
    default int getIndex()
    {
        if(getContainer()==null)return -1;
        return getContainer().getStockList().indexOf(this);
    }
}
