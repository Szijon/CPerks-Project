package test.server.ririyo.cPerks.lootcrate;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import test.server.ririyo.cPerks.CPerks;
import test.server.ririyo.cPerks.collections.CustomItemCollection;
import test.server.ririyo.cPerks.collections.NamespacedKeyCollection;
import test.server.ririyo.cPerks.configs.BlockDataHandler;
import test.server.ririyo.cPerks.handlers.PlayerMessageHandler;

import java.util.List;
import java.util.Objects;
import java.util.Random;


public class LootCrateHandler {

    ///GIVES SPECIFIED PLAYER A LOOT CRATE BLOCK
    public static void giveLootCrateBlock(Player player, int amount){
        player.getInventory().addItem(CustomItemCollection.getLootCrateBlock(amount));
    }
    ///GIVES SPECIFIED PLAYER A LOOT CRATE KEY WITH SPECIFIED AMOUNT AND TYPE
    public static void giveLootCrateKey(Player player, int amount, String type){
        player.getInventory().addItem(CustomItemCollection.createLootKey(amount, type));
    }

    ///GETS A RANDOM ITEM FROM A SPECIFIED LOOT POOL
    public static ItemStack getRandomDrop(List<LootPoolCollection.WeightedDrop> dropPool){
        int totalWeight = 0;
        for(LootPoolCollection.WeightedDrop drop : dropPool){
            totalWeight += drop.weight();
        }

        Random random = new Random();
        int dropIndex = random.nextInt(totalWeight);

        int cumulativeWeight = 0;
        for(LootPoolCollection.WeightedDrop drop : dropPool){
            cumulativeWeight += drop.weight();
            if(dropIndex < cumulativeWeight) {
                return drop.item();
            }
        }
        ///THIS SHOULD NEVER HAPPEN.
        return null;
    }

    ///OPENS THE LOOT BOX INTERFACE AND POPULATES IT WITH RANDOM ITEMS AS WELL AS A SURROUNDING BORDER.
    public static void openLootBoxGUI(Player player, String type) {
        Inventory lootBoxInv = Bukkit.createInventory(null, 27,  ChatColor.GOLD + type + " Loot Box");

        List<LootPoolCollection.WeightedDrop> pool;

        /// SETS SURROUNDING SLOTS TO RED PANES FOR AESTHETICS
        for (int i = 0; i < lootBoxInv.getSize(); i++){
            lootBoxInv.setItem(i, new ItemStack(Material.RED_STAINED_GLASS_PANE));
        }
        /// SETS MIDDLE TOP & BOTTOM TO GREEN PANES TO HIGHLIGHT FINAL LOOT ITEM
        lootBoxInv.setItem(4, new ItemStack(Material.GREEN_STAINED_GLASS_PANE));
        lootBoxInv.setItem(22, new ItemStack(Material.GREEN_STAINED_GLASS_PANE));
        ///SETS THE LOOT POOL
        if(type.equalsIgnoreCase("Rare")){
            pool = LootPoolCollection.rareLootPool;
        } else if(type.equalsIgnoreCase("Legendary")){
            pool = LootPoolCollection.legendaryLootPool;
        } else if(type.equalsIgnoreCase("Gear")){
            pool = LootPoolCollection.gearLootPool;
        } else {
            pool = LootPoolCollection.normalLootPool;
        }
        ///SETS CENTER ROW TO BE RANDOM SELECTED ITEMS FROM LOOT POOL
        for (int i = 9; i < 18; i++) {
            lootBoxInv.setItem(i, getRandomDrop(pool));
        }
        ///FINALLY OPENS THE INVENTORY FOR THE PLAYER TO WATCH THE SELECTION
        player.openInventory(lootBoxInv);
        player.setMetadata("Opened-Menu", new FixedMetadataValue(CPerks.getInstance(), "Loot-Crate"));
        rotateLootItems(player, lootBoxInv, pool, 0, 1);
    }

        ///ROTATES THE ITEMS AS TO IMITATE AN ANIMATION OF THE ITEM BEING SELECTED.
    private static void rotateLootItems(Player player, Inventory lootBoxInventory, List<LootPoolCollection.WeightedDrop> pool, int cycle, int delay) {
        if (cycle >= 70) {
            giveFinalLoot(player, lootBoxInventory);
            return;
        }

        for (int i = 9; i < 17; i++) {
            lootBoxInventory.setItem(i, lootBoxInventory.getItem(i + 1));
        }

        /// Add new Random item to the right hand side.
        lootBoxInventory.setItem(17, getRandomDrop(pool));

        player.updateInventory();
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.2f, 1);

        new BukkitRunnable() {
            @Override
            public void run() {
                rotateLootItems(player, lootBoxInventory, pool, cycle + 1, calculateNewDelay(cycle, delay));
            }
        }.runTaskLater(CPerks.getInstance(), delay);
    }
    ///GIVES THE PLAYER THE ITEM AT THE CENTER OF THE INTERFACE
    private static void giveFinalLoot(Player player, Inventory lootBoxInventory) {
        ItemStack finalLoot = lootBoxInventory.getItem(13);
        if (finalLoot != null) {
            if(player.getInventory().firstEmpty() != -1) {
                player.getInventory().addItem(finalLoot);
            } else {
                Objects.requireNonNull(player.getLocation().getWorld()).dropItemNaturally(player.getLocation(), finalLoot);
            }
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.2f, 1);
        }
        new BukkitRunnable() {
            @Override
            public void run() { ///CLOSES THE INVENTORY AFTER A DELAY
                player.closeInventory();
                player.removeMetadata("Opened-Menu", CPerks.getInstance());
            }
        }.runTaskLater(CPerks.getInstance(), 40L); /// 40 TICKS = 2 SECONDS
    }
        ///CHANGES THE DELAY BETWEEN ITEMS ROTATING TO MAKE THE ANIMATION SLOW DOWN UNTIL IT STOPS.
    private static int calculateNewDelay(int cycle, int currentDelay) {
        if (cycle == 10 || cycle == 30 || cycle == 45 || cycle == 55 || cycle == 65) {
            return currentDelay + 1;
        } else {
            return currentDelay;
        }
    }
        ///CHECKS IF THE PLAYER IS INTERACTING WITH A REGISTERED LOOT BOX AND HAS A PROPER KEY FOR IT
    public static void checkLootBoxInteraction(PlayerInteractEvent event, Player player, ItemStack item){
        Location blockLocation = Objects.requireNonNull(event.getClickedBlock()).getLocation();
        Location configLocation = BlockDataHandler.getLocation("Loot-Crate");
        if(configLocation != null && configLocation.getBlockX() == blockLocation.getBlockX() && configLocation.getBlockY() == blockLocation.getBlockY() && configLocation.getBlockZ() == blockLocation.getBlockZ()) {
            if(!player.isOp()){
                event.setCancelled(true);
                return;
            }

            if (item.getType() == Material.TRIPWIRE_HOOK && item.getItemMeta() != null) {
                event.setCancelled(true);
                ItemMeta meta = item.getItemMeta();
                PersistentDataContainer pdc = meta.getPersistentDataContainer();

                if (pdc.has(NamespacedKeyCollection.LootCrateKeyKey)) {
                    String type = pdc.get(NamespacedKeyCollection.LootCrateKeyKey, PersistentDataType.STRING);
                    item.setAmount(item.getAmount() - 1);
                    LootCrateHandler.openLootBoxGUI(player, type);
                    player.sendMessage(ChatColor.BLUE + "Opening Loot Crate!");
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 1);
                }
            }
        }
    }
        ///USED WHEN A PLAYER TRIGGERS THE 0.033% OF DROPPING A KEY TO GIVE THEM A RANDOM KEY FROM A LOOT POOL
    public static void getRandomLootKeyDrop(Player player){
        ItemStack key = LootCrateHandler.getRandomDrop(LootPoolCollection.keyLootPool);
        ItemMeta meta = key.getItemMeta();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        String keyType = pdc.get(NamespacedKeyCollection.LootCrateKeyKey, PersistentDataType.STRING);
        Objects.requireNonNull(player.getLocation().getWorld()).dropItemNaturally(player.getLocation(), key);

        ///BROADCASTS A MESSAGE IF THE KEY THEY DROPPED IS EXTRAORDINARILY RARE
        if(keyType.equalsIgnoreCase("Gear")){
            Bukkit.broadcastMessage(ChatColor.GREEN + player.getName() + ChatColor.DARK_PURPLE + " has dropped a " + keyType + " Key! (0.00125%");
            for (Player p : Bukkit.getOnlinePlayers()){
                p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_DEATH, 0.25f, 1);
            }
        } else if(keyType.equalsIgnoreCase("Legendary")){
            Bukkit.broadcastMessage(ChatColor.GREEN + player.getName() + ChatColor.DARK_PURPLE +  " has dropped a " + keyType + " Key! (0.00875%)");
        } else if(keyType.equalsIgnoreCase("Rare") && key.getAmount() == 5){
            Bukkit.broadcastMessage(ChatColor.GREEN + player.getName() + ChatColor.DARK_PURPLE + " has dropped 5 " + keyType + " Keys! (0.00375%)");
        } else {
            PlayerMessageHandler.sendRareDropMessage(player, meta.getDisplayName());
        }
    }
}
