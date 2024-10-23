package test.server.ririyo.cPerks.perks.perkmenu;

import org.bukkit.ChatColor;

import java.util.Map;

public class MenuCollection {

    /// SHOPS
        /// COINS SHOP
    public static Map<String, Integer> coinShopPrices = Map.ofEntries(
        Map.entry("Spawner", 100),
        Map.entry("Keep-Inventory", 150),
        Map.entry("Keep-Experience", 150),
        Map.entry("Flight", 200),
        Map.entry("Glow", 100)
    );

    public static Map<String, String> coinShopItemNames = Map.ofEntries(
        Map.entry("Spawner", "Silk-Touch Spawners"),
        Map.entry("Keep-Inventory", "Keep-Inventory"),
        Map.entry("Keep-Experience", "Keep-Experience"),
        Map.entry("Flight", "Flight"),
        Map.entry("Glow", "Glow")
    );

    public static String[] spawnerDescription = {ChatColor.BLUE + "Allows Silk-Touch Mining for Spawners",
                                                ChatColor.BLUE + "Costs: " + ChatColor.DARK_PURPLE + "30" + ChatColor.BLUE + " Levels."};

    public static String[] keepInvDescription = {ChatColor.BLUE + "Prevents Item Loss on Death.",
                                                ChatColor.BLUE + "Does " + ChatColor.RED + "NOT" + ChatColor.BLUE + " keep Exp on Death."};

    public static String[] keepExpDescription = {ChatColor.BLUE + "Prevents Exp Loss on Death.",
                                                ChatColor.BLUE + "Does " + ChatColor.RED + "NOT" + ChatColor.BLUE + " keep Items on Death."};

    public static String[] flightDescription = {ChatColor.BLUE + "Allows toggling Flight.",
                                                ChatColor.BLUE + "Use" + ChatColor.GREEN + " /flight " + ChatColor.BLUE + "to Toggle.",
                                                ChatColor.BLUE + "Use" + ChatColor.GREEN + " /flight time " + ChatColor.BLUE + "to check Time left."};

    public static String[] glowDescription = {ChatColor.BLUE + "Allows toggling Glow Effect."};

        /// GOLD SHOP
    public static Map<String, Integer> goldShopPrices = Map.ofEntries(
            Map.entry("Normal Loot Key", 5000),
            Map.entry("Rare Loot Key", 25000),
            Map.entry("Legendary Loot Key", 75000),
            Map.entry("Mythic Loot Key", 500000),
            Map.entry("Flight Credit", 2500)
    );
}
