package com.inceris.runecraftitems.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerFishEvent.State;

import com.inceris.runecraftitems.RuneCraftItems;
import com.inceris.runecraftitems.util.Items;
import com.inceris.runecraftitems.util.Util;

public class PlayerFishListener implements Listener {
	
	private static RuneCraftItems rci = RuneCraftItems.getPlugin(RuneCraftItems.class);
	
	@EventHandler
	public void onPlayerFish(PlayerFishEvent e) {
		if (rci.getConfig().getBoolean("stones.enabled")) {
			State state = e.getState();
			if (state.equals(State.CAUGHT_FISH)) {
				if (Util.percentChance(rci.getConfig().getDouble("stones.droprates.fish"))) {
					e.getPlayer().getInventory().addItem(Items.stoneFive);
				}
			}
		}
	}
}
