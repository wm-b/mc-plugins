package com.inceris.lockout.listeners;

import java.util.List;

import org.bukkit.entity.HumanEntity;
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

		for (HumanEntity h : e.getContents().getViewers()) {
			if (h instanceof Player) {
				Player p = (Player) h;
				for (GameInstance gi : pl.gameInstances) {
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
