package com.inceris.lockout.listeners;

import java.util.List;

import org.bukkit.entity.Cat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTameEvent;

import com.inceris.lockout.Lockout;
import com.inceris.lockout.util.GameInstance;
import com.inceris.lockout.util.Objective;

public class EntityTameListener implements Listener {

	public static Lockout pl = Lockout.getPlugin(Lockout.class);

	@EventHandler
	public void onEntityTame(EntityTameEvent e) {
		if (e.getOwner() instanceof Player) {
			Player p = (Player) e.getOwner();
			GameInstance gi = GameInstance.get(p);
			if (gi != null) {
				if (gi.isActive() && gi.getPlayers().contains(p)) {
					List<Objective> objectives = gi.getObjectives();
					Entity entity = e.getEntity();
					
					if (objectives.contains(Objective.tameHorse) && entity instanceof Horse) {
						Objective.complete(gi, Objective.tameHorse, p);
						
					} else if (objectives.contains(Objective.tameWolf) && entity instanceof Wolf) {
						Objective.complete(gi, Objective.tameWolf, p);
						
					} else if (objectives.contains(Objective.tameCat) && entity instanceof Cat) {
						Objective.complete(gi, Objective.tameCat, p);
						
					}
				}
			}
		}
	}
}