package com.inceris.atsutilities.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Items {
	
	public static boolean checkItem(ItemStack item, ItemStack against) {
		if (item.getEnchantmentLevel(Enchantment.ARROW_INFINITE) == 10
				&& item.getItemMeta().getDisplayName().equals(against.getItemMeta().getDisplayName()))
			return true;
		return false;
	}
	
	public static ItemStack constructItem(Material material, String name) {
		ItemStack item = new ItemStack(material);
		item.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 10);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack getItem(String name) {
		try {
			ItemStack item = null;
			switch (name.toLowerCase()) {
			case "prestigerankuptoken":
				item = prestigeRankupToken;
				break;
			}
			
			return item;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static ItemStack prestigeRankupToken = prestigeRankupToken();
	public static ItemStack prestigeRankupToken() {
		ItemStack item = constructItem(Material.PAPER, "&6&lPrestige Rank Token");
		ItemMeta meta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.add("");
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7Right click to increase your"));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7prestige rank by 1 level!"));
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
				
}
