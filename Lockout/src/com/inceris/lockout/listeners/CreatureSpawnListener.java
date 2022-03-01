package com.inceris.lockout.listeners;

import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import com.inceris.lockout.Lockout;
import com.inceris.lockout.util.GameInstance;
import com.inceris.lockout.util.Objective;

public class CreatureSpawnListener implements Listener {

	public static Lockout pl = Lockout.getPlugin(Lockout.class);

	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent e) {
		LivingEntity entity = e.getEntity();
		List<Entity> nearbyEntities = entity.getNearbyEntities(5, 5, 5);
		for (Entity ne : nearbyEntities) {
			if (ne instanceof Player) {
				Player p = (Player) ne;
				
				for (GameInstance gi : pl.getGameInstances()) {
					if (gi.isActive() && gi.getPlayers().contains(p)) {
						List<Objective> objectives = gi.getObjectives();

						if (objectives.contains(Objective.createIronGolem)
								&& e.getSpawnReason().equals(SpawnReason.BUILD_IRONGOLEM)) {
							Objective.complete(gi, Objective.createIronGolem, p);

						} else if (objectives.contains(Objective.buildSnowman)
								&& e.getSpawnReason().equals(SpawnReason.BUILD_SNOWMAN)) {
							Objective.complete(gi, Objective.buildSnowman, p);
							
						}
					}
				}
			}
		}
	}
}