package com.inceris.lockout.listeners;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

import com.inceris.lockout.Lockout;
import com.inceris.lockout.util.GameInstance;
import com.inceris.lockout.util.Objective;

public class PlayerBedEnterListener implements Listener {

	public static Lockout pl = Lockout.getPlugin(Lockout.class);

	@EventHandler
	public void onPlayerBedEnter(PlayerBedEnterEvent e) {
		Player p = e.getPlayer();
		for (GameInstance gi : pl.gameInstances) {
			if (gi.isActive() && gi.getPlayers().contains(p)) {
				List<Objective> objectives = gi.getObjectives();
				
				if (objectives.contains(Objective.goToSleep)) {
					Objective.complete(gi, Objective.goToSleep, p);
					
				}
				
				if (objectives.contains(Objective.sleepAlone) && p.getWorld().getPlayers().size() == 1) {
					Objective.complete(gi, Objective.sleepAlone, p);
					
				}
			}
		}
	}
}