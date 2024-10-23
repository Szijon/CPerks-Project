package test.server.ririyo.cPerks.collections;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import test.server.ririyo.cPerks.configs.UserDataHandler;
import test.server.ririyo.cPerks.enchantments.CustomEnchantments;
import test.server.ririyo.cPerks.perks.perklogic.PerkLogic;
import test.server.ririyo.cPerks.perks.features.SilkTouchSpawners;
import test.server.ririyo.cPerks.handlers.FormatHandler;

import java.util.*;

public class CustomItemCollection {
    ///COLLECTION OF ALL SORTS OF CUSTOM ITEMS. USED IN MENU DISPLAYING.

    ///FUNCTION TO CREATE BASIC CUSTOM ITEM WITH ABILITY TO CHANGE NAME AND ADDING ENCHANTMENTS BUT NOT USABLE FOR FUNCTIONS
    public static ItemStack createSimpleCustomItem(Material material, int amount, String displayName, Map<Enchantment, Integer> enchantments, Map<CustomEnchantments.CustomEnchantment, Integer> customEnchantments){
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        if(meta != null){
            if(displayName != null){
                meta.setDisplayName(displayName);
            }
            if(enchantments != null) {
                for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                    meta.addEnchant(entry.getKey(), entry.getValue(), true);
                }
            }
            item.setItemMeta(meta);
            if(customEnchantments != null) {
                CustomEnchantments.convertEnchantments(item);
                for (Map.Entry<CustomEnchantments.CustomEnchantment,Integer> entry : customEnchantments.entrySet()){
                    CustomEnchantments.addCustomEnchantment(item, entry.getKey(), entry.getValue());
                }
            }
        }
        return item;
    }

    ///USED TO CREATE MORE COMPLEX CUSTOM ITEMS WITH ABILITY TO BE USED FOR FUNCTIONS.
    public static ItemStack createCustomItem(Material material, int amount, String displayName, List<String> lore, Map<Enchantment, Integer> enchantments, Map<CustomEnchantments.CustomEnchantment, Integer> customEnchantments, List<ItemFlag> itemFlags, NamespacedKey pdcKey, PersistentDataType pdcType, Object pdcValue){
        ItemStack item;
        item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        if(meta != null){
            if(displayName != null){
                meta.setDisplayName(displayName);
            }
            if(lore != null){
                meta.setLore(lore);
            }
            if(enchantments != null){
                for(Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()){
                    meta.addEnchant(entry.getKey(), entry.getValue(), true);
                }
            }
            if(customEnchantments != null){
                CustomEnchantments.convertEnchantments(item);
                for (Map.Entry<CustomEnchantments.CustomEnchantment,Integer> entry : customEnchantments.entrySet()){
                    CustomEnchantments.addCustomEnchantment(item, entry.getKey(), entry.getValue());
                }
            }
            if(itemFlags != null){
                for (ItemFlag flag : itemFlags){
                    meta.addItemFlags(flag);
                }
            }
            if(pdcKey != null){
                PersistentDataContainer pdc = meta.getPersistentDataContainer();
                pdc.set(pdcKey, pdcType, pdcValue);
            }
            item.setItemMeta(meta);
        }
        return item;
    }

    public static ItemStack createCustomItemMutliplePDC(Material material, int amount, String displayName, List<String> lore, Map<Enchantment, Integer> enchantments, Map<CustomEnchantments.CustomEnchantment, Integer> customEnchantments, List<ItemFlag> itemFlags, NamespacedKey[] pdcKey, PersistentDataType[] pdcType, Object[] pdcValue){
        ItemStack item;
        item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        if(meta != null){
            if(displayName != null){
                meta.setDisplayName(displayName);
            }
            if(lore != null){
                meta.setLore(lore);
            }
            if(enchantments != null){
                for(Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()){
                    meta.addEnchant(entry.getKey(), entry.getValue(), true);
                }
            }
            if(customEnchantments != null){
                CustomEnchantments.convertEnchantments(item);
                for (Map.Entry<CustomEnchantments.CustomEnchantment,Integer> entry : customEnchantments.entrySet()){
                    CustomEnchantments.addCustomEnchantment(item, entry.getKey(), entry.getValue());
                }
            }
            if(itemFlags != null){
                for (ItemFlag flag : itemFlags){
                    meta.addItemFlags(flag);
                }
            }
            if(pdcKey != null){
                for(int i = 0; i < pdcKey.length; i++){
                    PersistentDataContainer pdc = meta.getPersistentDataContainer();
                    pdc.set(pdcKey[i], pdcType[i], pdcValue[i]);
                }
            }
            item.setItemMeta(meta);
        }
        return item;
    }


    ///LOOT CRATES
        ///ADMIN ITEMS
            ///PLACEABLE BLOCK TO INTERACT WITH TO OPEN LOOT CRATE MENU
    public static ItemStack getLootCrateBlock(int amount){
        return createCustomItem(
                Material.LIME_SHULKER_BOX,
                amount,
                ChatColor.GOLD + "Loot Crate",
                List.of(ChatColor.BLUE + "", ChatColor.BLUE + "Used to place down an interactive Loot Crate."),
                Map.of(Enchantment.SHARPNESS, 1),
                null,
                List.of(ItemFlag.HIDE_ENCHANTS),
                NamespacedKeyCollection.LootCrateKey,
                PersistentDataType.BOOLEAN,
                true
        );
    }

        ///LOOT
            ///UNABLE TO OBTAIN THROUGH NORMAL MEANS
                ///SPAWNER
    public static ItemStack createMonsterSpawner(EntityType entityType){
        return createCustomItem(
                Material.SPAWNER,
                1,
                SilkTouchSpawners.getSpawnerName(entityType),
                null,
                null,
                null,
                List.of(ItemFlag.HIDE_ADDITIONAL_TOOLTIP),
                NamespacedKeyCollection.SpawnerKey,
                PersistentDataType.STRING,
                entityType.name()
        );
    }

    public static ItemStack createEnchantedBook(Map<Enchantment, Integer> enchantments){
        ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
        for(Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()){
            meta.addStoredEnchant(entry.getKey(), entry.getValue(), true);
        }
        item.setItemMeta(meta);
        return item;
    }
                /// USABLE ITEM TO ADD FLIGHT TIME
    public static ItemStack getFlightCredit(int time){
        return createCustomItem(
                Material.FEATHER,
                1,
                ChatColor.GOLD + "Flight Credit",
                getTimeStrings(time),
                null,
                null,
                null,
                NamespacedKeyCollection.FlightKey,
                PersistentDataType.INTEGER,
                time
        );
    }

    public static ItemStack getInfiniteWaterBucket(){
        return createCustomItem(
                Material.WATER_BUCKET,
                1,
                ChatColor.AQUA + "Magical Water Bucket",
                List.of(ChatColor.BLUE + "A Water Bucket that seems Infinite."),
                Map.of(Enchantment.MENDING, 1),
                null,
                List.of(ItemFlag.HIDE_ENCHANTS),
                NamespacedKeyCollection.CustomItemKey,
                PersistentDataType.STRING,
                "MagicalWaterBucket"
        );
    }
    /// MENU ITEMS
        ///BACKGROUND ITEM
    public static ItemStack getBackgroundItem(){
        return createSimpleCustomItem(
                Material.GRAY_STAINED_GLASS_PANE,
                1,
                " ",
                null,
                null
        );
    }
    public static ItemStack getSellInterfaceItem(){
        return createCustomItem(
                Material.GOLD_NUGGET,
                1,
                ChatColor.GOLD + "Sell Items",
                List.of(" ", ChatColor.BLUE + "Put Items to sell in this Inventory", ChatColor.GREEN + "Close" + ChatColor.BLUE + " it to confirm Sale.", ChatColor.BLUE + "Unsellable Items will be returned to your Inventory."),
                null,
                null,
                null,
                NamespacedKeyCollection.SellKey,
                PersistentDataType.BOOLEAN,
                true
        );
    }

        ///PERK SHOP SHOWS ITEM TO GET TO GOLD & COIN SHOP
    public static ItemStack getPerkShopItem(){
        return createCustomItem(
                Material.NETHER_STAR,
                1,
                ChatColor.DARK_PURPLE + "Perk Shop",
                null,
                null,
                null,
                null,
                NamespacedKeyCollection.PerkShopKey,
                PersistentDataType.BOOLEAN,
                true
        );
    }
            ///USED TO GRAB AN ITEM THAT DISPLAYS A PERK'S STATISTICS LIKE LEVEL & EXP
    public static ItemStack getPerkItem(Player player, String perk){
        return createCustomItem(
                PerkLogic.perkIcon.get(perk),
                1,
                ChatColor.AQUA + perk,
                PerkLogic.getPerkDescription(player, perk),
                null,
                null,
                List.of(ItemFlag.HIDE_ADDITIONAL_TOOLTIP, ItemFlag.HIDE_ATTRIBUTES),
                null,
                null,
                null
        );
    }
            ///USED TO GRAB AN ITEM THAT DISPLAYS A FEATURE'S STATE, IF IT'S UNLOCKED AND AT WHICH LEVEL IT IS UNLOCKED, CAN ALSO BE CLICKED ON TO TOGGLE THE STATE
    public static ItemStack getFeatureItem(Player player, String perk, String feature){
        boolean unlocked = Integer.parseInt(UserDataHandler.get(player, player.getUniqueId(), perk + ".Level")) >= PerkLogic.featureLevels.get(feature);
        ChatColor nameColor;
        if(unlocked) nameColor = ChatColor.GREEN;
        else nameColor = ChatColor.RED;

        return createCustomItem(
                PerkLogic.featureIcon.get(perk + "." + feature),
                1,
                nameColor + feature,
                PerkLogic.getUnlockDescription(player, perk, feature),
                null,
                null,
                List.of(ItemFlag.HIDE_ADDITIONAL_TOOLTIP, ItemFlag.HIDE_ATTRIBUTES),
                NamespacedKeyCollection.featureKeys.get(perk + "." + feature),
                PersistentDataType.BOOLEAN,
                unlocked
        );
    }

        ///ITEMS VISIBLE IN PERK SHOP
            ///ITEM CLICKED TO GO TO COIN SHOP
    public static ItemStack getCoinShopItem(){
        return createCustomItem(
                Material.EMERALD,
                1,
                ChatColor.GREEN + "Coin Shop",
                List.of("", ChatColor.GREEN + "Opens the Coin Shop.", ChatColor.BLUE + "Used for purchasing Unlocks."),
                null,
                null,
                null,
                NamespacedKeyCollection.CoinShopKey,
                PersistentDataType.BOOLEAN,
                true
        );
    }
            ///ITEM CLICKED TO GO TO GOLD SHOP
    public static ItemStack getGoldShopItem(){
        return createCustomItem(
                Material.GOLD_INGOT,
                1,
                ChatColor.GOLD + "Gold Shop",
                List.of("", ChatColor.GREEN + "Opens the Gold Shop.", ChatColor.BLUE + "Used for purchasing Usable Items."),
                null,
                null,
                null,
                NamespacedKeyCollection.GoldShopKey,
                PersistentDataType.BOOLEAN,
                true
        );
    }
            ///ITEM CLICKED TO RETURN TO MAIN PAGE
    public static ItemStack getPerkOverviewItem(){
        return createCustomItem(
                Material.BEACON,
                1,
                ChatColor.AQUA + "Return to Perk Overview",
                null,
                null,
                null,
                null,
                NamespacedKeyCollection.PerkOverviewKey,
                PersistentDataType.BOOLEAN,
                true
        );
    }

    ///METHODS TO CHANGE ITEM DESCRIPTIONS
        ///TIME FORMATTING FROM SECONDS TO >> DAYS-HOURS-MINUTES-SECONDS <<
    private static List<String> getTimeStrings(int time) {
        int[] formattedTime = FormatHandler.getTime(time);

        List<String> lore = new ArrayList<>();
        lore.add("");
        int days = formattedTime[0];
        int hours = formattedTime[1];
        int minutes = formattedTime[2];
        if(days > 0) {
            lore.add(ChatColor.LIGHT_PURPLE + "" + days + "D");
        }
        if (hours > 0) {
            if (minutes > 0)
                lore.add(ChatColor.LIGHT_PURPLE + "" + hours + "H " + minutes + "m");
            else
                lore.add(ChatColor.LIGHT_PURPLE + "" + hours + "H");
        } else {
            lore.add(ChatColor.LIGHT_PURPLE + "" + minutes + "m");
        }
        return lore;
    }
}
