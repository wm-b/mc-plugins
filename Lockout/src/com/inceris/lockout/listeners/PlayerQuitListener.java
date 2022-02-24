package com.inceris.lockout.listeners;

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
			for (Player p : gi.getPlayerScores().keySet()) {
				if (!p.equals(quitter)) {
					Util.stopGameWithWinner(p);
					break;
				}
			}
		}
		
	}
	
}
