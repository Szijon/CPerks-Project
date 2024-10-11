package test.server.ririyo.cPerks.perks.perklogic;

import org.bukkit.Material;

import java.util.Map;

public class WoodCutterCollection {
        ///MAP OF LEVELS(KEY) AND THEIR RESPECTIVE VEIN MINER BLOCK CAP(VALUE) FOR THIS SPECIFIC PERK
    public static final Map<Integer, Integer> veinMineBlocks = Map.ofEntries(
            Map.entry(5, 3), Map.entry(6,6),
            Map.entry(7, 9), Map.entry(8,12),
            Map.entry(9, 15), Map.entry(10,20),
            Map.entry(11, 22), Map.entry(12,24),
            Map.entry(13, 26), Map.entry(14,28),
            Map.entry(15, 30), Map.entry(16,33),
            Map.entry(17, 36), Map.entry(18,39),
            Map.entry(19, 42), Map.entry(20,45),
            Map.entry(21, 50), Map.entry(22,55),
            Map.entry(23, 60), Map.entry(24,65),
            Map.entry(25, 70), Map.entry(26,76),
            Map.entry(27, 82), Map.entry(28,88),
            Map.entry(29, 94), Map.entry(30,100)
    );
        ///MAP OF LOGS(KEY) MATCHED TO THEIR RESPECTIVE SAPLINGS(VALUE)
    public static final Map<Material, Material> saplingMatch = Map.ofEntries(
            Map.entry(Material.OAK_LOG, Material.OAK_SAPLING),
            Map.entry(Material.BIRCH_LOG, Material.BIRCH_SAPLING),
            Map.entry(Material.SPRUCE_LOG, Material.SPRUCE_SAPLING),
            Map.entry(Material.JUNGLE_LOG, Material.JUNGLE_SAPLING),
            Map.entry(Material.DARK_OAK_LOG, Material.DARK_OAK_SAPLING),
            Map.entry(Material.CHERRY_LOG, Material.CHERRY_SAPLING),
            Map.entry(Material.ACACIA_LOG, Material.ACACIA_SAPLING)
    );
        ///BLOCKS THAT ARE CONSIDERED PERK BLOCKS FOR WOODCUTTER || USED FOR GRANTING EXP AND ALLOWING VEIN MINER AS WELL AS REPLANTING
    public static final Material[] blockList = {
            Material.OAK_LOG,
            Material.BIRCH_LOG,
            Material.SPRUCE_LOG,
            Material.JUNGLE_LOG,
            Material.DARK_OAK_LOG,
            Material.CHERRY_LOG,
            Material.MANGROVE_LOG,
            Material.ACACIA_LOG,
            Material.WARPED_STEM,
            Material.CRIMSON_STEM
    };
        ///TOOLS CONSIDERED TO BE WOODCUTTER TOOLS - NEEDED TO USE VEIN MINER
    public static final Material[] tools = {
            Material.WOODEN_AXE,
            Material.STONE_AXE,
            Material.IRON_AXE,
            Material.GOLDEN_AXE,
            Material.DIAMOND_AXE,
            Material.NETHERITE_AXE
    };

    public static Map<Material, Integer> expMulti = Map.ofEntries(
            Map.entry(Material.OAK_LOG, 2),
            Map.entry(Material.BIRCH_LOG, 2),
            Map.entry(Material.SPRUCE_LOG, 2),
            Map.entry(Material.JUNGLE_LOG, 2),
            Map.entry(Material.DARK_OAK_LOG, 2),
            Map.entry(Material.CHERRY_LOG, 2),
            Map.entry(Material.MANGROVE_LOG, 2),
            Map.entry(Material.ACACIA_LOG, 2),
            Map.entry(Material.CRIMSON_STEM, 3),
            Map.entry(Material.WARPED_STEM, 3)
    );
}
