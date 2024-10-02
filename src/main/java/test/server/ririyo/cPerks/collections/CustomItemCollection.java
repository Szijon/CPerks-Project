package test.server.ririyo.cPerks.collections;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import test.server.ririyo.cPerks.configs.UserDataHandler;
import test.server.ririyo.cPerks.perks.AllPerksCollection;
import test.server.ririyo.cPerks.perks.features.FlightHandler;
import test.server.ririyo.cPerks.perks.features.TimeHandler;

import java.util.*;

public class CustomItemCollection {
    ///COLLECTION OF ALL SORTS OF CUSTOM ITEMS. USED IN MENU DISPLAYING.

    ///FUNCTION TO CREATE BASIC CUSTOM ITEM WITH ABILITY TO CHANGE NAME AND ADDING ENCHANTMENTS BUT NOT USABLE FOR FUNCTIONS
    public static ItemStack createSimpleCustomItem(Material material, int amount, String displayName, Map<Enchantment, Integer> enchantments){
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
        }
        return item;
    }

    ///USED TO CREATE MORE COMPLEX CUSTOM ITEMS WITH ABILITY TO BE USED FOR FUNCTIONS.
    public static ItemStack createCustomItem(Material material, int amount, String displayName, List<String> lore, Map<Enchantment, Integer> enchantments, List<ItemFlag> itemFlags, NamespacedKey pdcKey, PersistentDataType pdcType, Object pdcValue){
        ItemStack customItem;
        customItem = new ItemStack(material, amount);
        ItemMeta meta = customItem.getItemMeta();
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
            if(itemFlags != null){
                for (ItemFlag flag : itemFlags){
                    meta.addItemFlags(flag);
                }
            }
            if(pdcKey != null){
                PersistentDataContainer pdc = meta.getPersistentDataContainer();
                pdc.set(pdcKey, pdcType, pdcValue);
            }
            customItem.setItemMeta(meta);
        }
        return customItem;
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
                List.of(ItemFlag.HIDE_ENCHANTS),
                NamespacedKeyCollection.LootCrateKey,
                PersistentDataType.BOOLEAN,
                true
        );
    }
            ///NEEDED ITEM TO OPEN A LOOT CRATE
    public static ItemStack createLootKey(int amount, String type){
        String displayName;
        ///SET DISPLAY NAME OF ITEM DEPENDENT ON TYPE
        if(type.equalsIgnoreCase("rare")) {
            displayName = ChatColor.BLUE + type + " Loot Crate Key";
        } else if(type.equalsIgnoreCase("legendary")) {
            displayName = ChatColor.LIGHT_PURPLE + type + " Loot Crate Key";
        } else if(type.equalsIgnoreCase("gear")) {
            displayName = ChatColor.GOLD + type + " Loot Crate Key";
        } else{
            displayName = ChatColor.GREEN + type + " Loot Crate Key";
        }
        return createCustomItem(
                Material.TRIPWIRE_HOOK,
                amount,
                displayName,
                List.of("", ChatColor.GREEN + "Use at Spawn to Open Loot Crate!"),
                Map.of(Enchantment.SHARPNESS, 1),
                List.of(ItemFlag.HIDE_ENCHANTS),
                NamespacedKeyCollection.LootCrateKeyKey,
                PersistentDataType.STRING,
                type
        );
    }

        ///LOOT
            ///UNABLE TO OBTAIN THROUGH NORMAL MEANS
                ///SPAWNER
    public static ItemStack createMonsterSpawner(EntityType entityType){
        return createCustomItem(
                Material.SPAWNER,
                1,
                ChatColor.DARK_PURPLE + entityType.name().substring(0, 1).toUpperCase() + entityType.name().substring(1).toLowerCase() + " Spawner",
                null,
                null,
                List.of(ItemFlag.HIDE_ADDITIONAL_TOOLTIP),
                NamespacedKeyCollection.SpawnerKey,
                PersistentDataType.STRING,
                entityType.name()
        );
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
                NamespacedKeyCollection.FlightKey,
                PersistentDataType.INTEGER,
                time
        );
    }
    /// MENU ITEMS
        ///BACKGROUND ITEM
    public static ItemStack getBackgroundItem(){
        return createSimpleCustomItem(
                Material.GRAY_STAINED_GLASS_PANE,
                1,
                " ",
                null
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
                NamespacedKeyCollection.PerkShopKey,
                PersistentDataType.BOOLEAN,
                true
        );
    }
            ///USED TO GRAB AN ITEM THAT DISPLAYS A PERK'S STATISTICS LIKE LEVEL & EXP
    public static ItemStack getPerkItem(Player player, String perk){
        return createCustomItem(
                AllPerksCollection.perkIcon.get(perk),
                1,
                ChatColor.AQUA + perk,
                AllPerksCollection.getPerkDescription(player, perk),
                null,
                List.of(ItemFlag.HIDE_ADDITIONAL_TOOLTIP, ItemFlag.HIDE_ATTRIBUTES),
                null,
                null,
                null
        );
    }
            ///USED TO GRAB AN ITEM THAT DISPLAYS A FEATURE'S STATE, IF IT'S UNLOCKED AND AT WHICH LEVEL IT IS UNLOCKED, CAN ALSO BE CLICKED ON TO TOGGLE THE STATE
    public static ItemStack getFeatureItem(Player player, String perk, String feature){
        boolean unlocked = Integer.parseInt(UserDataHandler.get(player, player.getUniqueId(), perk + ".Level")) >= AllPerksCollection.featureLevels.get(feature);
        ChatColor nameColor;
        if(unlocked) nameColor = ChatColor.GREEN;
        else nameColor = ChatColor.RED;

        return createCustomItem(
                AllPerksCollection.featureIcon.get(perk + "." + feature),
                1,
                nameColor + feature,
                AllPerksCollection.getUnlockDescription(player, perk, feature),
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
                NamespacedKeyCollection.PerkOverviewKey,
                PersistentDataType.BOOLEAN,
                true
        );
    }

    ///METHODS TO CHANGE ITEM DESCRIPTIONS
        ///TIME FORMATTING FROM SECONDS TO >> DAYS-HOURS-MINUTES-SECONDS <<
    private static List<String> getTimeStrings(int time) {
        int[] formattedTime = TimeHandler.getTime(time);

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
