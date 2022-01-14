package com.inceris.runecraftitems;

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
		item.addEnchantment(Enchantment.DIG_SPEED, 5);
		Damageable meta = (Damageable) item.getItemMeta();
		meta.setDamage(2031 - rci.getConfig().getInt("items.superpick.durability"));
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', rci.getConfig().getString("items.superpick.name")));
		item.setItemMeta(meta);
		return item;
	}
	
}
