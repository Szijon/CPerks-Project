package test.server.ririyo.cPerks.perks.perklogic;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import test.server.ririyo.cPerks.CPerks;
import test.server.ririyo.cPerks.handlers.PlayerMessageHandler;
import test.server.ririyo.cPerks.configs.UserDataHandler;

import java.util.Objects;

public class Hunter {
    

        ///IF THE CHANCE IS HIT DROPS A SPAWN EGG OF THE MOB THAT WAS KILLED
    public static void eggHunter(Player player, Entity entity, int multiplier){
        int eggChance = Math.round(Float.parseFloat(UserDataHandler.get(player, player.getUniqueId(), "Hunter.Egg-Hunter-Chance"))) * multiplier;
        if(eggChance > 0 && PerkLogic.getRandomChance(eggChance)){
            ItemStack eggDrop = new ItemStack(HunterCollection.entityEgg.get(entity.getType()), 1);
            entity.getWorld().dropItemNaturally(entity.getLocation(), eggDrop);
            PlayerMessageHandler.sendRareDropMessage(player, "Spawn-Egg", eggDrop.getAmount());
        }
    }
        ///LOADS BACKPACK CONTENTS FROM CONFIG AND OPENS THE INTERFACE FOR THE PLAYER IF UNLOCKED
    public static void openBackpack(Player player){
        if(Boolean.parseBoolean(UserDataHandler.get(player, player.getUniqueId(), "Hunter.Backpack"))){
            Inventory inventory = Bukkit.createInventory(player, Math.round(Float.parseFloat(UserDataHandler.get(player, player.getUniqueId(), "Hunter.Backpack-Size"))), ChatColor.DARK_PURPLE + "Backpack");
            if(!Objects.equals(UserDataHandler.get(player, player.getUniqueId(), "Backpack"), "0")){
                inventory.setContents(UserDataHandler.getBackpack(player, player.getUniqueId()));
            }
            player.openInventory(inventory);
            player.setMetadata("Opened-Menu", new FixedMetadataValue(CPerks.getInstance(), "Backpack"));
        } else {
            player.sendMessage(ChatColor.BLUE + "You have " + ChatColor.RED + "not" + ChatColor.BLUE + " Unlocked the " + ChatColor.GREEN + "Backpack" + ChatColor.BLUE + " feature yet.");
        }
    }
}
