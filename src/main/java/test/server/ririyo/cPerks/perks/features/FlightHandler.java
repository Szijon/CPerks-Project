package test.server.ririyo.cPerks.perks.features;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import test.server.ririyo.cPerks.CPerks;
import test.server.ririyo.cPerks.configs.UserDataHandler;
import test.server.ririyo.cPerks.handlers.FormatHandler;
import test.server.ririyo.cPerks.handlers.PlayerMessageHandler;

import java.util.HashMap;
import java.util.UUID;

public class FlightHandler {

    public static HashMap<UUID, BukkitRunnable> activeTimers = new HashMap<>();
    private static HashMap<UUID, Integer> timeLeft = new HashMap<>();

        ///TOGGLES FLIGHT IF PLAYER HAS ENOUGH FLIGHT TIME AND STARTS THE FLIGHT TIMER
    public static void toggleFlight(Player player) {
        if(Boolean.parseBoolean(UserDataHandler.get(player, player.getUniqueId(), "Shop.Flight"))) {
            if (!player.getAllowFlight()) {
                player.sendMessage(ChatColor.BLUE + "Flight turned" + ChatColor.GREEN + " on!");
                player.setAllowFlight(true);
                int time = Integer.parseInt(UserDataHandler.get(player, player.getUniqueId(), "Player.Flight-Time"));
                timeLeft.put(player.getUniqueId(), time);
                startFlightTimer(player);
            } else {
                player.sendMessage(ChatColor.BLUE + "Flight turned" + ChatColor.RED + " off!");
                player.setAllowFlight(false);
                player.setFlying(false);
                if(timeLeft.get(player.getUniqueId()) != null) {
                    UserDataHandler.set(player, player.getUniqueId(), "Player.Flight-Time", timeLeft.get(player.getUniqueId()));
                }
                try {
                    activeTimers.get(player.getUniqueId()).cancel();
                    activeTimers.remove(player.getUniqueId());
                } catch (Exception ignored) {}
            }
        } else {
            player.sendMessage(ChatColor.BLUE + "You have" + ChatColor.RED + " not " + ChatColor.BLUE + "unlocked Flight yet.");
        }
    }

        ///STARTS AN ASYNC RECURSIVE FUNCTION THAT REDUCES THE FLIGHT TIME EACH SECOND
    public static void startFlightTimer(Player player){
        BukkitRunnable countdownTask = new BukkitRunnable() {
            public void run() {
                int remainingTime = timeLeft.get(player.getUniqueId());
                if (remainingTime <= 0) {
                    UserDataHandler.set(player, player.getUniqueId(), "Player.Flight-Time", 0);
                    player.setAllowFlight(false);
                    player.setFlying(false);
                    player.sendMessage(ChatColor.BLUE + "Your" + ChatColor.GREEN + " Flight-Time " + ChatColor.RED + "ran out.");
                    cancel();
                } else {
                    if(player.isFlying()) {
                        timeLeft.put(player.getUniqueId(), remainingTime - 1);
                        int[] time = FormatHandler.getTime(timeLeft.get(player.getUniqueId()));
                        String message = ChatColor.BLUE + "Flight-Time: " + ChatColor.GREEN + (time[0] * 24 + time[1]) + "h " + time[2] + "m " + time[3] + "s";
                        PlayerMessageHandler.sendActionBarMessage(player, message);
                    }
                }
            }
        };
        countdownTask.runTaskTimer(CPerks.getInstance(), 20, 20); //Updates Flight Time once per second
        activeTimers.put(player.getUniqueId(), countdownTask);
    }

        ///ADDS FLIGHT TIME UPON USING A FLIGHT CREDIT AND SENDS PLAYER A MESSAGE OF THE UPDATED FLIGHT TIME
    public static void addFlightTime(Player player, int amount){
        int initialTime = Integer.parseInt(UserDataHandler.get(player, player.getUniqueId(), "Player.Flight-Time"));

        if(player.getAllowFlight()){
            if(player.getGameMode() == GameMode.CREATIVE) {
                UserDataHandler.set(player, player.getUniqueId(), "Player.Flight-Time", initialTime + amount);
                PlayerMessageHandler.sendFlightTime(player);
                return;
            }
            try {
                int remainingTime = timeLeft.get(player.getUniqueId());
                timeLeft.put(player.getUniqueId(), remainingTime + amount);
                UserDataHandler.set(player, player.getUniqueId(), "Player.Flight-Time", initialTime + amount);
            } catch (Exception ignored){}
        }
        else{
            UserDataHandler.set(player, player.getUniqueId(), "Player.Flight-Time", initialTime + amount);
        }
        PlayerMessageHandler.sendFlightTime(player);
    }
}
