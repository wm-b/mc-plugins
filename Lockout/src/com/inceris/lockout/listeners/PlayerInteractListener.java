package com.inceris.lockout.listeners;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import com.inceris.lockout.Lockout;
import com.inceris.lockout.util.GameInstance;
import com.inceris.lockout.util.Objective;
import com.inceris.lockout.util.Util;

public class PlayerInteractListener implements Listener {

	public static Lockout pl = Lockout.getPlugin(Lockout.class);

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {

		if (e.getHand().equals(EquipmentSlot.HAND)) {
			
			Player p = e.getPlayer();
			for (GameInstance gi : pl.getGameInstances()) {
				if (gi.isActive() && gi.getPlayers().contains(p)) {

					ItemStack itemInMainHand = p.getInventory().getItemInMainHand();
					if (itemInMainHand.getType().equals(Material.COMPASS) && itemInMainHand.getItemMeta()
							.getDisplayName().equals(Util.compass.getItemMeta().getDisplayName())) {
						List<Player> opponents = gi.getOpponents(p);
						for (int i = 0; i < opponents.size(); i++) {
							if (gi.getCompassTracking().get(p).equals(opponents.get(i))) {
								if (i != opponents.size() - 1) {
									gi.getCompassTracking().put(p, opponents.get(i + 1));
									p.sendMessage(
											Util.format("&7Compass now tracking&8: &f" + opponents.get(i + 1).getName()));
									Util.refreshCompass(gi, p);
									break;
								} else {
									gi.getCompassTracking().put(p, opponents.get(0));
									p.sendMessage(
											Util.format("&7Compass now tracking&8: &f" + opponents.get(0).getName()));
									Util.refreshCompass(gi, p);
									break;
								}
							}
						}
					}

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

					if (e.getClickedBlock() != null) {
						Material b = e.getClickedBlock().getType();
						if (objectives.contains(Objective.useAnvil) && b.equals(Material.ANVIL)) {
							Objective.complete(gi, Objective.useAnvil, p);

						} else if (objectives.contains(Objective.useComposter) && b.equals(Material.COMPOSTER)) {
							Objective.complete(gi, Objective.useComposter, p);

						} else if (objectives.contains(Objective.useFurnace) && b.equals(Material.FURNACE)) {
							Objective.complete(gi, Objective.useFurnace, p);

						} else if (objectives.contains(Objective.useLoom) && b.equals(Material.LOOM)) {
							Objective.complete(gi, Objective.useLoom, p);

						} else if (objectives.contains(Objective.useGrindstone) && b.equals(Material.GRINDSTONE)) {
							Objective.complete(gi, Objective.useGrindstone, p);

						} else if (objectives.contains(Objective.useFletchingTable)
								&& b.equals(Material.FLETCHING_TABLE)) {
							Objective.complete(gi, Objective.useFletchingTable, p);

						} else if (objectives.contains(Objective.useCartographyTable)
								&& b.equals(Material.CARTOGRAPHY_TABLE)) {
							Objective.complete(gi, Objective.useCartographyTable, p);

						} else if (objectives.contains(Objective.useBlastFurnace) && b.equals(Material.BLAST_FURNACE)) {
							Objective.complete(gi, Objective.useBlastFurnace, p);

						} else if (objectives.contains(Objective.useSmoker) && b.equals(Material.SMOKER)) {
							Objective.complete(gi, Objective.useSmoker, p);

						} else if (objectives.contains(Objective.useCampfire) && b.equals(Material.CAMPFIRE)) {
							Objective.complete(gi, Objective.useCampfire, p);

						} else if (objectives.contains(Objective.useJukebox) && b.equals(Material.JUKEBOX)) {
							Objective.complete(gi, Objective.useJukebox, p);

						}
					}
				}
			}
		}
	}

}
