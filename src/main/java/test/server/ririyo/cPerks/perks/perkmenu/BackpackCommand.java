package test.server.ririyo.cPerks.perks.perkmenu;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import test.server.ririyo.cPerks.CPerks;
import test.server.ririyo.cPerks.perks.perklogic.Hunter;


public class BackpackCommand implements CommandExecutor {

    private final CPerks plugin;
    public BackpackCommand(CPerks plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player player){
            Hunter.openBackpack(player);

        } else {
            sender.sendMessage("This is a player only command.");
        }

        return true;
    }
}
