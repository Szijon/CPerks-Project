package test.server.ririyo.cPerks.listerners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import test.server.ririyo.cPerks.perks.features.ExpRefund;
import test.server.ririyo.cPerks.perks.features.ExtraExperience;
import test.server.ririyo.cPerks.handlers.PlayerLevelHandler;
import test.server.ririyo.cPerks.configs.UserDataHandler;

public class EnchantListener implements Listener {

    @EventHandler   ///EXECUTES ENCHANTER LOGIC UPON ANY ENCHANTMENT EVENT
    public void onPlayerEnchant(EnchantItemEvent event){
        String job = "Enchanter";
        Player player = event.getEnchanter();
        int enchantingLevel = event.getExpLevelCost(); /// Level Cost

        PlayerLevelHandler.addExperience(player, job, enchantingLevel);
        boolean refunding = Boolean.parseBoolean(UserDataHandler.get(player, player.getUniqueId(), job + ".Refunding"));
        ExtraExperience.spawnExtraExp(player, event.getEnchantBlock().getLocation());

        ///REFUNDS PLAYER EXP IF THEY HAVE THE FEATURE UNLOCKED AND ENCHANT AN ITEM AT LEVEL 30
        if(refunding && enchantingLevel == 30){
            player.giveExp(ExpRefund.getRefundExp(player));
        }
    }
}
