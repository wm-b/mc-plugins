package com.inceris.runecraftitems.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import com.inceris.runecraftitems.Items;
import com.inceris.runecraftitems.Util;

public class PlayerDropItemListener implements Listener {
	
	@EventHandler
	public void onPlayerDropItemEvent(PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		ItemStack item = e.getItemDrop().getItemStack();
		
		if (Util.checkItem(item, Items.stargazer)) {
			p.removePotionEffect(PotionEffectType.GLOWING);
		} else if (Util.checkItem(item, Items.stargazer)) {
			p.removePotionEffect(PotionEffectType.GLOWING);
		}
	}
}
