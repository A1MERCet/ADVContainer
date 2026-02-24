package com.aimercet.advcontainer.loot.item;

import com.aimercet.advcontainer.container.slotitem.ISlotItem;
import com.aimercet.advcontainer.item.ItemManager;
import com.aimercet.advcontainer.item.ItemType;
import com.aimercet.advcontainer.item.Quality;
import com.aimercet.advcontainer.item.item.TypeItem;
import com.aimercet.advcontainer.loot.LootManager;
import com.aimercet.advcontainer.loot.trigger.ILootTrigger;
import com.aimercet.brlib.log.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public interface ILootTable
{
    public static Random random = new Random();
    List<ILootItem> getLoots();
    HashMap<Quality,List<ILootItem>> getLootMap();

    int getMinCount();
    int getMaxCount();

    default List<ISlotItem> random(){return random(LootManager.instance.triggerSystem);}
    default List<ISlotItem> random(ILootTrigger trigger)
    {
        List<ISlotItem> list = new ArrayList<>();
        int count = random.nextInt(getMinCount()) + getMaxCount();
        for (int i = 0; i < count; i++) {
            ISlotItem rnd = randomItem(trigger);
            if(rnd!=null) list.add(rnd);
        }
        return list;
    }

    default ISlotItem randomItem(){return randomItem(LootManager.instance.triggerSystem);}
    default ISlotItem randomItem(ILootTrigger trigger)
    {
        Quality quality = randomQuality();
        List<ILootItem> list = getLootMap().get(quality);
        if(list==null || list.isEmpty())return null;
        return list.get(random.nextInt(list.size())).random(trigger);
    }

    default Quality randomQuality(){return randomQuality(LootManager.instance.triggerSystem,Quality.fromLevel(0));}
    default Quality randomQuality(ILootTrigger trigger,Quality defaultQuality)
    {
        float quality = random.nextFloat();

        for (Quality q : Quality.values)
            if(quality < q.globalRate * trigger.getLootRateMulti())
                return q;

        return defaultQuality;
    }

    default ILootTable add(ILootItem item){
        getLoots().add(item);

        List<ILootItem> list = getLootMap().getOrDefault(item.getQualityOverlap(), new ArrayList<>());
        list.add(item);
        getLootMap().put(item.getQualityOverlap(), list);

        return this;
    }
    default ILootTable add(ItemType type){
        if(type==null)return this;

        List<TypeItem> list = ItemManager.instance.getTypeMap().get(type);
        if(list==null){
            Logger.warn("Type["+type.id+"] not item registered");
        }else{
            for (TypeItem typeItem : list) {
                LootItemStack l = new LootItemStack(typeItem);
                add(l);
            }
        }
        return this;
    }
    
}
