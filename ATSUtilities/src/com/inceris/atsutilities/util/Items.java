package com.inceris.atsutilities.util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Items {

    public static final ItemStack prestigeRankupToken = prestigeRankupToken();
    public static final ItemStack laserSpyglass = constructItem(Material.SPYGLASS, "&c&lLaser Spyglass");
    public static final ItemStack deathRay = constructItem(Material.SPYGLASS, "&4&lDeath Ray");

    public static boolean checkItem(ItemStack item, ItemStack against) {
        ItemMeta meta = item.getItemMeta();
        ItemMeta againstMeta = against.getItemMeta();
        if (meta == null || againstMeta == null) return false;
        return item.getEnchantmentLevel(Enchantment.ARROW_INFINITE) == 10
                && meta.getDisplayName().equals(againstMeta.getDisplayName());
    }

    public static ItemStack constructItem(Material material, String name) {
        ItemStack item = new ItemStack(material);
        item.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 10);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getItem(String name) {
        return switch (name.toLowerCase()) {
            case "prestigerankuptoken" -> prestigeRankupToken;
            case "laserspyglass" -> laserSpyglass;
            case "deathray" -> deathRay;
            default -> null;
        };
    }

    public static ItemStack prestigeRankupToken() {
        ItemStack item = constructItem(Material.PAPER, "&6&lPrestige Rank Token");
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<String>();
        lore.add("");
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Right click to increase your"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7prestige rank by 1 level!"));
        assert meta != null;
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

}
