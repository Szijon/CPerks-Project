package test.server.ririyo.cPerks.perks.features;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.spawner.Spawner;
import test.server.ririyo.cPerks.collections.CustomItemCollection;

public class SilkTouchSpawners {

    public static void mineSpawner(Block block, Player player){
        ///GETS THE SPAWNER'S SPAWNED MOB AND APPLIES IT TO THE THEN DROPPED ITEM
        Spawner spawner = (Spawner) block.getState();
        EntityType entityType = spawner.getSpawnedType();

        World world = block.getWorld();

        ItemStack item = CustomItemCollection.createMonsterSpawner(entityType);

        block.breakNaturally();
        world.dropItemNaturally(block.getLocation(), item);

        ///DEDUCTS THE PLAYER EXPERIENCE WORTH 30 LEVELS FOR MINING A SPAWNER WITH SILK-TOUCH
        int prevExp = player.getTotalExperience();
        player.setLevel(0);
        player.setExp(0);
        player.setTotalExperience(0);
        player.giveExp(prevExp-1395); ///1395 is the exact amount of exp needed to become lv 30
    }

    public static String getSpawnerName(EntityType entityType){
        String entityName = entityType.name();
        return ChatColor.DARK_PURPLE + FormatHandler.convertString(entityName) + " Spawner";
    }
}
