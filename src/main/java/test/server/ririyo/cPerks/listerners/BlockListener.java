package test.server.ririyo.cPerks.listerners;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.Ageable;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
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
import test.server.ririyo.cPerks.collections.*;
import test.server.ririyo.cPerks.enchantments.CustomEnchantments;
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
        Block startBlock = event.getBlock();
        Material blockType = startBlock.getType();
        String perk = null;

            ///SET THE PERK IN USE
        if(Arrays.asList(WoodCutterCollection.blockList).contains(blockType)){
            perk = "Woodcutter";
        } else if (Arrays.asList(MinerCollection.blockList).contains(blockType)) {
            if (!startBlock.getDrops(Objects.requireNonNull(player.getEquipment()).getItemInMainHand()).isEmpty()) {
                perk = "Miner";
            }
        } else if (Arrays.asList(FarmerCollection.blockList).contains(blockType)) {
            perk = "Farmer";
        }

        if(perk != null){
            event.setCancelled(true);
            PerkLogic.BlocksMined blocksMined = PerkLogic.mineBlock(player, Objects.requireNonNull(player.getEquipment()).getItemInMainHand(), event, perk);
            event.setExpToDrop(blocksMined.getExpOrbs());
            Map<Block, Collection<ItemStack>> drops = blocksMined.getDrops();
            int brokenBlocks = 0;

            for(Block block : blocksMined.getList()){
                if(perk.equalsIgnoreCase("farmer")){
                    Ageable ageable = (Ageable) block.getBlockData();
                    if(blocksMined.getHarvester() && Arrays.asList(FarmerCollection.tools_hoes).contains(blocksMined.getTool().getType())){
                        if (ageable.getMaximumAge() == ageable.getAge()) {
                            if (blocksMined.getReplant()) {
                                ageable.setAge(0);
                                block.setBlockData(ageable);
                            } else {
                                block.setType(Material.AIR);
                            }
                            brokenBlocks++;
                        } else {
                            if(blocksMined.getReplant()){
                                drops.remove(block);
                            }
                        }
                    } else {
                        if(blocksMined.getReplant()) {
                            if (ageable.getAge() == ageable.getMaximumAge()) {
                                ageable.setAge(0);
                                block.setBlockData(ageable);
                            } else {
                                block.setType(Material.AIR);
                            }
                            brokenBlocks++;
                        } else {
                            if(ageable.getMaximumAge() == ageable.getAge()) {
                                block.setType(Material.AIR);
                                brokenBlocks++;
                            } else {
                                block.setType(Material.AIR);
                            }
                        }
                    }

                } else if (perk.equalsIgnoreCase("woodcutter")){
                    if(Arrays.asList(WoodCutterCollection.tools).contains(blocksMined.getTool().getType())){
                        if (blocksMined.getReplant()) {
                            Material blockBelow = block.getWorld().getBlockAt(block.getLocation().add(new Vector(0, -1, 0))).getType();
                            if (blockBelow == Material.GRASS_BLOCK || blockBelow == Material.DIRT || blockBelow == Material.PODZOL) {
                                block.setType(WoodCutterCollection.saplingMatch.get(startBlock.getType()));
                            } else {
                                block.setType(Material.AIR);
                            }
                            brokenBlocks++;
                        } else {
                            block.setType(Material.AIR);
                            brokenBlocks++;
                        }
                    } else {
                        startBlock.setType(Material.AIR);
                        brokenBlocks++;
                    }
                } else if (perk.equalsIgnoreCase("miner")) {
                    if(blocksMined.getTool().getType().toString().contains("PICKAXE")) {
                        block.setType(Material.AIR);
                        if(blocksMined.getExpOrbs() > 0) {
                            ((ExperienceOrb) block.getWorld().spawnEntity(block.getLocation(), EntityType.EXPERIENCE_ORB)).setExperience(blocksMined.getExpOrbs());
                        }
                        brokenBlocks++;
                    }
                } else {
                    block.setType(Material.AIR);
                    brokenBlocks++;
                }
            }

            if(drops != null) {
                ItemStack item = event.getPlayer().getEquipment().getItemInMainHand();
                if(item != null) {
                    for (Map.Entry<Block, Collection<ItemStack>> entry : drops.entrySet()) {
                        CustomEnchantments.processCustomEnchants(item, event, drops.get(entry.getKey()));
                    }
                }
            }

            ///DAMAGE TOOL
            if(Arrays.asList(PerkLogic.tools).contains(blocksMined.getTool().getType())) {
                damageTool(player, blocksMined.getTool(), PerkLogic.calculateDamage(blocksMined.getTool(), brokenBlocks));
            }
            PlayerLevelHandler.addExperience(player, perk, PerkLogic.expMultiplier.get(blockType) * brokenBlocks);

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
        } else {
            ItemStack item = player.getEquipment().getItemInMainHand();
            if(item.hasItemMeta()) {
                if (item.getItemMeta().getPersistentDataContainer().has(NamespacedKeyCollection.TelekinesisEnchant)) {
                    event.setCancelled(true);
                    CustomEnchantments.processCustomEnchants(item, event, event.getBlock().getDrops(item));
                    event.getBlock().setType(Material.AIR);
                }
            }
        }
    }

    ///API SIDE EVENT THAT GETS CALLED ANY TIME A BLOCK IS PLACED
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        ItemStack item = event.getPlayer().getEquipment().getItemInMainHand();
        Block targetBlock = event.getBlock();

            ///IF THE SPAWNER IS A CUSTOM SPAWNER (FROM SILK TOUCH MINED OR LOOT CRATE) CHANGES MOB AS SPAWNER IS EMPTY BY DEFAULT
        if(targetBlock.getType() == Material.SPAWNER && item.getItemMeta().getPersistentDataContainer().has(NamespacedKeyCollection.SpawnerKey)) {
            ItemMeta itemMeta = item.getItemMeta();
            PersistentDataContainer spawnerDC = itemMeta.getPersistentDataContainer();
            String entityType = spawnerDC.get(NamespacedKeyCollection.SpawnerKey, PersistentDataType.STRING);
            BlockState blockState = targetBlock.getState();
            Spawner spawner = (Spawner) blockState;
            spawner.setSpawnedType(EntityType.valueOf(entityType));
            spawner.setMaxNearbyEntities(50);
            spawner.setRequiredPlayerRange(30);
            blockState.update(true);

            ///ADDS A LOOT CRATE BLOCK TO THE CONFIG TO ENABLE INTERACTION WITH IT AND PREVENT PLAYERS FROM BREAKING IT WITHOUT PERMISSION
        } else if(targetBlock.getType() == Material.LIME_SHULKER_BOX && item.getItemMeta().getPersistentDataContainer().has(NamespacedKeyCollection.LootCrateKey)){
            BlockDataHandler.set("Loot-Crate", targetBlock.getLocation());
            HologramHandler.spawnHologram(ChatColor.GOLD + "Loot Crate", targetBlock.getLocation());
            HologramHandler.spawnHologram(ChatColor.GREEN + "Use a Key to Open!", targetBlock.getLocation().add(new Vector(0, -0.25f, 0)));
        }
    }
        ///USED TO DAMAGE A USED TOOL
    public static void damageTool(Player player, ItemStack tool, int damageAmount){
        Damageable damageable = (Damageable) tool.getItemMeta();
        assert damageable != null;
        if(tool.getItemMeta().getPersistentDataContainer().has(NamespacedKeyCollection.UnbreakableEnchant)){
            damageable.setDamage(0);
        } else {
            damageable.setDamage(damageable.getDamage() + damageAmount);
        }
        if (damageable.getDamage() >= tool.getType().getMaxDurability()) {
            tool.setItemMeta(damageable);
            Objects.requireNonNull(player.getEquipment()).setItemInMainHand(new ItemStack(Material.AIR));
        } else {
            tool.setItemMeta(damageable);
        }
    }
}
