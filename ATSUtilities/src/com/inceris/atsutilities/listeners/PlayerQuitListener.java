package com.inceris.atsutilities.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		
		Player p = e.getPlayer();
		
		if (!p.hasPermission("essentials.gamemode.spectator")) {
			p.setGameMode(GameMode.SURVIVAL);
		}
		
	}
	
}
