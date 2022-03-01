package com.inceris.lockout.listeners;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.inceris.lockout.Lockout;
import com.inceris.lockout.util.GameInstance;
import com.inceris.lockout.util.Objective;

public class PlayerDeathListener implements Listener {

	public static Lockout pl = Lockout.getPlugin(Lockout.class);

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {

		Player p = e.getEntity();
		GameInstance gi = GameInstance.get(p);
		if (gi != null) {
			if (gi.isActive() && gi.getPlayers().contains(p)) {
				List<Objective> objectives = gi.getObjectives();
				String message = e.getDeathMessage();
				
				if (objectives.contains(Objective.dontDie)) {
					Objective.complete(gi, Objective.dontDie, gi.getOpponents(p).get(0));
					
				} else if (objectives.contains(Objective.dontDieByBed) && message.contains("Intentional Game Design")) {
					Objective.complete(gi, Objective.dontDieByBed, gi.getOpponents(p).get(0));
					
				} else if (objectives.contains(Objective.dieToAnvil) && message.contains("was squashed by a falling anvil")) {
					Objective.complete(gi, Objective.dieToAnvil, p);
					
				} else if (objectives.contains(Objective.dieToDripstone) && message.contains("was skewered by a falling stalactite")) {
					Objective.complete(gi, Objective.dieToDripstone, p);
					
				} else if (objectives.contains(Objective.dieToTNT) && (message.contains("blown up") || message.contains("blew up"))) {
					Objective.complete(gi, Objective.dieToTNT, p);
					
				} else if (objectives.contains(Objective.goOutWithABang) && message.contains("went off with a bang")) {
					Objective.complete(gi, Objective.goOutWithABang, p);
					
				}
			}
		}
	}

}
