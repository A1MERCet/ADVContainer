package com.aimercet.advcontainer.bridge.minecraft;

import com.aimercet.advcontainer.bridge.minecraft.customevent.InventoryChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class InventoryWatcher  extends BukkitRunnable
{
    private final HashMap<UUID, ItemStack[]> lastInventory = new HashMap<>();

    @Override
    public void run()
    {
        for (Player p : Bukkit.getOnlinePlayers())
        {
            ItemStack[] current = p.getInventory().getContents();
            ItemStack[] last = lastInventory.put(p.getUniqueId(), current.clone());

            if (last == null) continue;

            for (int i = 0; i < current.length; i++) {
                if (!Objects.equals(current[i] == null ? null : current[i].clone(), last[i] == null ? null : last[i].clone())) {
                    Bukkit.getPluginManager().callEvent(new InventoryChangeEvent(p.getInventory(), i, last[i], current[i]));
                }
            }
        }
    }
}
