package test.server.ririyo.cPerks.perks.perkmenu;

import org.bukkit.command.*;
import org.bukkit.entity.Player;
import test.server.ririyo.cPerks.CPerks;

public class PerksCommand implements CommandExecutor {

    private final CPerks plugin;
    public PerksCommand(CPerks plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player player) {
            if (args.length == 0) {
                MenuHandler.createInventoryOnCommand(player);
                return true;
            }
        }
        return false;
    }
}
