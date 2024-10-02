package test.server.ririyo.cPerks.listerners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import test.server.ririyo.cPerks.perks.perkmenu.MenuHandler;


public class PlayerDeathListener implements Listener {
    @EventHandler
    ///CHECKS FOR UNLOCKED FEATURES TO PREVENT ITEM OR EXPERIENCE LOSS IF UNLOCKED
    public void onPlayerDeath(PlayerDeathEvent event){
        Player player = event.getEntity();
        if(MenuHandler.hasFeatureUnlocked(player, "Keep-Inventory")){
            event.setKeepInventory(true);
            event.getDrops().clear();
        }
        if(MenuHandler.hasFeatureUnlocked(player, "Keep-Experience")){
            event.setKeepLevel(true);
            event.setDroppedExp(0);
        }
    }
}
