package com.inceris.runecraftitems.listeners;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.inceris.runecraftitems.RuneCraftItems;
import com.inceris.runecraftitems.util.Items;
import com.inceris.runecraftitems.util.RCIPlayer;
import com.inceris.runecraftitems.util.RTP;
import com.inceris.runecraftitems.util.Util;

public class PlayerInteractListener implements Listener {
	
	private static RuneCraftItems rci = RuneCraftItems.getPlugin(RuneCraftItems.class);
	
	@EventHandler
	public void onItemUse(PlayerInteractEvent e) {
		EquipmentSlot slot = e.getHand();
		if (!(slot == null)) {
			if (slot.equals(EquipmentSlot.HAND)) {
				Player p = e.getPlayer();
				String name = p.getName();
				ItemStack item = p.getInventory().getItemInMainHand();
				if (!(item == null)) {
					if (Util.checkItem(item, Items.flyToken)) {
						Util.sendCommand("lp user " + name + " permission settemp essentials.fly true 1d");
						item.setAmount(item.getAmount() - 1);

					} else if (Util.checkItem(item, Items.rankToken)) {
						Util.sendCommand("lp user " + name + " promote donator");
						item.setAmount(item.getAmount() - 1);

					} else if (Util.checkItem(item, Items.mobFreezer)) {
						List<Entity> entityArray = p.getNearbyEntities(5, 5, 5);
						
						double i = 1000;
						Entity entityToFreeze = null;
						for (Entity entity : entityArray) {
							double d = entity.getLocation().distanceSquared(p.getLocation());
							if (d < i && entity instanceof LivingEntity && entity.getCustomName() == null
									&& !(entity instanceof Player)) {
								i = d;
								entityToFreeze = entity;
							}
						}
						
						if (entityToFreeze != null) {
							Util.sendCommand("freeze " + p.getName());
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&6R&5C&9I&8] &cNo mob in range!"));
						}
						
					} else if (Util.checkItem(item, Items.chocolateBar)) {
						RCIPlayer rcip = RCIPlayer.getRCIPlayer(p);
						if (!rcip.healOnCooldown) {
							p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
							
							rcip.healOnCooldown = true;
							Bukkit.getScheduler().runTaskLater(rci, new Runnable() {
								@Override
								public void run() {
									rcip.healOnCooldown = false;
								}
							}, 6000);
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&6R&5C&9I&8] &cThis item is on cooldown!"));
						}
						
					} else if (Util.checkItem(item, Items.grapefruit)) {
						RCIPlayer rcip = RCIPlayer.getRCIPlayer(p);
						if (!rcip.grapefruitOnCooldown) {
							Util.sendCommand("execute at " + name
									+ " run particle minecraft:reverse_portal ~ ~1 ~ 0.5 0.5 0.5 0.1 50");
							Util.sendCommand(
									"execute at " + name + " run playsound minecraft:entity.enderman.teleport master "
											+ name + " ~ ~ ~ 1 1 1");
							RTP.rtp(p, 32);
							Bukkit.getScheduler().runTaskLater(rci, new Runnable() {
								@Override
								public void run() {
									Util.sendCommand("execute at " + name
											+ " run particle minecraft:reverse_portal ~ ~1 ~ 0.5 0.5 0.5 0.1 50");
									Util.sendCommand("execute at " + name
											+ " run playsound minecraft:entity.enderman.teleport master " + name
											+ " ~ ~ ~ 1 1 1");
								}
							}, 1);
							
							rcip.grapefruitOnCooldown = true;
							Bukkit.getScheduler().runTaskLater(rci, new Runnable() {
								@Override
								public void run() {
									rcip.grapefruitOnCooldown = false;
								}
							}, 200);
						} else {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&6R&5C&9I&8] &cThis item is on cooldown!"));
						}
						
					} else if (Util.checkItem(item, Items.ricksFlowers) && e.getClickedBlock() != null) {
						e.setCancelled(true);
						
					} else if (Util.checkItem(item, Items.cupidsCrown)) {
						if (e.getClickedBlock() != null) {
							e.setCancelled(true);
						}
						p.getInventory().setHelmet(item);
						p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
						
					} else if (Util.checkItem(item, Items.totemOfRunecraft)) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 256));
						p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 2400, 4));
						p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 6000, 0));
						p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 600, 1));
						Bukkit.getScheduler().runTaskLater(rci, new Runnable() {
							@Override
							public void run() {
								p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 5900, 0));
							}
						}, 101);
						
						p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
						
					}
				}
			}
		}
	}
}
