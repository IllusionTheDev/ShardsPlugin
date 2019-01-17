package IllusionShards;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ShardLossEvent extends Event {

    public ShardLossEvent(Player player, int Amount)
    {
        this.player = player;
        lossShards = Amount;
    }

    private int lossShards;
    private Player player;
    private static final HandlerList handlers = new HandlerList();

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
    public Player getPlayer()
    {
        return player;
    }

    public int getLossShards()
    {
        return lossShards;
    }
}
