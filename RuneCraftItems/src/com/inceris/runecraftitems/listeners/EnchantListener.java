package com.inceris.runecraftitems.listeners;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.inventory.ItemStack;

import com.inceris.runecraftitems.ItemList;

public class EnchantListener implements Listener {

	@EventHandler
	public void onEnchantItem(EnchantItemEvent e) {

		ItemStack item = e.getItem();
		if (item.getEnchantmentLevel(Enchantment.ARROW_INFINITE) == 10 && item.getItemMeta()
				.getDisplayName().equals(ItemList.superpick.getItemMeta().getDisplayName())) {
			e.setCancelled(true);
			e.getEnchanter().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&6R&5C&9I&8] &cYou can't enchant that item!"));
		}
	}
	
}
