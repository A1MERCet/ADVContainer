package com.aimercet.advcontainer.bridge.minecraft.vanilla;

import com.aimercet.advcontainer.bridge.minecraft.container.SlotItemStack;
import com.aimercet.advcontainer.container.slotitem.ISlotItem;
import com.aimercet.advcontainer.item.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class UtilVanillaInventory
{
    public static ISlotItem toSlotItem(ItemStack itemStack)
    {
        if(ItemManager.Get(itemStack)==null)return null;
        return new SlotItemStack(itemStack);
    }

    public static boolean tryPlaceItem(Player player,Inventory inventory, ItemStack isk, int index)
    {
        if(isk==null) return false;

        InventoryView view = player.openInventory(inventory);

        InventoryClickEvent evtDrop = new InventoryClickEvent(view, InventoryType.SlotType.CONTAINER, index, ClickType.LEFT, InventoryAction.PLACE_ALL);
        evtDrop.setCurrentItem(isk);
        Bukkit.getPluginManager().callEvent(evtDrop);
        if(evtDrop.isCancelled()) {return false;}

        inventory.setItem(index, isk);

        return true;
    }
    public static ItemStack tryRemoveItem(Player player, Inventory inventory, int index)
    {
        InventoryView view = player.openInventory(inventory);
        ItemStack isk = view.getItem(index);

        if(isk==null) return null;

        InventoryClickEvent evtPickup = new InventoryClickEvent(view, InventoryType.SlotType.CONTAINER, index, ClickType.LEFT, InventoryAction.PICKUP_ALL);
        Bukkit.getPluginManager().callEvent(evtPickup);
        if(evtPickup.isCancelled()){return null;}

        inventory.setItem(index, null);

        return isk;
    }
    public static boolean tryMoveItem(Player player,Inventory inventory, int indexSource, int indexTarget)
    {
        InventoryView view = player.openInventory(inventory);
        ItemStack iskSource = view.getItem(indexSource);
        ItemStack iskTarget = view.getItem(indexTarget);

        if(iskSource==null || iskTarget!=null) return false;

        InventoryClickEvent evtPickup = new InventoryClickEvent(view, InventoryType.SlotType.CONTAINER, indexSource, ClickType.LEFT, InventoryAction.PICKUP_ALL);
        Bukkit.getPluginManager().callEvent(evtPickup);
        if(evtPickup.isCancelled()){return false;}

        InventoryClickEvent evtDrop = new InventoryClickEvent(view, InventoryType.SlotType.CONTAINER, indexTarget, ClickType.LEFT, InventoryAction.DROP_ALL_CURSOR);
        Bukkit.getPluginManager().callEvent(evtDrop);
        if(evtDrop.isCancelled()) {return false;}

        inventory.setItem(indexSource, null);
        inventory.setItem(indexTarget, iskSource);
        return true;
    }
    public static boolean tryPickupItem(Player player, Inventory inventory, int index)
    {
        InventoryView view = player.openInventory(inventory);
        InventoryClickEvent evt = new InventoryClickEvent(view, InventoryType.SlotType.CONTAINER, index, ClickType.LEFT, InventoryAction.PICKUP_ALL);
        Bukkit.getPluginManager().callEvent(evt);
        if(evt.isCancelled()){return false;}
        return true;
    }
    public static boolean tryDropItem(Player player, Inventory inventory, int index)
    {
        InventoryClickEvent evt = new InventoryClickEvent(player.openInventory(inventory), InventoryType.SlotType.CONTAINER, index, ClickType.LEFT, InventoryAction.DROP_ALL_CURSOR);
        Bukkit.getPluginManager().callEvent(evt);
        if(evt.isCancelled()) {return false;}
        return true;
    }
}
