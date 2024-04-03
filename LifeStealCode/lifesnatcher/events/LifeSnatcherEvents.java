package me.plugin.lifesnatcher.events;

import me.plugin.lifesnatcher.Keys;
import me.plugin.lifesnatcher.gui.PlayerSelectionGUI;
import me.plugin.lifesnatcher.items.itemManager;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.Objects;

public class LifeSnatcherEvents implements Listener {

    @EventHandler
    public void onBowShot(EntityShootBowEvent e) {
        if(e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            PersistentDataContainer OffPlayerContainer = p.getInventory().getItemInOffHand().getItemMeta().getPersistentDataContainer();
            PersistentDataContainer PlayerContainer = p.getItemInHand().getItemMeta().getPersistentDataContainer();
            PersistentDataContainer TntItemContainer = Objects.requireNonNull(itemManager.Tnt_Bow.getItemMeta()).getPersistentDataContainer();
            PersistentDataContainer TnTAmmo = Objects.requireNonNull(itemManager.Tnt_Ammo.getItemMeta()).getPersistentDataContainer();

            if (TnTAmmo.has(Keys.Tnt_Ammo) && OffPlayerContainer.has(Keys.Tnt_Ammo)) {
                if (TntItemContainer.has(Keys.Tnt_Bow) && PlayerContainer.has(Keys.Tnt_Bow)) {
                    Entity proj = e.getProjectile();
                    TNTPrimed tnt = proj.getWorld().spawn(e.getProjectile().getLocation(), TNTPrimed.class);
                    if(proj instanceof Arrow) {
                        tnt.setTicksLived(5);
                        tnt.setFuseTicks(20);

                        tnt.setVelocity(proj.getVelocity());
                        e.setProjectile(tnt);
                        if (p.getInventory().getItemInOffHand().getAmount() == 1) {
                            p.getInventory().setItemInOffHand(new ItemStack(Material.AIR));
                    } else {
                        p.getInventory().getItemInOffHand().setAmount(p.getInventory().getItemInOffHand().getAmount() - 1);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getItemInHand().getItemMeta() != null) {
            PersistentDataContainer PlayerContainer = player.getItemInHand().getItemMeta().getPersistentDataContainer();
            PersistentDataContainer ReviveTokenItemContainer = Objects.requireNonNull(itemManager.Revive_Token.getItemMeta()).getPersistentDataContainer();
            PersistentDataContainer HeartsItemContainer = Objects.requireNonNull(itemManager.Hearts.getItemMeta()).getPersistentDataContainer();


            if (ReviveTokenItemContainer.has(Keys.CustomRevive) && PlayerContainer.has(Keys.CustomRevive)) {
                if (player.getInventory().getItemInHand().getAmount() == 1) {
                    player.getInventory().setItemInHand(new ItemStack(Material.AIR));
                } else {
                    player.getInventory().getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount()-1);
                }
                PlayerSelectionGUI playergui = new PlayerSelectionGUI();
                player.openInventory(playergui.getInventory());
                }

            if (HeartsItemContainer.has(Keys.CustomHeart) && PlayerContainer.has(Keys.CustomHeart)) {
                player.sendMessage(ChatColor.GOLD.toString() + ChatColor.BOLD + "(!) You Have Used A Heart And Received +1 Hearts");
                double playersmaxHealth = Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue();
                player.setMaxHealth(playersmaxHealth + 2);
                if (player.getInventory().getItemInHand().getAmount() == 1) {
                    player.getInventory().setItemInHand(new ItemStack(Material.AIR));
                } else {
                    player.getInventory().getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount()-1);
                }
            }
        }

        }




    @EventHandler
    static void OnDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        PersistentDataContainer PlayerContainer = player.getKiller().getItemInHand().getItemMeta().getPersistentDataContainer();
        PersistentDataContainer LifeSnatcherItemContainer = Objects.requireNonNull(itemManager.LifeSnatcher.getItemMeta()).getPersistentDataContainer();

        if (player.isDead()) {
            if (player.getKiller() != null) {
                if (LifeSnatcherItemContainer.has(Keys.CustomSword) && PlayerContainer.has(Keys.CustomSword)) {
                    player.getKiller().sendMessage(ChatColor.RED + "(!) You Killed " + player.getName() + " And Got +1 Hearts");
                    double KillermaxHealth = Objects.requireNonNull(player.getKiller().getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue();
                    player.getKiller().setMaxHealth(KillermaxHealth + 2);
                    double maxHealth = Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue();
                    if (maxHealth <= 2.0) {
                        Bukkit.getServer().broadcastMessage(ChatColor.RED + player.getName() + " Was Killed By " + player.getKiller().getName() + " And Lost All Of His Hearts");
                        player.getServer().getBanList(BanList.Type.NAME).addBan(player.getName(), " Lost ALl Of Your Hearts", null, null);
                        player.kickPlayer("Lost ALl Of Your Hearts");
                    } else {
                        player.setMaxHealth(maxHealth - 2);
                        player.sendMessage(ChatColor.RED + "(!) You Died By " + player.getKiller().getName() + " And Lost -1 Hearts");

                        }

                } else {
                    player.getKiller().sendMessage("(!) You Can Only Get Lives By Using The LifeSnatcher Weapon");
                }
            }

            }
        }
    }

