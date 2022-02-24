package com.inceris.lockout.listeners;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.PlayerInventory;

import com.inceris.lockout.Lockout;
import com.inceris.lockout.util.GameInstance;
import com.inceris.lockout.util.Objective;

public class PlayerInteractListener implements Listener {

	public static Lockout pl = Lockout.getPlugin(Lockout.class);

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {

		Player p = e.getPlayer();
		for (GameInstance gi : pl.gameInstances) {
			if (gi.isActive() && gi.getPlayerScores().keySet().contains(p)) {
				List<Objective> objectives = gi.getObjectives();
				PlayerInventory inv = p.getInventory();
				if ((inv.getArmorContents()[0].getType().equals(Material.LEATHER_HELMET)
						|| inv.getArmorContents()[1].getType().equals(Material.LEATHER_CHESTPLATE)
						|| inv.getArmorContents()[2].getType().equals(Material.LEATHER_LEGGINGS)
						|| inv.getArmorContents()[3].getType().equals(Material.LEATHER_BOOTS))
						&& objectives.contains(Objective.wearLeatherPiece)) {
					Objective.complete(gi, Objective.wearLeatherPiece, p);

				} else if ((inv.getArmorContents()[0].getType().equals(Material.CHAINMAIL_HELMET)
						|| inv.getArmorContents()[1].getType().equals(Material.CHAINMAIL_CHESTPLATE)
						|| inv.getArmorContents()[2].getType().equals(Material.CHAINMAIL_LEGGINGS)
						|| inv.getArmorContents()[3].getType().equals(Material.CHAINMAIL_BOOTS))
						&& objectives.contains(Objective.wearChainmailPiece)) {
					Objective.complete(gi, Objective.wearChainmailPiece, p);

				} else if ((inv.getArmorContents()[0].getType().equals(Material.IRON_HELMET)
						|| inv.getArmorContents()[1].getType().equals(Material.IRON_CHESTPLATE)
						|| inv.getArmorContents()[2].getType().equals(Material.IRON_LEGGINGS)
						|| inv.getArmorContents()[3].getType().equals(Material.IRON_BOOTS))
						&& objectives.contains(Objective.wearIronPiece)) {
					Objective.complete(gi, Objective.wearIronPiece, p);

				} else if ((inv.getArmorContents()[0].getType().equals(Material.GOLDEN_HELMET)
						|| inv.getArmorContents()[1].getType().equals(Material.GOLDEN_CHESTPLATE)
						|| inv.getArmorContents()[2].getType().equals(Material.GOLDEN_LEGGINGS)
						|| inv.getArmorContents()[3].getType().equals(Material.GOLDEN_BOOTS))
						&& objectives.contains(Objective.wearGoldPiece)) {
					Objective.complete(gi, Objective.wearGoldPiece, p);

				} else if ((inv.getArmorContents()[0].getType().equals(Material.DIAMOND_HELMET)
						|| inv.getArmorContents()[1].getType().equals(Material.DIAMOND_CHESTPLATE)
						|| inv.getArmorContents()[2].getType().equals(Material.DIAMOND_LEGGINGS)
						|| inv.getArmorContents()[3].getType().equals(Material.DIAMOND_BOOTS))
						&& objectives.contains(Objective.wearDiamondPiece)) {
					Objective.complete(gi, Objective.wearDiamondPiece, p);

				} else if (inv.getArmorContents()[0].getType().equals(Material.LEATHER_HELMET)
						&& inv.getArmorContents()[1].getType().equals(Material.LEATHER_CHESTPLATE)
						&& inv.getArmorContents()[2].getType().equals(Material.LEATHER_LEGGINGS)
						&& inv.getArmorContents()[3].getType().equals(Material.LEATHER_BOOTS)
						&& objectives.contains(Objective.wearLeatherSet)) {
					Objective.complete(gi, Objective.wearLeatherSet, p);

				} else if (inv.getArmorContents()[0].getType().equals(Material.CHAINMAIL_HELMET)
						&& inv.getArmorContents()[1].getType().equals(Material.CHAINMAIL_CHESTPLATE)
						&& inv.getArmorContents()[2].getType().equals(Material.CHAINMAIL_LEGGINGS)
						&& inv.getArmorContents()[3].getType().equals(Material.CHAINMAIL_BOOTS)
						&& objectives.contains(Objective.wearChainmailSet)) {
					Objective.complete(gi, Objective.wearChainmailSet, p);

				} else if (inv.getArmorContents()[0].getType().equals(Material.IRON_HELMET)
						&& inv.getArmorContents()[1].getType().equals(Material.IRON_CHESTPLATE)
						&& inv.getArmorContents()[2].getType().equals(Material.IRON_LEGGINGS)
						&& inv.getArmorContents()[3].getType().equals(Material.IRON_BOOTS)
						&& objectives.contains(Objective.wearIronSet)) {
					Objective.complete(gi, Objective.wearIronSet, p);

				} else if (inv.getArmorContents()[0].getType().equals(Material.GOLDEN_HELMET)
						&& inv.getArmorContents()[1].getType().equals(Material.GOLDEN_CHESTPLATE)
						&& inv.getArmorContents()[2].getType().equals(Material.GOLDEN_LEGGINGS)
						&& inv.getArmorContents()[3].getType().equals(Material.GOLDEN_BOOTS)
						&& objectives.contains(Objective.wearGoldSet)) {
					Objective.complete(gi, Objective.wearGoldSet, p);

				} else if (inv.getArmorContents()[0].getType().equals(Material.DIAMOND_HELMET)
						&& inv.getArmorContents()[1].getType().equals(Material.DIAMOND_CHESTPLATE)
						&& inv.getArmorContents()[2].getType().equals(Material.DIAMOND_LEGGINGS)
						&& inv.getArmorContents()[3].getType().equals(Material.DIAMOND_BOOTS)
						&& objectives.contains(Objective.wearDiamondSet)) {
					Objective.complete(gi, Objective.wearDiamondSet, p);

				}
			}
		}
	}

}
