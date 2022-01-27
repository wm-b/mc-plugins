package com.inceris.runecraftitems.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
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

import com.inceris.runecraftitems.Items;
import com.inceris.runecraftitems.RuneCraftItems;
import com.inceris.runecraftitems.Util;

public class ProjectileHitListener implements Listener {

	@EventHandler
	public void onProjectileHit(ProjectileHitEvent e) {
		if (RuneCraftItems.debug) Bukkit.getLogger().info("Detected projectile hit!");
		Entity entity = e.getEntity();
		ProjectileSource shooter = e.getEntity().getShooter();
		if (shooter instanceof Player) {
			Player p = (Player) shooter;
			ItemStack itemInHand = p.getInventory().getItemInMainHand();
			if (!(itemInHand == null)) {
				if (Util.CheckItem(itemInHand, Items.grapplingHook)) {

					if (RuneCraftItems.debug)
						Bukkit.getLogger().info("Detected grapplinghook hit!");

					Location l = e.getHitBlock().getLocation();
					Vector v = l.toVector().subtract(p.getLocation().toVector());
					v.normalize();
					v = v.multiply(2.5);
					v.setY(v.getY() + 0.25);
					p.setVelocity(v);
				}
			}
			
			if (entity instanceof EnderPearl) {
				ItemStack ep = ((EnderPearl) entity).getItem();
				if (Util.CheckItem(ep, Items.eyeOfTheDragon)) {
					p.getInventory().addItem(ep);
					p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1, 255));
				}
			}
		}
	}
}
