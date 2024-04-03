package me.plugin.lifesnatcher.events;

import me.plugin.lifesnatcher.gui.PlayerSelectionGUI;
import me.plugin.lifesnatcher.items.itemManager;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class inventoryEvents implements Listener {
    boolean giveitem = true;

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getClickedInventory() == null) {
            return;
        }
        if (e.getClickedInventory().getHolder() instanceof PlayerSelectionGUI) {
            e.setCancelled(true);
            if (e.isLeftClick()) {
                Player player = (Player) e.getWhoClicked();
                if (e.getCurrentItem() == null) {
                    return;
                }
                if (e.getCurrentItem().getType() == Material.PLAYER_HEAD) {
                    ItemStack clickedItem = e.getCurrentItem();
                    String name = ChatColor.stripColor(Objects.requireNonNull(clickedItem.getItemMeta()).getDisplayName());
                    OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(name);

                    BanList banList = Bukkit.getServer().getBanList(BanList.Type.NAME);
                    if (offlinePlayer != null && offlinePlayer.hasPlayedBefore()) {
                        String playerName = offlinePlayer.getName();
                        if (banList.isBanned(playerName)) {
                            giveitem = false;
                            banList.pardon(playerName);
                            player.closeInventory();
                            Bukkit.getServer().broadcastMessage(ChatColor.YELLOW.toString() + ChatColor.BOLD + player.getName() + " has revived " + playerName);
                            giveitem = true;
                        } else {
                            player.sendMessage(ChatColor.RED + "Code Error 30001");
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "Code Error 30002");
                    }
                }
            }
        }

        }
    @EventHandler
    public void invClose(InventoryCloseEvent event) {
        if (event.getInventory().getHolder() instanceof PlayerSelectionGUI) {
            if (giveitem == true) {
                Player p = (Player) event.getPlayer();
                p.getInventory().addItem(itemManager.Revive_Token);
            }

        }


    }
    }

