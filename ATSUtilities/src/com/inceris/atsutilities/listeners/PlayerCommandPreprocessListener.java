package com.inceris.atsutilities.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.inceris.atsutilities.ATSUtilities;
import com.inceris.atsutilities.util.Util;

public class PlayerCommandPreprocessListener implements Listener {

	private static final ATSUtilities pl = ATSUtilities.getPlugin(ATSUtilities.class);

	@EventHandler
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e) {

		String message = e.getMessage().toLowerCase();

		if (pl.denyTallGrass && (message.contains("tall_grass") || message.contains("tallgrass"))) {
			e.getPlayer().sendMessage(
					ChatColor.translateAlternateColorCodes('&', "&4&lThou shalt not placeth the grass of sin."));
			e.setCancelled(true);
		}
		
		if (message.equalsIgnoreCase("/towny")) {
			
			new BukkitRunnable(){

				@Override
				public void run() {

					Player p = e.getPlayer();
					p.sendMessage("");
					p.sendMessage(Util.colours("&8[&4Server&8] &fIf you're trying to switch servers, use &b/servers&f!"));
					p.sendMessage("");
					
				}
			}.runTaskLater(pl, 1);
		}
	}
}
