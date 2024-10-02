package test.server.ririyo.cPerks.perks.perklogic;

import java.util.Map;

public class EnchanterCollection {

    ///PERCENTAGE OF EXP(VALUE) TO BE REFUNDED FOR THE PLAYER'S LEVEL OF THE PERK(KEY)
    public static final Map<Integer, Integer> refundingAmount = Map.ofEntries(
            Map.entry(5, 2),
            Map.entry(7, 4),
            Map.entry(9, 6),
            Map.entry(11,8),
            Map.entry(13, 10),
            Map.entry(15, 12),
            Map.entry(17, 14),
            Map.entry(19, 16),
            Map.entry(21, 18),
            Map.entry(23, 21),
            Map.entry(25, 24),
            Map.entry(27, 27),
            Map.entry(29, 30),
            Map.entry(30, 33)
    );
}
