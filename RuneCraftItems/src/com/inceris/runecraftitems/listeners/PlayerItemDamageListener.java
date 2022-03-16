package com.inceris.runecraftitems.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

import com.inceris.runecraftitems.util.Items;
import com.inceris.runecraftitems.util.Util;

public class PlayerItemDamageListener implements Listener {
	
	@EventHandler
	public void onPlayerItemDamage(PlayerItemDamageEvent e) {
		
		ItemStack item = e.getItem();
		
		if (Util.checkItem(item, Items.getItem("excalibur"))) {
			e.setCancelled(true);
		}
	}
}
