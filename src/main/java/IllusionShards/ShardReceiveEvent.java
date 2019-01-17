package IllusionShards;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.entity.Player;

public final class ShardReceiveEvent extends Event {

    public ShardReceiveEvent(Player player, int Amount)
    {
        Receivedplayer = player;
        newShards = Amount;
    }

    private int newShards;
    private Player Receivedplayer;
    private static final HandlerList handlers = new HandlerList();

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
    public Player getPlayer()
    {
        return Receivedplayer;
    }

    public int getShardsReceived()
    {
        return newShards;
    }
}


