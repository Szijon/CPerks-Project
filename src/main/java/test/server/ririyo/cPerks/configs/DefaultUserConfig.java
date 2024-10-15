package test.server.ririyo.cPerks.configs;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class DefaultUserConfig {


        ///CREATES A CONFIG UPON USER JOINING. ALSO UPDATES ANY NEW KEYS TO A USER CONFIG UPON JOINING.
    public static FileConfiguration createConfig(Player player, File file){

        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        ConfigHandler.addValue(cfg, "Player.Name", player.getName());
        ConfigHandler.addValue(cfg, "Player.Gold", 0);
        ConfigHandler.addValue(cfg, "Player.Coins", 0);
        ConfigHandler.addValue(cfg, "Player.Flight-Time", 900);     // Time in seconds
        ConfigHandler.addValue(cfg, "Player.Key-Chance", 0.033f);

        ConfigHandler.addValue(cfg, "Shop.Spawner", false);
        ConfigHandler.addValue(cfg, "Shop.Keep-Inventory", false);
        ConfigHandler.addValue(cfg, "Shop.Keep-Experience", false);
        ConfigHandler.addValue(cfg, "Shop.Flight", false);
        ConfigHandler.addValue(cfg, "Shop.Glow", false);

        ConfigHandler.addValue(cfg, "Miner.Level", 0);
        ConfigHandler.addValue(cfg, "Miner.Experience", 0);
        ConfigHandler.addValue(cfg, "Miner.Level-Up", 100);
        ConfigHandler.addValue(cfg, "Miner.Total-Experience", 0);
        ConfigHandler.addValue(cfg, "Miner.Vein-Miner", false);
        ConfigHandler.addValue(cfg, "Miner.Vein-Miner-Amount", 0);
        ConfigHandler.addValue(cfg, "Miner.Auto-Smelt", false);

        ConfigHandler.addValue(cfg, "Woodcutter.Level", 0);
        ConfigHandler.addValue(cfg, "Woodcutter.Experience", 0);
        ConfigHandler.addValue(cfg, "Woodcutter.Level-Up", 100);
        ConfigHandler.addValue(cfg, "Woodcutter.Total-Experience", 0);
        ConfigHandler.addValue(cfg, "Woodcutter.Vein-Miner", false);
        ConfigHandler.addValue(cfg, "Woodcutter.Vein-Miner-Amount", 0);
        ConfigHandler.addValue(cfg, "Woodcutter.Replant", false);

        ConfigHandler.addValue(cfg, "Enchanter.Level", 0);
        ConfigHandler.addValue(cfg, "Enchanter.Experience", 0);
        ConfigHandler.addValue(cfg, "Enchanter.Level-Up", 100);
        ConfigHandler.addValue(cfg, "Enchanter.Total-Experience", 0);
        ConfigHandler.addValue(cfg, "Enchanter.Refunding", false);
        ConfigHandler.addValue(cfg, "Enchanter.Refunding-Amount", 0);
        ConfigHandler.addValue(cfg, "Enchanter.Extra-Experience", false);

        ConfigHandler.addValue(cfg, "Farmer.Level", 0);
        ConfigHandler.addValue(cfg, "Farmer.Experience", 0);
        ConfigHandler.addValue(cfg, "Farmer.Level-Up", 50);
        ConfigHandler.addValue(cfg, "Farmer.Total-Experience", 0);
        ConfigHandler.addValue(cfg, "Farmer.3x3-Harvest", false);
        ConfigHandler.addValue(cfg, "Farmer.Replant", false);

        ConfigHandler.addValue(cfg, "Hunter.Level", 0);
        ConfigHandler.addValue(cfg, "Hunter.Experience", 0);
        ConfigHandler.addValue(cfg, "Hunter.Level-Up", 100);
        ConfigHandler.addValue(cfg, "Hunter.Total-Experience", 0);
        ConfigHandler.addValue(cfg, "Hunter.Backpack", false);
        ConfigHandler.addValue(cfg, "Hunter.Backpack-Size", 0);
        ConfigHandler.addValue(cfg, "Hunter.Hunted-Amount", 0);
        ConfigHandler.addValue(cfg, "Hunter.Egg-Hunter", false);
        ConfigHandler.addValue(cfg, "Hunter.Egg-Hunter-Chance", 0);

        ConfigHandler.addValue(cfg, "Backpack", 0);

        return cfg;
    }
}
