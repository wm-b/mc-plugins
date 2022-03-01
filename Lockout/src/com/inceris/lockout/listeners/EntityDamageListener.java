package com.inceris.lockout.listeners;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.inceris.lockout.Lockout;
import com.inceris.lockout.util.GameInstance;
import com.inceris.lockout.util.Objective;

public class EntityDamageListener implements Listener {

	public static Lockout pl = Lockout.getPlugin(Lockout.class);

	@EventHandler
	public void onEntityDamage(EntityDamageEvent e) {

		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			for (GameInstance gi : pl.getGameInstances()) {
				if (gi.isActive() && gi.getPlayers().contains(p)) {
					List<Objective> objectives = gi.getObjectives();
					DamageCause cause = e.getCause();

					if (objectives.contains(Objective.dontTakeDamage)) {
						Objective.complete(gi, Objective.dontTakeDamage, gi.getOpponents(p).get(0));
					}
					
					if (objectives.contains(Objective.dont5Hearts) && p.getHealth() <= 10) {
						Objective.complete(gi, Objective.dont5Hearts, gi.getOpponents(p).get(0));
					}

					if (objectives.contains(Objective.suffocate) && cause.equals(DamageCause.SUFFOCATION)) {
						Objective.complete(gi, Objective.suffocate, p);

					} else if (objectives.contains(Objective.freeze) && cause.equals(DamageCause.FREEZE)) {
						Objective.complete(gi, Objective.freeze, p);

					} else if (objectives.contains(Objective.dontFall) && cause.equals(DamageCause.FALL)) {
						Objective.complete(gi, Objective.dontFall, gi.getOpponents(p).get(0));

					} else if (objectives.contains(Objective.dontCatchFire) && p.getFireTicks() > 0) {
						Objective.complete(gi, Objective.dontCatchFire, gi.getOpponents(p).get(0));

					}
				}
			}
		}
	}
}
