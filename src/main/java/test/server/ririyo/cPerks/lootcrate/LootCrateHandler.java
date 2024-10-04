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

import java.util.*;

public class LootCrateHandler {

    public static class LimitedQueue<T> {
        private final Queue<LootPoolCollection.WeightedDrop> queue;
        private final int maxSize;

        public LimitedQueue(int size){
            queue = new LinkedList<>();
            this.maxSize = size;
        }

        public void add(LootPoolCollection.WeightedDrop drop){
            if(queue.size() >= maxSize){
                queue.poll();
            }
            queue.offer(drop);
        }

        public LootPoolCollection.WeightedDrop get(){
            return queue.peek();
        }
    }

    ///GIVES SPECIFIED PLAYER A LOOT CRATE BLOCK
    public static void giveLootCrateBlock(Player player, int amount){
        player.getInventory().addItem(CustomItemCollection.getLootCrateBlock(amount));
    }
    ///GIVES SPECIFIED PLAYER A LOOT CRATE KEY WITH SPECIFIED AMOUNT AND TYPE
    public static void giveLootCrateKey(Player player, int amount, String type){
        player.getInventory().addItem(CustomItemCollection.createLootKey(amount, type));
    }

    ///GETS A RANDOM ITEM FROM A SPECIFIED LOOT POOL
    public static LootPoolCollection.WeightedDrop getRandomDrop(List<LootPoolCollection.WeightedDrop> dropPool){
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
                return drop;
            }
        }
        ///THIS SHOULD NEVER HAPPEN.
        return null;
    }

        ///MATCHES A LOOT POOL TO A TYPE OF KEY
    public static List<LootPoolCollection.WeightedDrop> getPool(String type){
        if(type.equalsIgnoreCase("Rare")){
            return LootPoolCollection.rareLootPool;
        } else if(type.equalsIgnoreCase("Legendary")){
            return LootPoolCollection.legendaryLootPool;
        } else if(type.equalsIgnoreCase("Gear")){
            return LootPoolCollection.gearLootPool;
        } else {
            return LootPoolCollection.normalLootPool;
        }
    }

    public static void openCratesInstantly(Player player, String type, int amount){
        List<LootPoolCollection.WeightedDrop> pool = getPool(type);

        for (int i = 0; i < amount; i++) {
            LootPoolCollection.WeightedDrop loot = getRandomDrop(pool);
            if (player.getInventory().firstEmpty() != -1) {
                player.getInventory().addItem(loot.item());
            } else {
                player.getWorld().dropItemNaturally(player.getLocation(), loot.item());
            }
            String origin = type + " Crate";
            PlayerMessageHandler.broadcastRareDrop(player, loot, origin);
        }
    }

    ///OPENS THE LOOT BOX INTERFACE AND POPULATES IT WITH RANDOM ITEMS AS WELL AS A SURROUNDING BORDER.
    public static void openLootBoxGUI(Player player, String type) {
        Inventory lootBoxInv = Bukkit.createInventory(null, 27,  ChatColor.GOLD + type + " Loot Box");

        List<LootPoolCollection.WeightedDrop> pool = getPool(type);
        LimitedQueue queue = new LimitedQueue(5);

        /// SETS SURROUNDING SLOTS TO RED PANES FOR AESTHETICS
        for (int i = 0; i < lootBoxInv.getSize(); i++){
            lootBoxInv.setItem(i, new ItemStack(Material.RED_STAINED_GLASS_PANE));
        }
        /// SETS MIDDLE TOP & BOTTOM TO GREEN PANES TO HIGHLIGHT FINAL LOOT ITEM
        lootBoxInv.setItem(4, new ItemStack(Material.GREEN_STAINED_GLASS_PANE));
        lootBoxInv.setItem(22, new ItemStack(Material.GREEN_STAINED_GLASS_PANE));

        ///SETS CENTER ROW TO BE RANDOM SELECTED ITEMS FROM LOOT POOL
        for (int i = 9; i < 18; i++) {
            LootPoolCollection.WeightedDrop drop = getRandomDrop(pool);
            lootBoxInv.setItem(i, drop.item());
            if(i > 13){
                queue.add(drop);
            }
        }
        ///FINALLY OPENS THE INVENTORY FOR THE PLAYER TO WATCH THE SELECTION
        player.openInventory(lootBoxInv);
        player.setMetadata("Opened-Menu", new FixedMetadataValue(CPerks.getInstance(), "Loot-Crate"));
        rotateLootItems(player, lootBoxInv, pool, 0, 1, queue, type);
    }

        ///ROTATES THE ITEMS AS TO IMITATE AN ANIMATION OF THE ITEM BEING SELECTED.
    private static void rotateLootItems(Player player, Inventory lootBoxInventory, List<LootPoolCollection.WeightedDrop> pool, int cycle, int delay, LimitedQueue queue, String crateType) {
        if (cycle >= 70) {
            giveFinalLoot(player, queue, crateType);
            return;
        }

        for (int i = 9; i < 17; i++) {
            lootBoxInventory.setItem(i, lootBoxInventory.getItem(i + 1));
        }

        /// Add new Random item to the right hand side.
        LootPoolCollection.WeightedDrop drop = getRandomDrop(pool);
        lootBoxInventory.setItem(17, drop.item());
        queue.add(drop);

        player.updateInventory();
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 0.2f, 1);

        new BukkitRunnable() {
            @Override
            public void run() {
                rotateLootItems(player, lootBoxInventory, pool, cycle + 1, calculateNewDelay(cycle, delay), queue, crateType);
            }
        }.runTaskLater(CPerks.getInstance(), delay);
    }
    ///GIVES THE PLAYER THE ITEM AT THE CENTER OF THE INTERFACE
    private static void giveFinalLoot(Player player, LimitedQueue queue, String crateType) {
        ItemStack finalLoot = queue.get().item();
        PlayerMessageHandler.broadcastRareDrop(player, queue.get(),  crateType + " Crate");
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
                    if(player.isSneaking()) {
                        int initialAmount = item.getAmount();
                        int amount;
                            ///HARD CAP CRATES TO OPEN AT 10
                        if(initialAmount > 10){
                            amount = 10;
                        } else {
                            amount = initialAmount;
                        }
                        item.setAmount(initialAmount - amount);
                        LootCrateHandler.openCratesInstantly(player, type, amount);
                    } else {
                        item.setAmount(item.getAmount() - 1);
                        LootCrateHandler.openLootBoxGUI(player, type);
                        player.sendMessage(ChatColor.BLUE + "Opening " + type + " Loot Crate!");
                        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 1);
                    }
                }
            }
        }
    }
        ///USED WHEN A PLAYER TRIGGERS THE 0.033% OF DROPPING A KEY TO GIVE THEM A RANDOM KEY FROM A LOOT POOL
    public static void getRandomLootKeyDrop(Player player){
        LootPoolCollection.WeightedDrop key = getRandomDrop(LootPoolCollection.keyLootPool);
        ItemStack keyItem = key.item();
        ItemMeta meta = keyItem.getItemMeta();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        Objects.requireNonNull(player.getLocation().getWorld()).dropItemNaturally(player.getLocation(), keyItem);
        String origin = "Perk Drop";

        PlayerMessageHandler.broadcastRareDrop(player, key, origin);

        ///SEND MESSAGE TO PLAYER TO NOTIFY THEM.
        PlayerMessageHandler.sendRareDropMessage(player, meta.getDisplayName(), keyItem.getAmount());
    }
}
