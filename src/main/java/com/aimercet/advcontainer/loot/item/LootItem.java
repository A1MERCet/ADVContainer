package com.aimercet.advcontainer.loot.item;

import com.aimercet.advcontainer.item.ItemManager;
import com.aimercet.advcontainer.item.Quality;
import com.aimercet.advcontainer.item.item.TypeItem;
import com.aimercet.advcontainer.loot.trigger.ILootTrigger;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class LootItem implements ILootItem , Cloneable
{
    public final Random random;
    private TypeItem item;

    public Quality qualityOverlap;

    public float rate = 1F;

    public long minAmount = 1L;
    public long maxAmount = 1L;

    public LootItem(TypeItem item)
    {
        this.item = item;
        this.qualityOverlap = item.getQuality();
        this.random = new Random();
    }


    @Override
    public ItemStack random(ILootTrigger trigger)
    {
        float rnd = random.nextFloat();
        long amount = (long)Math.min(1L,(maxAmount-minAmount+1L)*rnd)+minAmount-1L;

        amount = Math.min(amount, maxAmount);
        amount = Math.max(amount, minAmount);

        ItemStack isk = item.createItem();
        ItemManager.setAmount(isk, amount);

        return isk;
    }

    @Override
    public LootItem clone()
    {
        try {
            LootItem i = (LootItem) super.clone();

            i.item = item;
            i.minAmount = minAmount;
            i.maxAmount = maxAmount;

            return i;
        }catch (CloneNotSupportedException e) {throw new AssertionError();}
    }

    public LootItem setRate(float rate) {this.rate = rate;return this;}
    public LootItem setAmount(long min , long max){setMinAmount(min);setMaxAmount(max);return this;}
    public LootItem setMinAmount(long minAmount) {this.minAmount = minAmount;return this;}
    public LootItem setMaxAmount(long maxAmount) {this.maxAmount = maxAmount;return this;}

    @Override public TypeItem getItem() {return null;}
    @Override public float getRate() {return rate;}
    @Override public long getMaxAmount() {return maxAmount;}
    @Override public long getMinAmount() {return minAmount;}
    @Override public Quality getQualityOverlap() {return qualityOverlap;}
}
