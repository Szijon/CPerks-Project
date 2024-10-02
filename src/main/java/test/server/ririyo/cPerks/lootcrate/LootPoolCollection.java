package test.server.ririyo.cPerks.lootcrate;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import test.server.ririyo.cPerks.collections.CustomItemCollection;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class LootPoolCollection {

    public record WeightedDrop(ItemStack item, int weight) {}
    
        ///WEIGHTED LOOT POOLS TO NOT MAKE EVERY ITEM THE SAME CHANCE TO DROP
    public static List<WeightedDrop> keyLootPool = Arrays.asList(
            ///USED FOR GETTING A RANDOM KEY ON TRIGGERING 0.01% DROP CHANCE
            new WeightedDrop(CustomItemCollection.createLootKey(1, "Normal"), 10000),
            new WeightedDrop(CustomItemCollection.createLootKey(1, "Rare"), 8000),
            new WeightedDrop(CustomItemCollection.createLootKey(5, "Normal"), 4500),
            new WeightedDrop(CustomItemCollection.createLootKey(1, "Legendary"), 3500),
            new WeightedDrop(CustomItemCollection.createLootKey(5, "Rare"), 1500),
            ///OVERALL CHANCE TO DROP: 0.00125%
            new WeightedDrop(CustomItemCollection.createLootKey(1, "Tools"), 500)
    );

    public static List<WeightedDrop> normalLootPool = Arrays.asList(
            ///COMMON ~39% Total Chance
            new WeightedDrop(new ItemStack(Material.GOLDEN_CARROT, 16), 3000),
            new WeightedDrop(new ItemStack(Material.OAK_LOG, 64), 3000),
            new WeightedDrop(new ItemStack(Material.TOTEM_OF_UNDYING, 1), 3000),
            new WeightedDrop(new ItemStack(Material.GOLDEN_APPLE, 8), 3000),
            new WeightedDrop(new ItemStack(Material.IRON_ORE, 16), 3000),
            new WeightedDrop(new ItemStack(Material.GOLD_ORE, 16), 3000),
            new WeightedDrop(new ItemStack(Material.DIAMOND, 8), 3000),
            new WeightedDrop(new ItemStack(Material.EMERALD, 8), 3000),
            new WeightedDrop(new ItemStack(Material.ENDER_PEARL, 8), 3000),

            ///UNCOMMON ~29% Total Chance
            new WeightedDrop(CustomItemCollection.createLootKey(1, "Normal"), 2500),
            new WeightedDrop(new ItemStack(Material.NETHERITE_INGOT, 1), 2500),
            new WeightedDrop(new ItemStack(Material.NETHERITE_SCRAP, 4), 2500),
            new WeightedDrop(new ItemStack(Material.NETHERITE_UPGRADE_SMITHING_TEMPLATE, 1), 2500),
            new WeightedDrop(new ItemStack(Material.ELYTRA, 1), 2500),
            new WeightedDrop(new ItemStack(Material.EXPERIENCE_BOTTLE, 16), 2500),
            new WeightedDrop(new ItemStack(Material.SHULKER_SHELL, 2), 2500),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.ENCHANTED_BOOK, 1, null, Map.of(Enchantment.UNBREAKING, 3)), 2500),

            ///RARE ~17% Total Chance
            new WeightedDrop(CustomItemCollection.createLootKey(1, "Rare"), 2000),
            new WeightedDrop(new ItemStack(Material.ENDER_CHEST, 1), 2000),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.ENCHANTED_BOOK, 1, null, Map.of(Enchantment.SHARPNESS, 5)), 2000),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.ENCHANTED_BOOK, 1, null, Map.of(Enchantment.FORTUNE, 3)), 2000),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.ENCHANTED_BOOK, 1, null, Map.of(Enchantment.MENDING, 1)), 2000),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.ENCHANTED_BOOK, 1, null, Map.of(Enchantment.EFFICIENCY, 5)), 2000),

            ///VERY RARE ~13% Total Chance
            new WeightedDrop(CustomItemCollection.createLootKey(1, "Legendary"), 1500),
            new WeightedDrop(CustomItemCollection.createMonsterSpawner(EntityType.COW), 1500),
            new WeightedDrop(CustomItemCollection.createMonsterSpawner(EntityType.PIG), 1500),
            new WeightedDrop(CustomItemCollection.createMonsterSpawner(EntityType.SHEEP), 1500),
            new WeightedDrop(CustomItemCollection.createMonsterSpawner(EntityType.CHICKEN), 1500)
    );

    public static List<WeightedDrop> rareLootPool = Arrays.asList(
            ///COMMON
            new WeightedDrop(new ItemStack(Material.GOLDEN_APPLE, 8), 3000),
            new WeightedDrop(new ItemStack(Material.NETHERITE_INGOT, 1), 3000),
            new WeightedDrop(new ItemStack(Material.NETHERITE_SCRAP, 4), 3000),
            new WeightedDrop(new ItemStack(Material.NETHERITE_UPGRADE_SMITHING_TEMPLATE, 1), 3000),
            new WeightedDrop(new ItemStack(Material.EXPERIENCE_BOTTLE, 64), 3000),
            new WeightedDrop(new ItemStack(Material.SHULKER_SHELL, 4), 3000),
            new WeightedDrop(new ItemStack(Material.DIAMOND, 16), 3000),
            new WeightedDrop(new ItemStack(Material.EMERALD, 16), 3000),

            ///UNCOMMON
            new WeightedDrop(new ItemStack(Material.ELYTRA, 1), 2500),
            new WeightedDrop(new ItemStack(Material.ENDER_CHEST, 1), 2500),
            new WeightedDrop(new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1), 2500),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.ENCHANTED_BOOK, 1, null, Map.of(Enchantment.SHARPNESS, 5)), 2500),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.ENCHANTED_BOOK, 1, null, Map.of(Enchantment.FORTUNE, 3)), 2500),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.ENCHANTED_BOOK, 1, null, Map.of(Enchantment.MENDING, 1)), 2500),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.ENCHANTED_BOOK, 1, null, Map.of(Enchantment.EFFICIENCY, 5)), 2500),
            new WeightedDrop(new ItemStack(Material.SHULKER_SHELL, 16), 2500),

            ///RARE
            new WeightedDrop(CustomItemCollection.createLootKey(1, "Legendary"), 1500),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.ELYTRA, 1, null, Map.of(Enchantment.UNBREAKING, 4, Enchantment.MENDING, 1)), 1500),
            new WeightedDrop(new ItemStack(Material.NETHERITE_BLOCK, 1), 1500),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.DIAMOND_HELMET, 1, null, Map.of(Enchantment.PROTECTION, 2, Enchantment.UNBREAKING, 1, Enchantment.RESPIRATION, 1)), 1500),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.DIAMOND_CHESTPLATE, 1, null, Map.of(Enchantment.PROTECTION, 2, Enchantment.UNBREAKING, 1, Enchantment.THORNS, 1)), 1500),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.DIAMOND_LEGGINGS, 1, null, Map.of(Enchantment.PROTECTION, 2, Enchantment.UNBREAKING, 1, Enchantment.THORNS, 1)), 1500),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.DIAMOND_BOOTS, 1, null, Map.of(Enchantment.PROTECTION, 2, Enchantment.UNBREAKING, 1, Enchantment.SOUL_SPEED, 1, Enchantment.DEPTH_STRIDER, 1)), 1500),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.DIAMOND_SWORD, 1, null, Map.of(Enchantment.SHARPNESS, 2, Enchantment.UNBREAKING, 1, Enchantment.SWEEPING_EDGE, 1)), 1500),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.DIAMOND_PICKAXE, 1, null, Map.of(Enchantment.EFFICIENCY, 2, Enchantment.UNBREAKING, 1, Enchantment.FORTUNE, 1)), 1500),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.DIAMOND_AXE, 1, null, Map.of(Enchantment.EFFICIENCY, 2, Enchantment.UNBREAKING, 1, Enchantment.FORTUNE, 1)), 1500),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.DIAMOND_SHOVEL, 1, null, Map.of(Enchantment.EFFICIENCY, 2, Enchantment.UNBREAKING, 1, Enchantment.FORTUNE, 1)), 1500),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.DIAMOND_HOE, 1, null, Map.of(Enchantment.EFFICIENCY, 2, Enchantment.UNBREAKING, 1, Enchantment.FORTUNE, 1)), 1500),

            ///VERY RARE
            new WeightedDrop(CustomItemCollection.createMonsterSpawner(EntityType.ZOMBIE), 1000),
            new WeightedDrop(CustomItemCollection.createMonsterSpawner(EntityType.CREEPER), 1000),
            new WeightedDrop(CustomItemCollection.createMonsterSpawner(EntityType.SKELETON), 1000),
            new WeightedDrop(CustomItemCollection.createMonsterSpawner(EntityType.SPIDER), 1000),

            ///EXTRAORDINARILY RARE
            new WeightedDrop(CustomItemCollection.createLootKey(1, "Gear"), 500)
    );

    public static List<WeightedDrop> legendaryLootPool = Arrays.asList(
            ///COMMON
            new WeightedDrop(new ItemStack(Material.NETHERITE_INGOT, 2), 3000),
            new WeightedDrop(new ItemStack(Material.NETHERITE_SCRAP, 8), 3000),
            new WeightedDrop(new ItemStack(Material.NETHERITE_UPGRADE_SMITHING_TEMPLATE, 2), 3000),
            new WeightedDrop(new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 4), 3000),
            new WeightedDrop(new ItemStack(Material.ENDER_CHEST, 2), 3000),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.ENCHANTED_BOOK, 1, null, Map.of(Enchantment.SHARPNESS, 5)), 3000),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.ENCHANTED_BOOK, 1, null, Map.of(Enchantment.FORTUNE, 3)), 3000),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.ENCHANTED_BOOK, 1, null, Map.of(Enchantment.MENDING, 1)), 3000),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.ENCHANTED_BOOK, 1, null, Map.of(Enchantment.EFFICIENCY, 5)), 3000),
            new WeightedDrop(new ItemStack(Material.SHULKER_SHELL, 16), 3000),
            new WeightedDrop(new ItemStack(Material.BEACON, 1), 3000),

            ///UNCOMMON
            new WeightedDrop(CustomItemCollection.createLootKey(8, "Normal"), 2500),
            new WeightedDrop(CustomItemCollection.createLootKey(3, "Rare"), 2500),
            new WeightedDrop(CustomItemCollection.createLootKey(1, "Legendary"), 2500), ///RETRY KEY
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.ELYTRA, 1, null, Map.of(Enchantment.UNBREAKING, 4, Enchantment.MENDING, 1)), 2500),
            new WeightedDrop(new ItemStack(Material.NETHERITE_BLOCK, 1), 2500),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.DIAMOND_HELMET, 1, null, Map.of(Enchantment.PROTECTION, 2, Enchantment.UNBREAKING, 1, Enchantment.RESPIRATION, 1)), 2500),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.DIAMOND_CHESTPLATE, 1, null, Map.of(Enchantment.PROTECTION, 2, Enchantment.UNBREAKING, 1, Enchantment.THORNS, 1)), 2500),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.DIAMOND_LEGGINGS, 1, null, Map.of(Enchantment.PROTECTION, 2, Enchantment.UNBREAKING, 1, Enchantment.THORNS, 1)), 2500),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.DIAMOND_BOOTS, 1, null, Map.of(Enchantment.PROTECTION, 2, Enchantment.UNBREAKING, 1, Enchantment.SOUL_SPEED, 1, Enchantment.DEPTH_STRIDER, 1)), 2500),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.DIAMOND_SWORD, 1, null, Map.of(Enchantment.SHARPNESS, 2, Enchantment.UNBREAKING, 1, Enchantment.SWEEPING_EDGE, 1)), 2500),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.DIAMOND_PICKAXE, 1, null, Map.of(Enchantment.EFFICIENCY, 2, Enchantment.UNBREAKING, 1, Enchantment.FORTUNE, 1)), 2500),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.DIAMOND_AXE, 1, null, Map.of(Enchantment.EFFICIENCY, 2, Enchantment.UNBREAKING, 1, Enchantment.FORTUNE, 1)), 2500),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.DIAMOND_SHOVEL, 1, null, Map.of(Enchantment.EFFICIENCY, 2, Enchantment.UNBREAKING, 1, Enchantment.FORTUNE, 1)), 2500),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.DIAMOND_HOE, 1, null, Map.of(Enchantment.EFFICIENCY, 2, Enchantment.UNBREAKING, 1, Enchantment.FORTUNE, 1)), 2500),

            ///RARE
                ///MAX ENCHANTED ARMOR
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_HELMET, 1, null, Map.of(Enchantment.PROTECTION, 4, Enchantment.AQUA_AFFINITY, 1, Enchantment.RESPIRATION, 1, Enchantment.THORNS, 3, Enchantment.UNBREAKING, 3)), 1500),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_CHESTPLATE, 1, null, Map.of(Enchantment.PROTECTION, 4, Enchantment.THORNS, 3, Enchantment.UNBREAKING, 3)), 1500),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_LEGGINGS, 1, null, Map.of(Enchantment.PROTECTION, 4, Enchantment.SWIFT_SNEAK, 3, Enchantment.THORNS, 3, Enchantment.UNBREAKING, 3)), 1500),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_BOOTS, 1, null, Map.of(Enchantment.PROTECTION, 4, Enchantment.FROST_WALKER, 2, Enchantment.FEATHER_FALLING, 4, Enchantment.SOUL_SPEED, 3, Enchantment.DEPTH_STRIDER, 3, Enchantment.THORNS, 3, Enchantment.UNBREAKING, 3)), 1500),
                ///MAX ENCHANTED TOOLS
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_PICKAXE, 1, null, Map.of(Enchantment.EFFICIENCY, 5, Enchantment.FORTUNE, 3, Enchantment.UNBREAKING, 3)), 1500),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_AXE, 1, null, Map.of(Enchantment.EFFICIENCY , 5, Enchantment.FORTUNE, 3, Enchantment.UNBREAKING, 3)), 1500),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_SHOVEL, 1, null, Map.of(Enchantment.EFFICIENCY, 5, Enchantment.FORTUNE, 3, Enchantment.UNBREAKING, 3)), 1500),
                ///SPAWNERS FROM LOWER KEY POOL
            new WeightedDrop(CustomItemCollection.createMonsterSpawner(EntityType.ZOMBIE), 1500),
            new WeightedDrop(CustomItemCollection.createMonsterSpawner(EntityType.CREEPER), 1500),
            new WeightedDrop(CustomItemCollection.createMonsterSpawner(EntityType.SKELETON), 1500),
            new WeightedDrop(CustomItemCollection.createMonsterSpawner(EntityType.SPIDER), 1500),

            ///VERY RARE
            new WeightedDrop(CustomItemCollection.createLootKey(1, "Tools"), 1000),
            new WeightedDrop(CustomItemCollection.createMonsterSpawner(EntityType.IRON_GOLEM), 1000),
            new WeightedDrop(CustomItemCollection.createMonsterSpawner(EntityType.WARDEN), 1000),
            new WeightedDrop(CustomItemCollection.createMonsterSpawner(EntityType.WITHER_SKELETON), 1000),

            ///EXTRAORDINARILY RARE
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_SWORD, 1, ChatColor.GOLD + "" + ChatColor.BOLD + "OP SWORD", Map.of(Enchantment.SHARPNESS, 25, Enchantment.KNOCKBACK, 10, Enchantment.FIRE_ASPECT, 5, Enchantment.LOOTING, 5, Enchantment.SWEEPING_EDGE, 5, Enchantment.UNBREAKING, 5, Enchantment.MENDING, 1)), 500),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.BOW, 1, ChatColor.GOLD + "" + ChatColor.BOLD + "OP BOW", Map.of(Enchantment.POWER, 25, Enchantment.PUNCH, 10, Enchantment.FLAME, 5, Enchantment.LOOTING, 5, Enchantment.INFINITY, 1, Enchantment.UNBREAKING, 5, Enchantment.MENDING, 1)), 500),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_PICKAXE, 1, ChatColor.GOLD + "" + ChatColor.BOLD + "OP PICKAXE", Map.of(Enchantment.EFFICIENCY, 25, Enchantment.FORTUNE, 5, Enchantment.UNBREAKING, 5, Enchantment.MENDING, 1)), 500),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_AXE, 1, ChatColor.GOLD + "" + ChatColor.BOLD + "OP AXE", Map.of(Enchantment.EFFICIENCY, 25, Enchantment.FORTUNE, 5, Enchantment.UNBREAKING, 5, Enchantment.MENDING, 1)), 500),

            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_HELMET, 1, ChatColor.GOLD + "" + ChatColor.BOLD + "OP HELMET", Map.of(Enchantment.PROTECTION, 25, Enchantment.AQUA_AFFINITY, 5, Enchantment.RESPIRATION, 5, Enchantment.THORNS, 5, Enchantment.UNBREAKING, 5, Enchantment.MENDING, 1)), 500),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_CHESTPLATE, 1, ChatColor.GOLD + "" + ChatColor.BOLD + "OP CHESTPLATE", Map.of(Enchantment.PROTECTION, 25, Enchantment.THORNS, 5, Enchantment.UNBREAKING, 5, Enchantment.MENDING, 1)), 500),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_HELMET, 1, ChatColor.GOLD + "" + ChatColor.BOLD + "OP HELMET", Map.of(Enchantment.PROTECTION, 25, Enchantment.AQUA_AFFINITY, 5, Enchantment.RESPIRATION, 5, Enchantment.THORNS, 5, Enchantment.UNBREAKING, 5, Enchantment.MENDING, 1)), 500),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_BOOTS, 1, ChatColor.GOLD + "" + ChatColor.BOLD + "OP HELMET", Map.of(Enchantment.PROTECTION, 25, Enchantment.FEATHER_FALLING, 10, Enchantment.FROST_WALKER, 5, Enchantment.SOUL_SPEED, 5, Enchantment.DEPTH_STRIDER, 5, Enchantment.THORNS, 5, Enchantment.UNBREAKING, 5, Enchantment.MENDING, 1)), 500)
            );

    public static List<WeightedDrop> gearLootPool = Arrays.asList(
            ///ALL ITEMS HAVE EQUAL DROP CHANCE
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_SWORD, 1, ChatColor.GOLD + "" + ChatColor.BOLD + "OP SWORD", Map.of(Enchantment.SHARPNESS, 25, Enchantment.KNOCKBACK, 10, Enchantment.FIRE_ASPECT, 5, Enchantment.LOOTING, 5, Enchantment.SWEEPING_EDGE, 5, Enchantment.UNBREAKING, 5, Enchantment.MENDING, 1)), 10),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.BOW, 1, ChatColor.GOLD + "" + ChatColor.BOLD + "OP BOW", Map.of(Enchantment.POWER, 25, Enchantment.PUNCH, 10, Enchantment.FLAME, 5, Enchantment.LOOTING, 5, Enchantment.INFINITY, 1, Enchantment.UNBREAKING, 5, Enchantment.MENDING, 1)), 10),

            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_PICKAXE, 1, ChatColor.GOLD + "" + ChatColor.BOLD + "OP PICKAXE", Map.of(Enchantment.EFFICIENCY, 25, Enchantment.FORTUNE, 5, Enchantment.UNBREAKING, 5, Enchantment.MENDING, 1)), 10),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_AXE, 1, ChatColor.GOLD + "" + ChatColor.BOLD + "OP AXE", Map.of(Enchantment.EFFICIENCY, 25, Enchantment.FORTUNE, 5, Enchantment.UNBREAKING, 5, Enchantment.MENDING, 1)), 10),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_SHOVEL, 1, ChatColor.GOLD + "" + ChatColor.BOLD + "OP SHOVEL", Map.of(Enchantment.EFFICIENCY, 25, Enchantment.FORTUNE, 5, Enchantment.UNBREAKING, 5, Enchantment.MENDING, 1)), 10),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_HOE, 1, ChatColor.GOLD + "" + ChatColor.BOLD + "OP HOE", Map.of(Enchantment.EFFICIENCY, 25, Enchantment.FORTUNE, 5, Enchantment.UNBREAKING, 5, Enchantment.MENDING, 1)), 10),

            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_HELMET, 1, ChatColor.GOLD + "" + ChatColor.BOLD + "OP HELMET", Map.of(Enchantment.PROTECTION, 25, Enchantment.AQUA_AFFINITY, 5, Enchantment.RESPIRATION, 5, Enchantment.THORNS, 5, Enchantment.UNBREAKING, 5, Enchantment.MENDING, 1)), 10),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_CHESTPLATE, 1, ChatColor.GOLD + "" + ChatColor.BOLD + "OP CHESTPLATE", Map.of(Enchantment.PROTECTION, 25, Enchantment.THORNS, 5, Enchantment.UNBREAKING, 5, Enchantment.MENDING, 1)), 10),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_HELMET, 1, ChatColor.GOLD + "" + ChatColor.BOLD + "OP HELMET", Map.of(Enchantment.PROTECTION, 25, Enchantment.AQUA_AFFINITY, 5, Enchantment.RESPIRATION, 5, Enchantment.THORNS, 5, Enchantment.UNBREAKING, 5, Enchantment.MENDING, 1)), 10),
            new WeightedDrop(CustomItemCollection.createSimpleCustomItem(Material.NETHERITE_HELMET, 1, ChatColor.GOLD + "" + ChatColor.BOLD + "OP HELMET", Map.of(Enchantment.PROTECTION, 25, Enchantment.AQUA_AFFINITY, 5, Enchantment.RESPIRATION, 5, Enchantment.THORNS, 5, Enchantment.UNBREAKING, 5, Enchantment.MENDING, 1)), 10)
    );
}
