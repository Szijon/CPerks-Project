package test.server.ririyo.cPerks.perks.perkmenu;

import org.bukkit.Material;

import java.util.Map;

public class SaleMenuCollection {

    public static Map<Material, Integer> prices = Map.<Material, Integer>ofEntries(
            ///WOODCUTTER ITEMS
            Map.entry(Material.OAK_LOG, 5),
            Map.entry(Material.BIRCH_LOG, 5),
            Map.entry(Material.SPRUCE_LOG, 5),
            Map.entry(Material.JUNGLE_LOG, 5),
            Map.entry(Material.DARK_OAK_LOG, 5),
            Map.entry(Material.ACACIA_LOG, 5),
            Map.entry(Material.CHERRY_LOG, 5),
            Map.entry(Material.OAK_WOOD, 5),
            Map.entry(Material.WARPED_STEM, 5),
            Map.entry(Material.CRIMSON_STEM, 5),

            ///MINER ITEMS
            Map.entry(Material.COAL, 5),
            Map.entry(Material.COAL_BLOCK, 45),
            Map.entry(Material.RAW_COPPER, 2),
            Map.entry(Material.RAW_COPPER_BLOCK, 18),
            Map.entry(Material.COPPER_INGOT, 4),
            Map.entry(Material.COPPER_BLOCK, 36),
            Map.entry(Material.RAW_IRON, 3),
            Map.entry(Material.RAW_IRON_BLOCK, 27),
            Map.entry(Material.IRON_INGOT, 6),
            Map.entry(Material.IRON_BLOCK, 54),
            Map.entry(Material.RAW_GOLD, 5),
            Map.entry(Material.RAW_GOLD_BLOCK, 45),
            Map.entry(Material.GOLD_INGOT, 10),
            Map.entry(Material.GOLD_BLOCK, 91),
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

            ///FARMER ITEMS
                ///CROP YIELD
            Map.entry(Material.POTATO, 6),
            Map.entry(Material.CARROT, 6),
            Map.entry(Material.WHEAT, 12),
            Map.entry(Material.BEETROOT, 6),
            Map.entry(Material.PUMPKIN, 12),
            Map.entry(Material.MELON_SLICE, 2),
            Map.entry(Material.MELON, 18),
                ///CROP SEEDS
            Map.entry(Material.WHEAT_SEEDS, 6),
            Map.entry(Material.BEETROOT_SEEDS, 6),
            Map.entry(Material.MELON_SEEDS, 2),
            Map.entry(Material.PUMPKIN_SEEDS, 2),

            ///ENCHANTER GOT NOTHING TO SELL

            ///HUNTER ITEMS
                ///PASSIVES
            Map.entry(Material.MUTTON, 6),
            Map.entry(Material.PORKCHOP, 6),
            Map.entry(Material.BEEF, 6),
            Map.entry(Material.CHICKEN, 6),
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
            Map.entry(Material.ROTTEN_FLESH, 9),
            Map.entry(Material.BONE, 9),
            Map.entry(Material.ARROW, 9),
            Map.entry(Material.GUNPOWDER, 9),
            Map.entry(Material.STRING, 9),
            Map.entry(Material.SPIDER_EYE, 9),
            Map.entry(Material.SLIME_BALL, 9),
            Map.entry(Material.SHULKER_SHELL, 32),
            Map.entry(Material.ENDER_PEARL, 12),
            Map.entry(Material.PRISMARINE_SHARD, 12),
            Map.entry(Material.PRISMARINE_CRYSTALS, 12)
    );
}
