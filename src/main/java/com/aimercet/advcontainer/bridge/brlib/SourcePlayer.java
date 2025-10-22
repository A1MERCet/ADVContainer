package com.aimercet.advcontainer.bridge.brlib;

import com.aimercet.brlib.player.PlayerState;

import java.util.HashMap;

public class SourcePlayer extends SourceEntity
{
    public static HashMap<PlayerState, SourcePlayer> sourceMap = new HashMap<>();
    public static void removePlayer(PlayerState playerState) {sourceMap.remove(playerState);}
    public static SourcePlayer fromPlayer(PlayerState player)
    {
        if(!sourceMap.containsKey(player)) sourceMap.put(player,new SourcePlayer(player));
        return sourceMap.get(player);
    }

    public final PlayerState playerState;
    public SourcePlayer(PlayerState playerState)
    {
        super(playerState.getPlayer());
        this.playerState = playerState;
    }
}
