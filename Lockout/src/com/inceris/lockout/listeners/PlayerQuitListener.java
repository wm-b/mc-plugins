package com.inceris.lockout.listeners;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.inceris.lockout.Lockout;
import com.inceris.lockout.util.GameInstance;

public class PlayerQuitListener implements Listener {

	public static Lockout pl = Lockout.getPlugin(Lockout.class);
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		
		Player quitter = e.getPlayer();
		GameInstance gi = GameInstance.get(quitter);
		
		if (gi != null) {
			for (List<Player> players : gi.getTeams()) {
				if (!players.contains(quitter)) {
					gi.removePlayer(quitter);
					break;
				}
			}
		}
		
		pl.getHardModeEnabled().remove(quitter);
		
	}
	
}
