package com.aimercet.advcontainer.loot.item;

import com.aimercet.advcontainer.bridge.minecraft.container.SlotItemStack;
import com.aimercet.advcontainer.container.slotitem.ISlotItem;
import com.aimercet.advcontainer.item.ItemManager;
import com.aimercet.advcontainer.item.Quality;
import com.aimercet.advcontainer.item.item.TypeItem;
import com.aimercet.advcontainer.loot.trigger.ILootTrigger;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class LootItemStack implements ILootItem , Cloneable
{
    public static final Random random = new Random();
    private TypeItem item;

    public Quality qualityOverlap;

    public float rate = 1F;

    public long minAmount = 1L;
    public long maxAmount = 1L;

    public LootItemStack(TypeItem item)
    {
        this.item = item;
        this.qualityOverlap = item.getQuality();
    }


    @Override
    public ISlotItem random(ILootTrigger trigger)
    {
        float rnd = random.nextFloat();
        long amount = (long)Math.min(1L,(maxAmount-minAmount+1L)*rnd)+minAmount-1L;

        amount = Math.min(amount, maxAmount);
        amount = Math.max(amount, minAmount);

        ItemStack isk = item.createItemStack();
        ItemManager.setAmount(isk, amount);

        return new SlotItemStack(isk);
    }

    @Override
    public LootItemStack clone()
    {
        try {
            LootItemStack i = (LootItemStack) super.clone();

            i.item = item;
            i.minAmount = minAmount;
            i.maxAmount = maxAmount;

            return i;
        }catch (CloneNotSupportedException e) {throw new AssertionError();}
    }

    public LootItemStack setRate(float rate) {this.rate = rate;return this;}
    public LootItemStack setAmount(long min , long max){setMinAmount(min);setMaxAmount(max);return this;}
    public LootItemStack setMinAmount(long minAmount) {this.minAmount = minAmount;return this;}
    public LootItemStack setMaxAmount(long maxAmount) {this.maxAmount = maxAmount;return this;}

    @Override public TypeItem getItem() {return null;}
    @Override public float getRate() {return rate;}
    @Override public long getMaxAmount() {return maxAmount;}
    @Override public long getMinAmount() {return minAmount;}
    @Override public Quality getQualityOverlap() {return qualityOverlap;}
}
