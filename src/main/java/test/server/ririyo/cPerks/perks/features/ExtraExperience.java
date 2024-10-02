package test.server.ririyo.cPerks.perks.features;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import test.server.ririyo.cPerks.configs.UserDataHandler;

public class ExtraExperience {
        ///SPAWNS EXTRA EXPERIENCE IF THE FEATURE HAS BEEN UNLOCKED || CALLED UPON ANY PERK EXPERIENCE GAIN
    public static void spawnExtraExp(Player player, Location location){
        if(Boolean.parseBoolean(UserDataHandler.get(player, player.getUniqueId(), "Enchanter.Extra-Experience"))){
            location.getWorld().spawnEntity(location, EntityType.EXPERIENCE_ORB);
        }
    }
}
