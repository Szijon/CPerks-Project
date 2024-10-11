package test.server.ririyo.cPerks.perks.perklogic;

import org.bukkit.Material;

import org.bukkit.util.Vector;

import java.util.Map;

public class FarmerCollection {

        ///BLOCK POSITIONS TO CHECK FOR USING 3X3 HARVEST
    public static Vector[] vectors = {

        /// CENTER
        new Vector(0,0,0),

        /// CARDINAL BLOCKS
        new Vector(1, 0, 0),    //FRONT
        new Vector(-1, 0, 0),   //BACK
        new Vector(0, 0, 1),    //RIGHT
        new Vector(0, 0, -1),   //LEFT

        /// CORNERS
        new Vector(1, 0, 1),    //FRONT RIGHT
        new Vector(1, 0, -1),   //FRONT LEFT
        new Vector(-1, 0, 1),   //BACK RIGHT
        new Vector(-1, 0, -1),  //BACK LEFT
};
        ///TOOLS THAT ARE ALLOWED FOR HARVESTING CROPS
    public static Material[] tools_hoes = {
            Material.WOODEN_HOE,
            Material.STONE_HOE,
            Material.IRON_HOE,
            Material.GOLDEN_HOE,
            Material.DIAMOND_HOE,
            Material.NETHERITE_HOE,
    };
    public static Material[] tools_axes = {
            Material.WOODEN_AXE,
            Material.STONE_AXE,
            Material.IRON_AXE,
            Material.GOLDEN_AXE,
            Material.DIAMOND_AXE,
            Material.NETHERITE_AXE
    };

        ///CROPS THAT ARE ALLOWED FOR REPLANTING
    public static Material[] replants = {
            Material.WHEAT,
            Material.BEETROOTS,
            Material.CARROTS,
            Material.POTATOES,
            Material.NETHER_WART,
    };

        ///CROPS THAT WILL GIVE EXP UPON HARVESTING
    public static Material[] blockList = {
        Material.WHEAT,
        Material.BEETROOTS,
        Material.CARROTS,
        Material.POTATOES,
        Material.NETHER_WART,
        Material.PUMPKIN,
        Material.MELON,
    };

    public static Map<Material, Integer> expMulti = Map.ofEntries(
            Map.entry(Material.WHEAT, 3),
            Map.entry(Material.BEETROOT, 3),
            Map.entry(Material.CARROT, 2),
            Map.entry(Material.POTATO, 2),
            Map.entry(Material.NETHER_WART, 2),
            Map.entry(Material.PUMPKIN, 5),
            Map.entry(Material.MELON, 5)
    );
}
