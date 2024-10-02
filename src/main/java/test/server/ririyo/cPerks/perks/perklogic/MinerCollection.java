package test.server.ririyo.cPerks.perks.perklogic;

import org.bukkit.Material;

import java.util.Map;

public class MinerCollection {

        ///AMOUNT OF EXPERIENCE ORBS(VALUE) TO BE DROPPED WHEN MINING A BLOCK THAT DROPS THE ITEM(KEY)
    public static final Map<Material, Integer> dropExpOrbs = Map.ofEntries(
            Map.entry(Material.COAL, 1),
            Map.entry(Material.GOLD_NUGGET, 1),
            Map.entry(Material.DIAMOND, 5),
            Map.entry(Material.EMERALD, 5),
            Map.entry(Material.LAPIS_LAZULI, 4),
            Map.entry(Material.QUARTZ, 4),
            Map.entry(Material.REDSTONE, 3),
            Map.entry(Material.IRON_INGOT, 1),
            Map.entry(Material.COPPER_INGOT, 1),
            Map.entry(Material.GOLD_INGOT, 1),
            Map.entry(Material.NETHERITE_SCRAP, 10),
            Map.entry(Material.GOLD_BLOCK, 9),
            Map.entry(Material.COPPER_BLOCK, 9),
            Map.entry(Material.IRON_BLOCK, 9),
            Map.entry(Material.DEEPSLATE, 1),
            Map.entry(Material.STONE, 1)
    );
        ///MAP OF LEVELS(KEY) AND THEIR RESPECTIVE VEIN MINER BLOCK CAP(VALUE)
    public static final Map<Integer, Integer> veinMineBlocks = Map.ofEntries(
            Map.entry(5, 2),
            Map.entry(7, 3),
            Map.entry(9, 4),
            Map.entry(11,5),
            Map.entry(13, 6),
            Map.entry(15, 7),
            Map.entry(17, 8),
            Map.entry(19, 9),
            Map.entry(21, 10),
            Map.entry(23, 11),
            Map.entry(25, 12),
            Map.entry(27, 13),
            Map.entry(29, 14),
            Map.entry(30, 15)
    );

        ///ARRAY OF BLOCKS THAT ARE CONSIDERED MINER PERK BLOCKS
    public static final Material[] blockList = {
            Material.END_STONE,
            Material.OBSIDIAN,
            Material.COAL_ORE,
            Material.DEEPSLATE_COAL_ORE,
            Material.COPPER_ORE,
            Material.DEEPSLATE_COPPER_ORE,
            Material.IRON_ORE,
            Material.DEEPSLATE_IRON_ORE,
            Material.GOLD_ORE,
            Material.DEEPSLATE_GOLD_ORE,
            Material.LAPIS_ORE,
            Material.DEEPSLATE_LAPIS_ORE,
            Material.REDSTONE_ORE,
            Material.DEEPSLATE_REDSTONE_ORE,
            Material.DIAMOND_ORE,
            Material.DEEPSLATE_DIAMOND_ORE,
            Material.EMERALD_ORE,
            Material.DEEPSLATE_EMERALD_ORE,
            Material.NETHER_QUARTZ_ORE,
            Material.NETHER_GOLD_ORE,
            Material.NETHERRACK,
            Material.STONE,
            Material.DEEPSLATE,
            Material.DIORITE,
            Material.ANDESITE,
            Material.GRANITE,
            Material.DIORITE,
            Material.TUFF,
            Material.ANCIENT_DEBRIS,
            Material.RAW_GOLD_BLOCK,
            Material.RAW_IRON_BLOCK,
            Material.RAW_COPPER_BLOCK
    };

        ///ARRAY OF BLOCKS THAT ARE ALLOWED TO BE VEINMINED
    public static final Material[] veinMineBlockList = {
            Material.OBSIDIAN,
            Material.COAL_ORE,
            Material.DEEPSLATE_COAL_ORE,
            Material.COPPER_ORE,
            Material.DEEPSLATE_COPPER_ORE,
            Material.IRON_ORE,
            Material.DEEPSLATE_IRON_ORE,
            Material.GOLD_ORE,
            Material.DEEPSLATE_GOLD_ORE,
            Material.LAPIS_ORE,
            Material.DEEPSLATE_LAPIS_ORE,
            Material.REDSTONE_ORE,
            Material.DEEPSLATE_REDSTONE_ORE,
            Material.DIAMOND_ORE,
            Material.DEEPSLATE_DIAMOND_ORE,
            Material.EMERALD_ORE,
            Material.DEEPSLATE_EMERALD_ORE,
            Material.NETHER_QUARTZ_ORE,
            Material.NETHER_GOLD_ORE,
            Material.ANCIENT_DEBRIS
    };
        ///MAP OF BLOCKS(KEY) AND THEIR RESPECTIVE PERK-EXPERIENCE MULTIPLIER(VALUE)
    public static final Map<Material, Integer> expMulti  = Map.ofEntries(
            Map.entry(Material.STONE, 1),
            Map.entry(Material.TUFF, 1),
            Map.entry(Material.END_STONE, 1),
            Map.entry(Material.DEEPSLATE, 1),
            Map.entry(Material.DIORITE, 1),
            Map.entry(Material.GRANITE, 1),
            Map.entry(Material.ANDESITE, 1),
            Map.entry(Material.COAL_ORE, 3),
            Map.entry(Material.OBSIDIAN, 3),
            Map.entry(Material.DEEPSLATE_COAL_ORE, 3),
            Map.entry(Material.COPPER_ORE, 3),
            Map.entry(Material.DEEPSLATE_COPPER_ORE, 3),
            Map.entry(Material.IRON_ORE, 6),
            Map.entry(Material.DEEPSLATE_IRON_ORE, 6),
            Map.entry(Material.LAPIS_ORE, 6),
            Map.entry(Material.DEEPSLATE_LAPIS_ORE, 6),
            Map.entry(Material.GOLD_ORE, 9),
            Map.entry(Material.DEEPSLATE_GOLD_ORE, 9),
            Map.entry(Material.REDSTONE_ORE, 6),
            Map.entry(Material.DEEPSLATE_REDSTONE_ORE, 6),
            Map.entry(Material.DIAMOND_ORE, 15),
            Map.entry(Material.DEEPSLATE_DIAMOND_ORE, 15),
            Map.entry(Material.EMERALD_ORE, 20),
            Map.entry(Material.DEEPSLATE_EMERALD_ORE, 20),
            Map.entry(Material.NETHER_QUARTZ_ORE, 6),
            Map.entry(Material.NETHER_GOLD_ORE, 3),
            Map.entry(Material.NETHERRACK, 1),
            Map.entry(Material.ANCIENT_DEBRIS, 50),
            Map.entry(Material.RAW_COPPER_BLOCK, 27),
            Map.entry(Material.RAW_IRON_BLOCK, 54),
            Map.entry(Material.RAW_GOLD_BLOCK, 81)
    );
        ///MAP OF DROPPED ITEMS(KEY) AND THEIR CONVERTED AUTO SMELT RESULT(VALUE)
    public static final Map<Material, Material> AutoSmeltConverter = Map.ofEntries(
            Map.entry(Material.RAW_IRON, Material.IRON_INGOT),
            Map.entry(Material.RAW_GOLD, Material.GOLD_INGOT),
            Map.entry(Material.RAW_COPPER, Material.COPPER_INGOT),
            Map.entry(Material.RAW_COPPER_BLOCK, Material.COPPER_BLOCK),
            Map.entry(Material.RAW_IRON_BLOCK, Material.IRON_BLOCK),
            Map.entry(Material.RAW_GOLD_BLOCK, Material.GOLD_BLOCK),
            Map.entry(Material.COBBLESTONE, Material.STONE),
            Map.entry(Material.COBBLED_DEEPSLATE, Material.DEEPSLATE),
            Map.entry(Material.NETHERRACK, Material.NETHER_BRICK),
            Map.entry(Material.ANCIENT_DEBRIS, Material.NETHERITE_SCRAP)
    );
}
