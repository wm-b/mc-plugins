package com.inceris.runecraftitems.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.inceris.runecraftitems.RuneCraftItems;
import com.inceris.runecraftitems.util.Items;
import com.inceris.runecraftitems.util.RCIPlayer;
import com.inceris.runecraftitems.util.Util;

public class PlayerMoveListener implements Listener {

	private static RuneCraftItems rci = RuneCraftItems.getPlugin(RuneCraftItems.class);

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onJump(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (p.getVelocity().getY() >= 0 && !p.isOnGround() && !p.getAllowFlight()) {
			RCIPlayer rcip = RCIPlayer.getRCIPlayer(p);
			if (rci.getConfig().getBoolean("stones.enabled")) {
				if (!rcip.jumpStoneChanceOnCooldown) {
					if (Util.percentChance(rci.getConfig().getDouble("stones.droprates.jump"))) {
						p.getInventory().addItem(Items.stoneFour);

						rcip.jumpStoneChanceOnCooldown = true;
						Bukkit.getScheduler().runTaskLater(rci, new Runnable() {
							@Override
							public void run() {
								rcip.jumpStoneChanceOnCooldown = false;
							}
						}, 10);
					}
				}
			}
		}
	}
}
