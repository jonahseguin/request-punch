package com.jonahseguin.punch;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 * Created by Jonah on 5/5/2018.
 * Project: Punch
 *
 * @ 1:25 PM
 */
public class PunchCommand implements CommandExecutor {

    private final Punch instance;

    private final String senderMessage;
    private final String punchedMessage;
    private final double power;
    private final double add;
    private final double addY;

    public PunchCommand(Punch instance) {
        this.instance = instance;
        this.senderMessage = instance.getConfig().getString("senderMessage");
        this.punchedMessage = instance.getConfig().getString("punchedMessage");
        this.power = instance.getConfig().getDouble("power");
        this.add = instance.getConfig().getDouble("add");
        this.addY = instance.getConfig().getDouble("addY");
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("punch")) {
            if (sender.hasPermission("punch.use")) {
                if (args.length > 0) {
                    Player player = Bukkit.getPlayer(args[0]);
                    if (player != null && player.isOnline()) {

                        player.setVelocity(player.getLocation().getDirection().add(new Vector(add, addY, add)).multiply(power));
                        

                        if (punchedMessage != null && punchedMessage.length() > 0) {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', String.format(punchedMessage, sender.getName())));
                        }
                        if (senderMessage != null && senderMessage.length() > 0) {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', String.format(senderMessage, player.getName())));
                        }
                    }
                    else {
                        sender.sendMessage(ChatColor.RED + "Player '" + args[0] + "' not found.");
                    }
                }
                else {
                    sender.sendMessage(ChatColor.RED + "Usage: /punch <player>");
                }
            }
            else {
                sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            }
            return true;
        }
        return false;
    }
}
