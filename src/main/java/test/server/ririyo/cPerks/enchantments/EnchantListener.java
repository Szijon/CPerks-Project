package test.server.ririyo.cPerks.enchantments;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.inventory.PrepareGrindstoneEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import test.server.ririyo.cPerks.perks.features.ExpRefund;
import test.server.ririyo.cPerks.perks.features.ExtraExperience;
import test.server.ririyo.cPerks.handlers.PlayerLevelHandler;
import test.server.ririyo.cPerks.configs.UserDataHandler;

import java.util.HashMap;
import java.util.Map;

public class EnchantListener implements Listener {

    @EventHandler   ///EXECUTES ENCHANTER LOGIC UPON ANY ENCHANTMENT EVENT
    public void onPlayerEnchant(EnchantItemEvent event){
        String job = "Enchanter";
        Player player = event.getEnchanter();
        int enchantingLevel = event.getExpLevelCost(); /// Level Cost

        PlayerLevelHandler.addExperience(player, job, enchantingLevel);
        boolean refunding = Boolean.parseBoolean(UserDataHandler.get(player, player.getUniqueId(), job + ".Refunding"));
        ExtraExperience.spawnExtraExp(player, event.getEnchantBlock().getLocation());

        ///ADD CUSTOM ENCHANTMENTS IF CHANCE HIT
        CustomEnchantments.enchantEvent(event);

        ///REFUNDS PLAYER EXP IF THEY HAVE THE FEATURE UNLOCKED AND ENCHANT AN ITEM AT LEVEL 30
        if(refunding && enchantingLevel == 30){
            player.giveExp(ExpRefund.getRefundExp(player));
        }
    }

    /// WHEN DISENCHANTING IN A GRINDSTONE
    @EventHandler
    public void onPrepareGrindstone(PrepareGrindstoneEvent event){
        ItemStack item = null;
            /// ONE SLOT MUST BE EMPTY
        if(event.getInventory().getItem(0) != null && event.getInventory().getItem(1) == null){
            item = event.getInventory().getItem(0);
            /// ONE SLOT MUST BE EMPTY
        } else if (event.getInventory().getItem(1) != null && event.getInventory().getItem(0) == null) {
            item = event.getInventory().getItem(1);
        }
        if(item != null) {
            ItemStack result = new ItemStack(item.getType());
            Damageable resultDamageable = (Damageable) result.getItemMeta();
            Damageable damageable = (Damageable) item.getItemMeta();
            resultDamageable.setDamage(damageable.getDamage());
            result.setItemMeta(resultDamageable);
            event.setResult(result);
        }
    }

    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent event){
        if(event.getInventory().getItem(0) != null && event.getInventory().getItem(1) != null) {

            String displayName = null;

            if(event.getInventory().getItem(2) != null) {
                displayName = event.getInventory().getItem(2).getItemMeta().getDisplayName();
            }

            ItemStack mainItem = event.getInventory().getItem(0);
            ItemStack usedItem = event.getInventory().getItem(1);
            if (mainItem != null && usedItem != null) {
                if (mainItem.getType() == usedItem.getType()) {

                    Map<Enchantment, Integer> mainEnchantments = mainItem.getEnchantments();
                    Map<Enchantment, Integer> usedEnchantments = usedItem.getEnchantments();
                    Map<Enchantment, Integer> resultEnchantments = new HashMap<>();

                    Map<CustomEnchantments.CustomEnchantment, Integer> mainCustomEnchantments = new HashMap<>();
                    Map<CustomEnchantments.CustomEnchantment, Integer> usedCustomEnchantments = new HashMap<>();

                    if (!mainItem.getItemMeta().getPersistentDataContainer().isEmpty()) {
                        mainCustomEnchantments = CustomEnchantments.getCustomEnchantments(mainItem);
                    }

                    if (!usedItem.getItemMeta().getPersistentDataContainer().isEmpty()) {
                        usedCustomEnchantments = CustomEnchantments.getCustomEnchantments(usedItem);
                    }


                    resultEnchantments.putAll(mainEnchantments);
                    ///MERGE MAPS
                    ///NORMAL ENCHANTMENTS
                    for (Map.Entry<Enchantment, Integer> usedEntry : usedEnchantments.entrySet()) {

                        ///MAIN ITEM HAS SAME ENCHANTMENT ALREADY
                        if (mainEnchantments.containsKey(usedEntry.getKey())) {
                            if (usedEntry.getValue() > mainEnchantments.get(usedEntry.getKey())) {
                                resultEnchantments.put(usedEntry.getKey(), usedEntry.getValue());
                            }

                        ///MAIN ITEM DOESN'T HAVE THE ENCHANTMENT ALREADY
                        } else {
                            resultEnchantments.put(usedEntry.getKey(), usedEntry.getValue());
                        }
                    }

                    ///CUSTOM ENCHANTMENTS
                    for (Map.Entry<CustomEnchantments.CustomEnchantment, Integer> usedEntry : usedCustomEnchantments.entrySet()) {

                        ///MAIN ITEM HAS SAME ENCHANTMENT ALREADY
                        if (mainCustomEnchantments.containsKey(usedEntry.getKey())) {
                            if (usedEntry.getValue() > mainCustomEnchantments.get(usedEntry.getKey())) {
                                mainCustomEnchantments.put(usedEntry.getKey(), usedEntry.getValue());
                            }

                        ///MAIN ITEM DOESN'T HAVE THE ENCHANTMENT ALREADY
                        } else {
                            mainCustomEnchantments.put(usedEntry.getKey(), usedEntry.getValue());
                        }
                    }


                    ///CREATE A COPY OF THE MAIN ITEM
                ItemStack resultItem = new ItemStack(mainItem.getType());
                ItemMeta meta = mainItem.getItemMeta();
                if(displayName != null) {
                    meta.setDisplayName(displayName);
                } else {
                    meta.setDisplayName(mainItem.getItemMeta().getDisplayName());
                }
                meta.setLore(null);
                resultItem.setItemMeta(meta);

                CustomEnchantments.addVanillaEnchantments(resultItem, resultEnchantments);
                CustomEnchantments.addCustomEnchantments(resultItem, mainCustomEnchantments);
                event.setResult(resultItem);
                }
            }
        }
    }
}
