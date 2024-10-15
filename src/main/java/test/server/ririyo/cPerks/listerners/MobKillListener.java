package test.server.ririyo.cPerks.listerners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import test.server.ririyo.cPerks.configs.UserDataHandler;
import test.server.ririyo.cPerks.handlers.PlayerLevelHandler;
import test.server.ririyo.cPerks.perks.features.ExtraExperience;
import test.server.ririyo.cPerks.perks.perklogic.HunterCollection;
import test.server.ririyo.cPerks.perks.perklogic.Hunter;


public class MobKillListener implements Listener {

    @EventHandler ///EXECUTES HUNTER LOGIC UPON PLAYER KILLING A MOB (SUCH AS ADDING EXP AND GETTING THE DROP CHANCE OF A SPAWN-EGG)
    public void onMobKilled(EntityDeathEvent event){
        if(event.getDamageSource().getCausingEntity() instanceof Player player){
            Entity entity = event.getEntity();


            if(HunterCollection.mobExperience.containsKey(entity.getType())) {
                int amount = Integer.parseInt(UserDataHandler.get(player, player.getUniqueId(), "Hunter.Hunted-Amount")) + 1;
                int exp = HunterCollection.mobExperience.get(entity.getType());
                PlayerLevelHandler.addExperience(player, "Hunter", exp);
                if(amount >= 10){
                    Hunter.eggHunter(player, entity, amount);
                    amount = 0;
                }
                UserDataHandler.set(player, player.getUniqueId(), "Hunter.Hunted-Amount", amount);
            }
            ExtraExperience.spawnExtraExp(player, entity.getLocation());
        }
    }
}
