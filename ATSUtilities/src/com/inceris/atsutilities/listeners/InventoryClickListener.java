package com.inceris.atsutilities.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import com.inceris.atsutilities.ATSUtilities;

public class InventoryClickListener implements Listener {

	private static final ATSUtilities atsu = ATSUtilities.getPlugin(ATSUtilities.class);

	@EventHandler
	private void onInventoryClick(InventoryClickEvent e) {

		Inventory inventory = e.getInventory();
		if (inventory instanceof AnvilInventory inv) {
			if (e.getSlotType().equals(SlotType.RESULT)) {
				Player p = (Player) inv.getViewers().get(0);

				ItemStack item = e.getCurrentItem();
				if(item==null) return;

				for (Enchantment enchantment : atsu.allowedEnchantments) {
					int level = 0;
					if (item.getType().equals(Material.ENCHANTED_BOOK)) {
						EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
						assert meta != null;
						level = meta.getStoredEnchantLevel(enchantment);
					} else {
						level = item.getEnchantmentLevel(enchantment);
					}
					if (level > enchantment.getMaxLevel()) {
						int prestigeLevelRequired = level - enchantment.getMaxLevel();
						if (!p.hasPermission("atsutilities.prestige." + prestigeLevelRequired)) {

							e.setCancelled(true);
							p.sendMessage(ChatColor.translateAlternateColorCodes('&',
									"&cYou don't have the required prestige rank!"));
							p.sendMessage(ChatColor.translateAlternateColorCodes('&',
									"&7Check &9/ranks &7for details"));
						}
					}
				}
			}
		}
	}
}
