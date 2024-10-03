package test.server.ririyo.cPerks.perks;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import test.server.ririyo.cPerks.collections.NamespacedKeyCollection;
import test.server.ririyo.cPerks.configs.UserDataHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class AllPerksCollection {

    ///MESSY FILE WITH THINGS I DID NOT FIND A PROPER PLACE TO PUT THEM AT YET.

        ///USEFUL MAPS AND LISTS FOR PERKS TO GET INFORMATION FROM
            ///MAP OF FEATURES(KEY) AND THEIR UNLOCK LEVELS(VALUE) /// WILL BE MOVED TO A USER EDITABLE CONFIG LATER ON
    public static Map<String, Integer> featureLevels = Map.ofEntries(
            Map.entry("Vein-Miner",5),
            Map.entry("Refunding", 5),
            Map.entry("Backpack", 5),
            Map.entry("3x3-Harvest", 5),

            Map.entry("Egg-Hunter", 15),
            Map.entry("Replant", 15),
            Map.entry("Auto-Smelt", 15),

            Map.entry("Extra-Experience", 30)
    );
            ///MAP OF FEATURES(KEY) AND THEIR DESCRIPTIONS(VALUE)
    public static Map<String, String> featureDescription = Map.ofEntries(
            Map.entry("Woodcutter.Vein-Miner", "Gives the Ability to Mine multiple blocks"),
            Map.entry("Miner.Vein-Miner", "Gives the Ability to Mine multiple blocks"),
            Map.entry("Enchanter.Refunding", "Refunds a Percentage of Exp \n When Enchanting at Lv 30"),
            Map.entry("Farmer.3x3-Harvest", "Allows harvesting Crops in a 3x3 Area"),
            Map.entry("Hunter.Backpack", "Unlocks a Backpack \n Expands with Level"),

            Map.entry("Woodcutter.Replant", "Replants Harvested Blocks"),
            Map.entry("Miner.Auto-Smelt", "Auto-Smelts mined Blocks"),
            Map.entry("Farmer.Replant", "Replants Harvested Blocks"),
            Map.entry("Enchanter.Extra-Experience", "Drops Extra Experience Orbs \n Happens on ANY Perk-Experience gain"),
            Map.entry("Hunter.Egg-Hunter", "Percentage Chance to drop Spawn-Egg")
    );
            ///MAP OF NAMESPACEDKEYS(KEY) TO ALLOW ITEM INTERACTIONS FOR RESPECTIVE FEATURES(VALUE)
    public static Map<NamespacedKey, String> keyToPathConverter = Map.ofEntries(
            Map.entry(NamespacedKeyCollection.WoodcutterVeinMiner, "Woodcutter.Vein-Miner"),
            Map.entry(NamespacedKeyCollection.WoodcutterReplant, "Woodcutter.Replant"),
            Map.entry(NamespacedKeyCollection.MinerVeinMiner, "Miner.Vein-Miner"),
            Map.entry(NamespacedKeyCollection.MinerAutoSmelt, "Miner.Auto-Smelt"),
            Map.entry(NamespacedKeyCollection.Farmer3x3Harvest, "Farmer.3x3-Harvest"),
            Map.entry(NamespacedKeyCollection.FarmerReplant, "Farmer.Replant"),
            Map.entry(NamespacedKeyCollection.EnchanterRefunding, "Enchanter.Refunding"),
            Map.entry(NamespacedKeyCollection.EnchanterExtraExp, "Enchanter.Extra-Experience"),
            Map.entry(NamespacedKeyCollection.HunterBackpack, "Hunter.Backpack"),
            Map.entry(NamespacedKeyCollection.HunterEggHunter, "Hunter.Egg-Hunter")
    );
            ///MAP OF PERKS(KEY) AND ITEMS(VALUE) MATCHED FOR THEIR DISPLAY ICONS
    public static Map<String, Material> perkIcon = Map.ofEntries(
            Map.entry("Miner", Material.GOLDEN_PICKAXE),
            Map.entry("Woodcutter", Material.GOLDEN_AXE),
            Map.entry("Enchanter", Material.ENCHANTING_TABLE),
            Map.entry("Farmer",Material.GOLDEN_HOE),
            Map.entry("Hunter", Material.GOLDEN_SWORD)
    );
            ///MAP OF FEATURES(KEY) AND ITEMS(VALUE) MATCHED FOR THEIR DISPLAY ICONS
    public static Map<String, Material> featureIcon = Map.ofEntries(
            Map.entry("Woodcutter.Vein-Miner", Material.IRON_AXE),
            Map.entry("Miner.Vein-Miner", Material.IRON_PICKAXE),
            Map.entry("Farmer.3x3-Harvest",Material.IRON_HOE),
            Map.entry("Enchanter.Refunding", Material.EXPERIENCE_BOTTLE),
            Map.entry("Hunter.Backpack", Material.BROWN_SHULKER_BOX),

            Map.entry("Woodcutter.Replant", Material.OAK_SAPLING),
            Map.entry("Miner.Auto-Smelt", Material.FURNACE),
            Map.entry("Farmer.Replant", Material.WHEAT_SEEDS),
            Map.entry("Enchanter.Extra-Experience", Material.OMINOUS_BOTTLE),
            Map.entry("Hunter.Egg-Hunter", Material.COW_SPAWN_EGG)
    );

            ///INPUT A PERCENTAGE CHANCE TO RETURN A BOOLEAN IF CHANCE WAS HIT. ///WILL BE MOVED LATER
    public static boolean getRandomChance(float percentageChance){
        Random rand = new Random();
        int randInt = rand.nextInt(10000);
        int chance = Math.round(percentageChance * 100);
        return randInt <= chance;
    }

        ///EXP(VALUE) NEEDED FOR LEVEL(KEY)
    public static final Map<Integer, Integer> levelUps = Map.ofEntries(
            Map.entry(0, 100), Map.entry(1, 200), Map.entry(2, 300), Map.entry(3, 400), Map.entry(4, 500), Map.entry(5, 600),
            Map.entry(6, 750), Map.entry(7, 900), Map.entry(8, 1050), Map.entry(9, 1200), Map.entry(10, 1350), Map.entry(11, 1500),
            Map.entry(12, 1750), Map.entry(13, 2000), Map.entry(14, 2250), Map.entry(15, 2500), Map.entry(16, 2750), Map.entry(17, 3000),
            Map.entry(18, 3500), Map.entry(19, 4000), Map.entry(20, 4500), Map.entry(21, 5000), Map.entry(22, 5500), Map.entry(23, 6000),
            Map.entry(24, 7000), Map.entry(25, 8000), Map.entry(26, 9000), Map.entry(27, 10000), Map.entry(28, 11000), Map.entry(29, 12000),
            Map.entry(30, 0)
    );
        ///GETS A PERKS DESCRIPTION AND ADDS IT TO A LIST OF STRINGS TO RETURN || USED TO ADD DESCRIPTION TO SPECIFICALLY PERK ITEMS
    public static List<String> getPerkDescription(Player player, String perk){
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.BLUE + "Level: " + ChatColor.GREEN + UserDataHandler.get(player, player.getUniqueId(), perk + ".Level"));
        lore.add(ChatColor.BLUE + "Experience: " + ChatColor.GREEN + UserDataHandler.get(player, player.getUniqueId(), perk + ".Experience") + "/" + UserDataHandler.get(player, player.getUniqueId(), perk + ".Level-Up"));
        lore.add(ChatColor.BLUE + "Total-Experience: " + ChatColor.GREEN + UserDataHandler.get(player, player.getUniqueId(), perk + ".Total-Experience"));
        return lore;
    }
        ///GETS A FEATURE'S DESCRIPTION AS WELL AS THE UNLOCK STATE
    public static List<String> getUnlockDescription(Player player, String perk, String unlock){
        List<String> lore = new ArrayList<>();

        /// Level is greater than feature Unlock Level
        boolean unlocked = Integer.parseInt(UserDataHandler.get(player, player.getUniqueId(), perk + ".Level")) >= featureLevels.get(unlock);

        String unlockedString;
        String state;

        if(unlocked)
            unlockedString = ChatColor.GREEN + "Yea.";
        else
            unlockedString = ChatColor.RED + "Noh.";

        /// Feature's state in User Config
        if(Boolean.parseBoolean(UserDataHandler.get(player, player.getUniqueId(), perk + "." + unlock)))
            state = ChatColor.GREEN + "On";
        else
            state = ChatColor.RED + "Off";

        /// Add description to Item
        lore.add("");
        lore.add(ChatColor.BLUE + "Unlock Level: " + ChatColor.GREEN + featureLevels.get(unlock));
        lore.add(ChatColor.BLUE + featureDescription.get(perk + "." + unlock));
        lore.add(ChatColor.BLUE + "Unlocked: " + unlockedString);

            ///ADDS MORE TO THE DESCRIPTION IF FEATURE HAS A CONNECTED AMOUNT OR CHANCE
        switch(unlock){
            case "Vein-Miner":
                lore.add(ChatColor.BLUE + "Amount: " + ChatColor.GREEN + Math.round(Float.parseFloat(UserDataHandler.get(player, player.getUniqueId(), perk + ".Vein-Miner-Amount")))  + " Blocks");
                break;
            case "Refunding":
                lore.add(ChatColor.BLUE + "Amount: " + ChatColor.GREEN + UserDataHandler.get(player, player.getUniqueId(), perk + ".Refunding-Amount") + "%");
                break;
            case "Egg-Hunter":
                lore.add(ChatColor.BLUE + "Chance: " + ChatColor.GREEN + UserDataHandler.get(player, player.getUniqueId(), perk + ".Egg-Hunter-Chance") + "%");
                break;
        }

        lore.add(" ");
        lore.add(ChatColor.BLUE + "State: " + state);

        /// ADDS A TOGGLE TEXT IF THE FEATURE IS UNLOCKED
        if(unlocked) {
            lore.add(" ");
            lore.add(ChatColor.GRAY + "Click to toggle.");
        }

        return lore;
    }


    //// VeinMine Block Positions To Check in a 3x3 || USED IN A LOOP TO GET ALL CONNECTED BLOCKS
    public static final Vector[] vectors  = {

            /// CONNECTED BLOCKS FIRST
            new Vector(0, 1, 0),    //UP
            new Vector(0, -1, 0),   //DOWN
            new Vector(1, 0, 0),    //FRONT
            new Vector(-1, 0, 0),   //BACK
            new Vector(0, 0, 1),    //RIGHT
            new Vector(0, 0, -1),   //LEFT

            ///Surrounding Rings
            new Vector(1, 0, 1),    //FRONT RIGHT
            new Vector(1, 0, -1),   //FRONT LEFT
            new Vector(-1, 0, 1),   //BACK RIGHT
            new Vector(-1, 0, -1),  //BACK LEFT

            new Vector(1, 1, 0),    //UP FRONT
            new Vector(-1, 1, 0),   //UP BACK
            new Vector(1, -1, 0),   //DOWN FRONT
            new Vector(-1, -1, 0),  //DOWN BACK

            new Vector(0, 1, 1),    //UP RIGHT
            new Vector(0, 1, -1),   //UP LEFT
            new Vector(0, -1, 1),   //DOWN RIGHT
            new Vector(0, -1, -1),  //DOWN LEFT

            //CORNER BLOCKS
            new Vector(1, 1, 1),    //UP FRONT RIGHT
            new Vector(1, 1, -1),   //UP FRONT LEFT
            new Vector(-1, 1, 1),   //UP BACK RIGHT
            new Vector(-1, 1, -1),  //UP BACK LEFT

            new Vector(1, -1, 1),   //DOWN FRONT RIGHT
            new Vector(1, -1, -1),  //DOWN FRONT LEFT
            new Vector(-1, -1, 1),  //DOWN BACK RIGHT
            new Vector(-1, -1, -1), //DOWN BACK LEFT
    };
}
