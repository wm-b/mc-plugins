package com.inceris.runecraftitems.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.inceris.runecraftitems.RCIPlayer;

public class PlayerQuitListener implements Listener {

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		RCIPlayer.players.add(new RCIPlayer(e.getPlayer()));
	}
	
}
