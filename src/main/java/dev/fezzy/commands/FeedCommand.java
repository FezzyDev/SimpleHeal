package dev.fezzy.commands;

import dev.fezzy.ConfigHandler;
import dev.fezzy.Constants;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.UUID;

public class FeedCommand implements CommandExecutor {
    private final HashMap<UUID, Long> cooldown = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigHandler.getValue("playersOnly")));
            return true;
        }
        Player player = (Player) sender;
        if (!player.hasPermission(Constants.feedPermission)) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigHandler.getValue("noPermission")));
            return true;
        }

        if (args.length == 0) {

            // if player has never ran command
            if (!this.cooldown.containsKey(player.getUniqueId())){
                this.cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                player.setFoodLevel(20);
                player.setSaturation(20);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigHandler.getValue("feedMessage")));
            } else {

                // difference in ms
                long timeElapsed = System.currentTimeMillis() - cooldown.get(player.getUniqueId());
                long newElapsed = timeElapsed / 1000;
                double sec = Math.floor(newElapsed);

                if (newElapsed > Integer.parseInt(ConfigHandler.getValue("feedCooldown"))){
                    this.cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                    player.setFoodLevel(20);
                    player.setSaturation(20);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigHandler.getValue("feedMessage")));
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            ConfigHandler.getValue("cantFeed") + "&f " + (Integer.parseInt(ConfigHandler.getValue("feedCooldown")) - (int) sec) + "s"));
                }
            }
        } else {

            //feed Others
            if(!player.hasPermission(Constants.feedOthersPermission)){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigHandler.getValue("noPermission")));
                return true;
            }
            if (!this.cooldown.containsKey(player.getUniqueId())) {
                this.cooldown.put(player.getUniqueId(), System.currentTimeMillis());

                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigHandler.getValue("playerNotFound")));
                    return true;
                }

                target.setFoodLevel(20);
                target.setSaturation(20);
                target.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigHandler.getValue("fedBy")) + ChatColor.YELLOW + " " + player.getName());
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigHandler.getValue("youFed")) + ChatColor.YELLOW + " " + target.getName());
            } else {
                long timeElapsed = System.currentTimeMillis() - cooldown.get(player.getUniqueId());
                long newElapsed = timeElapsed / 1000;
                double sec = Math.floor(newElapsed);

                if (newElapsed > Integer.parseInt(ConfigHandler.getValue("feedCooldown"))) {
                    this.cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigHandler.getValue("playerNotFound")));
                        return true;
                    }

                    target.setFoodLevel(20);
                    target.setSaturation(20);
                    target.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigHandler.getValue("fedBy")) + ChatColor.YELLOW + " " + player.getName());
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigHandler.getValue("youFed")) + ChatColor.YELLOW + " " + target.getName());
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            ConfigHandler.getValue("cantFeed") + "&f " + (Integer.parseInt(ConfigHandler.getValue("feedCooldown")) - (int) sec) + "s"));
                }
            }
        }
        return true;
    }
}