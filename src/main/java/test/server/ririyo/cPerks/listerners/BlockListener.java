package test.server.ririyo.cPerks.listerners;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.spawner.Spawner;
import org.bukkit.util.Vector;
import test.server.ririyo.cPerks.CPerks;
import test.server.ririyo.cPerks.collections.*;
import test.server.ririyo.cPerks.perks.features.SilkTouchSpawners;
import test.server.ririyo.cPerks.configs.BlockDataHandler;
import test.server.ririyo.cPerks.lootcrate.HologramHandler;
import test.server.ririyo.cPerks.handlers.PlayerLevelHandler;
import test.server.ririyo.cPerks.configs.UserDataHandler;
import test.server.ririyo.cPerks.perks.perklogic.*;


import java.util.*;

public class BlockListener implements Listener {

    ///API SIDE EVENT THAT GETS CALLED ANY TIME A BLOCK IS BROKEN
        ///IF NONE OF THIS IS EXECUTED, BLOCK BREAKS AS IT SHOULD WITHOUT THE PLUGIN.
    @EventHandler
    void onPlayerBreakBlock(BlockBreakEvent event){

        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        Block startBlock = event.getBlock();
        Material blockType = startBlock.getType();
        String job;

            ///EXECUTES WOODCUTTER LOGIC IF THE BLOCK IS REGISTERED AS A WOOD BLOCK
        if(Arrays.asList(WoodCutterCollection.blockList).contains(blockType)){
            event.setCancelled(true);
            job = "Woodcutter";
            ItemStack tool = Objects.requireNonNull(player.getEquipment()).getItemInMainHand();

            int brokenBlocks = WoodCutter.mine(player, uuid, startBlock, tool);

            if(Arrays.asList(WoodCutterCollection.tools).contains(tool.getType())) {
                damageTool(player, tool, getToolDamage(tool, brokenBlocks));
            }
            PlayerLevelHandler.addExperience(player, job, brokenBlocks);

            ///EXECUTES MINER LOGIC IF THE ITEM IS REGISTERED AS A MINER BLOCK AND BROKEN BLOCK DROPS AN ITEM
        } else if (Arrays.asList(MinerCollection.blockList).contains(blockType)) {
            if (!startBlock.getDrops(Objects.requireNonNull(player.getEquipment()).getItemInMainHand()).isEmpty()) {
                event.setCancelled(true);
                job = "Miner";
                ItemStack tool = Objects.requireNonNull(player.getEquipment()).getItemInMainHand();

                int brokenBlocks = Miner.mine(player, uuid, startBlock, tool);
                damageTool(player, tool, getToolDamage(tool, brokenBlocks));
                PlayerLevelHandler.addExperience(player, job, brokenBlocks * MinerCollection.expMulti.get(blockType));
            }

            ///EXECUTES FARMER LOGIC IF THE ITEM IS A CROP
        } else if (Arrays.asList(FarmerCollection.blockList).contains(blockType)) {
            job = "Farmer";
            ItemStack tool = Objects.requireNonNull(player.getEquipment()).getItemInMainHand();

            int exp = Farmer.harvestCrops(player, uuid, startBlock, tool, event);
                ///DAMAGES TOOL IF A FARMER TOOL IS USED
            if(Arrays.asList(FarmerCollection.tools_hoes).contains(tool.getType()) || Arrays.asList(FarmerCollection.tools_axes).contains(tool.getType())) {
                damageTool(player, tool, getToolDamage(tool, exp));
            }

            PlayerLevelHandler.addExperience(player, job, exp);

            ///DROPS A SPAWNER IF PLAYER HAS A SILK-TOUCH TOOL AND UNLOCKED THE REQUIRED FEATURE ||| SPAWNERS DO NOT DROP NORMALLY.
        } else if (blockType == Material.SPAWNER){
            ItemStack tool = Objects.requireNonNull(player.getEquipment()).getItemInMainHand();
            boolean unlocked = Boolean.parseBoolean(UserDataHandler.get(player, player.getUniqueId(), "Shop.Spawner"));
            if(tool.containsEnchantment(Enchantment.SILK_TOUCH) && unlocked && player.getLevel() >= 30) {
                event.setCancelled(true);
                SilkTouchSpawners.mineSpawner(startBlock, player);
            }

            ///REMOVES THE PLACED LOOT CRATE BLOCK FROM THE CONFIG
        } else if (blockType == Material.LIME_SHULKER_BOX){
            Location blockLocation = startBlock.getLocation();
            Location configLocation = BlockDataHandler.getLocation("Loot-Crate");
            if(configLocation.getBlockX() == blockLocation.getBlockX() && configLocation.getBlockY() == blockLocation.getBlockY() && configLocation.getBlockZ() == blockLocation.getBlockZ() && player.isOp()){
                BlockDataHandler.set("Loot-Crate", null);
            } else {
                event.setCancelled(true);
            }
        }
    }

    ///API SIDE EVENT THAT GETS CALLED ANY TIME A BLOCK IS PLACED
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        ItemStack item = event.getPlayer().getEquipment().getItemInMainHand();
        Block targetBlock = event.getBlock();

            ///IF THE SPAWNER IS A CUSTOM SPAWNER (FROM SILK TOUCH MINED OR LOOT CRATE) CHANGES MOB AS SPAWNER IS EMPTY BY DEFAULT
        if(targetBlock.getType() == Material.SPAWNER && item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(CPerks.getInstance(), "SpawnerType"))){
            ItemMeta itemMeta = item.getItemMeta();
            PersistentDataContainer spawnerDC = itemMeta.getPersistentDataContainer();
            String entityType = spawnerDC.get(NamespacedKeyCollection.SpawnerKey, PersistentDataType.STRING);
            BlockState blockState = targetBlock.getState();
            Spawner spawner = (Spawner) blockState;
            spawner.setSpawnedType(EntityType.valueOf(entityType));
            blockState.update(true);

            ///ADDS A LOOT CRATE BLOCK TO THE CONFIG TO ENABLE INTERACTION WITH IT AND PREVENT PLAYERS FROM BREAKING IT WITHOUT PERMISSION
        } else if(targetBlock.getType() == Material.LIME_SHULKER_BOX && item.getItemMeta().getPersistentDataContainer().has(NamespacedKeyCollection.LootCrateKey)){
            BlockDataHandler.set("Loot-Crate", targetBlock.getLocation());
            HologramHandler.spawnHologram(ChatColor.GOLD + "Loot Crate", targetBlock.getLocation());
            HologramHandler.spawnHologram(ChatColor.GREEN + "Use a Key to Open!", targetBlock.getLocation().add(new Vector(0, -0.25f, 0)));
        }
    }
        ///USED TO DAMAGE A USED TOOL
    public void damageTool(Player player, ItemStack tool, int damageAmount){
        Damageable damageable = (Damageable) tool.getItemMeta();
        assert damageable != null;
        damageable.setDamage(damageable.getDamage() + damageAmount);
        if (damageable.getDamage() >= tool.getType().getMaxDurability()) {
            tool.setItemMeta(damageable);
            Objects.requireNonNull(player.getEquipment()).setItemInMainHand(new ItemStack(Material.AIR));
        } else {
            tool.setItemMeta(damageable);
        }
    }
        ///CALCULATES DAMAGE APPLIED TO TOOL BASED ON IT'S UNBREAKING ENCHANTMENT
    public int getToolDamage(ItemStack tool, int brokenBlocks){
        int damage = brokenBlocks;
        if(tool.containsEnchantment(Enchantment.UNBREAKING)){
            int enchantmentLevel = tool.getEnchantmentLevel(Enchantment.UNBREAKING);
            double d = brokenBlocks * ((double) 100 / enchantmentLevel / 100);
            damage = (int) Math.round(d);
        }
        return damage;
    }
}
