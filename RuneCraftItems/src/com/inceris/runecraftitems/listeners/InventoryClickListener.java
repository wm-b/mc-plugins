package com.inceris.runecraftitems.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.inceris.runecraftitems.RuneCraftItems;

public class InventoryClickListener implements Listener {
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {

		if (RuneCraftItems.debug) Bukkit.getLogger().info("Detected inventory click!");

		if (!e.isCancelled()) {
			HumanEntity human = e.getWhoClicked();

			if (human instanceof Player && e.getSlotType().equals(SlotType.RESULT)) {
				Inventory inv = e.getInventory();
				Player p = (Player) human;

				if (inv instanceof AnvilInventory) {
					if (RuneCraftItems.debug) Bukkit.getLogger().info("Detected anvil inventory click!");
					AnvilInventory anvil = (AnvilInventory) inv;
					ItemStack[] items = anvil.getContents();
					
					for (ItemStack i : items) {
						if (i.getEnchantmentLevel(Enchantment.ARROW_INFINITE) == 10) {
							if (RuneCraftItems.debug) Bukkit.getLogger().info("Detected RCItem click!");
							e.setCancelled(true);
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&6R&5C&9I&8] &cYou can't alter this item!"));
						}
					}
				}
			}
		}
	}
}
