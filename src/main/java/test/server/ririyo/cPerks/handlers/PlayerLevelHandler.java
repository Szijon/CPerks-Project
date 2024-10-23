package test.server.ririyo.cPerks.handlers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import test.server.ririyo.cPerks.collections.CustomItemCollection;
import test.server.ririyo.cPerks.configs.UserDataHandler;
import test.server.ririyo.cPerks.lootcrate.LootCrateHandler;
import test.server.ririyo.cPerks.lootcrate.LootCrateKeyItem;
import test.server.ririyo.cPerks.perks.perklogic.PerkLogic;
import test.server.ririyo.cPerks.perks.perklogic.EnchanterCollection;
import test.server.ririyo.cPerks.perks.perklogic.HunterCollection;
import test.server.ririyo.cPerks.perks.perklogic.MinerCollection;
import test.server.ririyo.cPerks.perks.perklogic.WoodCutterCollection;
import test.server.ririyo.cPerks.perks.perkmenu.ScoreboardHandler;

public class PlayerLevelHandler {

    /// GET LEVEL DETAILS

    public static int getPlayerLevel(Player player, String job){
        return Integer.parseInt(UserDataHandler.get(player, player.getUniqueId(), job + ".Level"));
    }

    public static int getPlayerExperience(Player player, String job){
        return Integer.parseInt(UserDataHandler.get(player, player.getUniqueId(), job + ".Experience"));
    }

    public static int getPlayerTotalExperience(Player player, String job){
        return Integer.parseInt(UserDataHandler.get(player, player.getUniqueId(), job + ".Total-Experience"));
    }

    public static int getPlayerLevelUpExp(Player player, String job){
        return Integer.parseInt(UserDataHandler.get(player, player.getUniqueId(), job + ".Level-Up"));
    }

    /// SET LEVEL DETAILS

    public static void setPlayerLevel(Player player, String job, int level){
        UserDataHandler.set(player, player.getUniqueId(), job + ".Level", level);
    }

    public static void setPlayerExperience(Player player, String job, int experience){
        UserDataHandler.set(player, player.getUniqueId(), job + ".Experience", experience);
    }

    public static void setPlayerLevelUpExp(Player player, String job, int experience){
        UserDataHandler.set(player, player.getUniqueId(), job + ".Level-Up", experience);
    }

    public static void setPlayerTotalExperience(Player player, String job, int experience){
        UserDataHandler.set(player, player.getUniqueId(), job + ".Total-Experience", experience);
    }

        ///ADDS EXPERIENCE TO A PLAYER AND LEVEL'S THEM UP IF EXPERIENCE EXCEEDS NEEDED EXPERIENCE
    public static void addExperience(Player player, String job, int amount){
        int exp = getPlayerExperience(player, job);
        int levelUp = getPlayerLevelUpExp(player, job);

        exp += amount;
        setPlayerExperience(player, job, exp);
        if(exp >= levelUp && Integer.parseInt(UserDataHandler.get(player, player.getUniqueId(), job + ".Level")) < 30){
            levelUp(player, job);
        }

        float chance = Float.parseFloat(UserDataHandler.get(player, player.getUniqueId(), "Player.Key-Chance"));

        if(PerkLogic.getRandomChance(chance * amount)){
            LootCrateHandler.getRandomLootKeyDrop(player);
        }
        int gold = Integer.parseInt(UserDataHandler.get(player, player.getUniqueId(), "Player.Gold"));
        UserDataHandler.set(player, player.getUniqueId(), "Player.Gold", gold+amount);
        setPlayerTotalExperience(player, job, getPlayerTotalExperience(player, job) + amount);
        ScoreboardHandler.updateScoreboard(player, job);
    }
        ///LEVELS UP THE PLAYER AND UNLOCKS FEATURES IF REQUIREMENTS ARE MET. ALSO GIVES THEM 10 COINS FOR EACH LEVEL UP.
    public static void levelUp(Player player, String job){
        int level = getPlayerLevel(player, job);
        int exp = getPlayerExperience(player, job);
        int levelUp = getPlayerLevelUpExp(player, job);

        UserDataHandler.setPlayerCoins(player, UserDataHandler.getPlayerCoins(player)+10);

        level++;

        setPlayerLevel(player, job, level);
        setPlayerExperience(player, job, exp - levelUp);

        int neededLevelUpExp = PerkLogic.levelUps.get(level);
        setPlayerLevelUpExp(player, job, neededLevelUpExp);

        PlayerMessageHandler.sendLevelUpMessage(player, job, level);

        if(level >= 5){
            updateUnlockedFeatures(player, job, level);
        }

        if(level == 30){
            PerkLogic.putInPlayerInventory(player, LootCrateKeyItem.get(5, LootCrateKeyItem.LootKeyType.LEGENDARY));
            player.sendMessage(ChatColor.BLUE + "You've been given " + ChatColor.GREEN + "5" + ChatColor.GOLD + " Legendary" + ChatColor.GREEN + " Loot Crate Keys " + ChatColor.BLUE + "for reaching level 30 " + job + "!");
        }
    }

    /// RETURNS BOOLEAN IF FEATURE IS ENABLED OR NOT
    public static boolean getPlayerFeatureState(Player player, String job, String feature){
        return Boolean.parseBoolean(UserDataHandler.get(player, player.getUniqueId(), job + "." + feature));
    }

        /// SETS A FEATURE'S STATE IN USER CONFIG
    public static void setPlayerFeatureState(Player player, String job, String feature, boolean state){
        UserDataHandler.set(player, player.getUniqueId(), job + "." + feature, state);
    }
        /// SETS A FEATURE'S CHANCE TO HAPPEN OR AMOUNT IN USER CONFIG
    public static void setPlayerFeatureAmount(Player player, String job, String feature, float amount){
        UserDataHandler.set(player, player.getUniqueId(), job + "." + feature, amount);
    }


        /// LOGIC TO UNLOCK FEATURES IN THE USER'S CONFIG   ///TO BE UPDATED
    public static void updateUnlockedFeatures(Player player, String job, int level){
        switch(job){

            case("Woodcutter"):
                if (WoodCutterCollection.veinMineBlocks.containsKey(level)) {
                    String feature = "Vein-Miner-Amount";
                    int woodcutterBlocks = WoodCutterCollection.veinMineBlocks.get(level);
                    setPlayerFeatureAmount(player, job, "Vein-Miner-Amount", woodcutterBlocks);
                    PlayerMessageHandler.sendFeatureUpdateMessage(player, feature, woodcutterBlocks);
                }
                if (level == 5){
                    String feature = "Vein-Miner";
                    setPlayerFeatureState(player, job, "Vein-Miner", true);
                    PlayerMessageHandler.sendFeatureUnlockMessage(player, feature);
                } else if (level == 15) {
                    String feature = "Replant";
                    setPlayerFeatureState(player, job, "Replant", true);
                    PlayerMessageHandler.sendFeatureUnlockMessage(player, feature);
                }

                break;

            case("Miner"):
                if (level == 5){
                    String feature = "Vein-Miner";
                    setPlayerFeatureState(player, job, feature, true);
                    PlayerMessageHandler.sendFeatureUnlockMessage(player, feature);
                } else if (level == 15) {
                    String feature = "Auto-Smelt";
                    setPlayerFeatureState(player, job, feature, true);
                    PlayerMessageHandler.sendFeatureUnlockMessage(player, feature);
                }
                if (MinerCollection.veinMineBlocks.containsKey(level)) {
                    String feature = "Vein-Miner-Amount";
                    int minerBlocks = MinerCollection.veinMineBlocks.get(level);
                    setPlayerFeatureAmount(player, job, feature, minerBlocks);
                    PlayerMessageHandler.sendFeatureUpdateMessage(player, feature, minerBlocks);
                }
                break;

            case("Farmer"):
                if (level == 5){
                    String feature = "3x3-Harvest";
                    setPlayerFeatureState(player, job, feature, true);
                    PlayerMessageHandler.sendFeatureUnlockMessage(player, feature);
                } else if (level == 15){
                    String feature = "Replant";
                    setPlayerFeatureState(player, job, feature, true);
                    PlayerMessageHandler.sendFeatureUnlockMessage(player, feature);
                }
                break;

            case("Enchanter"):
                if (level == 5){
                    String feature = "Refunding";
                    setPlayerFeatureState(player, job, feature, true);
                    PlayerMessageHandler.sendFeatureUnlockMessage(player, feature);
                } else if (level == 30){
                    String feature = "Extra-Experience";
                    setPlayerFeatureState(player, job, feature, true);
                    PlayerMessageHandler.sendFeatureUnlockMessage(player, feature);
                }
                if (EnchanterCollection.refundingAmount.containsKey(level)) {
                    String feature = "Refunding-Amount";
                    int refundAmount = EnchanterCollection.refundingAmount.get(level);
                    setPlayerFeatureAmount(player, job, feature, refundAmount);
                    PlayerMessageHandler.sendFeatureUpdateMessage(player, feature, refundAmount);
                }
                break;

            case("Hunter"):
                if (level == 5){
                    String feature = "Backpack";
                    setPlayerFeatureState(player, job, feature, true);
                    PlayerMessageHandler.sendFeatureUnlockMessage(player, feature);
                } else if (level == 15){
                    String feature = "Egg-Hunter";
                    setPlayerFeatureState(player, job, feature, true);
                    PlayerMessageHandler.sendFeatureUnlockMessage(player, feature);
                }
                if (HunterCollection.backpackLevels.containsKey(level)){
                    String feature = "Backpack-Size";
                    int backpackSize = HunterCollection.backpackLevels.get(level);
                    setPlayerFeatureAmount(player, job, "Backpack-Size", backpackSize);
                    PlayerMessageHandler.sendFeatureUpdateMessage(player, feature, backpackSize);
                }
                if (HunterCollection.eggDropChances.containsKey(level)){
                    String feature = "Egg-Hunter-Chance";
                    float eggChance = HunterCollection.eggDropChances.get(level);
                    setPlayerFeatureAmount(player, job, feature, eggChance);
                    PlayerMessageHandler.sendFeatureUpdateMessage(player, feature, eggChance);
                }
                break;
        }
    }
}
