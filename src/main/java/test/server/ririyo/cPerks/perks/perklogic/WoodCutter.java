package test.server.ririyo.cPerks.perks.perklogic;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import test.server.ririyo.cPerks.perks.AllPerksCollection;
import test.server.ririyo.cPerks.perks.features.ExtraExperience;
import test.server.ririyo.cPerks.handlers.PlayerLevelHandler;
import test.server.ririyo.cPerks.configs.UserDataHandler;

import java.util.*;

public class WoodCutter {

        ///FUNCTION TO MINE A TREE'S BLOCK AND ADD EXPERIENCE FOR THE PERK
    public static int mine(Player player, UUID uuid, Block block, ItemStack tool){
        String job = "Woodcutter";
        boolean veinMine = PlayerLevelHandler.getPlayerFeatureState(player, job, "Vein-Miner");
        boolean replant = PlayerLevelHandler.getPlayerFeatureState(player, job, "Replant");
        Material woodType = block.getType();

            ///CHECKS FOR VEIN MINER UNLOCK
        if(veinMine && Arrays.asList(WoodCutterCollection.tools).contains(tool.getType())) {
            int maximumBlocks = Math.round(Float.parseFloat(UserDataHandler.get(player, uuid, job + ".Vein-Miner-Amount")));
            int brokenBlocks;
            Set<Block> toBreak = getBlocksToBreak(block, maximumBlocks);
            for (Block blockToBreak : toBreak) {
                blockToBreak.breakNaturally(tool);
                ///REPLANTS A MATCHING SAPLING IF THE BLOCK BELOW THE MINED ONE IS GRASS OR DIRT
                if(replant && WoodCutterCollection.saplingMatch.containsKey(woodType)){
                    Material blockBelow = blockToBreak.getWorld().getBlockAt(blockToBreak.getLocation().add(new Vector(0, -1, 0))).getType();
                    if (blockBelow == Material.DIRT || blockBelow == Material.GRASS_BLOCK || blockBelow == Material.PODZOL) {
                        /// BLOCK BELOW IS DIRT OR GRASS
                        blockToBreak.setType(WoodCutterCollection.saplingMatch.get(woodType));
                    }
                }
                ExtraExperience.spawnExtraExp(player, blockToBreak.getLocation());
            }
            brokenBlocks = toBreak.size();
            if(WoodCutterCollection.expMulti.containsKey(block.getType())){
                return brokenBlocks * WoodCutterCollection.expMulti.get(block.getType());
            } else {
                return brokenBlocks;
            }
            ///BREAKS BLOCK NORMALLY IF PLAYER DOESN'T HAVE VEIN MINER UNLOCKED
        } else {
            block.breakNaturally();
            ExtraExperience.spawnExtraExp(player, block.getLocation());
            if(replant) { ///REPLANTS MATCHING SAPLING IF BLOCK BELOW IS DIRT OR GRASS
                if (block.getWorld().getBlockAt(block.getLocation().add(new Vector(0, -1, 0))).getType() == Material.DIRT || block.getWorld().getBlockAt(block.getLocation().add(new Vector(0, -1, 0))).getType() == Material.GRASS_BLOCK){
                    if(WoodCutterCollection.saplingMatch.containsKey(woodType)) {
                        block.setType(WoodCutterCollection.saplingMatch.get(woodType));
                    }
                }
            }
        }
        if(WoodCutterCollection.expMulti.containsKey(block.getType())){
            return WoodCutterCollection.expMulti.get(block.getType());
        } else {
            return 1;
        }
    }
        ///RETURNS SET OF CONNECTED BLOCKS ACCORDING TO VEIN MINER BLOCK CAP
    public static Set<Block> getBlocksToBreak(Block block, int maximumBlocks) {
        ArrayList<Block> toDo = new ArrayList<>();
        toDo.add(block);
        Set<Block> toBreak = new HashSet<>();
        World blockWorld = block.getWorld();
        int counter = 1;

        while(!toDo.isEmpty() && counter <= maximumBlocks) {
            block = toDo.getFirst();

            for (Vector vector : AllPerksCollection.vectors){
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
}
