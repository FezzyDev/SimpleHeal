package dev.fezzy;

import java.util.Iterator;
import java.util.Map;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigHandler {

    // default config values
    private static final Map<String, String> configMessages = Map.of(
            "healMessage", "&aYou have been healed.",
            "feedMessage", "&aYou have been fed.",
            "cantHeal", "&eYou cannot heal again for another ",
            "cantFeed", "&eYou cannot satisfy your hunger again for another ",
            "healedBy", "&aYou have been healed by ",
            "youHealed", "&aYou have just healed ",
            "fedBy", "&aYou have been fed by ",
            "youFed", "&aYou have just fed ");
    private static final Map<String, String> configValues = Map.of(
            "healCooldown", "60",
            "feedCooldown", "60",
            "playersOnly", "&cThis is for Players only Console!",
            "noPermission", "&cYou don't have permission to use that command.",
            "playerNotFound", "&cPlayer was not found");

    public ConfigHandler(SimpleHeal plugin) {
        plugin.saveDefaultConfig();
        FileConfiguration config = plugin.getConfig();
        Iterator<Map.Entry<String, String>> var3 = configValues.entrySet().iterator();

        Map.Entry<String, String> configKeys;
        while(var3.hasNext()) {
            configKeys = var3.next();
            config.addDefault((String)configKeys.getKey(), config.getConfigurationSection((String)configKeys.getValue()) == null ? configValues.get(configKeys.getKey()) : configKeys.getValue());
        }

        var3 = configMessages.entrySet().iterator();

        while(var3.hasNext()) {
            configKeys = var3.next();
            config.addDefault((String)configKeys.getKey(), config.getConfigurationSection((String)configKeys.getValue()) == null ? configMessages.get(configKeys.getKey()) : configKeys.getValue());
        }

        config.options().copyDefaults(true);
        plugin.saveConfig();
    }

    public static String getValue(String path) {
        String value = SimpleHeal.getPlugin().getConfig().getString(path);
        return value == null ? "&cThere is an error with your config." : value;
    }
}