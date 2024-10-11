package test.server.ririyo.cPerks.perks.perklogic;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import test.server.ririyo.cPerks.perks.features.ExtraExperience;
import test.server.ririyo.cPerks.configs.UserDataHandler;

import java.util.*;


public class Farmer {
        ///HARVESTS CROPS AND RETURNS EXP FOR AMOUNT OF CROPS HARVESTED
    public static int harvestCrops(Player player, UUID uuid, Block block, ItemStack tool, BlockBreakEvent event){
        boolean aoeHarvest = Boolean.parseBoolean(UserDataHandler.get(player, uuid, "Farmer.3x3-Harvest"));
        boolean replanting = Boolean.parseBoolean(UserDataHandler.get(player, uuid, "Farmer.Replant"));
        int exp = 0;
        Collection<ItemStack> blockDrop = block.getDrops(tool);

        ///BREAKS THE CROP NORMALLY IF IT ISN'T FULLY GROWN AND CAN'T BE REPLANTED || THIS IS FOR 3x3 HARVEST TO PREVENT BREAKING CROPS ON ACCIDENT IF THEY AREN'T FULLY GROWN
        if(Arrays.asList(FarmerCollection.replants).contains(block.getType())){
            Ageable ageable = (Ageable) block.getBlockData();
            if(ageable.getAge() != ageable.getMaximumAge()){
                return 0;
            }
        }

        ///CHECKS IF AOE HARVEST IS ENABLED
        if(aoeHarvest && Arrays.asList(FarmerCollection.tools_hoes).contains(tool.getType())){
            event.setCancelled(true);
            Set<Block> toBreak = getSurroundingCrops(block);
            for(Block blockToBreak : toBreak){
                final Material blockType = blockToBreak.getType();
                blockDrop = blockToBreak.getDrops(tool);
                ///CHECKS IF REPLANTING IS ENABLED AND REPLANTS THE BLOCK IF IT IS REPLANT-ABLE
                if(replanting && Arrays.asList(FarmerCollection.replants).contains(blockType)) {
                    if(replant(player, blockToBreak, blockType, blockDrop)) {
                        for (ItemStack drop : blockDrop){
                            if (FarmerCollection.expMulti.containsKey(drop.getType())){
                                exp += FarmerCollection.expMulti.get(drop.getType());
                                break;
                            }
                        }
                    }
                    ///BREAKS BLOCK NORMALLY
                } else {
                    if (mineBlock(player, blockToBreak, blockDrop)){
                        for (ItemStack drop : blockDrop){
                            if (FarmerCollection.expMulti.containsKey(drop.getType())){
                                exp += FarmerCollection.expMulti.get(drop.getType());
                                break;
                            }
                        }
                    }
                }
            }

            return exp;
        }
        ///CHECKS IF REPLANTING IS ENABLED
        else{
            final Material blockType = block.getType();
            if(replanting && Arrays.asList(FarmerCollection.replants).contains(blockType)) {
                event.setCancelled(true);
                replant(player, block, blockType, blockDrop);
            } else {
                mineBlock(player, block, blockDrop);
            }
            for (ItemStack drop : blockDrop){
                if (FarmerCollection.expMulti.containsKey(drop.getType())){
                    exp += FarmerCollection.expMulti.get(drop.getType());
                    break;
                }
            }
            return exp;
        }
    }

    ///ADDS BLOCKS TO A LIST FOR 3X3 HARVEST
    public static Set<Block> getSurroundingCrops(Block block){
        World blockWorld = block.getWorld();
        Set<Block> blocks = new HashSet<>();
        blocks.add(block);
        for(Vector vector : FarmerCollection.vectors){
            Block b = blockWorld.getBlockAt(block.getLocation().add(vector));
            if(Arrays.asList(FarmerCollection.blockList).contains(b.getType())){
                blocks.add(b);
            }
        }
        return blocks;
    }

        ///LOGIC FOR REPLANTING A CROP || SETS A CROP TO AGE 0 AFTER BREAKING IT
    public static boolean replant(Player player, Block block, Material blockType, Collection<ItemStack> drops){
        Ageable ageable = (Ageable) block.getBlockData();
        if(ageable.getAge() != ageable.getMaximumAge()){
            return false;
        }

        block.setType(blockType);
        for(ItemStack drop : drops) {
            block.getWorld().dropItemNaturally(block.getLocation(), drop);
            ExtraExperience.spawnExtraExp(player, block.getLocation());
        }

        return true;
    }
        ///BREAKS THE BLOCK NORMALLY AND PREVENTS EXP GAIN IF CROP IS NOT FULLY GROWN
    public static boolean mineBlock(Player player, Block block, Collection<ItemStack> drops) {
        if(Arrays.asList(FarmerCollection.replants).contains(block.getType())){
            Ageable ageable = (Ageable) block.getBlockData();
            if(ageable.getAge() != ageable.getMaximumAge()){
                return false;
            }
        }

        block.setType(Material.AIR);
        for(ItemStack drop : drops) {
            block.getWorld().dropItemNaturally(block.getLocation(), drop);
            ExtraExperience.spawnExtraExp(player, block.getLocation());
        }
        return true;
    }
}
