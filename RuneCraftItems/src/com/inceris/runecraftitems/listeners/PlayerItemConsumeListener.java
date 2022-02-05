package com.inceris.runecraftitems.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import com.inceris.runecraftitems.util.Items;
import com.inceris.runecraftitems.util.Util;

public class PlayerItemConsumeListener implements Listener {
	
	@EventHandler
	public static void onPlayerItemConsume(PlayerItemConsumeEvent e) {
		
		ItemStack item = e.getItem();
		Player p = e.getPlayer();
		
		if (Util.checkItem(item, Items.kfc)) {
			p.setFoodLevel(p.getFoodLevel() + 6);
			p.setSaturation(p.getSaturation() + 6);
			e.setCancelled(true);
		}
		
	}
	
}
