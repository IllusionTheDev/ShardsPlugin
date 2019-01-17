package IllusionShards;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public final class ShardsGUI {

    private static Inventory inventory;
    public static Inventory getInventory()
    {
        return inventory;
    }

    public static void refreshMenu()
    {
        setUpInventory();
    }

    public static void setUpInventory()
    {
        int i = 0;
        if(IllusionShards.Shards.getShardPluginConfig().getInt("menu-size") < IllusionShards.ShardsAPI.getItems().size())
        {
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "There are too many items for this menu-size");
            return;
        }
        inventory = Bukkit.getServer().createInventory(null, IllusionShards.Shards.getShardPluginConfig().getInt("menu-size"), ChatColor.translateAlternateColorCodes('&', IllusionShards.Shards.getShardPluginConfig().getString("menu-name")));
        for(ItemStack Item : IllusionShards.ShardsAPI.getItems().keySet())
        {
            i++;
            List<String> lore = Item.getItemMeta().getLore();
            lore.add(IllusionShards.Shards.getShardPluginConfig().getString("Item-add-lore").replace("[Shards}", String.valueOf(IllusionShards.ShardsAPI.getItems().get(Item))));
            Item.getItemMeta().setLore(lore);
            inventory.addItem(Item);
            IllusionShards.ShardsAPI.getGUIItems().put(i - 1, Item);
        }

    }
}
