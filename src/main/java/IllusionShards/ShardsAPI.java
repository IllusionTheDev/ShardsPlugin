package IllusionShards;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class ShardsAPI {

    public ShardsAPI(){}

    private static Map<ItemStack, Integer> Items = new HashMap<>();
    public static Map<Player, Integer> PlayerShards = new HashMap<>();
    private static Map<Integer, ItemStack> GUIItems = new HashMap<>();

    public static Map<ItemStack, Integer> getItems()
    {
        return Items;
    }

    public static Map<Integer, ItemStack> getGUIItems()
    {
        return GUIItems;
    }

    public static int getShards(Player player)
    {
        return PlayerShards.get(player);
    }

    public static void addItem(ItemStack item, int shardCost)
    {
        if(Items.containsKey(item))
        {
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Attempted to put an already-existing item");
            return;
        }
        Items.put(item, shardCost);
        ShardsGUI.refreshMenu();

    }

    public static void addShards(Player player, int Shards, Boolean triggerEvent)
    {
        int oldShards = PlayerShards.get(player);
        PlayerShards.remove(player);
        PlayerShards.put(player, oldShards + Shards);
        IllusionShards.Shards.getShardsConfig().set(player.getUniqueId().toString(), oldShards + Shards);
        if(triggerEvent)
        {
            ShardReceiveEvent CustomEvent = new ShardReceiveEvent(player, Shards);
            Bukkit.getServer().getPluginManager().callEvent(CustomEvent);
        }

    }

    public static void removeShards(Player player, int Shards, Boolean triggerEvent)
    {
        int oldShards = PlayerShards.get(player);
        if(Shards >= oldShards)
            Shards = oldShards;
        PlayerShards.remove(player);
        PlayerShards.put(player, oldShards - Shards);
        IllusionShards.Shards.getShardsConfig().set(player.getUniqueId().toString(), oldShards - Shards);
        if(triggerEvent)
        {
            ShardLossEvent CustomEvent = new ShardLossEvent(player, Shards);
            Bukkit.getServer().getPluginManager().callEvent(CustomEvent);
        }
    }
    public static void setShards (Player player, int Shards)
    {
        PlayerShards.remove(player);
        PlayerShards.put(player, Shards);
        IllusionShards.Shards.getShardsConfig().set(player.getUniqueId().toString(), Shards);
    }

}
