package test.server.ririyo.cPerks.perks.perkmenu;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
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
import test.server.ririyo.cPerks.configs.UserDataHandler;
import test.server.ririyo.cPerks.lootcrate.LootCrateKeyItem;
import test.server.ririyo.cPerks.perks.perklogic.PerkLogic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SaleMenu {

    public static ItemStack filler = CustomItemCollection.createCustomItem(Material.WHITE_STAINED_GLASS_PANE, 1, " ", null, null, null, null, null, null, null);

    public static void setSellInventory(Player player, Inventory inventory){

        MenuHandler.clearInventory(player, inventory);
        MenuHandler.clearRow(player, inventory, 0);
        MenuHandler.clearRow(player, inventory, 1);
        MenuHandler.clearRow(player, inventory, 2);

                        ///   Slot - 1|Row
        inventory.setItem(0 + 9 * 3, CustomItemCollection.getPerkOverviewItem());
        inventory.setItem(4 + 9 * 3, getConfirmItem());
        inventory.setItem(8 + 9 * 3, getSellableItemsMenu());

        clearReturnItems(player, inventory);

        player.setMetadata("Opened-Menu", new FixedMetadataValue(CPerks.getInstance(), "Selling Items"));
        player.updateInventory();
    }

    public static void updateReturnItems(Player player, Inventory inventory, int delay){
        new BukkitRunnable() {
            @Override
            public void run() {
                clearReturnItems(player, inventory);
                Collection<ItemStack> returnItems = getReturnItems(getSellItems(inventory));
                int counter = 0;
                if(returnItems.isEmpty()){
                    for(int i = 0; i < 18; i++) {
                        inventory.setItem(9 * 4 + i, filler);
                    }
                }
                for(ItemStack item :returnItems){
                    inventory.setItem(counter + 9 * 4, item);
                    counter++;
                }

                player.updateInventory();
            }
        }.runTaskLater(CPerks.getInstance(), delay);
    }

    public static void removeSoldItems(Player player, Inventory inventory){
        for(int i = 0; i< 9*3; i++){
            ItemStack item = inventory.getItem(i);
            if(item != null){
                if(SaleMenuCollection.prices.containsKey(item.getType())){
                    inventory.setItem(i, null);
                }
                else {
                    PerkLogic.putInPlayerInventory(player, item);
                    inventory.setItem(i, null);
                }
            }
        }
        for(int i = 9 * 4; i< 9*6; i++){
            if(inventory.getItem(i) != null) {
                if (inventory.getItem(i).getType() != Material.WHITE_STAINED_GLASS_PANE) {
                    inventory.setItem(i, filler);
                }
            } else {
                inventory.setItem(i, filler);
            }
        }
    }

    public static void confirmSale(Player player, Inventory inventory){
        updateReturnItems(player, inventory, 0);
        new BukkitRunnable() {
            @Override
            public void run() {

            for(int i = 9 * 4; i< 9*6; i++){
                ItemStack item = inventory.getItem(i);
                if (item != null) {
                    if (item.getType() == Material.WHITE_STAINED_GLASS_PANE){
                        continue;
                    }
                    if (item.getType() == Material.GOLD_NUGGET || item.getType() == Material.GOLD_INGOT || item.getType() == Material.GOLD_BLOCK) {
                        int gold = item.getItemMeta().getPersistentDataContainer().get(NamespacedKeyCollection.GoldAmount, PersistentDataType.INTEGER);
                        UserDataHandler.setPlayerGold(player, UserDataHandler.getPlayerGold(player) + gold);
                        ScoreboardHandler.updateScoreboard(player, ScoreboardHandler.lastPerkUsed.get(player.getUniqueId()));
                    } else {
                        PerkLogic.putInPlayerInventory(player, inventory.getItem(i));
                    }
                    player.sendMessage(ChatColor.BLUE + "Items successfully sold!");
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.25f, 1);
                }
            }
            removeSoldItems(player, inventory);
            }
        }.runTaskLater(CPerks.getInstance(), 1);
    }

    public static void closedWithoutConfirm(Player player, Inventory inventory){
        int counter = 0;
        for(ItemStack itemStack : inventory.getContents()){
            if (counter > 9*3-1){
                break;
            }
            if(itemStack != null) {
                PerkLogic.putInPlayerInventory(player, itemStack);
            } else {
                break;
            }
            counter++;
        }
    }

    public static void clearReturnItems(Player player, Inventory inventory){
        for(int i = 0; i < 18; i++){
            inventory.setItem(4*9 + i, filler);
        }
        player.updateInventory();
    }

    public static Collection<ItemStack> getSellItems(Inventory inventory){
        Collection<ItemStack> items = new ArrayList<>();
        for(int i = 0; i < 27; i++){
            if(inventory.getItem(i) != null){
                items.add(inventory.getItem(i));
            }
        }
        return items;
    }

    public static Collection<ItemStack> getReturnItems(Collection<ItemStack> sellItems){
        int gold = 0;
        Collection<ItemStack> returnItems = new ArrayList<>();

        int rareCounter = 0;
        int legendaryCounter = 0;
        int mythicCounter = 0;

        for(ItemStack itemStack : sellItems){

            if(SaleMenuCollection.prices.containsKey(itemStack.getType())) {
                gold += SaleMenuCollection.prices.get(itemStack.getType()) * itemStack.getAmount();

            } else if(itemStack.hasItemMeta()){
                PersistentDataContainer pdc = itemStack.getItemMeta().getPersistentDataContainer();

                if(pdc.has(NamespacedKeyCollection.SellOriginKey)){
                    String origin = pdc.get(NamespacedKeyCollection.SellOriginKey, PersistentDataType.STRING);

                    switch(origin){
                        case "Normal":
                            gold += 250 * itemStack.getAmount();
                            break;
                        case "Rare":
                            rareCounter += itemStack.getAmount();
                            break;
                        case "Legendary":
                            legendaryCounter += itemStack.getAmount();
                            break;
                        case "Mythic":
                            mythicCounter += itemStack.getAmount();
                            break;
                        case null:
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + origin);
                    }
                }
            }
        }

        if(gold > 0){
            returnItems.add(getGoldItem(gold));
        }
        if(rareCounter > 0){
            returnItems.add(getKeyItem("Rare", rareCounter));
        }
        if(legendaryCounter > 0){
            returnItems.add(getKeyItem("Legendary", legendaryCounter));
        }
        if(mythicCounter > 0){
            returnItems.add(getKeyItem("Mythic", mythicCounter));
        }

        return returnItems;
    }

    public static ItemStack getKeyItem(String sellTag, int amount){
        return switch (sellTag) {
            case "Rare" -> LootCrateKeyItem.get(amount, LootCrateKeyItem.LootKeyType.NORMAL);
            case "Legendary" -> LootCrateKeyItem.get(amount, LootCrateKeyItem.LootKeyType.RARE);
            case "Mythic" -> LootCrateKeyItem.get(amount, LootCrateKeyItem.LootKeyType.LEGENDARY);
            default -> null;
        };
    }

    public static ItemStack getGoldItem(int goldAmount){
        Material material;
        if(goldAmount < 1000)
            material = Material.GOLD_NUGGET;
        else if(goldAmount < 5000)
            material = Material.GOLD_INGOT;
        else
            material = Material.GOLD_BLOCK;

        ItemStack itemStack = new ItemStack(material);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "" + goldAmount + " Gold");
        meta.getPersistentDataContainer().set(NamespacedKeyCollection.GoldAmount, PersistentDataType.INTEGER, goldAmount);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public static ItemStack getSellableItemsMenu(){
        return CustomItemCollection.createCustomItem(
                Material.PAPER,
                1,
                ChatColor.WHITE + "List of Sellable Items",
                List.of(ChatColor.BLUE + "Click to see a List of sellable Items."),
                null,
                null,
                null,
                NamespacedKeyCollection.SellableListKey,
                PersistentDataType.BOOLEAN,
                true
        );
    }

    public static ItemStack getConfirmItem(){
        return CustomItemCollection.createCustomItem(
                Material.EMERALD,
                1,
                ChatColor.GREEN + "Confirm",
                List.of(ChatColor.BLUE + "Click to sell above Items", ChatColor.BLUE + "Gives Items below to you."),
                null,
                null,
                null,
                NamespacedKeyCollection.ConfirmKey,
                PersistentDataType.BOOLEAN,
                true
        );
    }
}
