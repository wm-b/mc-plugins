package com.inceris.lockout.listeners;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerFishEvent.State;

import com.inceris.lockout.Lockout;
import com.inceris.lockout.util.GameInstance;
import com.inceris.lockout.util.Objective;

public class PlayerFishListener implements Listener {

	public static Lockout pl = Lockout.getPlugin(Lockout.class);

	@EventHandler
	public void onPlayerFish(PlayerFishEvent e) {
		Player p = e.getPlayer();
		for (GameInstance gi : pl.gameInstances) {
			if (gi.isActive() && gi.getPlayerScores().keySet().contains(p)) {

				List<Objective> objectives = gi.getObjectives();
				State state = e.getState();

				if (objectives.contains(Objective.catchFish) && state.equals(State.CAUGHT_FISH)) {
					Objective.complete(gi, Objective.catchFish, p);
					
				}

				if (e.getCaught() != null) {
					if (e.getCaught() instanceof Item) {
						if (objectives.contains(Objective.catchFish)
								&& ((Item) e.getCaught()).getItemStack().getType().equals(Material.ENCHANTED_BOOK)) {
							Objective.complete(gi, Objective.catchFish, p);
							
						}
					}
				}
			}
		}
	}
}