package test.server.ririyo.cPerks.perks.perklogic;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import test.server.ririyo.cPerks.perks.AllPerksCollection;
import test.server.ririyo.cPerks.perks.features.ExtraExperience;
import test.server.ririyo.cPerks.handlers.PlayerLevelHandler;
import test.server.ririyo.cPerks.configs.UserDataHandler;

import java.util.*;

public class Miner {
        ///FUNCTION CALLED WHEN PLAYER IS MINING A BLOCK THAT IS REGISTERED
    public static int mine(Player player, UUID uuid, Block block, ItemStack tool){
        String job = "Miner";

        boolean veinMine = PlayerLevelHandler.getPlayerFeatureState(player, job, "Vein-Miner");
        boolean autoSmelt = PlayerLevelHandler.getPlayerFeatureState(player, job, "Auto-Smelt");
        Material dropType;
        int dropAmount;
        int maximumBlocks;
        ///SETS THE MAXIMUM AMOUNT OF BLOCKS TO MINE DEPENDING ON THE CAP OF THE PLAYER'S VEIN MINER FEATURE
        if(veinMine && Arrays.asList(MinerCollection.veinMineBlockList).contains(block.getType())){
            maximumBlocks = Math.round(Float.parseFloat(UserDataHandler.get(player, uuid, job + ".Vein-Miner-Amount")));
        } else maximumBlocks = 1;
        Set<Block> toBreak = getBlocksToBreak(block, maximumBlocks);

        ///MINES THE BLOCK AND CHECKS FOR AUTO SMELT FEATURE TO CONVERT THE DROPS
        for(Block blockToBreak : toBreak){
            if(autoSmelt && MinerCollection.AutoSmeltConverter.containsKey(blockToBreak.getDrops().stream().findFirst().get().getType()) && !tool.containsEnchantment(Enchantment.SILK_TOUCH)) {
                dropType = MinerCollection.AutoSmeltConverter.get(blockToBreak.getDrops(tool).stream().findFirst().get().getType());
            }
            else{
                dropType = blockToBreak.getDrops(tool).stream().findFirst().get().getType();
            }
            dropAmount = blockToBreak.getDrops(tool).stream().findFirst().get().getAmount();
            mineBlock(blockToBreak, dropType, dropAmount, tool);
            ExtraExperience.spawnExtraExp(player, blockToBreak.getLocation());
        }

        ///RETURNS THE AMOUNT OF BLOCKS MINED MULTIPLIED WITH THE BLOCK TYPE'S EXP MULTIPLIER
        return toBreak.size();
    }

        ///THE FUNCTION THAT ACTUALLY HANDLES BREAKING THE BLOCK AND DROPPING EXPERIENCE AS WELL AS ITEMS
    public static void mineBlock(Block block, Material dropType, int dropAmount, ItemStack tool){
        block.setType(Material.AIR);

        if(MinerCollection.dropExpOrbs.containsKey(dropType)){
            int alreadyExistingExp = 0;
            try {
                alreadyExistingExp = block.getWorld().spawn(block.getLocation(), ExperienceOrb.class).getExperience();
            } catch (Exception ignored){} ///IGNORED IF NO EXP ALREADY EXISTS THERE.
            block.getWorld().spawn(block.getLocation(), ExperienceOrb.class).setExperience(alreadyExistingExp + MinerCollection.dropExpOrbs.get(dropType));
        }
        block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(dropType, dropAmount));
    }
        ///FUNCTION THAT RETURNS A SET OF BLOCKS. GET CONNECTED BLOCKS FOR VEIN MINER DEPENDENT ON THE CAP
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
