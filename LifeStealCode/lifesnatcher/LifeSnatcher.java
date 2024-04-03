package me.plugin.lifesnatcher;
import me.plugin.lifesnatcher.events.OpPickaxeEvents;
import me.plugin.lifesnatcher.events.inventoryEvents;
import me.plugin.lifesnatcher.items.itemManager;
import me.plugin.lifesnatcher.commands.LifeSnatcherCommands;
import me.plugin.lifesnatcher.events.LifeSnatcherEvents;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class LifeSnatcher extends JavaPlugin {

    @Override
    public void onEnable() {

        LifeSnatcherCommands commands = new LifeSnatcherCommands();

        itemManager.init();
        getServer().getPluginManager().registerEvents(new LifeSnatcherEvents(), this);
        getServer().getPluginManager().registerEvents(new inventoryEvents(), this);
        getServer().getPluginManager().registerEvents(new OpPickaxeEvents(), this);


        Objects.requireNonNull(getCommand("LFS-Help")).setExecutor(commands);
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[LifeSnatcher]: is enabled");

    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[LifeSnatcher]: is disabled");
    }

    public static LifeSnatcher getInstance() {
        return JavaPlugin.getPlugin(LifeSnatcher.class);
    }
}
