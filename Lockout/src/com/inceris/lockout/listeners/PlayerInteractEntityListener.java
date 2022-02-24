package com.inceris.lockout.listeners;

import java.util.List;

import org.bukkit.entity.Boat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import com.inceris.lockout.Lockout;
import com.inceris.lockout.util.GameInstance;
import com.inceris.lockout.util.Objective;

public class PlayerInteractEntityListener implements Listener {

	public static Lockout pl = Lockout.getPlugin(Lockout.class);

	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {

		Player p = e.getPlayer();
		for (GameInstance gi : pl.gameInstances) {
			if (gi.isActive() && gi.getPlayerScores().keySet().contains(p)) {
				List<Objective> objectives = gi.getObjectives();
				Entity entity = e.getRightClicked();
				
				if (objectives.contains(Objective.sailBoat) && entity instanceof Boat) {
					Objective.complete(gi, Objective.sailBoat, p);
					
				} else if (objectives.contains(Objective.rideMinecart) && entity instanceof Minecart) {
					Objective.complete(gi, Objective.rideMinecart, p);
					
				}
			}
		}

	}

}
