package com.inceris.lockout.listeners;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityResurrectEvent;

import com.inceris.lockout.Lockout;
import com.inceris.lockout.util.GameInstance;
import com.inceris.lockout.util.Objective;

public class EntityResurrectListener implements Listener {

	public static Lockout pl = Lockout.getPlugin(Lockout.class);

	@EventHandler
	public void onEntityResurrect(EntityResurrectEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			GameInstance gi = GameInstance.get(p);
			if (gi != null) {
				if (gi.isActive() && gi.getPlayers().contains(p)) {
					List<Objective> objectives = gi.getObjectives();

					if (objectives.contains(Objective.getResurrected)) {
						Objective.complete(gi, Objective.getResurrected, p);

					}
				}
			}
		}
	}
}