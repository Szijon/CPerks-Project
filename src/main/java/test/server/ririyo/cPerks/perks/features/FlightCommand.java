package test.server.ririyo.cPerks.perks.features;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import test.server.ririyo.cPerks.CPerks;
import test.server.ririyo.cPerks.handlers.PlayerMessageHandler;

public class FlightCommand implements CommandExecutor {

    private final CPerks plugin;
    public FlightCommand(CPerks plugin) {
        this.plugin = plugin;
    }

    /// COMMAND TO TOGGLE FLIGHT IN-GAME IF YOU HAVE ENOUGH FLIGHT TIME
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player player){
            if(args.length == 0) {
                /// /flight -> TOGGLES FLIGHT IF THE PLAYER HAS ENOUGH FLIGHT TIME
                FlightHandler.toggleFlight(player);
            } else if(args.length == 1){
                /// /flight time -> SENDS PLAYER A MESSAGE WITH THEIR REMAINING FLIGHT TIME
                if(args[0].equalsIgnoreCase("time") || args[0].equalsIgnoreCase("zeit")) {
                    PlayerMessageHandler.sendFlightTime(player);
                }
            }
            return true;
        } else {
            ///IF COMMAND IS SENT FROM THE CONSOLE AS CONSOLE CAN'T FLY.
            sender.sendMessage("Only players may use this command.");
        }

        return false;
    }
}
