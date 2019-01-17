package IllusionShards;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Shards extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new Events(), this);
        saveDefaultConfig();
        ShardsGUI.setUpInventory();
        getCommand("addShards").setExecutor(new Commands());
        getCommand("removeShards").setExecutor(new Commands());
        getCommand("setShards").setExecutor(new Commands());
        getCommand("shardShop").setExecutor(new Commands());
    }

    @Override
    public void onDisable()
    {
        try {
            shards.save(shardsf);
            config.save(configf);
        } catch (IOException var2) {
            var2.printStackTrace();
        }
    }
    public static FileConfiguration getShardsConfig() {
        return shards;
    }
    public static FileConfiguration getShardPluginConfig(){ return config; }

    private static FileConfiguration config;
    private File configf;
    private static FileConfiguration shards;
    private File shardsf;

    private void createFiles() {
        shardsf = new File(getDataFolder(), "shards.yml");
        configf = new File(getDataFolder(), "config.yml");

        if (!shardsf.exists()) {
            shardsf.getParentFile().mkdirs();
            saveResource("shards.yml", false);
        }
        if (!configf.exists()) {
            configf.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }
        shards = new YamlConfiguration();
        config = new YamlConfiguration();
        try {
            shards.load(shardsf);
            config.load(configf);
            shards.options().copyDefaults(true);
        } catch (InvalidConfigurationException | IOException var2) {
            var2.printStackTrace();
        }
    }
}
