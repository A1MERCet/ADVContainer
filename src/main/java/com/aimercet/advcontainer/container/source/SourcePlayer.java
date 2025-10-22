package com.aimercet.advcontainer.container.source;

import org.bukkit.entity.Player;

public class SourcePlayer extends SourceEntity
{
    public final Player player;

    public SourcePlayer(Player player)
    {
        super(player);
        this.player = player;
    }

    @Override public String getSourceLang() {return player.getName();}
}
