package com.aimercet.advcontainer.container.handler.source;

import com.aimercet.advcontainer.container.ContainerIndex;
import com.aimercet.advcontainer.container.source.ISourceEntity;
import com.aimercet.brlib.player.PlayerState;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class HandleSourcePlayer implements IHandleSource , ISourceEntity
{
    public static HashMap<PlayerState,HandleSourcePlayer> handleMap = new HashMap<>();
    public static void removePlayer(PlayerState playerState) {handleMap.remove(playerState);}
    public static HandleSourcePlayer fromPlayer(PlayerState player)
    {
        if(!handleMap.containsKey(player)) handleMap.put(player,new HandleSourcePlayer(player));
        return handleMap.get(player);
    }


    public final PlayerState playerState;
    public final InventoryHandleHistory history;
    public final ContainerIndex<String> allowedContainers = new ContainerIndex<String>();

    private HandleSourcePlayer(PlayerState playerState)
    {
        if(playerState == null) throw new NullPointerException("PlayerState is null");
        this.playerState = playerState;
        this.history = new InventoryHandleHistory();
    }

    @Override public String getHandlerName() {return playerState.getName();}
    @Override public InventoryHandleHistory getInventoryHandleHistory() {return history;}
    @Override public ContainerIndex<String> getAllowedContainers() {return allowedContainers;}
    @Override public String toString() {return getClass().getSimpleName()+"["+(playerState==null?"null":playerState.name)+"]";}

    @Override public Entity getSourceEntity() {return playerState==null?null:playerState.getPlayer();}

    @Override
    public Location getSourceLocation()
    {
        if(playerState==null) return null;
        Player player = playerState.getPlayer();
        if(player == null) return null;
        return player.getLocation();
    }
}
