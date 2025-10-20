package com.aimercet.advcontainer.bridge.brlib;

import com.aimercet.brlib.player.PlayerState;

import java.util.HashMap;

public class ContainerSourcePlayer extends ContainerSourceEntity
{
    public static HashMap<PlayerState,ContainerSourcePlayer> sourceMap = new HashMap<>();
    public static void removePlayer(PlayerState playerState) {sourceMap.remove(playerState);}
    public static ContainerSourcePlayer fromPlayer(PlayerState player)
    {
        if(!sourceMap.containsKey(player)) sourceMap.put(player,new ContainerSourcePlayer(player));
        return sourceMap.get(player);
    }

    public final PlayerState playerState;
    public ContainerSourcePlayer(PlayerState playerState)
    {
        super(playerState.getPlayer());
        this.playerState = playerState;
    }
}
