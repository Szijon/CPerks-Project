package test.server.ririyo.cPerks.collections;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandCollection {

    ///FUNCTIONS FOR WHEN THE USER IS USING COMMANDS

        ///GETS MATCH FOR SUGGESTED COMPLETION UPON ENTERING SUB-STRINGS OF ARGUMENTS IN A COMMAND
    public static List<String> getMatches(String chatEntry, List<String> arguments){
        List<String> result = new ArrayList<>();
        for (String argument : arguments) {
            if (argument.toLowerCase().startsWith(chatEntry.toLowerCase())) {
                result.add(argument);
            }
        }
        return result;
    }

    public static void sendPlayerNotFoundMessage(Player player){
        player.sendMessage(ChatColor.RED + "Target Player not found.");
    }

    public static void sendNotANumberMessage(Player player){
        player.sendMessage(ChatColor.RED + "Input is not a valid number.");
    }

    public static void sendPlayerNoPermissionMessage(Player player){
        player.sendMessage(ChatColor.RED + "You do not have the Permission for this command.");
    }
}
