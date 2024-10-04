package test.server.ririyo.cPerks.perks.perkmenu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;
import test.server.ririyo.cPerks.configs.UserDataHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ScoreboardHandler {

    public static HashMap<UUID, String> lastPerkUsed = new HashMap<>();

        ///USED TO UPDATE THE SCOREBOARD, A SMALL HUD ELEMENT THAT CAN DISPLAY TEXT.
    public static void updateScoreboard(Player player, String perk){
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();

        Objective objective = board.registerNewObjective("Perk Stats", "dummy", ChatColor.GOLD + "" + ChatColor.BOLD + "Perk Stats");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        String gold = UserDataHandler.getPlayerGoldString(player);
        int coins = UserDataHandler.getPlayerCoins(player);
        int perkLevelString = getPerkLevel(player, perk);
        String perkExpString = getExperienceString(player, perk);

        Score headLine = objective.getScore(ChatColor.BLUE + "#" + ChatColor.STRIKETHROUGH + "" + ChatColor.BLUE + "    " + ChatColor.RED);
        Score shop = objective.getScore(ChatColor.BLUE + "#" + " " + ChatColor.BOLD + "" + ChatColor.LIGHT_PURPLE  + "/perks");
        Score empty2 = objective.getScore(ChatColor.BLUE + "#");
        Score goldBal = objective.getScore(ChatColor.BLUE + "#" + " " + ChatColor.GOLD + "Gold: " + gold);
        Score coinBal = objective.getScore(ChatColor.BLUE + "#" + " " + ChatColor.GREEN + "Coins: " + coins);
        Score empty1 = objective.getScore(ChatColor.BLUE + "#" + " ");
        Score perkName = objective.getScore(ChatColor.BLUE + "#" + " " + ChatColor.LIGHT_PURPLE + "Perk: " + ChatColor.GREEN + perk);
        Score perkLevel = objective.getScore(ChatColor.BLUE + "#" + " " + ChatColor.LIGHT_PURPLE + "Level: " + ChatColor.GREEN + perkLevelString);
        Score perkExp = objective.getScore(ChatColor.BLUE + "#" + " " + ChatColor.LIGHT_PURPLE + "Exp: " + ChatColor.GREEN + perkExpString);
        Score bottomLine = objective.getScore(ChatColor.BLUE + "#" + ChatColor.STRIKETHROUGH + "" + ChatColor.BLUE + "        ");

        headLine.setScore(10);
        shop.setScore(9);
        empty2.setScore(8);
        goldBal.setScore(7);
        coinBal.setScore(6);
        empty1.setScore(5);
        perkName.setScore(4);
        perkLevel.setScore(3);
        perkExp.setScore(2);
        bottomLine.setScore(1);

        player.setScoreboard(board);
        lastPerkUsed.put(player.getUniqueId(), perk);
    }

    public static int getPerkLevel(Player player, String perk){
        return Integer.parseInt(UserDataHandler.get(player, player.getUniqueId(), perk + ".Level"));
    }
        ///NEEDS TO GO INTO THE FORMAT HANDLER LATER
    public static String getExperienceString(Player player, String perk){
        int exp = Integer.parseInt(UserDataHandler.get(player, player.getUniqueId(), perk + ".Experience"));
        int levelUp = Integer.parseInt(UserDataHandler.get(player, player.getUniqueId(), perk + ".Level-Up"));
        String expStr;
        String levelUpStr;

        if(exp >= 1000) {
            expStr = String.valueOf(exp / 1000);
            if (exp % 1000 / 10 > 0) {
                if(exp % 1000 / 10 < 10) {
                    ///ADDS A .0 BEFORE SINGLE DIGITS
                    expStr = expStr + ".0" + exp % 1000 / 10 + "k";
                } else {
                    expStr = expStr + "." + exp % 1000 / 10 + "k";
                }
            } else {
                expStr = expStr + "k";
            }
        }
        else
            expStr = String.valueOf(exp);

        if(levelUp >= 1000) {
            levelUpStr = String.valueOf(levelUp / 1000);
            if (exp % 1000 / 10 < 10) {
                ///ADDS A .0 BEFORE SINGLE DIGITS
                levelUpStr = levelUpStr + ".0" + levelUp % 1000 / 10 + "k";
            } else {
                levelUpStr = levelUpStr + "." + levelUp % 1000 / 10 + "k";
            }
        } else {
            levelUpStr = String.valueOf(levelUp);
        }

        return expStr + "/" + levelUpStr;
    }
}
