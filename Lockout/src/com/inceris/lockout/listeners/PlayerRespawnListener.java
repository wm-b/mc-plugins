package com.inceris.lockout.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.inceris.lockout.util.GameInstance;

public class PlayerRespawnListener implements Listener {

	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		if (GameInstance.get(p) != null) {
			GameInstance gi = GameInstance.get(p);
			if (gi.isActive() && gi.getPlayerScores().keySet().contains(p)) {
				for (Player player : gi.getPlayerScores().keySet()) {
					if (!player.equals(p)) {
						p.getInventory().addItem(gi.compass(player));
					}
				}
			}
		}
	}

}
