package com.inceris.atstcg;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GameInv implements Listener {

	private Inventory inv;

	public GameInv() {
		inv = Bukkit.createInventory(null, 54, "Game Board");
		initializeItems();
	}

	public void initializeItems() {
		inv.addItem(createGuiItem(Material.DIAMOND_SWORD, "Example Sword", "§aFirst line of the lore",
				"§bSecond line of the lore"));
		inv.addItem(createGuiItem(Material.IRON_HELMET, "§bExample Helmet", "§aFirst line of the lore",
				"§bSecond line of the lore"));
	}

	protected ItemStack createGuiItem(final Material material, final String name, final String... lore) {
		final ItemStack item = new ItemStack(material, 1);
		final ItemMeta meta = item.getItemMeta();

		meta.setDisplayName(name);

		meta.setLore(Arrays.asList(lore));

		item.setItemMeta(meta);

		return item;
	}

	public void openInventory(final HumanEntity h) {
		h.openInventory(inv);
	}

	@EventHandler
	public void onInventoryClick(final InventoryClickEvent e) {
		
		if (!e.getInventory().equals(inv)) {
			return;
		}

		e.setCancelled(true);

		final ItemStack clickedItem = e.getCurrentItem();

		if (clickedItem == null || clickedItem.getType().isAir())
			return;

		final Player p = (Player) e.getWhoClicked();

		p.sendMessage("You clicked at slot " + e.getRawSlot());
	}

	@EventHandler
	public void onInventoryClick(final InventoryDragEvent e) {
		if (e.getInventory().equals(inv)) {
			e.setCancelled(true);
		}
	}

}
