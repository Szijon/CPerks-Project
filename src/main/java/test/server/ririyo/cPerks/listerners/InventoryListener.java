package test.server.ririyo.cPerks.listerners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import test.server.ririyo.cPerks.CPerks;
import test.server.ririyo.cPerks.collections.NamespacedKeyCollection;
import test.server.ririyo.cPerks.perks.perkmenu.MenuHandler;
import test.server.ririyo.cPerks.configs.UserDataHandler;
import test.server.ririyo.cPerks.perks.perkmenu.SaleMenu;

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
                SaleMenu.closedWithoutConfirm(player, inventory);
            }
            player.removeMetadata("Opened-Menu", CPerks.getInstance());
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        ///IF PLAYER CLICKS OUTSIDE OF INVENTORY
        Player player = (Player) event.getWhoClicked();
        if(event.getClickedInventory() == null){
            return;
        }
        if(event.getClickedInventory() == player.getInventory() && !player.hasMetadata("Opened-Menu")){
            return;
        } else if(event.getClickedInventory() == player.getInventory() && player.hasMetadata("Opened-Menu")) {
            String metadata = player.getMetadata("Opened-Menu").get(0).asString();
            if(!metadata.equalsIgnoreCase("Selling Items")){
                return;
            }
        }

        ///IF PLAYER IS INSIDE A MENU
        if(player.hasMetadata("Opened-Menu")) {
            String metadata = player.getMetadata("Opened-Menu").get(0).asString();
            Inventory inventory = event.getInventory();
            ItemStack clickedItem = null;
            if(event.getClickedInventory() != player.getInventory()) {
                clickedItem = inventory.getItem(event.getSlot());
            }

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
            } else if(metadata.equalsIgnoreCase("Selling Items")){
                int slot = event.getSlot();
                ItemStack item = event.getInventory().getItem(event.getSlot());
                SaleMenu.updateReturnItems(player, inventory, 1);
                if(slot > 26 && event.getClickedInventory() != player.getInventory()) {
                    event.setCancelled(true);
                }
                if(item != null) {
                    if (slot < 35 && item.hasItemMeta() && event.getClickedInventory() != player.getInventory()) {
                        ItemMeta meta = item.getItemMeta();
                        PersistentDataContainer pdc = meta.getPersistentDataContainer();
                        if (pdc.has(NamespacedKeyCollection.ConfirmKey)) {
                            SaleMenu.confirmSale(player, inventory);
                        }
                    }
                }

            }
        }
    }
}
