package dev.fezzy;

import dev.fezzy.commands.FeedCommand;
import dev.fezzy.commands.HealCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleHeal extends JavaPlugin {
    private static SimpleHeal instance;

    public static SimpleHeal getPlugin() {
        return instance;
    }

    public void onEnable() {
        instance = this;
        new ConfigHandler(this);
        this.getCommand("Heal").setExecutor(new HealCommand());
        this.getCommand("Feed").setExecutor(new FeedCommand());
    }

    public void onDisable() {
    }
}