package com.inceris.lockout.listeners;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.StructureType;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.inceris.lockout.Lockout;
import com.inceris.lockout.util.GameInstance;
import com.inceris.lockout.util.Objective;
import com.inceris.lockout.util.Util;

public class PlayerMoveListener implements Listener {

	public static Lockout pl = Lockout.getPlugin(Lockout.class);
	public static int actuallyCheck = 0;

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		if (actuallyCheck == 20) {
			actuallyCheck = 0;
		}

		if (actuallyCheck == 10 || actuallyCheck == 20) {
			Player p = e.getPlayer();
			if (GameInstance.get(p) != null) {
				GameInstance gi = GameInstance.get(p);
				if (gi.isActive() && gi.getPlayers().contains(p)) {
					List<Objective> objectives = gi.getObjectives();
					World w = gi.getWorld();
					World n = gi.getNether();
					Location l = p.getLocation();

					if (objectives.contains(Objective.reachBedrock) && l.getY() <= -60) {
						Objective.complete(gi, Objective.reachBedrock, p);

					} else if (objectives.contains(Objective.reachHeightLimit) && l.getY() >= 320) {
						Objective.complete(gi, Objective.reachHeightLimit, p);

					} else if (objectives.contains(Objective.reachHeightLimit) && l.getY() >= 320) {
						Objective.complete(gi, Objective.reachHeightLimit, p);

					} else if (objectives.contains(Objective.findMineshaft)
							&& w.locateNearestStructure(l, StructureType.MINESHAFT, 50, false) != null
							&& w.locateNearestStructure(l, StructureType.MINESHAFT, 50, false).getWorld()
									.equals(l.getWorld())
							&& w.locateNearestStructure(l, StructureType.MINESHAFT, 50, false).distance(l) < 50) {
						Objective.complete(gi, Objective.findMineshaft, p);

					} else if (objectives.contains(Objective.findPyramid)
							&& w.locateNearestStructure(l, StructureType.DESERT_PYRAMID, 50, false) != null
							&& w.locateNearestStructure(l, StructureType.DESERT_PYRAMID, 50, false).getWorld()
									.equals(l.getWorld())
							&& w.locateNearestStructure(l, StructureType.DESERT_PYRAMID, 50, false).distance(l) < 50) {
						Objective.complete(gi, Objective.findPyramid, p);

					} else if (objectives.contains(Objective.findVillage)
							&& w.locateNearestStructure(l, StructureType.VILLAGE, 50, false) != null
							&& w.locateNearestStructure(l, StructureType.VILLAGE, 50, false).getWorld()
									.equals(l.getWorld())
							&& w.locateNearestStructure(l, StructureType.VILLAGE, 50, false).distance(l) < 50) {
						Objective.complete(gi, Objective.findVillage, p);

					} else if (objectives.contains(Objective.findRuinedPortal)
							&& w.locateNearestStructure(l, StructureType.RUINED_PORTAL, 50, false) != null
							&& w.locateNearestStructure(l, StructureType.RUINED_PORTAL, 50, false).getWorld()
									.equals(l.getWorld())
							&& w.locateNearestStructure(l, StructureType.RUINED_PORTAL, 50, false).distance(l) < 50) {
						Objective.complete(gi, Objective.findRuinedPortal, p);

					} else if (objectives.contains(Objective.findOceanRuins)
							&& w.locateNearestStructure(l, StructureType.OCEAN_RUIN, 50, false) != null
							&& w.locateNearestStructure(l, StructureType.OCEAN_RUIN, 50, false).getWorld()
									.equals(l.getWorld())
							&& w.locateNearestStructure(l, StructureType.OCEAN_RUIN, 50, false).distance(l) < 50) {
						Objective.complete(gi, Objective.findOceanRuins, p);

					} else if (objectives.contains(Objective.findShipwreck)
							&& w.locateNearestStructure(l, StructureType.SHIPWRECK, 50, false) != null
							&& w.locateNearestStructure(l, StructureType.SHIPWRECK, 50, false).getWorld()
									.equals(l.getWorld())
							&& w.locateNearestStructure(l, StructureType.SHIPWRECK, 50, false).distance(l) < 50) {
						Objective.complete(gi, Objective.findShipwreck, p);

					} else if (objectives.contains(Objective.findFortress)
							&& n.locateNearestStructure(l, StructureType.NETHER_FORTRESS, 50, false) != null
							&& n.locateNearestStructure(l, StructureType.NETHER_FORTRESS, 50, false).getWorld()
									.equals(l.getWorld())
							&& n.locateNearestStructure(l, StructureType.NETHER_FORTRESS, 50, false).distance(l) < 50) {
						Objective.complete(gi, Objective.findFortress, p);

					} else if (objectives.contains(Objective.findBastion)
							&& n.locateNearestStructure(l, StructureType.BASTION_REMNANT, 50, false) != null
							&& n.locateNearestStructure(l, StructureType.BASTION_REMNANT, 50, false).getWorld()
									.equals(l.getWorld())
							&& n.locateNearestStructure(l, StructureType.BASTION_REMNANT, 50, false).distance(l) < 50) {
						Objective.complete(gi, Objective.findBastion, p);

					} else if (objectives.contains(Objective.findNetherFossil)
							&& n.locateNearestStructure(l, StructureType.NETHER_FOSSIL, 50, false) != null
							&& n.locateNearestStructure(l, StructureType.NETHER_FOSSIL, 50, false).getWorld()
									.equals(l.getWorld())
							&& n.locateNearestStructure(l, StructureType.NETHER_FOSSIL, 50, false).distance(l) < 50) {
						Objective.complete(gi, Objective.findNetherFossil, p);

					}
				}
			}

			if (actuallyCheck == 4 || actuallyCheck == 8 || actuallyCheck == 12 || actuallyCheck == 16
					|| actuallyCheck == 20) {
				if (GameInstance.get(p) != null) {
					GameInstance gi = GameInstance.get(p);
					Bukkit.getScheduler().runTaskAsynchronously(pl, new Runnable() {
						@Override
						public void run() {
							Util.refreshCompass(gi, p);
						}
					});
				}
			}
		}

		actuallyCheck++;
	}
}
