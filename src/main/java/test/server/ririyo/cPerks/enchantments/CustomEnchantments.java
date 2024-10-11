package test.server.ririyo.cPerks.enchantments;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


import java.util.List;
import java.util.Map;

public class CustomEnchantments {

    String name;
    int maxLevel;
    int minEnchantLevel;
    List<String> enchantmentItems;
    List<Enchantment> constraints;

    public CustomEnchantments(String name, int maxLevel, int minEnchantLevel, List<String> enchantmentItems, List<Enchantment> constraints){
        this.name = name;
        this.maxLevel = maxLevel;
        this.minEnchantLevel = minEnchantLevel;
        this.enchantmentItems = enchantmentItems;
        this.constraints = constraints;
    }

    public String getName(){
        return this.name;
    }

    public int getMaxLevel(){
        return this.maxLevel;
    }

    public int getMinEnchantLevel(){
        return this.minEnchantLevel;
    }

    public List<String> getEnchantmentItems(){
        return this.enchantmentItems;
    }

    public List<Enchantment> getConstraints(){
        return this.constraints;
    }


    public static ItemStack convertEnchantments(ItemStack item){
        ItemMeta meta = item.getItemMeta();
        Map<Enchantment, Integer> enchants = meta.getEnchants();
        if(!enchants.isEmpty()){
            List<String> lore = List.of();
            ///Adds Enchantments one per row.
            for (Map.Entry<Enchantment, Integer> entry : enchants.entrySet()) {
                String enchantmentName = entry.getKey().getKey().getKey();
                lore.add(enchantmentName + " " + entry.getValue());
            }
            meta.setLore(lore);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            item.setItemMeta(meta);
        }
        return item;
    }
}
