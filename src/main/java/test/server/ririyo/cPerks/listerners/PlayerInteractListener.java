package test.server.ririyo.cPerks.listerners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import test.server.ririyo.cPerks.collections.NamespacedKeyCollection;
import test.server.ririyo.cPerks.perks.features.FlightHandler;
import test.server.ririyo.cPerks.lootcrate.LootCrateHandler;

public class PlayerInteractListener implements Listener{

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

            ///CHECKS IF PLAYER IS USING A FLIGHT CREDIT TO ADD FLIGHT TIME
        if(item.getType() == Material.FEATHER){
            ItemMeta meta = item.getItemMeta();
            if(meta != null){
                PersistentDataContainer pdc = meta.getPersistentDataContainer();
                int time = pdc.get(NamespacedKeyCollection.FlightKey, PersistentDataType.INTEGER);
                FlightHandler.addFlightTime(player, time);
                item.setAmount(item.getAmount() - 1);
            }
        }
            ///CHECKS IF THE BLOCK IS A LOOT CRATE AND IF PLAYER HAS A KEY - THEN OPENS THE LOOT CRATE.
        if(event.hasBlock()) {
            LootCrateHandler.checkLootBoxInteraction(event, player, item);
        }
    }
}
