package test.server.ririyo.cPerks.perks.perklogic;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import test.server.ririyo.cPerks.collections.NamespacedKeyCollection;
import test.server.ririyo.cPerks.configs.UserDataHandler;

import java.util.*;

public class PerkLogic {

    ///MESSY FILE WITH THINGS I DID NOT FIND A PROPER PLACE TO PUT THEM AT YET.

    public static class BlocksMined{
        String perk;
        Block startBlock;
        Material dropType;
        int amount;
        int exp;
        int expOrbs;
        ItemStack tool;
        Material blockType;
        List<Block> list;
        Map<Block, Collection<ItemStack>> drops = new HashMap<>();
        boolean autoSmelt;
        boolean smelted;
        boolean replant;
        boolean harvester;

        public BlocksMined(String perk, Block startBlock, int amount, ItemStack tool, Material blockType, List<Block> list, boolean autoSmelt, boolean replant, boolean harvester){
            this.perk = perk;
            this.startBlock = startBlock;
            this.amount = amount;
            this.tool = tool;
            this.list = list;
            this.blockType = blockType;
            this.autoSmelt = autoSmelt;
            this.replant = replant;
            this.harvester = harvester;
            this.smelted = false;
            this.dropType = startBlock.getDrops(this.tool).stream().findFirst().get().getType();
            populateDropsMap();
            calculateExp();
        }

        public void setDrops(Map<Block, Collection<ItemStack>> drops) {
            this.drops = drops;
        }

        public int getExp(){
            return this.exp;
        }

        public int getExpOrbs(){
            return this.expOrbs;
        }

        public int getAmount(){
            return this.amount;
        }

        public int getDamage(){
            return calculateDamage(this.tool, this.amount);
        }

        public Material getBlockType(){
            return this.blockType;
        }

        public ItemStack getTool(){
            return this.tool;
        }

        public List<Block> getList(){
            return this.list;
        }

        public Map<Block, Collection<ItemStack>> getDrops(){
            return this.drops;
        }

        public boolean getAutoSmelt(){
            return this.autoSmelt;
        }

        public boolean getHarvester(){
            return this.harvester;
        }

        public boolean getReplant(){
            return this.replant;
        }

        public boolean getSmelted() {
            return this.smelted;
        }

        public void calculateExp(){
            if(MinerCollection.dropExpOrbs.containsKey(dropType)) {
                this.expOrbs = amount * MinerCollection.dropExpOrbs.get(dropType);
            } else {
                this.expOrbs = 0;
            }
            if(this.autoSmelt && MinerCollection.dropExpOrbs.containsKey(dropType)){
                this.smelted = true;
            }
            this.exp = amount * expMultiplier.get(blockType);
        }

        public void populateDropsMap(){
            for(Block block : list) {
                if (this.autoSmelt) {
                    drops.put(block, applyAutoSmelt(block.getDrops(this.tool)));
                    if(MinerCollection.AutoSmeltConverter.containsKey(dropType)){
                    dropType = MinerCollection.AutoSmeltConverter.get(dropType);
                    }
                } else {
                    drops.put(block, block.getDrops(this.tool));
                }
            }
        }
    }

    public static BlocksMined mineBlock(Player player, ItemStack tool, BlockBreakEvent event, String perk){
        Block startBlock = event.getBlock();
        List<Block> blockList = new ArrayList<>();
        int veinMinerCap = 0;
        boolean autoSmelt = false;
        boolean harvester = false;
        boolean replant = false;
        switch(perk){
            case "Miner":
                if(Boolean.parseBoolean(UserDataHandler.get(player, player.getUniqueId(), perk + ".Vein-Miner")) && tool.getType().toString().contains("PICKAXE")){
                    veinMinerCap = Math.round(Float.parseFloat(UserDataHandler.get(player, player.getUniqueId(), perk + ".Vein-Miner-Amount")));
                }
                autoSmelt = Boolean.parseBoolean(UserDataHandler.get(player, player.getUniqueId(), perk + ".Auto-Smelt"));
                break;
            case "Woodcutter":
                if(Boolean.parseBoolean(UserDataHandler.get(player, player.getUniqueId(), perk + ".Vein-Miner")) && Arrays.asList(WoodCutterCollection.tools).contains(tool.getType())){
                    veinMinerCap = Math.round(Float.parseFloat(UserDataHandler.get(player, player.getUniqueId(), perk + ".Vein-Miner-Amount")));
                }
                replant = Boolean.parseBoolean(UserDataHandler.get(player, player.getUniqueId(), perk + ".Replant"));
                break;
            case "Farmer":
                harvester = Boolean.parseBoolean(UserDataHandler.get(player, player.getUniqueId(), perk + ".3x3-Harvest")) && Arrays.asList(FarmerCollection.tools_hoes).contains(tool.getType());
                replant = Boolean.parseBoolean(UserDataHandler.get(player, player.getUniqueId(), perk + ".Replant"));
                break;
        }

        if(veinMinerCap > 0){
            if(Arrays.asList(MinerCollection.veinMineBlockList).contains(startBlock.getType()) || Arrays.asList(WoodCutterCollection.blockList).contains(startBlock.getType())) {
                List<Block> blocks = getVeinMinerBlocks(startBlock, veinMinerCap);
                blockList.addAll(blocks);
            }
            else {
                blockList.add(startBlock);
            }
        } else if (harvester) {
            List<Block> blocks = getHarvesterBlocks(startBlock);
            blockList.addAll(blocks);
        } else {
            blockList.add(startBlock);
        }
        return new BlocksMined(perk, startBlock, blockList.size(), tool, startBlock.getType(), blockList, autoSmelt, replant, harvester);
    }

    public static Collection<ItemStack> applyAutoSmelt(Collection<ItemStack> items){
        Collection<ItemStack> itemStacks = new ArrayList<>();
        for(ItemStack itemStack : items){
            if(MinerCollection.AutoSmeltConverter.containsKey(itemStack.getType())){
                ItemStack convertedItem = new ItemStack(MinerCollection.AutoSmeltConverter.get(itemStack.getType()), itemStack.getAmount());
                itemStacks.add(convertedItem);
            }
            else {
                itemStacks.add(itemStack);
            }
        }
        return itemStacks;
    }

    public static List<Block> getVeinMinerBlocks(Block block, int maximumBlocks){
        List<Block> toDo = new ArrayList<>();
        toDo.add(block);
        List<Block> toBreak = new ArrayList<>();
        World blockWorld = block.getWorld();
        int counter = 1;
        while(!toDo.isEmpty() && counter <= maximumBlocks) {
            block = toDo.getFirst();
            for (Vector vector : PerkLogic.vectors){
                Location blockLocation = block.getLocation();
                Block b = blockWorld.getBlockAt(blockLocation.add(vector));
                if (b.getType() == block.getType()) {
                    if (!toDo.contains(b) && !toBreak.contains(b)) {
                        toDo.add(b);
                    }
                }
            }
            toDo.removeFirst();
            toBreak.add(block);
            counter++;
        }
        return toBreak;
    }

    public static List<Block> getTillingBlocks(Block block){
        World blockWorld = block.getWorld();
        List<Block> blocks = new ArrayList<>();
        for(Vector vector : FarmerCollection.vectors){
            Block b = blockWorld.getBlockAt(block.getLocation().add(vector));
            if(b.getType() == Material.GRASS_BLOCK || b.getType() == Material.DIRT){
                blocks.add(b);
            }
        }
        return blocks;
    }

    public static List<Block> getHarvesterBlocks(Block block){
        World blockWorld = block.getWorld();
        List<Block> blocks = new ArrayList<>();
        for(Vector vector : FarmerCollection.vectors){
            Block b = blockWorld.getBlockAt(block.getLocation().add(vector));
            if(Arrays.asList(FarmerCollection.blockList).contains(b.getType())){
                blocks.add(b);
            }
        }
        return blocks;
    }

    public static void putInPlayerInventory(Player player, ItemStack item){
            ///INVENTORY HAS AT LEAST 1 EMPTY SLOT
        Inventory inv = player.getInventory();
        if (inv.firstEmpty() != -1){
            inv.addItem(item);
        } else {
            ///INVENTORY HAS THIS ITEM
            if(inv.contains(item.getType())){
                int amountToAdd = item.getAmount();
                int remaining = amountToAdd;
                for(ItemStack itemStack : inv.getContents()){
                    if(itemStack != null){
                        if(itemStack.getType() == item.getType()) {
                            int itemAmount = itemStack.getAmount();
                            if (itemAmount < 64) {
                                itemAmount += amountToAdd;
                                remaining = itemAmount - 64;
                            }
                            if(itemAmount > 64){
                                itemAmount = 64;
                            }
                            itemStack.setAmount(itemAmount);
                        }
                    }
                }
                player.getLocation().getWorld().dropItemNaturally(player.getLocation(), new ItemStack(item.getType(), remaining));
            } else {
                player.getLocation().getWorld().dropItemNaturally(player.getLocation(), item);
            }
        }
    }

    public static int calculateDamage(ItemStack tool, int amount){
        if(tool.containsEnchantment(Enchantment.UNBREAKING)){
            switch(tool.getEnchantmentLevel(Enchantment.UNBREAKING)){
                case 1:
                    return amount / 2;
                case 2:
                    return amount / 3;
                case 3:
                    return amount / 4;
                case 4:
                    return amount / 5;
                case 5:
                    return amount / 6;
            }
        } else {
            return amount;
        }
        return amount;
    }

    public static Material[] tools = {
            Material.WOODEN_HOE,
            Material.STONE_HOE,
            Material.IRON_HOE,
            Material.GOLDEN_HOE,
            Material.DIAMOND_HOE,
            Material.NETHERITE_HOE,

            Material.WOODEN_PICKAXE,
            Material.STONE_PICKAXE,
            Material.IRON_PICKAXE,
            Material.GOLDEN_PICKAXE,
            Material.DIAMOND_PICKAXE,
            Material.NETHERITE_PICKAXE,

            Material.WOODEN_AXE,
            Material.STONE_AXE,
            Material.IRON_AXE,
            Material.GOLDEN_AXE,
            Material.DIAMOND_AXE,
            Material.NETHERITE_AXE,

            Material.WOODEN_SHOVEL,
            Material.STONE_SHOVEL,
            Material.IRON_SHOVEL,
            Material.GOLDEN_SHOVEL,
            Material.DIAMOND_SHOVEL,
            Material.NETHERITE_SHOVEL
    };

    public static Material[] weapons = {
            Material.WOODEN_SWORD,
            Material.STONE_SWORD,
            Material.IRON_SWORD,
            Material.GOLDEN_SWORD,
            Material.DIAMOND_SWORD,
            Material.NETHERITE_SWORD,

            Material.BOW
    };

    public static Material[] armor = {
            Material.LEATHER_HELMET,
            Material.LEATHER_CHESTPLATE,
            Material.LEATHER_LEGGINGS,
            Material.LEATHER_BOOTS,

            Material.IRON_HELMET,
            Material.IRON_CHESTPLATE,
            Material.IRON_LEGGINGS,
            Material.IRON_BOOTS,

            Material.GOLDEN_HELMET,
            Material.GOLDEN_CHESTPLATE,
            Material.GOLDEN_LEGGINGS,
            Material.GOLDEN_BOOTS,

            Material.DIAMOND_HELMET,
            Material.DIAMOND_CHESTPLATE,
            Material.DIAMOND_LEGGINGS,
            Material.DIAMOND_BOOTS,

            Material.CHAINMAIL_HELMET,
            Material.CHAINMAIL_CHESTPLATE,
            Material.CHAINMAIL_LEGGINGS,
            Material.CHAINMAIL_BOOTS,

            Material.NETHERITE_HELMET,
            Material.NETHERITE_CHESTPLATE,
            Material.NETHERITE_LEGGINGS,
            Material.NETHERITE_BOOTS
    };

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

            ///Experience Multipliers for all Perks
    public static Map<Material, Integer> expMultiplier = Map.ofEntries(

                ///WOODCUTTER
                    Map.entry(Material.OAK_LOG, 2),
                    Map.entry(Material.BIRCH_LOG, 2),
                    Map.entry(Material.SPRUCE_LOG, 2),
                    Map.entry(Material.JUNGLE_LOG, 2),
                    Map.entry(Material.DARK_OAK_LOG, 2),
                    Map.entry(Material.CHERRY_LOG, 2),
                    Map.entry(Material.MANGROVE_LOG, 2),
                    Map.entry(Material.ACACIA_LOG, 2),
                    Map.entry(Material.CRIMSON_STEM, 3),
                    Map.entry(Material.WARPED_STEM, 3),

                ///MINER
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
                    Map.entry(Material.RAW_GOLD_BLOCK, 81),

                ///FARMER
                    Map.entry(Material.WHEAT, 3),
                    Map.entry(Material.BEETROOTS, 3),
                    Map.entry(Material.CARROTS, 2),
                    Map.entry(Material.POTATOES, 2),
                    Map.entry(Material.NETHER_WART, 2),
                    Map.entry(Material.PUMPKIN, 5),
                    Map.entry(Material.MELON, 5)
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
