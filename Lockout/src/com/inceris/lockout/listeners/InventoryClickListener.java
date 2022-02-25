package com.inceris.lockout.listeners;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import com.inceris.lockout.Lockout;
import com.inceris.lockout.util.GameInstance;
import com.inceris.lockout.util.Objective;
import com.inceris.lockout.util.Util;

public class InventoryClickListener implements Listener {

	public static Lockout pl = Lockout.getPlugin(Lockout.class);

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (e.getWhoClicked() instanceof Player) {
			Player p = (Player) e.getWhoClicked();
			for (GameInstance gi : pl.gameInstances) {
				if (gi.isActive() && gi.getPlayerScores().keySet().contains(p)) {
					List<Objective> objectives = gi.getObjectives();
					ItemStack[] ac = p.getInventory().getArmorContents();

					Util.checkWearingArmorPiece(gi, p, objectives, Objective.wearLeatherPiece, ac,
							Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS,
							Material.LEATHER_BOOTS);

					Util.checkWearingArmorPiece(gi, p, objectives, Objective.wearChainmailPiece, ac,
							Material.CHAINMAIL_HELMET, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_LEGGINGS,
							Material.CHAINMAIL_BOOTS);

					Util.checkWearingArmorPiece(gi, p, objectives, Objective.wearIronPiece, ac, Material.IRON_HELMET,
							Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS);

					Util.checkWearingArmorPiece(gi, p, objectives, Objective.wearGoldPiece, ac, Material.GOLDEN_HELMET,
							Material.GOLDEN_CHESTPLATE, Material.GOLDEN_LEGGINGS, Material.GOLDEN_BOOTS);

					Util.checkWearingArmorPiece(gi, p, objectives, Objective.wearDiamondPiece, ac,
							Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS,
							Material.DIAMOND_BOOTS);

					Util.checkWearingArmorSet(gi, p, objectives, Objective.wearLeatherSet, ac, Material.LEATHER_HELMET,
							Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS);

					Util.checkWearingArmorSet(gi, p, objectives, Objective.wearChainmailSet, ac,
							Material.CHAINMAIL_HELMET, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_LEGGINGS,
							Material.CHAINMAIL_BOOTS);

					Util.checkWearingArmorSet(gi, p, objectives, Objective.wearIronSet, ac, Material.IRON_HELMET,
							Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS);

					Util.checkWearingArmorSet(gi, p, objectives, Objective.wearGoldSet, ac, Material.GOLDEN_HELMET,
							Material.GOLDEN_CHESTPLATE, Material.GOLDEN_LEGGINGS, Material.GOLDEN_BOOTS);

					Util.checkWearingArmorSet(gi, p, objectives, Objective.wearDiamondSet, ac, Material.DIAMOND_HELMET,
							Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS);

					if (e.getCurrentItem() != null) {
						Material m = e.getCurrentItem().getType();
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

						} else if (m.equals(Material.COBBLED_DEEPSLATE)
								&& objectives.contains(Objective.obtainDeepslate)) {
							Objective.complete(gi, Objective.obtainDeepslate, p);

						} else if (m.equals(Material.ANCIENT_DEBRIS)
								&& objectives.contains(Objective.obtainAncientDebris)) {
							Objective.complete(gi, Objective.obtainAncientDebris, p);

						} else if (m.equals(Material.NETHERITE_INGOT)
								&& objectives.contains(Objective.obtainNetheriteIngot)) {
							Objective.complete(gi, Objective.obtainNetheriteIngot, p);

						} else if (m.equals(Material.BEACON) && objectives.contains(Objective.obtainBeacon)) {
							Objective.complete(gi, Objective.obtainBeacon, p);

						}
					}
				}
			}
		}
	}
}
