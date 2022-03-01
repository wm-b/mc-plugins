package com.inceris.lockout.listeners;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

import com.inceris.lockout.Lockout;
import com.inceris.lockout.util.GameInstance;
import com.inceris.lockout.util.Objective;

public class EntityPickupItemListener implements Listener {

	public static Lockout pl = Lockout.getPlugin(Lockout.class);

	@EventHandler
	public void onEntityPickupItem(EntityPickupItemEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			GameInstance gi = GameInstance.get(p);
			if (gi != null) {
				if (gi.isActive() && gi.getPlayers().contains(p)) {
					List<Objective> objectives = gi.getObjectives();
					Material m = e.getItem().getItemStack().getType();

					if (m.equals(Material.DIAMOND) && objectives.contains(Objective.obtainDiamond)) {
						Objective.complete(gi, Objective.obtainDiamond, p);

					} else if (m.equals(Material.REDSTONE) && objectives.contains(Objective.obtainRedstone)) {
						Objective.complete(gi, Objective.obtainRedstone, p);

					} else if (m.equals(Material.LAPIS_LAZULI) && objectives.contains(Objective.obtainLapis)) {
						Objective.complete(gi, Objective.obtainLapis, p);

					} else if (m.equals(Material.COAL) && objectives.contains(Objective.obtainCoal)) {
						Objective.complete(gi, Objective.obtainCoal, p);

					} else if (m.equals(Material.RAW_IRON) && objectives.contains(Objective.obtainRawIron)) {
						Objective.complete(gi, Objective.obtainRawIron, p);

					} else if (m.equals(Material.RAW_GOLD) && objectives.contains(Objective.obtainRawGold)) {
						Objective.complete(gi, Objective.obtainRawGold, p);

					} else if (m.equals(Material.IRON_INGOT) && objectives.contains(Objective.obtainIronIngot)) {
						Objective.complete(gi, Objective.obtainIronIngot, p);

					} else if (m.equals(Material.GOLD_INGOT) && objectives.contains(Objective.obtainGoldIngot)) {
						Objective.complete(gi, Objective.obtainGoldIngot, p);

					} else if (m.equals(Material.TUFF) && objectives.contains(Objective.obtainTuff)) {
						Objective.complete(gi, Objective.obtainTuff, p);

					} else if (m.equals(Material.GRANITE) && objectives.contains(Objective.obtainGranite)) {
						Objective.complete(gi, Objective.obtainGranite, p);

					} else if (m.equals(Material.ANDESITE) && objectives.contains(Objective.obtainAndesite)) {
						Objective.complete(gi, Objective.obtainAndesite, p);

					} else if (m.equals(Material.DIORITE) && objectives.contains(Objective.obtainDiorite)) {
						Objective.complete(gi, Objective.obtainDiorite, p);

					} else if (m.equals(Material.COBBLED_DEEPSLATE) && objectives.contains(Objective.obtainDeepslate)) {
						Objective.complete(gi, Objective.obtainDeepslate, p);

					} else if (m.equals(Material.STONE) && objectives.contains(Objective.obtainStone)) {
						Objective.complete(gi, Objective.obtainStone, p);

					} else if (m.equals(Material.ANCIENT_DEBRIS)
							&& objectives.contains(Objective.obtainAncientDebris)) {
						Objective.complete(gi, Objective.obtainAncientDebris, p);

					} else if (m.equals(Material.NETHERITE_INGOT)
							&& objectives.contains(Objective.obtainNetheriteIngot)) {
						Objective.complete(gi, Objective.obtainNetheriteIngot, p);

					} else if (m.equals(Material.BEACON) && objectives.contains(Objective.obtainBeacon)) {
						Objective.complete(gi, Objective.obtainBeacon, p);

					} else if (objectives.contains(Objective.dontPickUpObsidian) && m.equals(Material.OBSIDIAN)) {
						Objective.complete(gi, Objective.dontPickUpObsidian, gi.getOpponents(p).get(0));

					} else if (objectives.contains(Objective.dontPickUpWood) && m.name().contains("_PLANKS")
							|| m.name().contains("_LOG")) {
						Objective.complete(gi, Objective.dontPickUpWood, gi.getOpponents(p).get(0));

					}
				}
			}
		}
	}
}
