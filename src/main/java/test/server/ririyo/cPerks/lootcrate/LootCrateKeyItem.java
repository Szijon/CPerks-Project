package test.server.ririyo.cPerks.lootcrate;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import test.server.ririyo.cPerks.collections.NamespacedKeyCollection;

import java.util.List;
import java.util.Map;

import static test.server.ririyo.cPerks.collections.CustomItemCollection.createCustomItemMutliplePDC;

public class LootCrateKeyItem {
    public enum LootKeyType{
        NORMAL("Normal"),
        RARE("Rare"),
        LEGENDARY("Legendary"),
        MYTHIC("Mythic");

        private String name;

        LootKeyType(String name){
            this.name = name;
        }

        public String getName(){
            return name;
        }
    }

    public static ItemStack get(int amount, LootKeyType type){
        String displayName = type.getName() + " Loot Crate Key";
        ChatColor color = switch (type) {
            case NORMAL -> ChatColor.GREEN;
            case RARE -> ChatColor.BLUE;
            case LEGENDARY -> ChatColor.GOLD;
            case MYTHIC -> ChatColor.LIGHT_PURPLE;
        };

        NamespacedKey[] keys = {NamespacedKeyCollection.LootCrateKeyKey, NamespacedKeyCollection.SellOriginKey};
        PersistentDataType[] types = {PersistentDataType.STRING, PersistentDataType.STRING};
        String[] values = {type.getName(), type.getName()};

        return createCustomItemMutliplePDC(
                Material.TRIPWIRE_HOOK,
                amount,
                color + displayName,
                List.of("", ChatColor.GREEN + "Use at Spawn to Open Loot Crate!"),
                Map.of(Enchantment.SHARPNESS, 1),
                null,
                List.of(ItemFlag.HIDE_ENCHANTS),
                keys,
                types,
                values
        );
    }

}
