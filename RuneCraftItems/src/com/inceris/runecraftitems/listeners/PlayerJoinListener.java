package com.inceris.runecraftitems.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.inceris.runecraftitems.util.RCIPlayer;

public class PlayerJoinListener implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		RCIPlayer.players.remove(new RCIPlayer(e.getPlayer()));
	}
	
}
