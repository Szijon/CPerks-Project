package test.server.ririyo.cPerks.listerners;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import test.server.ririyo.cPerks.perks.perklogic.HunterCollection;
import test.server.ririyo.cPerks.perks.perklogic.Hunter;


public class MobKillListener implements Listener {

    @EventHandler ///EXECUTES HUNTER LOGIC UPON PLAYER KILLING A MOB (SUCH AS ADDING EXP AND GETTING THE DROP CHANCE OF A SPAWN-EGG)
    public void onMobKilled(EntityDeathEvent event){
        if(event.getDamageSource().getCausingEntity() instanceof Player player){
            EntityType entityType = event.getEntity().getType();
            if(HunterCollection.mobExperience.containsKey(entityType)){
                Hunter.mobKilled(player, event.getEntity());
            }
        }
    }
}
