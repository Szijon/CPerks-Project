package test.server.ririyo.cPerks.configs;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import test.server.ririyo.cPerks.CPerks;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class UserDataHandler {

        ///UPDATES OR CREATES A USER CONFIG | CALLED WHEN PLAYER IS JOINING
    public static void updateUserConfig(Player player, UUID uuid) {

        File file = new File(CPerks.getInstance().getDataFolder().getAbsolutePath() + "/players/" + uuid + "/general.yml");

        FileConfiguration cfg = DefaultUserConfig.createConfig(player, file);
        try {
            cfg.save(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

        ///GETS A USER'S CONFIG UPON ANY PERK INTERACTION ||| THIS MAY BE INEFFICIENT AND WILL BE IMPROVED LATER ON.
    public static FileConfiguration loadUserConfig(Player player, UUID uuid){
        File file = new File(CPerks.getInstance().getDataFolder().getAbsolutePath() + "/players/" + uuid + "/general.yml");
        if (!file.exists()) {
            updateUserConfig(player, uuid);
        }
        return YamlConfiguration.loadConfiguration(file);
    }

        ///SAVES USER CONFIG TO A FILE PATH IN THE PLUGIN'S FOLDER
    public static void saveUserConfig(FileConfiguration cfg, File file) {
        try {
            cfg.save(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
        ///GETS A KEY'S VALUE FROM A SET PATH IN A SPECIFIED CONFIG
    public static String get(Player player, UUID uuid, String string) {
        FileConfiguration cfg = loadUserConfig(player, uuid);
        return Objects.requireNonNull(cfg.get(string)).toString();
    }
        ///USED TO SET A KEY'S VALUE WITHIN A SPECIFIED USER'S CONFIG
    public static void set(Player player, UUID uuid, String string, Object value){
        FileConfiguration cfg = loadUserConfig(player, uuid);
        cfg.set(string, value);
        saveUserConfig(cfg, new File(CPerks.getInstance().getDataFolder().getAbsolutePath() + "/players/" + uuid + "/general.yml"));
    }

        ///USED TO GRAB PLAYER COINS (CURRENCY)
    public static int getPlayerCoins(Player player){
        return Integer.parseInt(UserDataHandler.get(player, player.getUniqueId(), "Player.Coins"));
    }
        ///USED TO UPDATE PLAYER'S COINS IN CONFIG
    public static void setPlayerCoins(Player player, int amount){
        UserDataHandler.set(player, player.getUniqueId(), "Player.Coins", amount);
    }
        ///USED TO GRAB PLAYER GOLD (DIFFERENT CURRENCY)
    public static int getPlayerGold(Player player){
        return Integer.parseInt(UserDataHandler.get(player, player.getUniqueId(), "Player.Gold"));
    }
        ///USED TO GET PLAYER'S GOLD AS A FORMATTED STRING - ANYTHING ABOVE 1000 GETS ABBREVIATED WITH "K" EXAMPLE: "1500" -> "1.5K"
    public static String getPlayerGoldString(Player player){
        int gold = Integer.parseInt(UserDataHandler.get(player, player.getUniqueId(), "Player.Gold"));
        if(gold >= 1000){
            return gold/1000 + "." + gold%1000/10 + "k";
        } else {
            return String.valueOf(gold);
        }
    }
        ///USED TO UPDATE PLAYER'S GOLD IN CONFIG
    public static void setPlayerGold(Player player, int amount){
        UserDataHandler.set(player, player.getUniqueId(), "Player.Gold", amount);
    }
        ///RETURNS SAVED BACKPACK CONTENTS
    public static ItemStack[] getBackpack (Player player, UUID uuid){
        FileConfiguration cfg = loadUserConfig(player, uuid);
        ArrayList<ItemStack> backpack = (ArrayList<ItemStack>) cfg.get("Backpack");
        if(backpack == null){
            return new ItemStack[Integer.parseInt(get(player, uuid, "Hunter.BackpackSize"))+9];

        }
        return backpack.toArray(new ItemStack[backpack.size()]);
    }
}
