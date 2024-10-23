package test.server.ririyo.cPerks.perks.perkmenu;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import test.server.ririyo.cPerks.lootcrate.LootCrateKeyItem;
import test.server.ririyo.cPerks.perks.perklogic.PerkLogic;
import test.server.ririyo.cPerks.collections.CustomItemCollection;
import test.server.ririyo.cPerks.collections.NamespacedKeyCollection;
import test.server.ririyo.cPerks.configs.UserDataHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PerkMenuCollection {

    /// PERK OVERVIEW

    public static void toggleOnFeatureClick(Player player, Inventory inventory, NamespacedKey key){
        String path = PerkLogic.keyToPathConverter.get(key);
        boolean state = Boolean.parseBoolean(UserDataHandler.get(player, player.getUniqueId(), path));
        if(state){
            UserDataHandler.set(player, player.getUniqueId(), path, false);
        } else {
            UserDataHandler.set(player, player.getUniqueId(), path, true);
        }
        MenuHandler.updateMenuInventory(player, "Perk Overview", inventory);
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.2f, 1);
    }

    /// SHOPS
        /// COIN SHOP

    public static ItemStack getCoinBalanceItem(Player player){
        ItemStack balance = new ItemStack(Material.EMERALD, 1);
        ItemMeta balanceMeta = balance.getItemMeta();
        int bal = UserDataHandler.getPlayerCoins(player);
        balanceMeta.setDisplayName(ChatColor.GREEN + "Balance");
        balanceMeta.setLore(List.of(ChatColor.GREEN + "" + bal + " Coins"));
        balanceMeta.addEnchant(Enchantment.SHARPNESS, 1, true);
        balanceMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        balance.setItemMeta(balanceMeta);
        return balance;
    }

    public static ItemStack getSpawnerItem(Player player){
        ItemStack spawner = new ItemStack(Material.SPAWNER, 1);
        ItemMeta spawnerMeta = spawner.getItemMeta();
        spawnerMeta.setDisplayName(ChatColor.GOLD + "Silk-Touch Spawners");
        spawnerMeta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        spawnerMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        PersistentDataContainer spawnerPDC = spawnerMeta.getPersistentDataContainer();

        if(Boolean.parseBoolean(UserDataHandler.get(player, player.getUniqueId(), "Shop.Spawner"))) {
            spawnerPDC.set(NamespacedKeyCollection.SpawnerKey, PersistentDataType.BOOLEAN, true);
        } else {
            spawnerPDC.set(NamespacedKeyCollection.SpawnerKey, PersistentDataType.BOOLEAN, false);
        }

        spawner.setItemMeta(coinShopAddItemDetails(player, spawnerMeta, "Spawner"));
        return spawner;
    }

    public static ItemStack getKeepInventoryItem(Player player){
        ItemStack keepInv = new ItemStack(Material.NETHER_STAR, 1);
        ItemMeta meta = keepInv.getItemMeta();
        if(meta != null) {
            meta.setDisplayName(ChatColor.GOLD + "Keep Inventory");
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            PersistentDataContainer keepInvPDC = meta.getPersistentDataContainer();

            if (Boolean.parseBoolean(UserDataHandler.get(player, player.getUniqueId(), "Shop.Keep-Inventory"))) {
                keepInvPDC.set(NamespacedKeyCollection.KeepInvKey, PersistentDataType.BOOLEAN, true);
            } else {
                keepInvPDC.set(NamespacedKeyCollection.KeepInvKey, PersistentDataType.BOOLEAN, false);
            }

            keepInv.setItemMeta(coinShopAddItemDetails(player, meta, "Keep-Inventory"));
        }
        return keepInv;
    }

    public static ItemStack getKeepExpItem(Player player){
        ItemStack keepExp = new ItemStack(Material.EXPERIENCE_BOTTLE, 1);
        ItemMeta meta = keepExp.getItemMeta();
        if(meta != null) {
            meta.setDisplayName(ChatColor.GOLD + "Keep Experience");
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            PersistentDataContainer PDC = meta.getPersistentDataContainer();

            if (Boolean.parseBoolean(UserDataHandler.get(player, player.getUniqueId(), "Shop.Keep-Experience"))) {
                PDC.set(NamespacedKeyCollection.KeepExpKey, PersistentDataType.BOOLEAN, true);
            } else {
                PDC.set(NamespacedKeyCollection.KeepExpKey, PersistentDataType.BOOLEAN, false);
            }

            keepExp.setItemMeta(coinShopAddItemDetails(player, meta, "Keep-Experience"));
        }
        return keepExp;
    }

    public static ItemStack getFlightItem(Player player) {
        ItemStack flight = new ItemStack(Material.FEATHER, 1);
        ItemMeta meta = flight.getItemMeta();
        if(meta != null) {
            meta.setDisplayName(ChatColor.GREEN + "Unlock Flight");
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            PersistentDataContainer PDC = meta.getPersistentDataContainer();

            if (Boolean.parseBoolean(UserDataHandler.get(player, player.getUniqueId(), "Shop.Flight"))) {
                PDC.set(NamespacedKeyCollection.FlightKey, PersistentDataType.BOOLEAN, true);
            } else {
                PDC.set(NamespacedKeyCollection.FlightKey, PersistentDataType.BOOLEAN, false);
            }

            flight.setItemMeta(coinShopAddItemDetails(player, meta, "Flight"));
        }
        return flight;
    }

        /// GOLD SHOP

    public static ItemStack getGoldBalanceItem(Player player){
        ItemStack balance = new ItemStack(Material.GOLD_INGOT);
        ItemMeta meta = balance.getItemMeta();
        if(meta != null) {
            int bal = Integer.parseInt(UserDataHandler.get(player, player.getUniqueId(), "Player.Gold"));
            meta.setDisplayName(ChatColor.GOLD + "Balance");
            meta.setLore(List.of(ChatColor.GOLD + "" + bal + " Gold"));
            meta.addEnchant(Enchantment.SHARPNESS, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            balance.setItemMeta(meta);
        }
        return balance;
    }

    public static ItemStack getNormalLootKeyShopItem(){
        ItemStack key = LootCrateKeyItem.get(1, LootCrateKeyItem.LootKeyType.NORMAL);
        ItemMeta meta = key.getItemMeta();
        if(meta != null) {
            PersistentDataContainer pdc = meta.getPersistentDataContainer();
            pdc.set(NamespacedKeyCollection.LootKeyKey, PersistentDataType.STRING, "Normal Loot Key");
            int price = MenuCollection.goldShopPrices.get("Normal Loot Key");
            List<String> lore = meta.getLore();
            lore.add("");
            lore.add(ChatColor.BLUE + "Price: " + ChatColor.GOLD + price + " Gold");
            meta.setLore(lore);
            key.setItemMeta(meta);
        }
        return key;
    }

    public static ItemStack getRareLootKeyShopItem(){
        ItemStack key = LootCrateKeyItem.get(1, LootCrateKeyItem.LootKeyType.RARE);
        ItemMeta meta = key.getItemMeta();
        if(meta != null) {
            PersistentDataContainer pdc = meta.getPersistentDataContainer();
            pdc.set(NamespacedKeyCollection.LootKeyKey, PersistentDataType.STRING, "Rare Loot Key");
            int price = MenuCollection.goldShopPrices.get("Rare Loot Key");
            List<String> lore = meta.getLore();
            lore.add("");
            lore.add(ChatColor.BLUE + "Price: " + ChatColor.GOLD + price + " Gold");
            meta.setLore(lore);
            key.setItemMeta(meta);
        }
        return key;
    }

    public static ItemStack getLegendaryLootKeyShopItem(){
        ItemStack key = LootCrateKeyItem.get(1, LootCrateKeyItem.LootKeyType.LEGENDARY);
        ItemMeta meta = key.getItemMeta();
        if(meta != null) {
            PersistentDataContainer pdc = meta.getPersistentDataContainer();
            pdc.set(NamespacedKeyCollection.LootKeyKey, PersistentDataType.STRING, "Legendary Loot Key");
            int price = MenuCollection.goldShopPrices.get("Legendary Loot Key");
            List<String> lore = meta.getLore();
            lore.add("");
            lore.add(ChatColor.BLUE + "Price: " + ChatColor.GOLD + price + " Gold");
            meta.setLore(lore);
            key.setItemMeta(meta);
        }
        return key;
    }

    public static ItemStack getMythicLootKeyShopItem(){
        ItemStack key = LootCrateKeyItem.get(1, LootCrateKeyItem.LootKeyType.MYTHIC);
        ItemMeta meta = key.getItemMeta();
        if(meta != null) {
            PersistentDataContainer pdc = meta.getPersistentDataContainer();
            pdc.set(NamespacedKeyCollection.LootKeyKey, PersistentDataType.STRING, "Mythic Loot Key");
            int price = MenuCollection.goldShopPrices.get("Mythic Loot Key");
            List<String> lore = meta.getLore();
            lore.add("");
            lore.add(ChatColor.BLUE + "Price: " + ChatColor.GOLD + price + " Gold");
            meta.setLore(lore);
            key.setItemMeta(meta);
        }
        return key;
    }

    public static ItemStack getFlightCreditShopItem(){
        ItemStack credit = CustomItemCollection.getFlightCredit(900); // 15 Minutes
        ItemMeta meta = credit.getItemMeta();
        if(meta != null) {
            PersistentDataContainer pdc = meta.getPersistentDataContainer();
            pdc.set(NamespacedKeyCollection.FlightCreditKey, PersistentDataType.BOOLEAN, true);
            int price = MenuCollection.goldShopPrices.get("Flight Credit");
            List<String> lore = meta.getLore();
            lore.add("");
            lore.add(ChatColor.BLUE + "Price: " + ChatColor.GOLD + price + " Gold");
            meta.setLore(lore);
            credit.setItemMeta(meta);
        }
        return credit;
    }

    public static ItemMeta coinShopAddItemDetails(Player player, ItemMeta itemMeta, String unlock){
        int price = MenuCollection.coinShopPrices.get(unlock);
        String[] description = MenuHandler.getShopItemDescription(unlock);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("");
        lore.add(ChatColor.BLUE + "Price: " + ChatColor.GREEN + price + " Coins");
        lore.add("");
        lore.addAll(Arrays.asList(description));
        lore.add("");

        if (Boolean.parseBoolean(UserDataHandler.get(player, player.getUniqueId(), "Shop." + unlock))) {
            itemMeta.addEnchant(Enchantment.SHARPNESS, 10, true);
            lore.add(ChatColor.GREEN + "Already purchased.");
        } else {
            lore.add(ChatColor.RED + "Not purchased yet.");
        }

        itemMeta.setLore(lore);
        return itemMeta;
    }

}
