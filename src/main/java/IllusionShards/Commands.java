package IllusionShards;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

    @Override
    public
    boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().endsWith("Shards"))
        {
            if(!sender.hasPermission(command.getName().replace("Shards", "")))
            {
                sender.sendMessage(ChatColor.RED + "No Permission");
                return true;
            }
            if(args.length < 2)
                sender.sendMessage(ChatColor.RED + "Usage: /" + command.getName() + " <Player> <Shards>");
            if(args.length == 2)
            {
                if(!StringUtils.isNumeric(args[1]))
                {
                    sender.sendMessage(ChatColor.RED + "Please input a numerical amount of shards");
                    return true;
                }
                for(Player player : Bukkit.getServer().getOnlinePlayers())
                {
                    if(player.getName().equalsIgnoreCase(args[0]))
                    {
                        if(command.getName().equalsIgnoreCase("addShards"))
                            ShardsAPI.addShards(player, Integer.valueOf(args[0]), true);
                        if(command.getName().equalsIgnoreCase("removeShards"))
                            ShardsAPI.removeShards(player, Integer.valueOf(args[0]), true);
                        if(command.getName().equalsIgnoreCase("setShards"))
                            ShardsAPI.setShards(player, Integer.valueOf(args[0]));
                    }
                }
            }
            if(args.length > 2)
             sender.sendMessage(ChatColor.RED + "Too many args!");
        }

        if(command.getName().equalsIgnoreCase("shardShop"))
        {
            if(!(sender instanceof Player))
            {
                sender.sendMessage(ChatColor.RED + "You can only execute this command as a Player");
                return true;
            }
            Player p = (Player) sender;
            p.openInventory(ShardsGUI.getInventory());
        }
        return true;
    }
}
