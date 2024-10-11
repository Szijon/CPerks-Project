package test.server.ririyo.cPerks.lootcrate;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LootPool {

    public enum Rarity {
        COMMON(50),
        RARE(30),
        EPIC(15),
        LEGENDARY(5),
        MYTHIC(0.5f);

        private float chanceInPercent;

        Rarity(float chanceInPercent) {
            this.chanceInPercent = chanceInPercent;
        }

        public void setChance(int chance){
            this.chanceInPercent = chance;
        }

        public float getChance() {
            return chanceInPercent;
        }
    }

    public static class Drop{
        private final ItemStack item;
        private final Rarity rarity;
        private int weight = 0;

        public Drop(ItemStack item, Rarity rarity) {
            this.item = item;
            this.rarity = rarity;
        }

        public ItemStack getItem(){
            return item;
        }
        public Rarity getRarity(){
            return rarity;
        }
        public int getWeight(){
            return weight;
        }
        public void setWeight(int value){
            this.weight = value;
        }
    }

    public static class Pool{
        private final String name;
        private final List<Drop> dropList = new ArrayList<>();
        private int totalWeight;

        public Pool(String name){
            this.name = name;
            this.totalWeight = 0;
        }
        public String getName(){
            return name;
        }
        public List<Drop> getDropList(){
            return dropList;
        }
        public int getTotalWeight(){
            return totalWeight;
        }
        public void addItem(ItemStack itemStack, Rarity rarity){
            dropList.add(new Drop(itemStack, rarity));
        }
        public void calculateWeight(){
            int common = 0;
            int commonWeight = 0;
            int rare = 0;
            int rareWeight = 0;
            int epic = 0;
            int epicWeight = 0;
            int legendary = 0;
            int legendaryWeight = 0;
            int mythic = 0;
            int mythicWeight = 0;

                ///Counts how many of each Rarity there are.
            for(Drop drop : dropList){
                switch (drop.getRarity()){
                    case COMMON:
                        common++;
                        break;
                    case RARE:
                        rare++;
                        break;
                    case EPIC:
                        epic++;
                        break;
                    case LEGENDARY:
                        legendary++;
                        break;
                    case MYTHIC:
                        mythic++;
                        break;
                }
            }

            ///Scales the Weight higher to not work with floats, then applies the percentage chance of the given Rarity.
            if(common > 0)
                commonWeight = Math.round(Rarity.COMMON.getChance() / 100 * (common * 10000));
            if(rare > 0)
                rareWeight = Math.round(Rarity.RARE.getChance() / 100 * (rare*10000));
            if(epic > 0)
                epicWeight = Math.round(Rarity.EPIC.getChance() / 100 * (epic*10000));
            if(legendary > 0)
                legendaryWeight = Math.round(Rarity.LEGENDARY.getChance() / 100 * (legendary*10000));
            if(mythic > 0)
                mythicWeight = Math.round(Rarity.MYTHIC.getChance() / 100 * (mythic*10000));

                ///Applies the Weight to each Drop.
            for(Drop drop : dropList){
                switch (drop.getRarity()){
                    case COMMON:
                        drop.setWeight(commonWeight/common);
                        break;
                    case RARE:
                        drop.setWeight(rareWeight/rare);
                        break;
                    case EPIC:
                        drop.setWeight(epicWeight/epic);
                        break;
                    case LEGENDARY:
                        drop.setWeight(legendaryWeight/legendary);
                        break;
                    case MYTHIC:
                        drop.setWeight(mythicWeight/mythic);
                        break;
                }
            }
            this.totalWeight = commonWeight + rareWeight + epicWeight + legendaryWeight + mythicWeight;
        }
    }

    public static void simulateDrop(int amount, Pool pool) {
        List<Drop> drops = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            int totalWeight = pool.getTotalWeight();
            int cumulativeWeight = 0;
            Random random = new Random();
            int dropIndex = random.nextInt(totalWeight);

            for (Drop drop : pool.getDropList()) {
                cumulativeWeight += drop.getWeight();
                if (dropIndex < cumulativeWeight) {
                    System.out.println("Drop Index: " + dropIndex + "||| Cumulative Weight: " + cumulativeWeight + "||| Total Weight: " + totalWeight + "||| Drop: " + drop.getItem().getType());
                    if(!drops.contains(drop)){
                        drops.add(drop);
                    }
                    break;
                }
            }
            System.out.println("--------------------------------------------------------------------------------------------------");
        }
        System.out.println("Total Drops: " + drops.size());
        for(int i2 = 0; i2 < drops.size(); i2++){
            System.out.println(drops.get(i2).getItem().getType());
        }
    }
}
