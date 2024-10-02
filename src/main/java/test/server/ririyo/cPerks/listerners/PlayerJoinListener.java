package test.server.ririyo.cPerks.listerners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import test.server.ririyo.cPerks.perks.perkmenu.ScoreboardHandler;
import test.server.ririyo.cPerks.configs.UserDataHandler;


public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        ///ENABLES THE SCOREBOARD FOR A PLAYER UPON JOINING, AS WELL AS UPDATING THEIR CONFIG WITH NEW KEYS OR CREATING ONE IF IT DOESN'T EXIST
        ScoreboardHandler.updateScoreboard(event.getPlayer(), "Woodcutter");
        UserDataHandler.updateUserConfig(event.getPlayer(), event.getPlayer().getUniqueId());
    }
}
