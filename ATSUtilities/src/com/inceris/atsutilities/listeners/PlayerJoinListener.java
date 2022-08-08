package com.inceris.atsutilities.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.inceris.atsutilities.ATSUtilities;

public class PlayerJoinListener implements Listener {
	
	private static ATSUtilities pl = ATSUtilities.getPlugin(ATSUtilities.class);
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		
		String name = e.getPlayer().getName();
		
		pl.latestJoin = name;
		
		new BukkitRunnable(){
			@Override
			public void run() {
				
				if(pl.latestJoin.equals(name)) {
					pl.latestJoin = null;
				}
				
			}
		}.runTaskLater(pl, 1200);
		
	}
	
}
