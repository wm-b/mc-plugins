package com.inceris.lockout.listeners;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.inceris.lockout.util.GameInstance;
import com.inceris.lockout.util.Util;

public class PlayerQuitListener implements Listener {
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		
		Player quitter = e.getPlayer();
		GameInstance gi = GameInstance.get(quitter);
		
		if (gi != null) {
			for (List<Player> players : gi.getTeams()) {
				if (!players.contains(quitter)) {
					Util.stopGameWithWinner(players);
					break;
				}
			}
		}
		
	}
	
}
