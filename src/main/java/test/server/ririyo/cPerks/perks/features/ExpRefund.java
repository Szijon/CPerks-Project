package test.server.ririyo.cPerks.perks.features;

import org.bukkit.entity.Player;
import test.server.ririyo.cPerks.configs.UserDataHandler;

public class ExpRefund {

        ///RETURNS AMOUNT OF EXP TO BE REFUNDED
    public static int getRefundExp(Player player){
        float refundPercentage = Float.parseFloat(UserDataHandler.get(player, player.getUniqueId(), "Enchanter.Refunding-Amount"));
        int expValue = 306;
        return Math.round(expValue * (refundPercentage/100));
    }
}
