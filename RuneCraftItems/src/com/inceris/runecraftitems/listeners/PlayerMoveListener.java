package com.inceris.runecraftitems.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import com.inceris.runecraftitems.RuneCraftItems;
import com.inceris.runecraftitems.util.Items;
import com.inceris.runecraftitems.util.RCIPlayer;
import com.inceris.runecraftitems.util.Util;

public class PlayerMoveListener implements Listener {

	private static int counter = 0;
	private static RuneCraftItems rci = RuneCraftItems.getPlugin(RuneCraftItems.class);

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (rci.getConfig().getBoolean("stones.enabled")) {
			if (p.getVelocity().getY() >= 0 && !p.isOnGround() && !p.getAllowFlight()) {
				RCIPlayer rcip = RCIPlayer.getRCIPlayer(p);
				if (!rcip.jumpStoneChanceOnCooldown) {
					if (Util.percentChance(rci.getConfig().getDouble("stones.droprates.jump"))) {
						p.getInventory().addItem(Items.getItem("stoneFour"));

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

		if (p.getInventory().getItem(36) != null
				&& Util.checkItem(p.getInventory().getItem(36), Items.getItem("sandalsofchrist")) && p.isInWater()) {
			Vector v = p.getLocation().getDirection().multiply(0.2);
			v.setY(0.003);
			p.setVelocity(v);
		}

		if (counter >= 10) {
			counter = 0;
			if (p.getInventory().getItem(38) != null
					&& Util.checkItem(p.getInventory().getItem(38), Items.getItem("kappasplatebody"))
					&& p.isInWater()) {
				p.setHealth(0);
			}
		}
		counter++;
	}
}
