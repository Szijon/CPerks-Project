package test.server.ririyo.cPerks.configs;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import test.server.ririyo.cPerks.CPerks;

import java.io.File;
import java.io.IOException;

public class BlockDataHandler {
    ///AT THE MOMENT USED TO SIMPLY PROTECT AND FORWARD INTERACTIONS WITH LOOT CRATE BLOCK
        ///CREATES A BLOCK CONFIG IF NONE EXISTS YET. CALLED WHEN LOADING THE CONFIG.
    public static void createBlockConfig() {
        File file = new File(CPerks.getInstance().getDataFolder().getAbsolutePath() + "/blocks/general.yml");
        if (!file.exists()) {
            FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
            cfg.set("Loot-Crate", null);
            try {
                cfg.save(file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

        ///USED TO SET A BLOCK'S LOCATION OR REMOVE IT FROM THE CONFIG ENTIRELY.
    public static void set(String string, Object value){
        FileConfiguration cfg = loadBlockConfig();
        cfg.set(string, value);
        saveBlockConfig(cfg, new File(CPerks.getInstance().getDataFolder().getAbsolutePath() + "/blocks/general.yml"));
    }
        ///RETURNS A BLOCK'S LOCATION FROM CONFIG
    public static Location getLocation(String string){
        FileConfiguration cfg = loadBlockConfig();
        return cfg.getLocation(string);
    }
        ///LOADS THE BLOCK CONFIG FILE
    public static FileConfiguration loadBlockConfig(){
        File file = new File(CPerks.getInstance().getDataFolder().getAbsolutePath() + "/blocks/general.yml");
        if (!file.exists()) {
            createBlockConfig();
        }
        return YamlConfiguration.loadConfiguration(file);
    }
        ///SAVES TO THE BLOCK CONFIG FILE
    public static void saveBlockConfig(FileConfiguration cfg, File file) {
        try {
            cfg.save(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
