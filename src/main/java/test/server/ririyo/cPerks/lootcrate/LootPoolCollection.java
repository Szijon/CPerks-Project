package test.server.ririyo.cPerks.lootcrate;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import test.server.ririyo.cPerks.collections.CustomItemCollection;

import java.util.Map;

public class LootPoolCollection {

    public static LootPool.Pool keyPool = initializeKeyPool();
    public static LootPool.Pool normalLootCratePool = initializeNormalLootCratePool();
    public static LootPool.Pool rareLootCratePool = initializeRareLootCratePool();
    public static LootPool.Pool legendaryLootCratePool = initializeLegendaryLootCratePool();
    public static LootPool.Pool mythicLootCratePool = initializeMythicLootCratePool();

    public static LootPool.Pool initializeKeyPool() {
        LootPool.Pool pool = new LootPool.Pool("Key Drop");
        pool.addItem(LootCrateKeyItem.get(1, LootCrateKeyItem.LootKeyType.NORMAL), LootPool.Rarity.COMMON);
        pool.addItem(LootCrateKeyItem.get(1, LootCrateKeyItem.LootKeyType.RARE), LootPool.Rarity.RARE);
        pool.addItem(LootCrateKeyItem.get(5, LootCrateKeyItem.LootKeyType.NORMAL), LootPool.Rarity.EPIC);
        pool.addItem(LootCrateKeyItem.get(1, LootCrateKeyItem.LootKeyType.LEGENDARY), LootPool.Rarity.EPIC);
        pool.addItem(LootCrateKeyItem.get(5, LootCrateKeyItem.LootKeyType.RARE), LootPool.Rarity.EPIC);
        pool.addItem(LootCrateKeyItem.get(5, LootCrateKeyItem.LootKeyType.LEGENDARY), LootPool.Rarity.MYTHIC);
        pool.addItem(LootCrateKeyItem.get(1, LootCrateKeyItem.LootKeyType.MYTHIC), LootPool.Rarity.MYTHIC);

        pool.calculateWeight();
        return pool;
    }

    public static LootPool.Pool initializeNormalLootCratePool(){
        LootPool.Pool pool = new LootPool.Pool(ChatColor.GREEN + "Normal Loot Crate");

        ///COMMON
        pool.addItem(new ItemStack(Material.GOLDEN_CARROT, 16), LootPool.Rarity.COMMON);
        pool.addItem(new ItemStack(Material.OAK_LOG, 64), LootPool.Rarity.COMMON);
        pool.addItem(new ItemStack(Material.TOTEM_OF_UNDYING, 1), LootPool.Rarity.COMMON);
        pool.addItem(new ItemStack(Material.GOLDEN_APPLE, 8), LootPool.Rarity.COMMON);
        pool.addItem(new ItemStack(Material.RAW_IRON, 16), LootPool.Rarity.COMMON);
        pool.addItem(new ItemStack(Material.RAW_GOLD, 16), LootPool.Rarity.COMMON);
        pool.addItem(new ItemStack(Material.DIAMOND, 8), LootPool.Rarity.COMMON);
        pool.addItem(new ItemStack(Material.EMERALD, 8), LootPool.Rarity.COMMON);
        pool.addItem(new ItemStack(Material.ENDER_PEARL, 8), LootPool.Rarity.COMMON);
        pool.addItem(CustomItemCollection.getFlightCredit(900), LootPool.Rarity.COMMON);

        ///RARE
        pool.addItem(LootCrateKeyItem.get(1, LootCrateKeyItem.LootKeyType.NORMAL), LootPool.Rarity.RARE);
        pool.addItem(new ItemStack(Material.NETHERITE_INGOT, 1), LootPool.Rarity.RARE);
        pool.addItem(new ItemStack(Material.NETHERITE_SCRAP, 4), LootPool.Rarity.RARE);
        pool.addItem(new ItemStack(Material.NETHERITE_UPGRADE_SMITHING_TEMPLATE, 1), LootPool.Rarity.RARE);
        pool.addItem(new ItemStack(Material.ELYTRA, 1), LootPool.Rarity.RARE);
        pool.addItem(new ItemStack(Material.EXPERIENCE_BOTTLE, 16), LootPool.Rarity.RARE);
        pool.addItem(new ItemStack(Material.SHULKER_SHELL, 2), LootPool.Rarity.RARE);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.ENCHANTED_BOOK, 1, null, Map.of(Enchantment.UNBREAKING, 3), false), LootPool.Rarity.RARE);
        pool.addItem(CustomItemCollection.getFlightCredit(1800), LootPool.Rarity.RARE);

        ///EPIC
        pool.addItem(new ItemStack(Material.BEACON, 1), LootPool.Rarity.EPIC);
        pool.addItem(LootCrateKeyItem.get(1, LootCrateKeyItem.LootKeyType.RARE), LootPool.Rarity.EPIC);
        pool.addItem(new ItemStack(Material.ENDER_CHEST, 1), LootPool.Rarity.EPIC);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.ENCHANTED_BOOK, 1, null, Map.of(Enchantment.SHARPNESS, 5), false), LootPool.Rarity.EPIC);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.ENCHANTED_BOOK, 1, null, Map.of(Enchantment.FORTUNE, 3), false), LootPool.Rarity.EPIC);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.ENCHANTED_BOOK, 1, null, Map.of(Enchantment.MENDING, 1), false), LootPool.Rarity.EPIC);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.ENCHANTED_BOOK, 1, null, Map.of(Enchantment.EFFICIENCY, 5), false), LootPool.Rarity.EPIC);
        pool.addItem(CustomItemCollection.getFlightCredit(2700), LootPool.Rarity.EPIC);

        ///LEGENDARY
        pool.addItem(LootCrateKeyItem.get(1, LootCrateKeyItem.LootKeyType.LEGENDARY), LootPool.Rarity.LEGENDARY);
        pool.addItem(CustomItemCollection.createMonsterSpawner(EntityType.COW), LootPool.Rarity.LEGENDARY);
        pool.addItem(CustomItemCollection.createMonsterSpawner(EntityType.PIG), LootPool.Rarity.LEGENDARY);
        pool.addItem(CustomItemCollection.createMonsterSpawner(EntityType.SHEEP), LootPool.Rarity.LEGENDARY);
        pool.addItem(CustomItemCollection.createMonsterSpawner(EntityType.CHICKEN), LootPool.Rarity.LEGENDARY);
        pool.addItem(CustomItemCollection.getFlightCredit(3600), LootPool.Rarity.LEGENDARY);

        pool.calculateWeight();
        return pool;
    }

    public static LootPool.Pool initializeRareLootCratePool(){
        LootPool.Pool pool = new LootPool.Pool(ChatColor.BLUE + "Rare Loot Crate");

        ///COMMON
        pool.addItem(new ItemStack(Material.GOLDEN_APPLE, 8), LootPool.Rarity.COMMON);
        pool.addItem(new ItemStack(Material.NETHERITE_INGOT, 1), LootPool.Rarity.COMMON);
        pool.addItem(new ItemStack(Material.NETHERITE_SCRAP, 4), LootPool.Rarity.COMMON);
        pool.addItem(new ItemStack(Material.NETHERITE_UPGRADE_SMITHING_TEMPLATE, 1), LootPool.Rarity.COMMON);
        pool.addItem(new ItemStack(Material.EXPERIENCE_BOTTLE, 64), LootPool.Rarity.COMMON);
        pool.addItem(new ItemStack(Material.SHULKER_SHELL, 4), LootPool.Rarity.COMMON);
        pool.addItem(new ItemStack(Material.DIAMOND, 16), LootPool.Rarity.COMMON);
        pool.addItem(new ItemStack(Material.EMERALD, 16), LootPool.Rarity.COMMON);
        pool.addItem(CustomItemCollection.getFlightCredit(1200), LootPool.Rarity.COMMON);

        ///RARE
        pool.addItem(new ItemStack(Material.BEACON, 1), LootPool.Rarity.RARE);
        pool.addItem(new ItemStack(Material.ELYTRA, 1), LootPool.Rarity.RARE);
        pool.addItem(new ItemStack(Material.ENDER_CHEST, 1), LootPool.Rarity.RARE);
        pool.addItem(new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1), LootPool.Rarity.RARE);
        pool.addItem(CustomItemCollection.getFlightCredit(1800), LootPool.Rarity.RARE);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.ENCHANTED_BOOK, 1, null, Map.of(Enchantment.SHARPNESS, 5), false), LootPool.Rarity.RARE);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.ENCHANTED_BOOK, 1, null, Map.of(Enchantment.FORTUNE, 3), false), LootPool.Rarity.RARE);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.ENCHANTED_BOOK, 1, null, Map.of(Enchantment.MENDING, 1), false), LootPool.Rarity.RARE);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.ENCHANTED_BOOK, 1, null, Map.of(Enchantment.EFFICIENCY, 5), false), LootPool.Rarity.RARE);
        pool.addItem(new ItemStack(Material.SHULKER_SHELL, 16), LootPool.Rarity.RARE);

        ///EPIC
        pool.addItem(LootCrateKeyItem.get(1, LootCrateKeyItem.LootKeyType.LEGENDARY), LootPool.Rarity.EPIC);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.ELYTRA, 1, null, Map.of(Enchantment.UNBREAKING, 4, Enchantment.MENDING, 1), false), LootPool.Rarity.EPIC);
        pool.addItem(new ItemStack(Material.NETHERITE_BLOCK, 1), LootPool.Rarity.EPIC);
        pool.addItem(CustomItemCollection.getFlightCredit(2700), LootPool.Rarity.EPIC);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.DIAMOND_HELMET, 1, null, Map.of(Enchantment.PROTECTION, 2, Enchantment.UNBREAKING, 1, Enchantment.RESPIRATION, 1), false), LootPool.Rarity.EPIC);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.DIAMOND_CHESTPLATE, 1, null, Map.of(Enchantment.PROTECTION, 2, Enchantment.UNBREAKING, 1, Enchantment.THORNS, 1), false), LootPool.Rarity.EPIC);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.DIAMOND_LEGGINGS, 1, null, Map.of(Enchantment.PROTECTION, 2, Enchantment.UNBREAKING, 1, Enchantment.THORNS, 1), false), LootPool.Rarity.EPIC);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.DIAMOND_BOOTS, 1, null, Map.of(Enchantment.PROTECTION, 2, Enchantment.UNBREAKING, 1, Enchantment.SOUL_SPEED, 1, Enchantment.DEPTH_STRIDER, 1), false), LootPool.Rarity.EPIC);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.DIAMOND_SWORD, 1, null, Map.of(Enchantment.SHARPNESS, 2, Enchantment.UNBREAKING, 1, Enchantment.SWEEPING_EDGE, 1), false), LootPool.Rarity.EPIC);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.DIAMOND_PICKAXE, 1, null, Map.of(Enchantment.EFFICIENCY, 2, Enchantment.UNBREAKING, 1, Enchantment.FORTUNE, 1), false), LootPool.Rarity.EPIC);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.DIAMOND_AXE, 1, null, Map.of(Enchantment.EFFICIENCY, 2, Enchantment.UNBREAKING, 1, Enchantment.FORTUNE, 1), false), LootPool.Rarity.EPIC);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.DIAMOND_SHOVEL, 1, null, Map.of(Enchantment.EFFICIENCY, 2, Enchantment.UNBREAKING, 1, Enchantment.FORTUNE, 1), false), LootPool.Rarity.EPIC);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.DIAMOND_HOE, 1, null, Map.of(Enchantment.EFFICIENCY, 2, Enchantment.UNBREAKING, 1, Enchantment.FORTUNE, 1), false), LootPool.Rarity.EPIC);

        ///LEGENDARY
        pool.addItem(LootCrateKeyItem.get(2, LootCrateKeyItem.LootKeyType.LEGENDARY), LootPool.Rarity.LEGENDARY);
        pool.addItem(CustomItemCollection.createMonsterSpawner(EntityType.ZOMBIE), LootPool.Rarity.LEGENDARY);
        pool.addItem(CustomItemCollection.createMonsterSpawner(EntityType.CREEPER), LootPool.Rarity.LEGENDARY);
        pool.addItem(CustomItemCollection.createMonsterSpawner(EntityType.SKELETON), LootPool.Rarity.LEGENDARY);
        pool.addItem(CustomItemCollection.createMonsterSpawner(EntityType.SPIDER), LootPool.Rarity.LEGENDARY);
        pool.addItem(CustomItemCollection.getFlightCredit(3600), LootPool.Rarity.LEGENDARY);

        ///MYTHIC 0.5%
        pool.addItem(LootCrateKeyItem.get(1, LootCrateKeyItem.LootKeyType.MYTHIC), LootPool.Rarity.MYTHIC);

        pool.calculateWeight();
        return pool;
    }

    public static LootPool.Pool initializeLegendaryLootCratePool(){
        LootPool.Pool pool = new LootPool.Pool(ChatColor.GOLD + "Legendary Loot Crate");

        ///COMMON
        pool.addItem(new ItemStack(Material.NETHERITE_INGOT, 2), LootPool.Rarity.COMMON);
        pool.addItem(new ItemStack(Material.NETHERITE_SCRAP, 8), LootPool.Rarity.COMMON);
        pool.addItem(new ItemStack(Material.NETHERITE_UPGRADE_SMITHING_TEMPLATE, 2), LootPool.Rarity.COMMON);
        pool.addItem(new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 4), LootPool.Rarity.COMMON);
        pool.addItem(new ItemStack(Material.ENDER_CHEST, 2), LootPool.Rarity.COMMON);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.ENCHANTED_BOOK, 1, null, Map.of(Enchantment.SHARPNESS, 5), false), LootPool.Rarity.COMMON);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.ENCHANTED_BOOK, 1, null, Map.of(Enchantment.FORTUNE, 3), false), LootPool.Rarity.COMMON);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.ENCHANTED_BOOK, 1, null, Map.of(Enchantment.MENDING, 1), false), LootPool.Rarity.COMMON);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.ENCHANTED_BOOK, 1, null, Map.of(Enchantment.EFFICIENCY, 5), false), LootPool.Rarity.COMMON);
        pool.addItem(new ItemStack(Material.SHULKER_SHELL, 16), LootPool.Rarity.COMMON);
        pool.addItem(new ItemStack(Material.BEACON, 1), LootPool.Rarity.COMMON);
        pool.addItem(CustomItemCollection.getFlightCredit(1800), LootPool.Rarity.COMMON);

        ///RARE
        pool.addItem(LootCrateKeyItem.get(8, LootCrateKeyItem.LootKeyType.NORMAL), LootPool.Rarity.RARE);
        pool.addItem(LootCrateKeyItem.get(4, LootCrateKeyItem.LootKeyType.RARE), LootPool.Rarity.RARE);
        pool.addItem(LootCrateKeyItem.get(2, LootCrateKeyItem.LootKeyType.LEGENDARY), LootPool.Rarity.RARE);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.ELYTRA, 1, null, Map.of(Enchantment.UNBREAKING, 4, Enchantment.MENDING, 1), false), LootPool.Rarity.RARE);
        pool.addItem(new ItemStack(Material.NETHERITE_BLOCK, 1), LootPool.Rarity.RARE);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.DIAMOND_HELMET, 1, null, Map.of(Enchantment.PROTECTION, 2, Enchantment.UNBREAKING, 1, Enchantment.RESPIRATION, 1), false), LootPool.Rarity.RARE);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.DIAMOND_CHESTPLATE, 1, null, Map.of(Enchantment.PROTECTION, 2, Enchantment.UNBREAKING, 1, Enchantment.THORNS, 1), false), LootPool.Rarity.RARE);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.DIAMOND_LEGGINGS, 1, null, Map.of(Enchantment.PROTECTION, 2, Enchantment.UNBREAKING, 1, Enchantment.THORNS, 1), false), LootPool.Rarity.RARE);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.DIAMOND_BOOTS, 1, null, Map.of(Enchantment.PROTECTION, 2, Enchantment.UNBREAKING, 1, Enchantment.SOUL_SPEED, 1, Enchantment.DEPTH_STRIDER, 1), false), LootPool.Rarity.RARE);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.DIAMOND_SWORD, 1, null, Map.of(Enchantment.SHARPNESS, 2, Enchantment.UNBREAKING, 1, Enchantment.SWEEPING_EDGE, 1), false), LootPool.Rarity.RARE);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.DIAMOND_PICKAXE, 1, null, Map.of(Enchantment.EFFICIENCY, 2, Enchantment.UNBREAKING, 1, Enchantment.FORTUNE, 1), false), LootPool.Rarity.RARE);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.DIAMOND_AXE, 1, null, Map.of(Enchantment.EFFICIENCY, 2, Enchantment.UNBREAKING, 1, Enchantment.FORTUNE, 1), false), LootPool.Rarity.RARE);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.DIAMOND_SHOVEL, 1, null, Map.of(Enchantment.EFFICIENCY, 2, Enchantment.UNBREAKING, 1, Enchantment.FORTUNE, 1), false), LootPool.Rarity.RARE);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.DIAMOND_HOE, 1, null, Map.of(Enchantment.EFFICIENCY, 2, Enchantment.UNBREAKING, 1, Enchantment.FORTUNE, 1), false), LootPool.Rarity.RARE);
        pool.addItem(CustomItemCollection.getFlightCredit(3600), LootPool.Rarity.RARE);

        ///EPIC
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_HELMET, 1, null, Map.of(Enchantment.PROTECTION, 4, Enchantment.AQUA_AFFINITY, 1, Enchantment.RESPIRATION, 1, Enchantment.THORNS, 3, Enchantment.UNBREAKING, 3), false), LootPool.Rarity.EPIC);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_CHESTPLATE, 1, null, Map.of(Enchantment.PROTECTION, 4, Enchantment.THORNS, 3, Enchantment.UNBREAKING, 3), false), LootPool.Rarity.EPIC);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_LEGGINGS, 1, null, Map.of(Enchantment.PROTECTION, 4, Enchantment.SWIFT_SNEAK, 3, Enchantment.THORNS, 3, Enchantment.UNBREAKING, 3), false), LootPool.Rarity.EPIC);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_BOOTS, 1, null, Map.of(Enchantment.PROTECTION, 4, Enchantment.FROST_WALKER, 2, Enchantment.FEATHER_FALLING, 4, Enchantment.SOUL_SPEED, 3, Enchantment.DEPTH_STRIDER, 3, Enchantment.THORNS, 3, Enchantment.UNBREAKING, 3), false), LootPool.Rarity.EPIC);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_PICKAXE, 1, null, Map.of(Enchantment.EFFICIENCY, 5, Enchantment.FORTUNE, 3, Enchantment.UNBREAKING, 3), false), LootPool.Rarity.EPIC);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_AXE, 1, null, Map.of(Enchantment.EFFICIENCY , 5, Enchantment.FORTUNE, 3, Enchantment.UNBREAKING, 3), false), LootPool.Rarity.EPIC);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_SHOVEL, 1, null, Map.of(Enchantment.EFFICIENCY, 5, Enchantment.FORTUNE, 3, Enchantment.UNBREAKING, 3), false), LootPool.Rarity.EPIC);
        pool.addItem(CustomItemCollection.createMonsterSpawner(EntityType.ZOMBIE), LootPool.Rarity.EPIC);
        pool.addItem(CustomItemCollection.createMonsterSpawner(EntityType.CREEPER), LootPool.Rarity.EPIC);
        pool.addItem(CustomItemCollection.createMonsterSpawner(EntityType.SKELETON), LootPool.Rarity.EPIC);
        pool.addItem(CustomItemCollection.createMonsterSpawner(EntityType.SPIDER), LootPool.Rarity.EPIC);
        pool.addItem(CustomItemCollection.getFlightCredit(7200), LootPool.Rarity.EPIC);

        ///LEGENDARY
        pool.addItem(LootCrateKeyItem.get(1, LootCrateKeyItem.LootKeyType.MYTHIC), LootPool.Rarity.LEGENDARY);
        pool.addItem(CustomItemCollection.createMonsterSpawner(EntityType.IRON_GOLEM), LootPool.Rarity.LEGENDARY);
        pool.addItem(CustomItemCollection.createMonsterSpawner(EntityType.WARDEN), LootPool.Rarity.LEGENDARY);
        pool.addItem(CustomItemCollection.createMonsterSpawner(EntityType.WITHER_SKELETON), LootPool.Rarity.LEGENDARY);

        ///MYTHIC
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_SWORD, 1, ChatColor.GOLD + "" + ChatColor.BOLD + "TIER 10 SWORD", Map.of(Enchantment.SHARPNESS, 25, Enchantment.KNOCKBACK, 10, Enchantment.FIRE_ASPECT, 5, Enchantment.LOOTING, 5, Enchantment.SWEEPING_EDGE, 5, Enchantment.UNBREAKING, 5, Enchantment.MENDING, 1), true), LootPool.Rarity.MYTHIC);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.BOW, 1, ChatColor.GOLD + "" + ChatColor.BOLD + "TIER 10 BOW", Map.of(Enchantment.POWER, 25, Enchantment.PUNCH, 10, Enchantment.FLAME, 5, Enchantment.LOOTING, 5, Enchantment.INFINITY, 1, Enchantment.UNBREAKING, 5, Enchantment.MENDING, 1), true), LootPool.Rarity.MYTHIC);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_PICKAXE, 1, ChatColor.GOLD + "" + ChatColor.BOLD + "TIER 10 PICKAXE", Map.of(Enchantment.EFFICIENCY, 25, Enchantment.FORTUNE, 5, Enchantment.UNBREAKING, 5, Enchantment.MENDING, 1), true), LootPool.Rarity.MYTHIC);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_AXE, 1, ChatColor.GOLD + "" + ChatColor.BOLD + "TIER 10 AXE", Map.of(Enchantment.EFFICIENCY, 25, Enchantment.FORTUNE, 5, Enchantment.UNBREAKING, 5, Enchantment.MENDING, 1), true), LootPool.Rarity.MYTHIC);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_HELMET, 1, ChatColor.GOLD + "" + ChatColor.BOLD + "TIER 10 HELMET", Map.of(Enchantment.PROTECTION, 25, Enchantment.AQUA_AFFINITY, 5, Enchantment.RESPIRATION, 5, Enchantment.THORNS, 5, Enchantment.UNBREAKING, 5, Enchantment.MENDING, 1), true), LootPool.Rarity.MYTHIC);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_CHESTPLATE, 1, ChatColor.GOLD + "" + ChatColor.BOLD + "TIER 10 CHESTPLATE", Map.of(Enchantment.PROTECTION, 25, Enchantment.THORNS, 5, Enchantment.UNBREAKING, 5, Enchantment.MENDING, 1), true), LootPool.Rarity.MYTHIC);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_LEGGINGS, 1, ChatColor.GOLD + "" + ChatColor.BOLD + "TIER 10 LEGGINGS", Map.of(Enchantment.PROTECTION, 25, Enchantment.SWIFT_SNEAK, 5, Enchantment.THORNS, 5, Enchantment.UNBREAKING, 5, Enchantment.MENDING, 1), true), LootPool.Rarity.MYTHIC);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_BOOTS, 1, ChatColor.GOLD + "" + ChatColor.BOLD + "TIER 10 BOOTS", Map.of(Enchantment.PROTECTION, 25, Enchantment.FEATHER_FALLING, 10, Enchantment.FROST_WALKER, 5, Enchantment.SOUL_SPEED, 5, Enchantment.DEPTH_STRIDER, 5, Enchantment.THORNS, 5, Enchantment.UNBREAKING, 5, Enchantment.MENDING, 1), true), LootPool.Rarity.MYTHIC);
        pool.addItem(LootCrateKeyItem.get(2, LootCrateKeyItem.LootKeyType.MYTHIC), LootPool.Rarity.MYTHIC);

        pool.calculateWeight();
        return pool;
    }

    public static LootPool.Pool initializeMythicLootCratePool(){
        LootPool.Pool pool = new LootPool.Pool(ChatColor.LIGHT_PURPLE + "Mythic Loot Crate");

        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_SWORD, 1, ChatColor.GOLD + "" + ChatColor.BOLD + "TIER 10 SWORD", Map.of(Enchantment.SHARPNESS, 25, Enchantment.KNOCKBACK, 10, Enchantment.FIRE_ASPECT, 5, Enchantment.LOOTING, 5, Enchantment.SWEEPING_EDGE, 5, Enchantment.UNBREAKING, 5, Enchantment.MENDING, 1), true), LootPool.Rarity.MYTHIC);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.BOW, 1, ChatColor.GOLD + "" + ChatColor.BOLD + "TIER 10 BOW", Map.of(Enchantment.POWER, 25, Enchantment.PUNCH, 10, Enchantment.FLAME, 5, Enchantment.LOOTING, 5, Enchantment.INFINITY, 1, Enchantment.UNBREAKING, 5, Enchantment.MENDING, 1), true), LootPool.Rarity.MYTHIC);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_PICKAXE, 1, ChatColor.GOLD + "" + ChatColor.BOLD + "TIER 10 PICKAXE", Map.of(Enchantment.EFFICIENCY, 25, Enchantment.FORTUNE, 5, Enchantment.UNBREAKING, 5, Enchantment.MENDING, 1), true), LootPool.Rarity.MYTHIC);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_AXE, 1, ChatColor.GOLD + "" + ChatColor.BOLD + "TIER 10 AXE", Map.of(Enchantment.EFFICIENCY, 25, Enchantment.FORTUNE, 5, Enchantment.UNBREAKING, 5, Enchantment.MENDING, 1), true), LootPool.Rarity.MYTHIC);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_SHOVEL, 1, ChatColor.GOLD + "" + ChatColor.BOLD + "TIER 10 SHOVEL", Map.of(Enchantment.EFFICIENCY, 25, Enchantment.FORTUNE, 5, Enchantment.UNBREAKING, 5, Enchantment.MENDING, 1), true), LootPool.Rarity.MYTHIC);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_HOE, 1, ChatColor.GOLD + "" + ChatColor.BOLD + "TIER 10 HOE", Map.of(Enchantment.EFFICIENCY, 25, Enchantment.FORTUNE, 5, Enchantment.UNBREAKING, 5, Enchantment.MENDING, 1), true), LootPool.Rarity.MYTHIC);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_HELMET, 1, ChatColor.GOLD + "" + ChatColor.BOLD + "TIER 10 HELMET", Map.of(Enchantment.PROTECTION, 25, Enchantment.AQUA_AFFINITY, 5, Enchantment.RESPIRATION, 5, Enchantment.THORNS, 5, Enchantment.UNBREAKING, 5, Enchantment.MENDING, 1), true), LootPool.Rarity.MYTHIC);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_CHESTPLATE, 1, ChatColor.GOLD + "" + ChatColor.BOLD + "TIER 10 CHESTPLATE", Map.of(Enchantment.PROTECTION, 25, Enchantment.THORNS, 5, Enchantment.UNBREAKING, 5, Enchantment.MENDING, 1), true), LootPool.Rarity.MYTHIC);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_LEGGINGS, 1, ChatColor.GOLD + "" + ChatColor.BOLD + "TIER 10 LEGGINGS", Map.of(Enchantment.PROTECTION, 25, Enchantment.SWIFT_SNEAK, 5, Enchantment.THORNS, 5, Enchantment.UNBREAKING, 5, Enchantment.MENDING, 1), true), LootPool.Rarity.MYTHIC);
        pool.addItem(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_BOOTS, 1, ChatColor.GOLD + "" + ChatColor.BOLD + "TIER 10 BOOTS", Map.of(Enchantment.PROTECTION, 25, Enchantment.FEATHER_FALLING, 10, Enchantment.FROST_WALKER, 5, Enchantment.SOUL_SPEED, 5, Enchantment.DEPTH_STRIDER, 5, Enchantment.THORNS, 5, Enchantment.UNBREAKING, 5, Enchantment.MENDING, 1), true), LootPool.Rarity.MYTHIC);

        pool.calculateWeight();
        return pool;
    }
}
