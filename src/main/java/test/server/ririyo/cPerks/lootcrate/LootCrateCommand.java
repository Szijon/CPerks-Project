package test.server.ririyo.cPerks.lootcrate;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import test.server.ririyo.cPerks.CPerks;
import test.server.ririyo.cPerks.collections.CommandCollection;

import java.util.ArrayList;
import java.util.List;

public class LootCrateCommand implements CommandExecutor, TabCompleter {
    private final CPerks plugin;
    public LootCrateCommand(CPerks plugin) {
        this.plugin = plugin;
    }

    @Override
    ///USED TO GIVE A SPECIFIED PLAYER A LOOT CRATE BLOCK OR KEYS WITH A SPECIFIED TYPE AND AMOUNT. | OPERATOR ONLY
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        /// EXAMPLE COMMAND: /lc give [target] [crate/key] (amount) | amount is optional
        if(sender instanceof Player player) {
            if (player.isOp()) {
                switch (args.length) {
                    case 0, 1, 2:
                        sendErrorMessage(player);
                        break;
                    case 3, 4:
                        int amount = 1;
                        Player target = Bukkit.getPlayerExact(args[1]);

                        if (!args[0].equals("give") || target == null) {
                            CommandCollection.sendPlayerNotFoundMessage(player);
                            return true;
                        }
                        if (args.length == 4)
                            try {
                                amount = Integer.parseInt(args[3]);
                            } catch (Exception ignored) {
                                CommandCollection.sendNotANumberMessage(player);
                            }
                        switch (args[2]) {
                            case "crate":
                                LootCrateHandler.giveLootCrateBlock(target, amount);
                                break;
                            case "key:normal":
                                LootCrateHandler.giveLootCrateKey(target, amount, "Normal");
                                break;
                            case "key:rare":
                                LootCrateHandler.giveLootCrateKey(target, amount, "Rare");
                                break;
                            case "key:legendary":
                                LootCrateHandler.giveLootCrateKey(target, amount, "Legendary");
                                break;
                            case "key:gear":
                                LootCrateHandler.giveLootCrateKey(target, amount, "Gear");
                                break;
                        }
                        break;
                }
                return true;
            }
            CommandCollection.sendPlayerNoPermissionMessage(player);
            return true;
        }
        return false;
    }

    List<String> arguments = new ArrayList<>();
    @Override
    ///USED TO SUGGEST THE PLAYER WHICH ARGUMENTS ARE POSSIBLE TO USE. | OPERATOR ONLY
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (arguments.isEmpty()) {
            arguments.add("crate");
            arguments.add("key:normal");
            arguments.add("key:rare");
            arguments.add("key:legendary");
            arguments.add("key:gear");
        }

        if (sender instanceof Player player) {
            if (player.isOp()){
                List<String> result = new ArrayList<>();
                switch (args.length) {
                    case 1:
                        result.add("give");
                        break;
                    case 2:
                        return null; /// Returns all Players on The Server
                    case 3:
                        String curArg = args[args.length - 1];
                        result.addAll(CommandCollection.getMatches(curArg, arguments));
                        break;
                    case 4:
                        result.add("amount(optional)");
                        break;
                }
                return result;
            }
            else {
                return null;
            }
        }
        return null;
    }
        ///SENDS A USAGE HELP MESSAGE IF THE PLAYER ENTERS A FAULTY COMMAND.
    public void sendErrorMessage(Player player){
        player.sendMessage(ChatColor.RED + "/lc give [target] [crate/key] [amount(optional)]");
    }
}
