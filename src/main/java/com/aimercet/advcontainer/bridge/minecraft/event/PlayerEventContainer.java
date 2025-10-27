package com.aimercet.advcontainer.bridge.minecraft.event;

import com.aimercet.advcontainer.bridge.minecraft.container.SlotItemStack;
import com.aimercet.advcontainer.container.ContainerManager;
import com.aimercet.advcontainer.container.backpack.Backpack;
import com.aimercet.advcontainer.container.handler.PlaceResult;
import com.aimercet.advcontainer.container.handler.source.IHandleSource;
import com.aimercet.advcontainer.container.slotitem.ISlotItem;
import com.aimercet.advcontainer.container.source.ISource;
import com.aimercet.advcontainer.item.item.TypeItem;
import com.aimercet.advcontainer.util.Util;
import com.aimercet.advcontainer.util.UtilItem;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class PlayerEventContainer implements Listener
{
    @EventHandler
    public void onInventory(InventoryClickEvent evt)
    {
    }

    @EventHandler
    public void onPickItem(EntityPickupItemEvent evt)
    {
        if(evt.isCancelled())return;

        Item item = evt.getItem();
        if(evt.getEntity() instanceof Player)
        {
            Player player = (Player)evt.getEntity();
            Backpack backpack = Backpack.get(player);
            ISlotItem slotItem = UtilItem.toSlotItem(item.getItemStack());

            if(backpack!=null && slotItem!=null)
            {
                PlaceResult result = backpack.addItem(slotItem);

                if(result.type == PlaceResult.Type.SUCCESS)
                {
                    evt.setCancelled(true);
                    item.remove();
                }
            }
        }
    }

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
