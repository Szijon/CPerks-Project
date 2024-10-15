package test.server.ririyo.cPerks.perks.perklogic;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import java.util.Map;

public class HunterCollection {

        ///LEVEL(KEY) AT WHICH THE BACKPACK GETS INCREASED TO A SPECIFIC SIZE(KEY)
    public static Map<Integer, Integer> backpackLevels = Map.ofEntries(
            Map.entry(5,9),
            Map.entry(10,18),
            Map.entry(15, 27),
            Map.entry(20, 36),
            Map.entry(25, 45),
            Map.entry(30, 54)
    );
        ///LEVEL(KEY) AT WHICH THE EGG DROP CHANCE(VALUE) GETS INCREASED
    public static Map<Integer, Float> eggDropChances = Map.ofEntries(
            Map.entry(15, 0.2f),
            Map.entry(18,0.4f),
            Map.entry(21, 0.6f),
            Map.entry(24, 0.8f),
            Map.entry(27, 1f)
    );
        ///MAP OF MOBS(KEY) AND THEIR EXPERIENCE FOR THE PERK(VALUE)
    public static Map<EntityType, Integer> mobExperience = Map.<EntityType, Integer>ofEntries(
            ///PASSIVES
            Map.entry(EntityType.COW, 3),
            Map.entry(EntityType.MOOSHROOM, 3),
            Map.entry(EntityType.PIG, 3),
            Map.entry(EntityType.ARMADILLO, 3),
            Map.entry(EntityType.FROG, 3),
            Map.entry(EntityType.SHEEP, 3),
            Map.entry(EntityType.CHICKEN, 3),
            Map.entry(EntityType.PANDA, 3),
            Map.entry(EntityType.TURTLE, 3),
            Map.entry(EntityType.BAT, 3),
            Map.entry(EntityType.SQUID, 3),
            Map.entry(EntityType.GLOW_SQUID, 3),
            Map.entry(EntityType.RABBIT, 3),
            Map.entry(EntityType.GOAT, 4),
            Map.entry(EntityType.CAMEL, 4),
            Map.entry(EntityType.LLAMA, 4),
            Map.entry(EntityType.POLAR_BEAR, 4),
            Map.entry(EntityType.WOLF, 4),
            Map.entry(EntityType.DONKEY, 4),
            Map.entry(EntityType.HORSE, 4),
            Map.entry(EntityType.MULE, 4),
            Map.entry(EntityType.TRADER_LLAMA, 4),

            ///FRIENDLIES
            Map.entry(EntityType.CAT, -1),
            Map.entry(EntityType.FOX, -1),
            Map.entry(EntityType.OCELOT, -1),
            Map.entry(EntityType.BEE, -1),
            Map.entry(EntityType.AXOLOTL, -1),
            Map.entry(EntityType.DOLPHIN, -2),
            Map.entry(EntityType.VILLAGER, -3),
            Map.entry(EntityType.IRON_GOLEM, -5),

            ///HOSTILES-OVERWORLD
            Map.entry(EntityType.SLIME, 1),
            Map.entry(EntityType.SILVERFISH, 6),
            Map.entry(EntityType.ZOMBIE, 6),
            Map.entry(EntityType.SKELETON, 6),
            Map.entry(EntityType.STRAY, 6),
            Map.entry(EntityType.CREEPER, 6),
            Map.entry(EntityType.SPIDER, 6),
            Map.entry(EntityType.CAVE_SPIDER, 6),
            Map.entry(EntityType.PILLAGER, 6),
            Map.entry(EntityType.ZOMBIE_VILLAGER, 6),
            Map.entry(EntityType.DROWNED, 6),
            Map.entry(EntityType.GUARDIAN, 6),
            Map.entry(EntityType.ZOGLIN, 8),
            Map.entry(EntityType.PHANTOM, 8),
            Map.entry(EntityType.WITCH, 8),
            Map.entry(EntityType.VINDICATOR, 8),
            Map.entry(EntityType.EVOKER, 10),
            Map.entry(EntityType.RAVAGER, 12),

            ///HOSTILES-NETHER
            Map.entry(EntityType.BLAZE, 6),
            Map.entry(EntityType.MAGMA_CUBE, 6),
            Map.entry(EntityType.ZOMBIFIED_PIGLIN, 6),
            Map.entry(EntityType.PIGLIN, 6),
            Map.entry(EntityType.HOGLIN, 8),
            Map.entry(EntityType.WITHER_SKELETON, 8),
            Map.entry(EntityType.GHAST, 8),
            Map.entry(EntityType.PIGLIN_BRUTE, 10),

            ///HOSTILES-END
            Map.entry(EntityType.SHULKER, 3),
            Map.entry(EntityType.ENDERMITE, 4),
            Map.entry(EntityType.ENDERMAN, 10),

            ///HOSTILES-BOSSES
            Map.entry(EntityType.ELDER_GUARDIAN, 50),
            Map.entry(EntityType.WITHER, 100),
            Map.entry(EntityType.WARDEN, 50),
            Map.entry(EntityType.ENDER_DRAGON, 500)
    );
            ///MAP TO MATCH A MOB(KEY) TO THEIR RESPECTIVE SPAWN EGG(VALUE)
    public static Map<EntityType, Material> entityEgg = Map.<EntityType, Material>ofEntries(
            ///PASSIVES
            Map.entry(EntityType.COW, Material.COW_SPAWN_EGG),
            Map.entry(EntityType.MOOSHROOM, Material.MOOSHROOM_SPAWN_EGG),
            Map.entry(EntityType.PIG, Material.PIG_SPAWN_EGG),
            Map.entry(EntityType.ARMADILLO, Material.ARMADILLO_SPAWN_EGG),
            Map.entry(EntityType.FROG, Material.FROG_SPAWN_EGG),
            Map.entry(EntityType.CHICKEN, Material.CHICKEN_SPAWN_EGG),
            Map.entry(EntityType.SHEEP, Material.SHEEP_SPAWN_EGG),
            Map.entry(EntityType.PANDA, Material.PANDA_SPAWN_EGG),
            Map.entry(EntityType.TURTLE, Material.TURTLE_SPAWN_EGG),
            Map.entry(EntityType.BAT, Material.BAT_SPAWN_EGG),
            Map.entry(EntityType.SQUID, Material.SQUID_SPAWN_EGG),
            Map.entry(EntityType.GLOW_SQUID, Material.GLOW_SQUID_SPAWN_EGG),
            Map.entry(EntityType.RABBIT, Material.RABBIT_SPAWN_EGG),
            Map.entry(EntityType.GOAT, Material.GOAT_SPAWN_EGG),
            Map.entry(EntityType.CAMEL, Material.CAMEL_SPAWN_EGG),
            Map.entry(EntityType.LLAMA, Material.LLAMA_SPAWN_EGG),
            Map.entry(EntityType.POLAR_BEAR, Material.POLAR_BEAR_SPAWN_EGG),
            Map.entry(EntityType.WOLF, Material.WOLF_SPAWN_EGG),
            Map.entry(EntityType.DONKEY, Material.DONKEY_SPAWN_EGG),
            Map.entry(EntityType.HORSE, Material.HORSE_SPAWN_EGG),
            Map.entry(EntityType.MULE, Material.MULE_SPAWN_EGG),

            ///FRIENDLIES
            Map.entry(EntityType.CAT, Material.CAT_SPAWN_EGG),
            Map.entry(EntityType.FOX, Material.FOX_SPAWN_EGG),
            Map.entry(EntityType.OCELOT, Material.OCELOT_SPAWN_EGG),
            Map.entry(EntityType.BEE, Material.BEE_SPAWN_EGG),
            Map.entry(EntityType.AXOLOTL, Material.AXOLOTL_SPAWN_EGG),
            Map.entry(EntityType.DOLPHIN, Material.DOLPHIN_SPAWN_EGG),
            Map.entry(EntityType.VILLAGER, Material.VILLAGER_SPAWN_EGG),
            Map.entry(EntityType.IRON_GOLEM, Material.IRON_GOLEM_SPAWN_EGG),

            ///HOSTILES-OVERWORLD
            Map.entry(EntityType.SLIME, Material.SLIME_SPAWN_EGG),
            Map.entry(EntityType.SILVERFISH, Material.SILVERFISH_SPAWN_EGG),
            Map.entry(EntityType.ZOMBIE, Material.ZOMBIE_SPAWN_EGG),
            Map.entry(EntityType.SKELETON, Material.SKELETON_SPAWN_EGG),
            Map.entry(EntityType.STRAY, Material.STRAY_SPAWN_EGG),
            Map.entry(EntityType.CREEPER, Material.CREEPER_SPAWN_EGG),
            Map.entry(EntityType.SPIDER, Material.SPIDER_SPAWN_EGG),
            Map.entry(EntityType.CAVE_SPIDER, Material.CAVE_SPIDER_SPAWN_EGG),
            Map.entry(EntityType.PILLAGER, Material.PILLAGER_SPAWN_EGG),
            Map.entry(EntityType.ZOMBIE_VILLAGER, Material.ZOMBIE_VILLAGER_SPAWN_EGG),
            Map.entry(EntityType.DROWNED, Material.DROWNED_SPAWN_EGG),
            Map.entry(EntityType.GUARDIAN, Material.GUARDIAN_SPAWN_EGG),
            Map.entry(EntityType.ZOGLIN, Material.ZOGLIN_SPAWN_EGG),
            Map.entry(EntityType.PHANTOM, Material.PHANTOM_SPAWN_EGG),
            Map.entry(EntityType.WITCH, Material.WITCH_SPAWN_EGG),
            Map.entry(EntityType.VINDICATOR, Material.VINDICATOR_SPAWN_EGG),
            Map.entry(EntityType.EVOKER, Material.EVOKER_SPAWN_EGG),
            Map.entry(EntityType.RAVAGER, Material.RAVAGER_SPAWN_EGG),

            ///HOSTILES-NETHER
            Map.entry(EntityType.BLAZE, Material.BLAZE_SPAWN_EGG),
            Map.entry(EntityType.MAGMA_CUBE, Material.MAGMA_CUBE_SPAWN_EGG),
            Map.entry(EntityType.ZOMBIFIED_PIGLIN, Material.ZOMBIFIED_PIGLIN_SPAWN_EGG),
            Map.entry(EntityType.PIGLIN, Material.PIGLIN_SPAWN_EGG),
            Map.entry(EntityType.HOGLIN, Material.HOGLIN_SPAWN_EGG),
            Map.entry(EntityType.WITHER_SKELETON, Material.WITHER_SKELETON_SPAWN_EGG),
            Map.entry(EntityType.GHAST, Material.GHAST_SPAWN_EGG),
            Map.entry(EntityType.PIGLIN_BRUTE, Material.PIGLIN_BRUTE_SPAWN_EGG),

            ///HOSTILES-END
            Map.entry(EntityType.SHULKER, Material.SHULKER_SPAWN_EGG),
            Map.entry(EntityType.ENDERMITE, Material.ENDERMITE_SPAWN_EGG),
            Map.entry(EntityType.ENDERMAN, Material.ENDERMAN_SPAWN_EGG),

            ///HOSTILES-BOSSES
            Map.entry(EntityType.ELDER_GUARDIAN, Material.ELDER_GUARDIAN_SPAWN_EGG),
            Map.entry(EntityType.WARDEN, Material.WARDEN_SPAWN_EGG)
    );
}
