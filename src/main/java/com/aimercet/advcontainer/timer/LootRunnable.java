package com.aimercet.advcontainer.timer;

import com.aimercet.advcontainer.loot.ILootState;
import com.aimercet.advcontainer.loot.LootManager;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class LootRunnable extends BukkitRunnable
{
    private long period = 1L;

    @Override
    public void run()
    {
        for (ILootState lootState : LootManager.instance.lootStates)
        {
            if(lootState==null || lootState.getLoot()==null)continue;
            if(!lootState.getLoot().isAutoRefresh())continue;
            if(lootState.getLoot().canRefresh(LootManager.instance.triggerSystem, lootState))
                lootState.getLoot().refresh(LootManager.instance.triggerSystem, lootState);
        }
    }


    @Override
    public synchronized BukkitTask runTaskTimerAsynchronously(Plugin plugin, long delay, long period) throws IllegalArgumentException, IllegalStateException
    {
        setPeriod(period);
        return super.runTaskTimerAsynchronously(plugin, delay, period);
    }

    public long getPeriod() {return period;}
    public LootRunnable setPeriod(long period) {this.period = period;return this;}
}
