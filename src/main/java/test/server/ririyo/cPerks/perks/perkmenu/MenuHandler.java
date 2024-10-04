package test.server.ririyo.cPerks.perks.perkmenu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import test.server.ririyo.cPerks.CPerks;
import test.server.ririyo.cPerks.collections.CustomItemCollection;
import test.server.ririyo.cPerks.collections.NamespacedKeyCollection;
import test.server.ririyo.cPerks.handlers.PlayerMessageHandler;
import test.server.ririyo.cPerks.configs.UserDataHandler;


public class MenuHandler {


    ///PERK OVERVIEW
        ///CREATE INVENTORY
    public static void createInventoryOnCommand(Player player){
        Inventory inv = Bukkit.createInventory(player, 6*9, ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "PERKS");
        updateMenuInventory(player, "Perk Overview", inv);
        player.openInventory(inv);
    }
    public static void setPerkOverviewInventory(Player player, Inventory inv){
        /// Set Background of the Inventory to grey
        MenuHandler.clearInventory(player, inv);

        /// 1ST ROW
        inv.setItem(8, CustomItemCollection.getPerkShopItem());

        /// 2ND ROW (+9 Slots)
        inv.setItem(2 + 9, CustomItemCollection.getPerkItem(player, "Woodcutter"));
        inv.setItem(3 + 9, CustomItemCollection.getPerkItem(player, "Miner"));
        inv.setItem(4 + 9, CustomItemCollection.getPerkItem(player, "Farmer"));
        inv.setItem(5 + 9, CustomItemCollection.getPerkItem(player, "Enchanter"));
        inv.setItem(6 + 9, CustomItemCollection.getPerkItem(player, "Hunter"));

        /// 3RD ROW (+18 Slots)

        inv.setItem(2 + 9*3, CustomItemCollection.getFeatureItem(player, "Woodcutter", "Vein-Miner"));
        inv.setItem(3 + 9*3, CustomItemCollection.getFeatureItem(player, "Miner", "Vein-Miner"));
        inv.setItem(4 + 9*3, CustomItemCollection.getFeatureItem(player, "Farmer", "3x3-Harvest"));
        inv.setItem(5 + 9*3, CustomItemCollection.getFeatureItem(player, "Enchanter", "Refunding"));
        inv.setItem(6 + 9*3, CustomItemCollection.getFeatureItem(player, "Hunter", "Backpack"));

        /// 4TH ROW (+27 Slots)

        inv.setItem(2 + 9*4, CustomItemCollection.getFeatureItem(player, "Woodcutter", "Replant"));
        inv.setItem(3 + 9*4, CustomItemCollection.getFeatureItem(player, "Miner", "Auto-Smelt"));
        inv.setItem(4 + 9*4, CustomItemCollection.getFeatureItem(player, "Farmer", "Replant"));
        inv.setItem(5 + 9*4, CustomItemCollection.getFeatureItem(player, "Enchanter", "Extra-Experience"));
        inv.setItem(6 + 9*4, CustomItemCollection.getFeatureItem(player, "Hunter", "Egg-Hunter"));
        inv.setItem(inv.getSize()-1, CustomItemCollection.getSellInterfaceItem());


        player.setMetadata("Opened-Menu", new FixedMetadataValue(CPerks.getInstance(), "Perk-Overview"));
        player.updateInventory();
    }

    public static void processPerkOverviewInteraction(Player player, Inventory inventory, ItemStack item){
        ItemMeta meta = item.getItemMeta();
        if(meta != null) {
            PersistentDataContainer pdc = meta.getPersistentDataContainer();

            if (pdc.has(NamespacedKeyCollection.PerkShopKey)) {
                updateMenuInventory(player, "Perk Shop", inventory);
            } else if (pdc.has(NamespacedKeyCollection.SellKey)) {
                updateMenuInventory(player, "Selling Items", inventory);
            }else {
                try {
                    NamespacedKey featureKey = pdc.getKeys().stream().findFirst().get();
                    boolean unlocked = Boolean.TRUE.equals(pdc.get(featureKey, PersistentDataType.BOOLEAN));
                    if (unlocked) {
                        PerkMenuCollection.toggleOnFeatureClick(player, inventory, featureKey);
                    }
                } catch (Exception ignored) {
                } /// Item does not have a linked feature and PDC which is used to toggle the state of said feature.
            }
        }
    }

    /// PERK SHOP

    public static void setPerkShopInventory(Player player, Inventory inventory){
        clearInventory(player, inventory);
        inventory.setItem(3 + 9*2, CustomItemCollection.getCoinShopItem());
        inventory.setItem(5 + 9*2, CustomItemCollection.getGoldShopItem());
        inventory.setItem(0, CustomItemCollection.getPerkOverviewItem());

        player.setMetadata("Opened-Menu", new FixedMetadataValue(CPerks.getInstance(), "Perk-Shop"));
        player.updateInventory();
    }

    public static void processPerkShopInteraction(Player player, Inventory inventory, ItemStack clickedItem){
        ItemMeta meta = clickedItem.getItemMeta();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();

        if(pdc.has(NamespacedKeyCollection.CoinShopKey)){
            updateMenuInventory(player, "Coin Shop", inventory);
        } else if(pdc.has(NamespacedKeyCollection.GoldShopKey)) {
            updateMenuInventory(player, "Gold Shop", inventory);
        } else if(pdc.has(NamespacedKeyCollection.PerkOverviewKey)){
            updateMenuInventory(player, "Perk Overview", inventory);
        }
    }


    /// COIN SHOP

    public static void setCoinShopInventory(Player player, Inventory inventory){
        clearInventory(player, inventory);

        inventory.setItem(0, CustomItemCollection.getPerkShopItem());
        inventory.setItem(8, PerkMenuCollection.getCoinBalanceItem(player));
        inventory.setItem(1 + 9*2, PerkMenuCollection.getKeepInventoryItem(player));
        inventory.setItem(3 + 9*2, PerkMenuCollection.getSpawnerItem(player));
        inventory.setItem(5 + 9*2, PerkMenuCollection.getKeepExpItem(player));
        inventory.setItem(7 + 9*2, PerkMenuCollection.getFlightItem(player));

        player.setMetadata("Opened-Menu", new FixedMetadataValue(CPerks.getInstance(), "Coin Shop"));
        player.updateInventory();
    }

    public static void processCoinShopInteraction(Player player, Inventory inventory, ItemStack clickedItem){
        ItemMeta meta = clickedItem.getItemMeta();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        String unlock;
        boolean unlocked;

        if (pdc.has(NamespacedKeyCollection.SpawnerKey)) {
            unlocked = Boolean.TRUE.equals(pdc.get(NamespacedKeyCollection.SpawnerKey, PersistentDataType.BOOLEAN));
            unlock = "Spawner";
        } else if (pdc.has(NamespacedKeyCollection.KeepInvKey)) {
            unlocked = Boolean.TRUE.equals(pdc.get(NamespacedKeyCollection.KeepInvKey, PersistentDataType.BOOLEAN));
            unlock = "Keep-Inventory";
        } else if (pdc.has(NamespacedKeyCollection.KeepExpKey)) {
            unlocked = Boolean.TRUE.equals(pdc.get(NamespacedKeyCollection.KeepExpKey, PersistentDataType.BOOLEAN));
            unlock = "Keep-Experience";
        } else if (pdc.has(NamespacedKeyCollection.FlightKey)) {
            unlocked = Boolean.TRUE.equals(pdc.get(NamespacedKeyCollection.FlightKey, PersistentDataType.BOOLEAN));
            unlock = "Flight";
        } else if (pdc.has(NamespacedKeyCollection.PerkShopKey)) {
            updateMenuInventory(player, "Perk Shop", inventory);
            return;
        }  else {
            return;
        }

        if (!unlocked) {
            if (MenuHandler.attemptUnlock(player, unlock)) {
                MenuHandler.updateMenuInventory(player, "Coin Shop", inventory);
            }
        } else {
            PlayerMessageHandler.sendAlreadyBoughtMessage(player, unlock);
        }
    }


    /// GOLD SHOP

    public static void setGoldShopInventory(Player player, Inventory inventory){

        inventory.setItem(0, CustomItemCollection.getPerkShopItem());
        inventory.setItem(8, PerkMenuCollection.getGoldBalanceItem(player));
        inventory.setItem(3 + 9*2, PerkMenuCollection.getLootKeyShopItem());
        inventory.setItem(5 + 9*2, PerkMenuCollection.getFlightCreditShopItem());

        player.setMetadata("Opened-Menu", new FixedMetadataValue(CPerks.getInstance(), "Gold Shop"));
        player.updateInventory();
    }

    public static void processGoldShopInteraction(Player player, Inventory inventory, ItemStack clickedItem){
        ItemMeta meta = clickedItem.getItemMeta();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        String itemName = null;
        if (pdc.has(NamespacedKeyCollection.LootKeyKey)) {
            itemName = "Loot Key";
        } else if (pdc.has(NamespacedKeyCollection.FlightCreditKey)) {
            itemName = "Flight Credit";
        }else if (pdc.has(NamespacedKeyCollection.PerkShopKey)) {
            updateMenuInventory(player, "Perk Shop", inventory);
            return;
        }
        if(itemName != null) {
            attemptPurchase(player, itemName);
            updateMenuInventory(player, "Gold Shop", inventory);
        }
    }

    /// SELL ITEMS MENU

    public static void setSellInventory(Player player, Inventory inventory){
        for(int i = 0; i < inventory.getSize(); i++){
            inventory.setItem(i , new ItemStack(Material.AIR));
        }
        player.setMetadata("Opened-Menu", new FixedMetadataValue(CPerks.getInstance(), "Selling Items"));
        player.updateInventory();
    }

    public static void processSellingInterfaceClosure(Player player, Inventory inventory){
        Inventory playerInventory = player.getInventory();

        for(ItemStack item : inventory.getContents()){
            if(item != null) {
                if (SaleMenuCollection.prices.containsKey(item.getType())) {
                    UserDataHandler.setPlayerGold(player, UserDataHandler.getPlayerGold(player) + SaleMenuCollection.prices.get(item.getType()));
                } else {
                    if (player.getInventory().firstEmpty() != -1) {
                        playerInventory.addItem(item);
                    } else {
                        player.getLocation().getWorld().dropItemNaturally(player.getLocation(), item);
                    }
                }
            }
        }
        ScoreboardHandler.updateScoreboard(player, ScoreboardHandler.lastPerkUsed.get(player.getUniqueId()));
    }

    /// ALL SHOPS

    public static void clearInventory(Player player, Inventory inventory) {
        for(int i = 0; i < inventory.getSize(); i++){
            inventory.setItem(i, CustomItemCollection.getBackgroundItem());
        }
        player.updateInventory();
    }

    public static void updateMenuInventory(Player player, String menu, Inventory inventory) {
        clearInventory(player, inventory);

        if(menu.equalsIgnoreCase("Perk Shop")){
            setPerkShopInventory(player, inventory);

        } else if(menu.equalsIgnoreCase("Coin Shop")) {
            setCoinShopInventory(player, inventory);

        } else if(menu.equalsIgnoreCase("Gold Shop")){
            setGoldShopInventory(player, inventory);

        } else if(menu.equalsIgnoreCase("Perk Overview")){
            setPerkOverviewInventory(player, inventory);

        } else if(menu.equalsIgnoreCase("Selling Items")){
            setSellInventory(player, inventory);
        }
    }

    public static boolean attemptPurchase(Player player, String itemName) {

        int price = MenuCollection.goldShopPrices.get(itemName);
        if (UserDataHandler.getPlayerGold(player) >= price) {
            ItemStack item;
            if (itemName.equalsIgnoreCase("Loot Key")) {
                item = CustomItemCollection.createLootKey(1, "Normal");
                if (player.getInventory().firstEmpty() != -1)
                    player.getInventory().addItem(CustomItemCollection.createLootKey(1, "Normal"));
                else
                    player.getLocation().getWorld().dropItemNaturally(player.getLocation(), CustomItemCollection.createLootKey(1, "Normal"));
                PlayerMessageHandler.sendPurchaseSuccessMessage(player, item.getItemMeta().getDisplayName());
                ScoreboardHandler.updateScoreboard(player, ScoreboardHandler.lastPerkUsed.get(player.getUniqueId()));
            }

            if (itemName.equalsIgnoreCase("Flight Credit")) {
                item = CustomItemCollection.getFlightCredit(900);
                if (player.getInventory().firstEmpty() != -1)
                    player.getInventory().addItem(item);
                else
                    player.getLocation().getWorld().dropItemNaturally(player.getLocation(), item);
                PlayerMessageHandler.sendPurchaseSuccessMessage(player, item.getItemMeta().getDisplayName());
            }

            UserDataHandler.setPlayerGold(player, UserDataHandler.getPlayerGold(player) - price);
            return true;
        } else {
            PlayerMessageHandler.sendPurchaseFailureMessage(player, itemName);
        }
        return false;
    }

    public static boolean attemptUnlock(Player player, String unlock){
        int balance = UserDataHandler.getPlayerCoins(player);
        int price = MenuCollection.coinShopPrices.get(unlock);
        if(balance >= price){
            balance -= price;
            UserDataHandler.setPlayerCoins(player, balance);
            unlockFeature(player, unlock);
            PlayerMessageHandler.sendUnlockSuccessMessage(player, MenuCollection.coinShopItemNames.get(unlock));
            return true;
        } else {
            PlayerMessageHandler.sendUnlockFailureMessage(player, MenuCollection.coinShopItemNames.get(unlock));
            return false;
        }
    }

    public static void unlockFeature(Player player, String unlock){
        UserDataHandler.set(player, player.getUniqueId(), "Shop." + unlock, true);
    }

    public static boolean hasFeatureUnlocked(Player player, String unlock){
        return Boolean.parseBoolean(UserDataHandler.get(player, player.getUniqueId(), "Shop." + unlock));
    }

    public static String[] getShopItemDescription(String unlock){
        if(unlock.equalsIgnoreCase("Spawner")){
            return MenuCollection.spawnerDescription;
        } else if(unlock.equalsIgnoreCase("Keep-Inventory")) {
            return MenuCollection.keepInvDescription;
        } else if(unlock.equalsIgnoreCase("Flight")) {
            return MenuCollection.flightDescription;
        } else if(unlock.equalsIgnoreCase("Keep-Experience")){
            return MenuCollection.keepExpDescription;
        } else {
            return MenuCollection.glowDescription;
        }
    }
}
