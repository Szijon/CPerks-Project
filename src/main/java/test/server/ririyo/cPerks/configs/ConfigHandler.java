package test.server.ririyo.cPerks.configs;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigHandler {

    ///ADDS KEYS TO CONFIG IF THEY DON'T ALREADY EXIST.
    public static void addValue(FileConfiguration config, String path, Object value){
        if(!config.contains(path)){
            config.set(path, value);
        }
    }
}
