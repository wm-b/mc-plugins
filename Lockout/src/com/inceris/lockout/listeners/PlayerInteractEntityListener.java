package com.inceris.lockout.listeners;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Axolotl;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import com.inceris.lockout.Lockout;
import com.inceris.lockout.util.GameInstance;
import com.inceris.lockout.util.Objective;

public class PlayerInteractEntityListener implements Listener {

	public static Lockout pl = Lockout.getPlugin(Lockout.class);

	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {

		if (e.getHand().equals(EquipmentSlot.HAND)) {
			Player p = e.getPlayer();
			for (GameInstance gi : pl.gameInstances) {
				if (gi.isActive() && gi.getPlayers().contains(p)) {
					List<Objective> objectives = gi.getObjectives();
					Entity entity = e.getRightClicked();
					ItemStack item = e.getPlayer().getInventory().getItemInMainHand();

					if (objectives.contains(Objective.sailBoat) && entity instanceof Boat) {
						Objective.complete(gi, Objective.sailBoat, p);

					} else if (objectives.contains(Objective.rideMinecart) && entity instanceof Minecart) {
						Objective.complete(gi, Objective.rideMinecart, p);

					} else if (item != null && item.getType().equals(Material.WATER_BUCKET)
							&& objectives.contains(Objective.bucketAxolotl) && entity instanceof Axolotl) {
						Objective.complete(gi, Objective.bucketAxolotl, p);
					}
				}
			}
		}

	}

}
