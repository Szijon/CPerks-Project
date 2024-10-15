package test.server.ririyo.cPerks.listerners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import test.server.ririyo.cPerks.perks.features.FlightHandler;
import test.server.ririyo.cPerks.perks.perkmenu.ScoreboardHandler;
import test.server.ririyo.cPerks.configs.UserDataHandler;


public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        ///ENABLES THE SCOREBOARD FOR A PLAYER UPON JOINING, AS WELL AS UPDATING THEIR CONFIG WITH NEW KEYS OR CREATING ONE IF IT DOESN'T EXIST
        ScoreboardHandler.updateScoreboard(event.getPlayer(), "Woodcutter");
        UserDataHandler.updateUserConfig(event.getPlayer(), event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event){
        Player player = event.getPlayer();
        if(FlightHandler.activeTimers.containsKey(player.getUniqueId())){
            FlightHandler.toggleFlight(player);
        }
    }
}
