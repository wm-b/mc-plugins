package com.inceris.runecraftitems.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

import com.inceris.runecraftitems.RuneCraftItems;
import com.inceris.runecraftitems.util.Items;
import com.inceris.runecraftitems.util.Util;

public class ProjectileHitListener implements Listener {
	
	private static RuneCraftItems rci = RuneCraftItems.getPlugin(RuneCraftItems.class);

	@EventHandler
	public void onProjectileHit(ProjectileHitEvent e) {
		if (rci.debug) Bukkit.getLogger().info("Detected projectile hit!");
		
		Player target = null;
		Entity targetEntity = e.getHitEntity();
		Block targetBlock = e.getHitBlock();
		
		if (targetEntity instanceof Player) {
			target = (Player) targetEntity;
		}
		
		Entity entity = e.getEntity();
		ProjectileSource shooterEntity = e.getEntity().getShooter();
		if (shooterEntity instanceof Player) {
			Player shooter = (Player) shooterEntity;
			ItemStack itemInHand = shooter.getInventory().getItemInMainHand();
			if (itemInHand != null) {
				if (targetBlock != null) {
					if (Util.checkItem(itemInHand, Items.getItem("grapplingHook"))) {

						if (rci.debug)
							Bukkit.getLogger().info("Detected grapplinghook hit!");

						Location l = targetBlock.getLocation();
						Vector v = l.toVector().subtract(shooter.getLocation().toVector());
						v.normalize();
						v = v.multiply(2.5);
						v.setY(v.getY() + 0.25);
						shooter.setVelocity(v);
						
					}
				}
				
				if (Util.checkItem(itemInHand, Items.getItem("cupidsBow"))) {
					if (target != null) {
						target.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 1));
						Util.sendCommand("trailsid HEART " + target.getName());
						
						final Player targetPass = target;
						Bukkit.getScheduler().runTaskLater(rci, new Runnable() {
							@Override
							public void run() {
								Util.sendCommand("trailsid NONE " + targetPass.getName());
							}
						}, 200);
						
					}
					
				} else if (Util.checkItem(itemInHand, Items.getItem("thunderBow"))) {
					if (Util.percentChance(15)) {
						if (targetBlock != null) {
							shooter.getWorld().strikeLightning(targetBlock.getLocation());
						} else if (target != null) {
							target.getWorld().strikeLightning(target.getLocation());
						}
					}
					
				}
			}
			
			if (entity instanceof EnderPearl) {
				ItemStack ep = ((EnderPearl) entity).getItem();
				if (Util.checkItem(ep, Items.getItem("eyeOfTheDragon"))) {
					shooter.getInventory().addItem(ep);
					shooter.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1, 255));
				}
			}
		}
	}
}
