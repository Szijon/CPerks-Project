package test.server.ririyo.cPerks.lootcrate;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.util.Vector;


public class HologramHandler {
        ///SPAWNS A HOLOGRAM (FLOATING TEXT) WITH SPECIFIED TEXT AND LOCATION
    public static void spawnHologram(String text, Location location){
        ArmorStand holo = (ArmorStand) location.getWorld().spawnEntity(location.add(new Vector(0.5f, -0.5f, 0.5f)), EntityType.ARMOR_STAND);
        holo.setInvisible(true);
        holo.setInvulnerable(true);
        holo.setCustomName(text);
        holo.setCustomNameVisible(true);
        holo.setGravity(false);
    }
}
