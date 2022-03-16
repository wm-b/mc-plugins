package com.inceris.runecraftitems.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

import com.inceris.runecraftitems.RuneCraftItems;
import com.inceris.runecraftitems.util.Items;
import com.inceris.runecraftitems.util.Util;

public class CraftItemListener implements Listener {
	
	private static RuneCraftItems rci = RuneCraftItems.getPlugin(RuneCraftItems.class);

	@EventHandler
	public void onCraftItem(CraftItemEvent e) {

		if (rci.getConfig().getBoolean("stones.enabled")) {
			if (Util.percentChance(rci.getConfig().getDouble("stones.droprates.craft"))) {
				e.getWhoClicked().getInventory().addItem(Items.getItem("stoneSeven"));
			}
		}
	}
	
}
