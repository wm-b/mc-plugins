package com.inceris.lockout.listeners;

import java.util.List;

import org.bukkit.entity.AbstractVillager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.VillagerAcquireTradeEvent;

import com.inceris.lockout.Lockout;
import com.inceris.lockout.util.GameInstance;
import com.inceris.lockout.util.Objective;

public class VillagerAcquireTradeListener implements Listener {

	public static Lockout pl = Lockout.getPlugin(Lockout.class);

	@EventHandler
	public void onVillagerAcquireTrade(VillagerAcquireTradeEvent e) {
		AbstractVillager villager = e.getEntity();
		List<Entity> nearbyEntities = villager.getNearbyEntities(5, 5, 5);
		for (Entity ne : nearbyEntities) {
			if (ne instanceof Player) {
				Player p = (Player) ne;
				
				for (GameInstance gi : pl.gameInstances) {
					if (gi.isActive() && gi.getPlayers().contains(p)) {
						List<Objective> objectives = gi.getObjectives();

						if (objectives.contains(Objective.teachVillagerTrade)) {
							Objective.complete(gi, Objective.teachVillagerTrade, p);

						}
					}
				}
			}
		}
	}
}