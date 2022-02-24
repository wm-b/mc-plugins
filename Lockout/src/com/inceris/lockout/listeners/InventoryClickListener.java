package com.inceris.lockout.listeners;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.PlayerInventory;

import com.inceris.lockout.Lockout;
import com.inceris.lockout.util.GameInstance;
import com.inceris.lockout.util.Objective;

public class InventoryClickListener implements Listener {

	public static Lockout pl = Lockout.getPlugin(Lockout.class);

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e){
		if (e.getWhoClicked() instanceof Player) {
			Player p = (Player) e.getWhoClicked();
			for (GameInstance gi : pl.gameInstances) {
				if (gi.isActive() && gi.getPlayerScores().keySet().contains(p)) {
					List<Objective> objectives = gi.getObjectives();
					if (e.getCurrentItem().getType().equals(Material.DIAMOND)
							&& objectives.contains(Objective.obtainDiamond)) {
						Objective.complete(gi, Objective.obtainDiamond, p);
						
					} else if (e.getCurrentItem().getType().equals(Material.REDSTONE)
							&& objectives.contains(Objective.obtainRedstone)) {
						Objective.complete(gi, Objective.obtainRedstone, p);
						
					} else if (e.getCurrentItem().getType().equals(Material.LAPIS_LAZULI)
							&& objectives.contains(Objective.obtainLapis)) {
						Objective.complete(gi, Objective.obtainLapis, p);
						
					} else if (e.getCurrentItem().getType().equals(Material.COAL)
							&& objectives.contains(Objective.obtainCoal)) {
						Objective.complete(gi, Objective.obtainCoal, p);
						
					} else if (e.getCurrentItem().getType().equals(Material.RAW_IRON)
							&& objectives.contains(Objective.obtainRawIron)) {
						Objective.complete(gi, Objective.obtainRawIron, p);
						
					} else if (e.getCurrentItem().getType().equals(Material.RAW_GOLD)
							&& objectives.contains(Objective.obtainRawGold)) {
						Objective.complete(gi, Objective.obtainRawGold, p);
						
					} else if (e.getCurrentItem().getType().equals(Material.IRON_INGOT)
							&& objectives.contains(Objective.obtainIronIngot)) {
						Objective.complete(gi, Objective.obtainIronIngot, p);
						
					} else if (e.getCurrentItem().getType().equals(Material.GOLD_INGOT)
							&& objectives.contains(Objective.obtainGoldIngot)) {
						Objective.complete(gi, Objective.obtainGoldIngot, p);
						
					} else if (e.getCurrentItem().getType().equals(Material.TUFF)
							&& objectives.contains(Objective.obtainTuff)) {
						Objective.complete(gi, Objective.obtainTuff, p);
						
					} else if (e.getCurrentItem().getType().equals(Material.GRANITE)
							&& objectives.contains(Objective.obtainGranite)) {
						Objective.complete(gi, Objective.obtainGranite, p);
						
					} else if (e.getCurrentItem().getType().equals(Material.ANDESITE)
							&& objectives.contains(Objective.obtainAndesite)) {
						Objective.complete(gi, Objective.obtainAndesite, p);
						
					} else if (e.getCurrentItem().getType().equals(Material.DIORITE)
							&& objectives.contains(Objective.obtainDiorite)) {
						Objective.complete(gi, Objective.obtainDiorite, p);
						
					} else if (e.getCurrentItem().getType().equals(Material.COBBLED_DEEPSLATE)
							&& objectives.contains(Objective.obtainDeepslate)) {
						Objective.complete(gi, Objective.obtainDeepslate, p);
						
					} else if (e.getCurrentItem().getType().equals(Material.ANCIENT_DEBRIS)
							&& objectives.contains(Objective.obtainAncientDebris)) {
						Objective.complete(gi, Objective.obtainAncientDebris, p);
						
					} else if (e.getCurrentItem().getType().equals(Material.NETHERITE_INGOT)
							&& objectives.contains(Objective.obtainNetheriteIngot)) {
						Objective.complete(gi, Objective.obtainNetheriteIngot, p);
						
					} else if (e.getCurrentItem().getType().equals(Material.BEACON)
							&& objectives.contains(Objective.obtainBeacon)) {
						Objective.complete(gi, Objective.obtainBeacon, p);
						
					} else if (e.getInventory() instanceof PlayerInventory) {
						PlayerInventory inv = (PlayerInventory) e.getInventory();
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
	}
	
}
