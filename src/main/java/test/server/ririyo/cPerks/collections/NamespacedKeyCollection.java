package test.server.ririyo.cPerks.collections;

import org.bukkit.NamespacedKey;
import test.server.ririyo.cPerks.CPerks;

import java.util.Map;

public class NamespacedKeyCollection {
    ///NAMESPACED KEYS ARE USED TO GIVE ITEMS PERSISTENT DATA TO LINK THEM TO FUNCTIONS

    ///LOOT CRATES
    public static NamespacedKey LootCrateKey = new NamespacedKey(CPerks.getInstance(), "Is-Loot-Crate");
    public static NamespacedKey LootCrateKeyKey = new NamespacedKey(CPerks.getInstance(), "LootCrateKey");

    ///PERK OVERVIEW
        ///BUTTON TO GET BACK
    public static NamespacedKey PerkOverviewKey = new NamespacedKey(CPerks.getInstance(), "Perk-Overview");
    public static NamespacedKey SellKey = new NamespacedKey(CPerks.getInstance(), "Sell-Key");

        ///FEATURE UNLOCK KEYS
    public static NamespacedKey WoodcutterVeinMiner = new NamespacedKey(CPerks.getInstance(), "Woodcutter.Vein-Miner");
    public static NamespacedKey MinerVeinMiner = new NamespacedKey(CPerks.getInstance(), "Miner.Vein-Miner");
    public static NamespacedKey Farmer3x3Harvest = new NamespacedKey(CPerks.getInstance(), "Farmer.3x3-Harvest");
    public static NamespacedKey EnchanterRefunding = new NamespacedKey(CPerks.getInstance(), "Enchanter.Refunding");
    public static NamespacedKey HunterBackpack = new NamespacedKey(CPerks.getInstance(), "Hunter.Backpack");
    public static NamespacedKey WoodcutterReplant = new NamespacedKey(CPerks.getInstance(), "Woodcutter.Replant");
    public static NamespacedKey MinerAutoSmelt = new NamespacedKey(CPerks.getInstance(), "Miner.Auto-Smelt");
    public static NamespacedKey FarmerReplant = new NamespacedKey(CPerks.getInstance(), "Farmer.Replant");
    public static NamespacedKey EnchanterExtraExp = new NamespacedKey(CPerks.getInstance(), "Enchanter.Extra-Experience");
    public static NamespacedKey HunterEggHunter = new NamespacedKey(CPerks.getInstance(), "Hunter.Egg-Hunter");

        /// PERK SHOP
    public static NamespacedKey CoinShopKey = new NamespacedKey(CPerks.getInstance(), "Coin-Shop");
    public static NamespacedKey GoldShopKey = new NamespacedKey(CPerks.getInstance(), "Gold-Shop");
    public static NamespacedKey PerkShopKey = new NamespacedKey(CPerks.getInstance(), "Perk-Shop");

        /// COIN SHOP
    public static NamespacedKey SpawnerKey = new NamespacedKey(CPerks.getInstance(), "Shop-Spawner");
    public static NamespacedKey KeepInvKey = new NamespacedKey(CPerks.getInstance(), "Shop-Keep-Inventory");
    public static NamespacedKey KeepExpKey = new NamespacedKey(CPerks.getInstance(), "Shop-Keep-Experience");
    public static NamespacedKey FlightKey = new NamespacedKey(CPerks.getInstance(), "Shop-Flight");

        /// GOLD SHOP
    public static NamespacedKey LootKeyKey = new NamespacedKey(CPerks.getInstance(), "Loot-Key");
    public static NamespacedKey FlightCreditKey = new NamespacedKey(CPerks.getInstance(), "Flight-Credit");

        /// PERK FEATURES
    public static Map<String, NamespacedKey> featureKeys = Map.ofEntries(
            Map.entry("Woodcutter.Vein-Miner", NamespacedKeyCollection.WoodcutterVeinMiner),
            Map.entry("Miner.Vein-Miner", NamespacedKeyCollection.MinerVeinMiner),
            Map.entry("Farmer.3x3-Harvest", NamespacedKeyCollection.Farmer3x3Harvest),
            Map.entry("Enchanter.Refunding", NamespacedKeyCollection.EnchanterRefunding),
            Map.entry("Hunter.Backpack", NamespacedKeyCollection.HunterBackpack),

            Map.entry("Woodcutter.Replant", NamespacedKeyCollection.WoodcutterReplant),
            Map.entry("Miner.Auto-Smelt", NamespacedKeyCollection.MinerAutoSmelt),
            Map.entry("Farmer.Replant", NamespacedKeyCollection.FarmerReplant),
            Map.entry("Enchanter.Extra-Experience", NamespacedKeyCollection.EnchanterExtraExp),
            Map.entry("Hunter.Egg-Hunter", NamespacedKeyCollection.HunterEggHunter)
    );
}
