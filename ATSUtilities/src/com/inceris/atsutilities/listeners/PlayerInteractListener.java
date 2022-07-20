package com.inceris.atsutilities.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.util.RayTraceResult;

import com.inceris.atsutilities.ATSUtilities;
import com.inceris.atsutilities.util.Items;
import com.inceris.atsutilities.util.Util;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class PlayerInteractListener implements Listener {

	private static ATSUtilities atsu = ATSUtilities.getPlugin(ATSUtilities.class);

	@EventHandler
	public void onItemUse(PlayerInteractEvent e) {
		EquipmentSlot slot = e.getHand();

		if (slot == null) {
			return;
		}

		if (slot.equals(EquipmentSlot.HAND)) {
			Player p = e.getPlayer();
			PlayerInventory inv = p.getInventory();
			ItemStack item = inv.getItemInMainHand();

			if (item == null) {
				return;
			}

			if (Items.checkItem(item, Items.prestigeRankupToken)) {
				ConsoleCommandSender console = atsu.getServer().getConsoleSender();
				Bukkit.dispatchCommand((CommandSender) console, "prestigeRankup " + p.getName());
				inv.setItemInMainHand(new ItemStack(Material.AIR));

			}

			if (Items.checkItem(item, Items.laserSpyglass)) {
				laserSpyglass(p);
			}

			if (Items.checkItem(item, Items.deathRay)) {
				deathRay(p);
			}
			
			if (atsu.denyTallGrass) {
				if (item.getType().equals(Material.TALL_GRASS) && e.getClickedBlock() != null) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							"&4&lThou shalt not placeth the grass of sin."));
					e.setCancelled(true);

				}
			}
			
			if (atsu.denyInfested) {
				if (item.getType().name().toLowerCase().contains("infested") && e.getClickedBlock() != null) {
					p.sendMessage(ChatColor.translateAlternateColorCodes('&',
							"&4&lThou shalt not placeth the block of sin."));
					e.setCancelled(true);

				}
			}
		}
	}
	
	public boolean rtrChecks(RayTraceResult rtr, Player target) {
		if (rtr == null) {
			return true;
		}
		
		if (!(rtr.getHitEntity() instanceof Player)) {
			return true;
		}
		
		Player check = (Player) rtr.getHitEntity();
		if (!check.getUniqueId().equals(target.getUniqueId())) {
			return true;
		}
		
		return false;
	}
	
	public void deathRay(Player p) {
		Location l = p.getTargetBlock(null, 2).getLocation();
		l.setDirection(p.getLocation().getDirection());
		
		RayTraceResult rtr = p.getWorld().rayTraceEntities(l, l.getDirection(), 1000, 0.25);
		
		if (rtr == null) {
			return;
		}
		
		if (!(rtr.getHitEntity() instanceof LivingEntity)) {
			return;
		}
		
		LivingEntity target = (LivingEntity) rtr.getHitEntity();
		
		target.setHealth(0);
		if (target instanceof Player) {
			Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', target.getName() + " couldn't handle the deadly gaze of " + p.getName()));
		}
		
	}
	
	public void laserSpyglass(Player p) {
		Location l = p.getTargetBlock(null, 2).getLocation();
		l.setDirection(p.getLocation().getDirection());
		
		RayTraceResult rtr = p.getWorld().rayTraceEntities(l, l.getDirection(), 1000, 0.25);
		
		if (rtr == null) {
			return;
		}
		
		if (!(rtr.getHitEntity() instanceof Player)) {
			return;
		}
		
		Player target = (Player) rtr.getHitEntity();
		
		p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
				new TextComponent(Util.colours("&cTarget Acquired")));

		Bukkit.getScheduler().runTaskLater(atsu, new Runnable() {
			@Override
			public void run() {
				if (!Items.checkItem(p.getInventory().getItemInMainHand(), Items.laserSpyglass)) {
					return;
				}
				
				Location l = p.getTargetBlock(null, 2).getLocation();
				l.setDirection(p.getLocation().getDirection());
				
				RayTraceResult rtr = p.getWorld().rayTraceEntities(l, l.getDirection(), 1000, 0.25);
				
				if (rtrChecks(rtr, target)) {
					return;
				}
				
				target.spigot().sendMessage(ChatMessageType.ACTION_BAR,
						new TextComponent(Util.colours("&cYou feel warm. Too warm...")));
				
				p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
						new TextComponent(Util.colours("&cTarget warming up...")));
				
				Bukkit.getScheduler().runTaskLater(atsu, new Runnable() {
					@Override
					public void run() {
						if (!Items.checkItem(p.getInventory().getItemInMainHand(), Items.laserSpyglass)) {
							return;
						}
						
						Location l = p.getTargetBlock(null, 2).getLocation();
						l.setDirection(p.getLocation().getDirection());
						
						RayTraceResult rtr = p.getWorld().rayTraceEntities(l, l.getDirection(), 1000, 0.25);
						
						if (rtrChecks(rtr, target)) {
							return;
						}
						
						target.setFireTicks(100);
						
						Bukkit.getScheduler().runTaskLater(atsu, new Runnable() {
							@Override
							public void run() {
								if (!Items.checkItem(p.getInventory().getItemInMainHand(), Items.laserSpyglass)) {
									return;
								}
								
								Location l = p.getTargetBlock(null, 2).getLocation();
								l.setDirection(p.getLocation().getDirection());
								
								RayTraceResult rtr = p.getWorld().rayTraceEntities(l, l.getDirection(), 1000, 0.25);
								
								if (rtrChecks(rtr, target)) {
									return;
								}
								
								target.setHealth(0);
								Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', target.getName() + " couldn't handle the deadly gaze of " + p.getName()));
							}
						}, 40);
						
					}
				}, 40);
				
			}
		}, 40);
		
	}
}
