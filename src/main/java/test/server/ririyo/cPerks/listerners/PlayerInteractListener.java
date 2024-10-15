package test.server.ririyo.cPerks.listerners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import test.server.ririyo.cPerks.CPerks;
import test.server.ririyo.cPerks.collections.CustomItemCollection;
import test.server.ririyo.cPerks.collections.NamespacedKeyCollection;
import test.server.ririyo.cPerks.configs.UserDataHandler;
import test.server.ririyo.cPerks.perks.features.FlightHandler;
import test.server.ririyo.cPerks.lootcrate.LootCrateHandler;
import test.server.ririyo.cPerks.perks.perklogic.FarmerCollection;
import test.server.ririyo.cPerks.perks.perklogic.PerkLogic;

import java.util.Arrays;
import java.util.List;

public class PlayerInteractListener implements Listener{

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if(item != null) {
            if (item.getItemMeta().getPersistentDataContainer().has(NamespacedKeyCollection.UnbreakableEnchant)) {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                    Damageable damageable = (Damageable) event.getItem().getItemMeta();
                    damageable.setDamage(0);
                    item.setItemMeta(damageable);
                }
                }.runTaskLater(CPerks.getInstance(), 1);

            }

            ///CHECKS IF PLAYER IS USING A FLIGHT CREDIT TO ADD FLIGHT TIME
            if (item.getType() == Material.FEATHER) {
                ItemMeta meta = item.getItemMeta();
                if (meta != null) {
                    PersistentDataContainer pdc = meta.getPersistentDataContainer();
                    int time = pdc.get(NamespacedKeyCollection.FlightKey, PersistentDataType.INTEGER);
                    FlightHandler.addFlightTime(player, time);
                    item.setAmount(item.getAmount() - 1);
                }
            }
            if (event.hasBlock()) {
                Block block = event.getClickedBlock();
                if (block.getType() == Material.DIRT || block.getType() == Material.GRASS_BLOCK && event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    if (Arrays.asList(FarmerCollection.tools_hoes).contains(item.getType()) && Boolean.parseBoolean(UserDataHandler.get(player, player.getUniqueId(), "Farmer.3x3-Harvest"))) {
                        event.setCancelled(true);
                        List<Block> blocks = PerkLogic.getTillingBlocks(block);
                        int damage = 0;
                        for (Block b : blocks) {
                            if (block.getWorld().getBlockAt(b.getLocation().add(new Vector(0, +1, 0))).getType() == Material.AIR) {
                                b.setType(Material.FARMLAND);
                                damage++;
                            }
                        }
                        BlockListener.damageTool(player, item, PerkLogic.calculateDamage(item, damage));
                    }
                } else {
                    LootCrateHandler.checkLootBoxInteraction(event, player, item);
                }
            }
        }
    }
        ///INFINITE WATER BUCKET CODE
    @EventHandler
    public void PlayerBucketEvent(PlayerBucketEmptyEvent event){
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getEquipment().getItemInMainHand();
        ItemStack offHandItem = player.getEquipment().getItemInOffHand();
        if(mainHandItem.hasItemMeta()){
            PersistentDataContainer pdc = mainHandItem.getItemMeta().getPersistentDataContainer();
            if(pdc.has(NamespacedKeyCollection.CustomItemKey)) {
                if (pdc.get(NamespacedKeyCollection.CustomItemKey, PersistentDataType.STRING).equalsIgnoreCase("MagicalWaterBucket")) {
                    new BukkitRunnable(){
                        @Override
                        public void run(){
                            player.getEquipment().setItemInMainHand(CustomItemCollection.getInfiniteWaterBucket());

                        }
                    }.runTaskLater(CPerks.getInstance(), 1);
                }
            }
        } else if(offHandItem.hasItemMeta()){
            PersistentDataContainer pdc = offHandItem.getItemMeta().getPersistentDataContainer();
            if(pdc.has(NamespacedKeyCollection.CustomItemKey)) {
                if (pdc.get(NamespacedKeyCollection.CustomItemKey, PersistentDataType.STRING).equalsIgnoreCase("MagicalWaterBucket")) {
                    new BukkitRunnable(){
                        @Override
                        public void run(){
                            player.getEquipment().setItemInOffHand(CustomItemCollection.getInfiniteWaterBucket());

                        }
                    }.runTaskLater(CPerks.getInstance(), 1);
                }
            }
        }
    }
}
