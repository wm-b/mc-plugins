package com.inceris.runecraftitems.listeners;

import com.inceris.runecraftitems.RuneCraftItems;
import com.inceris.runecraftitems.util.Items;
import com.inceris.runecraftitems.util.RCIPlayer;
import com.inceris.runecraftitems.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerInteractEntityListener implements Listener {
	private static RuneCraftItems rci = (RuneCraftItems) RuneCraftItems.getPlugin(RuneCraftItems.class);

	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
		EquipmentSlot slot = e.getHand();
		if (!(slot == null)) {
			if (slot.equals(EquipmentSlot.HAND)) {
				Player p = e.getPlayer();
				Entity entity = e.getRightClicked();
				ItemStack item = p.getInventory().getItemInMainHand();

				if (entity instanceof Player) {
					Player target = (Player) entity;
					if (Util.checkItem(target.getInventory().getItemInMainHand(), Items.getItem("ricksFlowers"))) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 0, 10));
						p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 0, 10));
						p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 0, 10));
						p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 0, 10));
						Util.sendCommand("trailsid HEART " + p.getName());
						final Player playerPass = p;
						Bukkit.getScheduler().runTaskLater((Plugin) rci, new Runnable() {
							public void run() {
								Util.sendCommand("trailsid NONE " + playerPass.getName());
							}
						}, 200L);

					} else if (Util.checkItem(item, Items.getItem("escobarsSalt"))) {
						RCIPlayer rcip = RCIPlayer.getRCIPlayer(p);
						if (!rcip.escobarsSaltOnCooldown) {
							target.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 0));
							rcip.escobarsSaltOnCooldown = true;
							Bukkit.getScheduler().runTaskLater(rci, new Runnable() {
								@Override
								public void run() {
									rcip.escobarsSaltOnCooldown = false;
								}
							}, 1200);
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&',
									"&8[&6R&5C&9I&8] &cThis item is on cooldown!"));
						}

					}

				} else if (Util.checkItem(item, Items.getItem("thunderstone"))) {
					if (entity instanceof Creeper) {
						RCIPlayer rcip = RCIPlayer.getRCIPlayer(p);
						if (!rcip.thunderstoneOnCooldown) {
							Creeper creeper = (Creeper) entity;
							creeper.setPowered(true);
							rcip.thunderstoneOnCooldown = true;
							Bukkit.getScheduler().runTaskLater(rci, new Runnable() {
								@Override
								public void run() {
									rcip.thunderstoneOnCooldown = false;
								}
							}, 3600);
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&',
									"&8[&6R&5C&9I&8] &cThis item is on cooldown!"));
						}
					} else if (entity instanceof Sheep) {
						e.setCancelled(true);
					}
				}
			}
		}
	}
}