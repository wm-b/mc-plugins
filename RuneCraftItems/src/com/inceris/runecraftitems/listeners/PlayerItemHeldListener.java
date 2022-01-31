package com.inceris.runecraftitems.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.inceris.runecraftitems.util.Items;
import com.inceris.runecraftitems.util.Util;

public class PlayerItemHeldListener implements Listener {

	@EventHandler
	public void onPlayerItemHeld(PlayerItemHeldEvent e) {

		Player p = e.getPlayer();
		ItemStack itemInNewSlot = p.getInventory().getItem(e.getNewSlot());
		ItemStack itemInPreviousSlot = p.getInventory().getItem(e.getPreviousSlot());
		if (itemInNewSlot != null) {
			
			if (Util.checkItem(itemInNewSlot, Items.pulsingHeart)) {
				Util.sendCommand("trailsid HEART " + p.getName());
				
			} else if (Util.checkItem(itemInNewSlot, Items.stargazer)) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 60000000, 0));
			}
			
		} 
		
		if (itemInPreviousSlot != null) {
			
			if (Util.checkItem(itemInPreviousSlot, Items.pulsingHeart)) {
				Util.sendCommand("trailsid NONE " + p.getName());
				
			} else if (Util.checkItem(itemInPreviousSlot, Items.stargazer)) {
				p.removePotionEffect(PotionEffectType.GLOWING);
			}
		}
	}

}
