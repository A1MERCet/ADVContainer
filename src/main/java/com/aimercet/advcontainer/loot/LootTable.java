package com.aimercet.advcontainer.loot;

import com.aimercet.advcontainer.item.Quality;
import com.aimercet.advcontainer.loot.item.ILootItem;
import com.aimercet.advcontainer.loot.item.ILootTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class LootTable implements ILootTable
{
    public final Random random;

    public final List<ILootItem> lootItems = new ArrayList<>();
    public final HashMap<Quality,List<ILootItem>> lootMap = new HashMap<>();

    private int minCount;
    private int maxCount;

    public LootTable()
    {
        random = new Random();
    }


    @Override public HashMap<Quality, List<ILootItem>> getLootMap() {return lootMap;}
    public LootTable setMaxCount(int maxCount)              {this.maxCount = maxCount;return this;}
    public LootTable setMinCount(int minCount)              {this.minCount = minCount;return this;}
    public LootTable setAmount(int minAmount,int maxAmount) {setMinCount(minAmount);setMaxCount(maxAmount);return this;}

    @Override public int getMaxCount()                      {return maxCount;}
    @Override public int getMinCount()                      {return minCount;}
    @Override public List<ILootItem> getLoots()             {return lootItems;}
}
