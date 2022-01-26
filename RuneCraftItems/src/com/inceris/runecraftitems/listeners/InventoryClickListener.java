package com.inceris.runecraftitems.listeners;

import java.util.concurrent.ThreadLocalRandom;

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

import com.inceris.runecraftitems.Items;
import com.inceris.runecraftitems.RuneCraftItems;
import com.inceris.runecraftitems.Util;

public class InventoryClickListener implements Listener {
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {

		if (RuneCraftItems.debug) Bukkit.getLogger().info("Detected inventory click!");

		if (!e.isCancelled()) {
			HumanEntity human = e.getWhoClicked();

			if (human instanceof Player) {
				Player p = (Player) human;
				
				if (e.getSlotType().equals(SlotType.RESULT)) {
					Inventory inv = e.getInventory();

					if (inv instanceof AnvilInventory) {
						if (RuneCraftItems.debug)
							Bukkit.getLogger().info("Detected anvil/grindstone inventory click!");
						AnvilInventory anvil = (AnvilInventory) inv;
						ItemStack[] items = anvil.getContents();

						for (ItemStack i : items) {
							if (i.getEnchantmentLevel(Enchantment.ARROW_INFINITE) == 10) {
								if (RuneCraftItems.debug)
									Bukkit.getLogger().info("Detected RCItem click!");
								e.setCancelled(true);
								p.sendMessage(ChatColor.translateAlternateColorCodes('&',
										"&8[&6R&5C&9I&8] &cYou can't alter this item!"));
							}
						}
					}
				} else if (e.getSlotType().equals(SlotType.ARMOR)) {
					ItemStack currentItem = e.getCurrentItem();
					ItemStack cursorItem = e.getCursor();
					if (cursorItem.getEnchantmentLevel(Enchantment.ARROW_INFINITE) == 10 && cursorItem.getItemMeta()
							.getDisplayName().equals(Items.disguiseCap.getItemMeta().getDisplayName())) {
						String randomPeacefulMob = null;
						switch (ThreadLocalRandom.current().nextInt(1, 6)) {
						case 1:
							randomPeacefulMob = "cow";
							break;
						case 2:
							randomPeacefulMob = "sheep";
							break;
						case 3:
							randomPeacefulMob = "pig";
							break;
						case 4:
							randomPeacefulMob = "rabbit";
							break;
						case 5:
							randomPeacefulMob = "chicken";
							break;
						}
						Util.SendCommand("disguiseplayer " + p.getName() + " " + randomPeacefulMob);
					} else if (currentItem.getEnchantmentLevel(Enchantment.ARROW_INFINITE) == 10 && currentItem.getItemMeta()
							.getDisplayName().equals(Items.disguiseCap.getItemMeta().getDisplayName())) {
						Util.SendCommand("undisguiseplayer " + p.getName());
					}
				}
			}
		}
	}
}
