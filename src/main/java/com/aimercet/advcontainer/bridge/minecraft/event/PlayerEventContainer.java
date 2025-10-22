package com.aimercet.advcontainer.bridge.minecraft.event;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class PlayerEventContainer implements Listener
{
    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent e)
    {
        if(e.getHand() == EquipmentSlot.HAND){
            handlePlayerInteractEntity(e.getPlayer(), e.getRightClicked());
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e)
    {
        switch (e.getAction()){
            case RIGHT_CLICK_BLOCK: {
                handlePlayerInteractBlock(e.getPlayer(),e.getClickedBlock());
            }

        }
    }

    public void handlePlayerInteractBlock(Player player, Block block)
    {
        if(block==null)return;
    }
    public void handlePlayerInteractEntity(Player player, Entity entity)
    {
        if(entity==null)return;
    }
}
