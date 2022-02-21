package com.inceris.atsutilities.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.inceris.atsutilities.ATSUtilities;

public class PlayerCommandPreprocessListener implements Listener {
	
	private static ATSUtilities atsu = ATSUtilities.getPlugin(ATSUtilities.class);
	
	@EventHandler
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e) {
		
		if (atsu.denyTallGrass) {
			if (e.getMessage() != null) {
				if (e.getMessage().toLowerCase().contains("tall_grass")
						|| e.getMessage().toLowerCase().contains("tallgrass")) {
					e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',
							"&4&lThou shalt not placeth the grass of sin."));
					e.setCancelled(true);
				}
			}
		}
	}
	
}
