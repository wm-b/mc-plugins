package com.inceris.lockout.listeners;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;

import com.inceris.lockout.Lockout;
import com.inceris.lockout.util.GameInstance;
import com.inceris.lockout.util.Objective;

public class PlayerMoveListener implements Listener {

	public static Lockout pl = Lockout.getPlugin(Lockout.class);

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if (GameInstance.get(p) != null) {
			GameInstance gi = GameInstance.get(p);
			if (gi.isActive() && gi.getPlayers().contains(p)) {
				List<Objective> objectives = gi.getObjectives();

				if (objectives.contains(Objective.reachBedrock) && p.getLocation().getY() <= -60) {
					Objective.complete(gi, Objective.reachBedrock, p);

				}
			}

			for (Player player : gi.getPlayers()) {
				if (!p.equals(player)) {
					for (ItemStack item : player.getInventory().getContents()) {
						if (item != null) {
							if (item.getType().equals(Material.COMPASS)
									&& item.getItemMeta().getDisplayName().contains(p.getName())) {
								CompassMeta meta = (CompassMeta) item.getItemMeta();
								meta.setLodestoneTracked(true);
								meta.setLodestone(p.getLocation());
								item.setItemMeta(meta);
							}
						}
					}
				}
			}
		}
	}
}
