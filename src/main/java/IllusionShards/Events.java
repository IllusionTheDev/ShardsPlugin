package IllusionShards;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;

public class Events implements Listener {

    @EventHandler
    public void onJoin(PlayerLoginEvent e)
    {
        if(Shards.getShardsConfig().getString(e.getPlayer().getUniqueId().toString()) == null)
        {
            Shards.getShardsConfig().addDefault(e.getPlayer().getUniqueId().toString(), Shards.getShardsConfig().get("starting-shards"));
        }
        ShardsAPI.PlayerShards.put(e.getPlayer(), Shards.getShardsConfig().getInt(e.getPlayer().getUniqueId().toString()));
    }

    @EventHandler
    public void onLogout(PlayerQuitEvent e)
    {
        ShardsAPI.PlayerShards.remove(e.getPlayer());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e)
    {
        if(e.getInventory().equals(ShardsGUI.getInventory()))
        {
            e.setCancelled(true);
            ItemStack Air = new ItemStack(Material.AIR);
            if(e.getInventory().getItem(e.getSlot()).equals(Air) || e.getInventory().getItem(e.getSlot()) == null)
                return;
            ItemStack Item = ShardsAPI.getGUIItems().get(e.getSlot());
            int cost = ShardsAPI.getItems().get(Item);

            if(ShardsAPI.getShards((Player) e.getWhoClicked()) >= cost)
            {
                if(e.getWhoClicked().getInventory().firstEmpty() == -1)
                    e.getWhoClicked().getWorld().dropItemNaturally(e.getWhoClicked().getLocation(), Item);
                else
                    e.getWhoClicked().getInventory().addItem(Item);
                ShardsAPI.removeShards((Player) e.getWhoClicked(), cost, false);
            }
        }
    }
}
