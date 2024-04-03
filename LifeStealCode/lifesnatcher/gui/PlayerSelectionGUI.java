package me.plugin.lifesnatcher.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.List;

public class PlayerSelectionGUI implements InventoryHolder {

    private Inventory inv;

    public PlayerSelectionGUI() {
        inv = Bukkit.createInventory(this, 36, "Revive A Player"); // 54 max size
        init();

    }

    private void init() {
        ItemStack item;
        int i = 0;
        if (!(Bukkit.getBannedPlayers().isEmpty())) {
            for (OfflinePlayer offlineUser : Bukkit.getBannedPlayers()) {
                if (offlineUser.hasPlayedBefore()) {
                    String playerName = offlineUser.getName();
                    item = createItem("§6§l" + playerName, Material.PLAYER_HEAD, Collections.singletonList("§eClick To Revive This Player"));
                    inv.setItem(i, item);
                    i++;
                }

            }
        }
    }


    private ItemStack createItem(String name, Material mat, List<String> lore) {
        ItemStack item = new ItemStack(mat, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;

    }


    @Override
    public Inventory getInventory() {
        return inv;
    }
}
