package test.server.ririyo.cPerks.handlers;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import test.server.ririyo.cPerks.perks.perkmenu.MenuCollection;
import test.server.ririyo.cPerks.configs.UserDataHandler;
import test.server.ririyo.cPerks.perks.features.TimeHandler;

public class PlayerMessageHandler {

    /// COLORS
    static ChatColor GREEN = ChatColor.GREEN;
    static ChatColor BLUE = ChatColor.BLUE;
    static ChatColor RED = ChatColor.RED;
    static ChatColor PURPLE = ChatColor.LIGHT_PURPLE;

        ///USED TO SEND A MESSAGE ABOVE THE PLAYER'S HOT-BAR ||| CURRENTLY USED FOR DISPLAYING REMAINING FLIGHT TIME
    public static void sendActionBarMessage(Player player, String message){
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacy(message));
    }
        ///SENDS THE PLAYER A MESSAGE WHEN THEY LEVEL UP A PERK
    public static void sendLevelUpMessage(Player player, String job, int level){
        player.sendMessage(BLUE + "You leveled up your " + GREEN + job + BLUE +  " perk to level " + level + "!");
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, 1);
    }
        ///SENDS THE PLAYER A MESSAGE WHEN THEY UNLOCK A NEW FEATURE (AT LEVEL 5 OR 15 OF PERKS)
    public static void sendFeatureUnlockMessage(Player player, String feature){
        player.sendMessage(BLUE + "You've unlocked the " + GREEN + feature + BLUE + " Feature!");
    }
        ///SENDS A MESSAGE WHEN THE CHANCE OR AMOUNT OF A FEATURE CHANGES
    public static void sendFeatureUpdateMessage(Player player, String feature, float amount){
        if(feature.equalsIgnoreCase("Refunding-Amount") || feature.equalsIgnoreCase("Egg-Hunter-Chance"))
            player.sendMessage(BLUE + "Your " + GREEN + feature + BLUE + " has increased to: " + GREEN + amount + "%");
        else
            player.sendMessage(BLUE + "Your " + GREEN + feature + BLUE + " has increased to: " + GREEN + Math.round(amount));
    }

        ///SENDS A SHOP PURCHASE CONFIRMATION MESSAGE
    public static void sendPurchaseSuccessMessage(Player player, String itemName){
        player.sendMessage(BLUE + "You successfully bought: " + GREEN + itemName + "!");
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 0.5f, 1);
    }
        ///SENDS A SHOP PURCHASE FAILURE MESSAGE
    public static void sendPurchaseFailureMessage(Player player, String itemName){
        player.sendMessage(BLUE + "You can not afford: " + RED + itemName + ".");
        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 0.5f, 1);
    }
        ///SENDS A MESSAGE WHEN UNLOCKING A NEW FEATURE IN SHOP SUCCEEDS
    public static void sendUnlockSuccessMessage(Player player, String feature){
        player.sendMessage(BLUE + "You successfully unlocked: " + GREEN + feature + "!");
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.5f, 1);
    }
        ///SENDS A MESSAGE WHEN PLAYER DOESN'T HAVE ENOUGH COINS TO UNLOCK A CLICKED FEATURE
    public static void sendUnlockFailureMessage(Player player, String feature){
        player.sendMessage(BLUE + "You can not afford: " + RED + feature + BLUE + ".");
        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 0.5f, 1);
    }
        ///SENDS A MESSAGE IF A FEATURE HAS ALREADY BEEN BOUGHT BUT IT STILL GETS CLICKED ON
    public static void sendAlreadyBoughtMessage(Player player, String unlock){
        String feature = MenuCollection.coinShopItemNames.get(unlock);
        player.sendMessage(BLUE + "You've already purchased: " + GREEN + feature + BLUE + ".");
        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 0.5f, 1);
    }
        ///SENDS A MESSAGE WHEN THE PLAYER DROPS A RARE ITEM LIKE A LOOT-KEY(0.033%) OR A SPAWN-EGG(LEVEL 15 HUNTER 0.1%)
    public static void sendRareDropMessage(Player player, String item){
        player.sendMessage(PURPLE + "RARE!" + " You've dropped a " + item + "!");
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);
    }
        ///SENDS A MESSAGE TO A PLAYER WHEN THEY USE THE COMMAND /FLIGHT TIME OR THEY ADD MORE FLIGHT TIME BY USING A FLIGHT CREDIT ITEM
    public static void sendFlightTime(Player player){
        int[] time = TimeHandler.getTime(Integer.parseInt(UserDataHandler.get(player, player.getUniqueId(), "Player.Flight-Time")));
        player.sendMessage(BLUE + "Flight Time: " + GREEN + time[0] +"h " + time[1] + "m " + time[2] + "s" );
    }
}
