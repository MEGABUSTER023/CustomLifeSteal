package me.plugin.lifesnatcher.commands;

import me.plugin.lifesnatcher.items.itemManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;

import java.util.Objects;

public class LifeSnatcherCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {return true; }
        Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("LFS-Help")) {
            player.sendMessage("LifeSnatcher is simple. Every time you get killed by a player you lose a heart. You can craft hearts, revive players and craft custom sword with custom crafting recipes.");
        }
        return true;
    }
}
