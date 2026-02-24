package com.aimercet.advcontainer.bridge.minecraft.vanilla;

import com.aimercet.advcontainer.bridge.minecraft.container.SlotItemStack;
import com.aimercet.advcontainer.container.handler.PlaceResult;
import com.aimercet.advcontainer.container.handler.RemoveResult;
import com.aimercet.advcontainer.container.handler.SlotSource;
import com.aimercet.advcontainer.container.handler.source.HandleSourcePlayer;
import com.aimercet.brlib.player.PlayerState;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class VanillaInventoryHandler
{
    public void transform2Vanilla(PlayerState ps, SlotSource source, Inventory inventory, int index)
    {
        if(UtilVanillaInventory.tryPlaceItem(ps.getPlayer(),inventory, ((SlotItemStack)source.getItem()).getItemStack(),index)){
            if(source.container.getHandler()!=null) {
                RemoveResult result = source.container.getHandler().remove(HandleSourcePlayer.fromPlayer(ps), source);
                if(result.type == RemoveResult.Type.SUCCESS) {
                    inventory.setItem(index,((SlotItemStack)source.getItem()).getItemStack());
                }
            }
        }
    }
    public void transform2ADVC(PlayerState ps, SlotSource source, Inventory inventory, int index, boolean rotate)
    {
        ItemStack isk = UtilVanillaInventory.tryRemoveItem(ps.getPlayer(), inventory, index);
        if(isk != null && source.container.getHandler()!=null){
            PlaceResult result = source.container.getHandler().place(HandleSourcePlayer.fromPlayer(ps), new SlotItemStack(isk), source, rotate);
            if(result.type == PlaceResult.Type.SUCCESS) {
                inventory.setItem(index,null);
            }
        }
    }
}
