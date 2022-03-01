package com.inceris.lockout.listeners;

import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Piglin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PiglinBarterEvent;

import com.inceris.lockout.Lockout;
import com.inceris.lockout.util.GameInstance;
import com.inceris.lockout.util.Objective;

public class PiglinBarterListener implements Listener {

	public static Lockout pl = Lockout.getPlugin(Lockout.class);

	@EventHandler
	public void onPiglinBarter(PiglinBarterEvent e) {
		Piglin piglin = e.getEntity();
		List<Entity> nearbyEntities = piglin.getNearbyEntities(5, 5, 5);
		for (Entity ne : nearbyEntities) {
			if (ne instanceof Player) {
				Player p = (Player) ne;
				
				for (GameInstance gi : pl.getGameInstances()) {
					if (gi.isActive() && gi.getPlayers().contains(p)) {
						List<Objective> objectives = gi.getObjectives();

						if (objectives.contains(Objective.tradeWithPiglin)) {
							Objective.complete(gi, Objective.tradeWithPiglin, p);

						}
					}
				}
			}
		}
	}
}