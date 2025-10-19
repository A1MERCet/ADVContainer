package com.aimercet.advcontainer.container.handler.source;

import com.aimercet.advcontainer.container.handler.HandleResult;
import org.bukkit.entity.Player;

public class HandleSourcePlayer implements IHandleSource
{
    public final Player player;
    public final InventoryHandleHistory history;

    public HandleSourcePlayer(Player player)
    {
        if(player == null) throw new NullPointerException("player is null");
        this.player = player;
        this.history = new InventoryHandleHistory();
    }

    @Override public String getHandlerName() {return player.getName();}
    @Override public InventoryHandleHistory getInventoryHandleHistory() {return history;}
    @Override public void AddHistory(HandleResult result) {this.history.add(result);}


}
