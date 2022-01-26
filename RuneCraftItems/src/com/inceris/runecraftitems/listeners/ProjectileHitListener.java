package com.inceris.runecraftitems.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

import com.inceris.runecraftitems.Items;
import com.inceris.runecraftitems.RuneCraftItems;

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
				if (itemInHand.getEnchantmentLevel(Enchantment.ARROW_INFINITE) == 10 && itemInHand.getItemMeta()
						.getDisplayName().equals(Items.grapplingHook.getItemMeta().getDisplayName())) {

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
				if (ep.getEnchantmentLevel(Enchantment.ARROW_INFINITE) == 10 && ep.getItemMeta().getDisplayName()
						.equals(Items.eyeOfTheDragon.getItemMeta().getDisplayName())) {
					p.getInventory().addItem(ep);
				}
			}
		}
	}
}
