package test.server.ririyo.cPerks.listerners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import test.server.ririyo.cPerks.CPerks;
import test.server.ririyo.cPerks.perks.perkmenu.MenuHandler;
import test.server.ririyo.cPerks.configs.UserDataHandler;

public class InventoryListener implements Listener {

    @EventHandler
    public void onClose(InventoryCloseEvent event) {

        Player player = (Player) event.getPlayer();
        if (player.hasMetadata("Opened-Menu")) {

            String metadata = player.getMetadata("Opened-Menu").get(0).asString();
            Inventory inventory = event.getInventory();

            if (metadata.equalsIgnoreCase("Backpack")) {
                UserDataHandler.set(player, player.getUniqueId(), "Backpack", inventory.getContents());
            } else if (metadata.equalsIgnoreCase("Selling Items")){
                MenuHandler.processSellingInterfaceClosure(player, inventory);
            }
            player.removeMetadata("Opened-Menu", CPerks.getInstance());
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        ///IF PLAYER CLICKS OUTSIDE OF INVENTORY
        Player player = (Player) event.getWhoClicked();
        if(event.getClickedInventory() == null || event.getClickedInventory() == player.getInventory()){
            return;
        }
        ///IF PLAYER IS INSIDE A MENU
        if(player.hasMetadata("Opened-Menu")) {
            String metadata = player.getMetadata("Opened-Menu").get(0).asString();
            Inventory inventory = event.getInventory();
            ItemStack clickedItem = inventory.getItem(event.getRawSlot());

            ///EXECUTES LOGIC DEPENDING ON THE SPECIFIC MENU THE PLAYER IS IN
            if (metadata.equalsIgnoreCase("Coin-Shop")) {
                event.setCancelled(true);
                if (clickedItem != null) {
                    MenuHandler.processCoinShopInteraction(player, inventory, clickedItem);
                }
            } else if (metadata.equalsIgnoreCase("Perk-Shop")){
                event.setCancelled(true);
                if (clickedItem != null) {
                    MenuHandler.processPerkShopInteraction(player, inventory, clickedItem);
                }
            } else if (metadata.equalsIgnoreCase("Gold-Shop")){
                event.setCancelled(true);
                if(clickedItem != null) {
                    MenuHandler.processGoldShopInteraction(player, inventory, clickedItem);
                }
            } else if(metadata.equalsIgnoreCase("Perk-Overview")){
                event.setCancelled(true);
                if(clickedItem != null){
                    MenuHandler.processPerkOverviewInteraction(player, inventory, clickedItem);
                }
            }
        }
    }
}
