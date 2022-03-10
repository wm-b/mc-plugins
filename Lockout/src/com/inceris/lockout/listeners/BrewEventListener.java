package com.inceris.lockout.listeners;

import java.util.Collection;
import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.BrewEvent;

import com.inceris.lockout.Lockout;
import com.inceris.lockout.util.GameInstance;
import com.inceris.lockout.util.Objective;

public class BrewEventListener implements Listener {

	public static Lockout pl = Lockout.getPlugin(Lockout.class);

	@EventHandler
	public void onBrewEvent(BrewEvent e) {
		Block b = e.getBlock();
		Collection<Entity> nearbyEntities = b.getWorld().getNearbyEntities(b.getLocation(), 5, 5, 5);
		for (Entity ne : nearbyEntities) {
			if (ne instanceof Player) {
				Player p = (Player) ne;
				GameInstance gi = GameInstance.get(p);
				if (gi != null) {
					if (gi.isActive() && gi.getPlayers().contains(p)) {
						List<Objective> objectives = gi.getObjectives();

						if (objectives.contains(Objective.brewPotion)) {
							Objective.complete(gi, Objective.brewPotion, p);

						}
					}
				}
			}
		}
	}
}
