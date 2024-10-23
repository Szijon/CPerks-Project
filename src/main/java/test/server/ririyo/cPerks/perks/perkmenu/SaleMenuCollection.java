package test.server.ririyo.cPerks.perks.perkmenu;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import test.server.ririyo.cPerks.lootcrate.LootCrateKeyItem;

import java.util.Map;

public class SaleMenuCollection {

    public static Map<Material, Integer> prices = Map.<Material, Integer>ofEntries(

            ///TERRAFORMING
            Map.entry(Material.DIRT, 1),
            Map.entry(Material.FLINT, 1),
            Map.entry(Material.GRAVEL, 1),
            Map.entry(Material.COBBLESTONE, 1),
            Map.entry(Material.STONE, 1),
            Map.entry(Material.DEEPSLATE, 1),
            Map.entry(Material.COBBLED_DEEPSLATE, 1),
            Map.entry(Material.GRANITE, 1),
            Map.entry(Material.ANDESITE, 1),
            Map.entry(Material.DIORITE, 1),

            ///WOODCUTTER ITEMS
                ///LOGS
            Map.entry(Material.OAK_LOG, 5),
            Map.entry(Material.BIRCH_LOG, 5),
            Map.entry(Material.SPRUCE_LOG, 5),
            Map.entry(Material.JUNGLE_LOG, 5),
            Map.entry(Material.DARK_OAK_LOG, 5),
            Map.entry(Material.ACACIA_LOG, 5),
            Map.entry(Material.CHERRY_LOG, 5),
            Map.entry(Material.WARPED_STEM, 5),
            Map.entry(Material.CRIMSON_STEM, 5),
                ///SAPLINGS
            Map.entry(Material.OAK_SAPLING, 5),
            Map.entry(Material.BIRCH_SAPLING, 5),
            Map.entry(Material.SPRUCE_SAPLING, 5),
            Map.entry(Material.JUNGLE_SAPLING, 5),
            Map.entry(Material.DARK_OAK_SAPLING, 5),
            Map.entry(Material.ACACIA_SAPLING, 5),
            Map.entry(Material.CHERRY_SAPLING, 5),

            ///MINER ITEMS
            Map.entry(Material.COAL, 5),
            Map.entry(Material.COAL_BLOCK, 45),
            Map.entry(Material.RAW_COPPER, 2),
            Map.entry(Material.RAW_COPPER_BLOCK, 18),
            Map.entry(Material.COPPER_INGOT, 3),
            Map.entry(Material.COPPER_BLOCK, 27),
            Map.entry(Material.RAW_IRON, 3),
            Map.entry(Material.RAW_IRON_BLOCK, 27),
            Map.entry(Material.IRON_INGOT, 5),
            Map.entry(Material.IRON_BLOCK, 45),
            Map.entry(Material.RAW_GOLD, 4),
            Map.entry(Material.RAW_GOLD_BLOCK, 36),
            Map.entry(Material.GOLD_INGOT, 8),
            Map.entry(Material.GOLD_BLOCK, 72),
            Map.entry(Material.LAPIS_LAZULI, 7),
            Map.entry(Material.LAPIS_BLOCK, 63),
            Map.entry(Material.REDSTONE, 5),
            Map.entry(Material.REDSTONE_BLOCK, 45),
            Map.entry(Material.DIAMOND, 15),
            Map.entry(Material.DIAMOND_BLOCK, 135),
            Map.entry(Material.EMERALD, 15),
            Map.entry(Material.EMERALD_BLOCK, 135),
            Map.entry(Material.ANCIENT_DEBRIS, 30),
            Map.entry(Material.NETHERITE_SCRAP, 37),
            Map.entry(Material.NETHERITE_INGOT, 150),
            Map.entry(Material.NETHERITE_BLOCK, 1350),
            Map.entry(Material.QUARTZ, 5),
            Map.entry(Material.QUARTZ_BLOCK, 20),
            Map.entry(Material.OBSIDIAN, 20),
            Map.entry(Material.TUFF, 1),

            ///FARMER ITEMS
                ///CROP YIELD
            Map.entry(Material.POTATO, 3),
            Map.entry(Material.CARROT, 3),
            Map.entry(Material.WHEAT, 6),
            Map.entry(Material.HAY_BLOCK, 54),
            Map.entry(Material.BEETROOT, 3),
            Map.entry(Material.PUMPKIN, 6),
            Map.entry(Material.MELON_SLICE, 1),
            Map.entry(Material.MELON, 9),
            Map.entry(Material.POISONOUS_POTATO, 1),
                ///CROP SEEDS
            Map.entry(Material.WHEAT_SEEDS, 2),
            Map.entry(Material.BEETROOT_SEEDS, 3),
            Map.entry(Material.MELON_SEEDS, 1),
            Map.entry(Material.PUMPKIN_SEEDS, 1),

            ///ENCHANTER GOT NOTHING TO SELL YET

            ///HUNTER ITEMS
                ///PASSIVES
            Map.entry(Material.MUTTON, 6),
            Map.entry(Material.PORKCHOP, 6),
            Map.entry(Material.BEEF, 6),
            Map.entry(Material.CHICKEN, 6),
            Map.entry(Material.EGG, 6),
            Map.entry(Material.COOKED_MUTTON, 9),
            Map.entry(Material.COOKED_PORKCHOP, 9),
            Map.entry(Material.COOKED_BEEF, 6),
            Map.entry(Material.COOKED_CHICKEN, 6),
            Map.entry(Material.WHITE_WOOL, 6),
            Map.entry(Material.LEATHER, 6),
            Map.entry(Material.FEATHER, 6),
            Map.entry(Material.INK_SAC, 6),
            Map.entry(Material.GLOW_INK_SAC, 9),
            Map.entry(Material.RABBIT, 9),
            Map.entry(Material.COOKED_RABBIT, 14),
                ///HOSTILES
            Map.entry(Material.ROTTEN_FLESH, 4),
            Map.entry(Material.BONE, 4),
            Map.entry(Material.ARROW, 4),
            Map.entry(Material.GUNPOWDER, 4),
            Map.entry(Material.STRING, 4),
            Map.entry(Material.SPIDER_EYE, 4),
            Map.entry(Material.SLIME_BALL, 4),
            Map.entry(Material.SHULKER_SHELL, 16),
            Map.entry(Material.ENDER_PEARL, 6),
            Map.entry(Material.PRISMARINE_SHARD, 12),
            Map.entry(Material.PRISMARINE_CRYSTALS, 12),

            /// OTHER ITEMS
            Map.entry(Material.GOLDEN_APPLE, 25)
    );

    public static Map<String, ItemStack> saleTags = Map.ofEntries(
                ///SOME ITEMS OBTAINED FROM LOOT CRATES CAN BE SOLD FOR LOWER TIER LOOT CRATE KEYS
            Map.entry("Rare", LootCrateKeyItem.get(1, LootCrateKeyItem.LootKeyType.NORMAL)),
            Map.entry("Legendary", LootCrateKeyItem.get(1, LootCrateKeyItem.LootKeyType.RARE)),
            Map.entry("Mythic", LootCrateKeyItem.get(1, LootCrateKeyItem.LootKeyType.LEGENDARY))
    );
}
