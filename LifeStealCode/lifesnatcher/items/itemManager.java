package me.plugin.lifesnatcher.items;

import me.plugin.lifesnatcher.Keys;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public class itemManager {
    public static ItemStack LifeSnatcher;

    public static ItemStack Hearts;

    public static ItemStack Revive_Token;

    public static ItemStack Tnt_Bow;

    public static ItemStack Tnt_Ammo;

    public static ItemStack Op_Pickaxe;


    public static void init() {
        createLifeSnatcher();
        createHearts();
        createReviveToken();
        createTNTbow();
        createTNTAmmo();
        createOpPickaxe();
    }

    public static void createLifeSnatcher() {
        ItemStack Sword = new ItemStack(Material.NETHERITE_AXE, 1);
        ItemMeta meta = Sword.getItemMeta();
        Objects.requireNonNull(meta).setUnbreakable(true);
        meta.addEnchant(Enchantment.DAMAGE_ALL, 5, false);
        meta.setDisplayName("§l§cLife Snatcher");
        List<String> lore = new ArrayList<>();
        lore.add("§eA Weapon So Powerfull It Can Take Peoples Lives");
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);

        meta.getPersistentDataContainer().set(Keys.CustomSword, PersistentDataType.BOOLEAN, true);

        Sword.setItemMeta(meta);
        LifeSnatcher = Sword;


        ShapedRecipe sr1 = new ShapedRecipe(NamespacedKey.minecraft("lifesnatcher"), Sword);
        sr1.shape("RNN",
                  "DSN",
                  "DSR");
        sr1.setIngredient('R', Material.REDSTONE_BLOCK);
        sr1.setIngredient('N', Material.NETHERITE_INGOT);
        sr1.setIngredient('S', Material.STICK);
        sr1.setIngredient('D', Material.DIAMOND_BLOCK);
        Bukkit.getServer().addRecipe(sr1);
    }
    public static void createHearts() {
        ItemStack CustomHearts = new ItemStack(Material.RED_DYE, 1);
        ItemMeta meta = CustomHearts.getItemMeta();
        Objects.requireNonNull(meta).setDisplayName(ChatColor.RED + "Heart");

        meta.getPersistentDataContainer().set(Keys.CustomHeart, PersistentDataType.BOOLEAN, true);

        CustomHearts.setItemMeta(meta);

        Hearts = CustomHearts;

        ShapedRecipe sr2 = new ShapedRecipe(NamespacedKey.minecraft("hearts"), CustomHearts);
        sr2.shape("OGO",
                  "NSN",
                  "RGR");
        sr2.setIngredient('R', Material.REDSTONE_BLOCK);
        sr2.setIngredient('N', Material.NETHERITE_SCRAP);
        sr2.setIngredient('S', Material.FERMENTED_SPIDER_EYE);
        sr2.setIngredient('G', Material.GOLD_BLOCK);
        sr2.setIngredient('O', Material.OBSIDIAN);
        Bukkit.getServer().addRecipe(sr2);

    }
    public static void createReviveToken() {
        ItemStack customrevive = new ItemStack(Material.TOTEM_OF_UNDYING);
        ItemMeta meta = customrevive.getItemMeta();

        Objects.requireNonNull(meta).setDisplayName(ChatColor.GOLD + "Revive Totem");

        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_PURPLE + "Revive A Player");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.getPersistentDataContainer().set(Keys.CustomRevive, PersistentDataType.BOOLEAN, true);

        customrevive.setItemMeta(meta);

        Revive_Token = customrevive;

        ShapedRecipe sr3 = new ShapedRecipe(NamespacedKey.minecraft("revivetoken"), customrevive);
        sr3.shape("RGR",
                  "DTD",
                  "RGR");
        sr3.setIngredient('D', Material.DIAMOND_BLOCK);
        sr3.setIngredient('R', Material.REDSTONE_BLOCK);
        sr3.setIngredient('T', Material.TOTEM_OF_UNDYING);
        sr3.setIngredient('G', Material.GOLD_BLOCK);
        Bukkit.getServer().addRecipe(sr3);
    }
    public static void createTNTbow() {
        ItemStack customtntbow = new ItemStack(Material.BOW);
        ItemMeta meta = customtntbow.getItemMeta();

        Objects.requireNonNull(meta).setDisplayName(ChatColor.RED.toString() + ChatColor.BOLD + "TNT Bow");

        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_PURPLE + "Shoot A TNT Arrow");
        lore.add("");
        lore.add(ChatColor.YELLOW + "(!) Requires TNT Ammo to Work");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.PIERCING, 4, true);
        meta.addEnchant(Enchantment.ARROW_DAMAGE, 5, true);
        meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
        meta.addEnchant(Enchantment.ARROW_FIRE, 1, true);
        meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
        meta.addEnchant(Enchantment.ARROW_KNOCKBACK, 2, true);

        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.getPersistentDataContainer().set(Keys.Tnt_Bow, PersistentDataType.BOOLEAN, true);

        customtntbow.setItemMeta(meta);

        Tnt_Bow = customtntbow;

        ShapedRecipe sr3 = new ShapedRecipe(NamespacedKey.minecraft("tntbow"), customtntbow);
        sr3.shape("TNT",
                "RBR",
                "TNT");
        sr3.setIngredient('B', Material.BOW);
        sr3.setIngredient('N', Material.NETHERITE_INGOT);
        sr3.setIngredient('T', Material.TNT);
        sr3.setIngredient('R', Material.REDSTONE_BLOCK);
        Bukkit.getServer().addRecipe(sr3);

    }
    public static void createTNTAmmo() {
        ItemStack customtntammo = new ItemStack(Material.TNT);
        ItemMeta meta = customtntammo.getItemMeta();

        Objects.requireNonNull(meta).setDisplayName(ChatColor.DARK_RED.toString() + ChatColor.BOLD + "TNT Ammo");

        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_PURPLE + "Ammo for TNT Bow");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1, true);

        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.getPersistentDataContainer().set(Keys.Tnt_Ammo, PersistentDataType.BOOLEAN, true);

        customtntammo.setItemMeta(meta);

        Tnt_Ammo = customtntammo;

        ShapedRecipe sr3 = new ShapedRecipe(NamespacedKey.minecraft("tntammo"), customtntammo);
        sr3.shape("TTT",
                "TTT",
                "TTT");
        sr3.setIngredient('T', Material.TNT);
        Bukkit.getServer().addRecipe(sr3);
    }

    public static void createOpPickaxe() {
        ItemStack custompickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta meta = custompickaxe.getItemMeta();

        Objects.requireNonNull(meta).setDisplayName(ChatColor.GREEN.toString() + ChatColor.BOLD + "Multi Pickaxe");

        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "A Mysterious Pickaxe");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.DIG_SPEED, 8, true);
        meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 3, true);
        meta.addEnchant(Enchantment.DURABILITY, 6, true);

        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.getPersistentDataContainer().set(Keys.Tnt_Ammo, PersistentDataType.BOOLEAN, true);

        custompickaxe.setItemMeta(meta);

        Op_Pickaxe = custompickaxe;

        ShapedRecipe sr3 = new ShapedRecipe(NamespacedKey.minecraft("oppickaxe"), custompickaxe);
        sr3.shape("DDN",
                "DSD",
                "SDD");
        sr3.setIngredient('N', Material.NETHER_STAR);
        sr3.setIngredient('S', Material.STICK);
        sr3.setIngredient('D', Material.DIAMOND_BLOCK);
        Bukkit.getServer().addRecipe(sr3);
    }
}
