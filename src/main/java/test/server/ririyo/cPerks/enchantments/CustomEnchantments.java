package test.server.ririyo.cPerks.enchantments;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import test.server.ririyo.cPerks.CPerks;
import test.server.ririyo.cPerks.collections.NamespacedKeyCollection;
import test.server.ririyo.cPerks.handlers.FormatHandler;
import test.server.ririyo.cPerks.perks.perklogic.PerkLogic;


import java.util.*;

public class CustomEnchantments {

    public static CustomEnchantment Telekinesis = new CustomEnchantment(
            ChatColor.GOLD,
            "Telekinesis",
            1,
            30,
            List.of(PerkLogic.tools, PerkLogic.weapons),
            null,
            1,
            NamespacedKeyCollection.TelekinesisEnchant
    );

    public static CustomEnchantment Unbreakable = new CustomEnchantment(
            ChatColor.LIGHT_PURPLE,
            "Unbreakable",
            1,
            30,
            List.of(PerkLogic.tools, PerkLogic.weapons, PerkLogic.armor),
            null,
            5,
            NamespacedKeyCollection.UnbreakableEnchant
    );

    public static Map<NamespacedKey, CustomEnchantment> enchantmentKeys = Map.ofEntries(
            Map.entry(NamespacedKeyCollection.UnbreakableEnchant, Unbreakable),
            Map.entry(NamespacedKeyCollection.TelekinesisEnchant, Telekinesis)
    );

    public static List<CustomEnchantment> customEnchantments = List.of(
            Unbreakable,
            Telekinesis
    );

    public static class CustomEnchantment {
        ChatColor color;
        String name;
        int maxLevel;
        int minEnchantLevel;
        List<Material[]> enchantmentItems;
        List<Enchantment> constraints;
        float chance;
        NamespacedKey key;

        public CustomEnchantment(ChatColor color, String name, int maxLevel, int minEnchantLevel, List<Material[]> enchantmentItems, List<Enchantment> constraints, float chance, NamespacedKey key) {
            this.color = color;
            this.name = name;
            this.maxLevel = maxLevel;
            this.minEnchantLevel = minEnchantLevel;
            this.enchantmentItems = enchantmentItems;
            this.constraints = constraints;
            this.chance = chance;
            this.key = key;
        }

        public void setChance(int chance){
            this.chance = chance;
        }

        public NamespacedKey getKey(){
            return this.key;
        }

        public float getChance(){
            return this.chance;
        }

        public ChatColor getColor() {
            return this.color;
        }

        public String getName() {
            return this.name;
        }

        public int getMaxLevel() {
            return this.maxLevel;
        }

        public int getMinEnchantLevel() {
            return this.minEnchantLevel;
        }

        public List<Material[]> getEnchantmentItems() {
            return this.enchantmentItems;
        }

        public List<Enchantment> getConstraints() {
            return this.constraints;
        }
    }

    public static void enchantEvent(EnchantItemEvent event) {
        ItemStack item = event.getItem();
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        int enchantLevel = event.getExpLevelCost();
        Map<Enchantment, Integer> enchantsToAdd = event.getEnchantsToAdd();
        boolean constraints = false;
        boolean foundMaterial = false;
        int chanceMulti = 1;

        for(Map.Entry<Enchantment, Integer> entry : enchantsToAdd.entrySet()){
            addEnchantment(item, entry.getKey(), entry.getValue());
        }

        for (CustomEnchantment customEnchantment : customEnchantments) {

            if(enchantsToAdd.containsKey(Enchantment.UNBREAKING)){
                if(enchantsToAdd.get(Enchantment.UNBREAKING) == 3){
                    chanceMulti = 2;
                }
            }

            if (PerkLogic.getRandomChance(customEnchantment.getChance()*chanceMulti) && enchantLevel >= customEnchantment.getMinEnchantLevel()) {
                ///CHECK FOR CONSTRAINTS BETWEEN ENCHANTMENTS
                if (customEnchantment.getConstraints() != null) {
                    for (Enchantment constraint : customEnchantment.getConstraints()) {
                        if (enchantsToAdd.containsKey(constraint)) {
                            constraints = true;
                            break;
                        }
                    }
                }
                ///CHECK IF CUSTOM ENCHANTMENT IS COMPATIBLE WITH ENCHANTED ITEM
                for (Material[] materials : customEnchantment.getEnchantmentItems()) {
                    if (Arrays.asList(materials).contains(item.getType())) {
                        foundMaterial = true;
                    }
                }
                ///ADD CUSTOM ENCHANTMENT IF THERE ARE NO CONSTRAINTS
                if (!constraints && foundMaterial) {
                    addCustomEnchantment(item, customEnchantment, getRandomLevel(customEnchantment.getMaxLevel()));
                }

                    ///REMOVES ANY OCCURRENCES OF UNBREAKING FROM DESCRIPTION IF UNBREAKABLE IS APPLIED.
                if(customEnchantment == Unbreakable){
                    removeUnbreaking(item);
                }
            }
        }
    }

    public static Map<CustomEnchantment, Integer> getCustomEnchantments(ItemStack item){
        Map<CustomEnchantment, Integer> map = new HashMap<>();

        PersistentDataContainer pdc = item.getItemMeta().getPersistentDataContainer();
        for(NamespacedKey key : pdc.getKeys()){
            CustomEnchantment enchantment = enchantmentKeys.get(key);
            map.put(enchantment, 1);
        }
        return map;
    }

        ///VANILLA ENCHANTMENTS
    public static void addEnchantment(ItemStack item, Enchantment enchantment, int level){
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(enchantment, level, true);
        List<String> lore = new ArrayList<>();
        if(meta.hasLore()) {
            lore = meta.getLore();
        }
        if(level == 1) {
            lore.add(ChatColor.DARK_AQUA + FormatHandler.convertString(enchantment.getKey().getKey()));
        } else {
            lore.add(ChatColor.DARK_AQUA + FormatHandler.convertString(enchantment.getKey().getKey()) + " " + level);
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
    }

        ///CUSTOM ENCHANTMENTS
    public static void addCustomEnchantment(ItemStack item, CustomEnchantment enchantment, int level){
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        if(meta.hasLore()) {
            lore = meta.getLore();
        }

        if(level == 1 && !lore.contains(enchantment.getColor() + enchantment.getName())) {
            lore.add(enchantment.getColor() + enchantment.getName());
        } else if(level > 1 && !lore.contains(enchantment.getColor() + enchantment.getName() + level)){
            lore.add(enchantment.getColor() + enchantment.getName() + " " + level);
        }

        meta.setLore(lore);
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        pdc.set(enchantment.getKey(), PersistentDataType.INTEGER, level);
        item.setItemMeta(meta);
        if(enchantment == Unbreakable){
            removeUnbreaking(item);
        }
    }

    public static void addVanillaEnchantments(ItemStack item, Map<Enchantment, Integer> enchantments){
        for(Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()){
            addEnchantment(item, entry.getKey(), entry.getValue());
        }
    }

    public static void addCustomEnchantments(ItemStack item, Map<CustomEnchantment, Integer> enchantments){
        for(Map.Entry<CustomEnchantment, Integer> entry : enchantments.entrySet()){
            addCustomEnchantment(item, entry.getKey(), entry.getValue());
        }
    }

        ///CONVERTS EXISTING ITEM WITH ENCHANTS INTO LORE ENCHANTED ITEM
    public static void convertEnchantments(ItemStack item){
        ItemMeta meta = item.getItemMeta();
        Map<Enchantment, Integer> enchants = meta.getEnchants();
        if(!enchants.isEmpty()){
            List<String> lore = new ArrayList<>();
            ///Adds Enchantments one per row.
            for (Map.Entry<Enchantment, Integer> entry : enchants.entrySet()) {
                String enchantmentName = FormatHandler.convertString(entry.getKey().getKey().getKey());
                lore.add(ChatColor.AQUA + enchantmentName + " " + entry.getValue());
            }
            meta.setLore(lore);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            item.setItemMeta(meta);
        }
    }

    public static void processCustomEnchants(ItemStack item, BlockBreakEvent event, Collection<ItemStack> drops) {
        if (item.hasItemMeta()) {
            PersistentDataContainer pdc = item.getItemMeta().getPersistentDataContainer();
            if (pdc.has(NamespacedKeyCollection.TelekinesisEnchant)) {
                for(ItemStack drop : drops) {
                    PerkLogic.putInPlayerInventory(event.getPlayer(), drop);
                }
                event.setDropItems(false);
            } else {
                for(ItemStack drop : drops) {
                    event.getPlayer().getWorld().dropItemNaturally(event.getBlock().getLocation(), drop);
                }
            }

            if (pdc.has(NamespacedKeyCollection.UnbreakableEnchant)) {
                Damageable damageable = (Damageable) item.getItemMeta();
                damageable.setDamage(0);
                item.setItemMeta(damageable);
            }
        } else {
            for(ItemStack drop : drops) {
                event.getPlayer().getWorld().dropItemNaturally(event.getBlock().getLocation(), drop);
            }
        }
    }

    public static void removeUnbreaking(ItemStack item){
        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.getLore();
        lore.remove(ChatColor.DARK_AQUA + "Unbreaking");
        lore.remove(ChatColor.DARK_AQUA + "Unbreaking 2");
        lore.remove(ChatColor.DARK_AQUA + "Unbreaking 3");
        meta.setLore(lore);
        item.setItemMeta(meta);
    }

    public static int getRandomLevel(int maxLevel){
        Random random = new Random();
        int level = random.nextInt(maxLevel);
        if(level == 0) {
            return 1;
        } else {
            return level;
        }
    }
}
