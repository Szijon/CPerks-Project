package test.server.ririyo.cPerks;

import org.bukkit.plugin.java.JavaPlugin;
import test.server.ririyo.cPerks.listerners.*;
import test.server.ririyo.cPerks.lootcrate.LootCrateCommand;
import test.server.ririyo.cPerks.lootcrate.LootPool;
import test.server.ririyo.cPerks.lootcrate.LootPoolCollection;
import test.server.ririyo.cPerks.perks.features.FlightCommand;
import test.server.ririyo.cPerks.perks.perkmenu.BackpackCommand;
import test.server.ririyo.cPerks.perks.perkmenu.PerksCommand;

import java.util.Objects;

public final class CPerks extends JavaPlugin {

    private static CPerks instance;

    public static CPerks getInstance(){
        return instance;
    }

    @Override
    public void onEnable() {

        instance = this;
        System.out.println("Plugin CPerks started.");

        ///REGISTER COMMANDS
        Objects.requireNonNull(getCommand("perks")).setExecutor(new PerksCommand(this));
        Objects.requireNonNull(getCommand("backpack")).setExecutor(new BackpackCommand(this));
        Objects.requireNonNull(getCommand("lootcrate")).setExecutor(new LootCrateCommand(this));
        Objects.requireNonNull(getCommand("flight")).setExecutor(new FlightCommand(this));

        ///REGISTER LISTENERS
        getServer().getPluginManager().registerEvents(new BlockListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new MobKillListener(), this);
        getServer().getPluginManager().registerEvents(new EnchantListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);

        ///ADD LOADING OF USER EDITABLE CONFIG HERE LATER
    }

    @Override
    public void onDisable() {
        System.out.println("Plugin CPerks stopped.");
    }
}
