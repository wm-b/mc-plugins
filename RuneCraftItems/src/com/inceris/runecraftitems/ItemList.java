package com.inceris.runecraftitems;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

public class ItemList {
	
	private static RuneCraftItems rci = RuneCraftItems.getPlugin(RuneCraftItems.class);
	
	public static ItemStack superpick = superpick();
	public static ItemStack superpick() {
		ItemStack item = new ItemStack(Material.NETHERITE_PICKAXE);
		item.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 10);
//		item.addEnchantment(Enchantment.DIG_SPEED, 5);
		Damageable meta = (Damageable) item.getItemMeta();
		meta.setDamage(2031 - rci.getConfig().getInt("items.superpick.durability"));
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', rci.getConfig().getString("items.superpick.name")));
		List<String> lore = rci.getConfig().getStringList("items.superpick.lore");
		for (int i = 0; i < lore.size(); i++) {
			lore.set(i, ChatColor.translateAlternateColorCodes('&', lore.get(i)));
		}
		lore.add(0, "");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack grapplingHook = grapplingHook();
	public static ItemStack grapplingHook() {
		ItemStack item = new ItemStack(Material.FISHING_ROD);
		item.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 10);
		Damageable meta = (Damageable) item.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', rci.getConfig().getString("items.grapplinghook.name")));
		List<String> lore = rci.getConfig().getStringList("items.grapplinghook.lore");
		for (int i = 0; i < lore.size(); i++) {
			lore.set(i, ChatColor.translateAlternateColorCodes('&', lore.get(i)));
		}
		lore.add(0, "");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	
}
