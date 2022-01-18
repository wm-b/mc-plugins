package com.inceris.runecraftitems.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

import com.inceris.runecraftitems.ItemList;
import com.inceris.runecraftitems.RuneCraftItems;

public class ProjectileHitListener implements Listener {

	@EventHandler
	public void onProjectileHit(ProjectileHitEvent e) {
		if (RuneCraftItems.debug) Bukkit.getLogger().info("Detected projectile hit!");

		ProjectileSource shooter = e.getEntity().getShooter();
		if (shooter instanceof Player) {
			Player p = (Player) shooter;
			ItemStack itemInHand = p.getInventory().getItemInMainHand();
			if (itemInHand.getEnchantmentLevel(Enchantment.ARROW_INFINITE) == 10 && itemInHand.getItemMeta()
					.getDisplayName().equals(ItemList.grapplingHook.getItemMeta().getDisplayName())) {
				
				if (RuneCraftItems.debug) Bukkit.getLogger().info("Detected grapplinghook hit!");
				
				Location l = e.getHitBlock().getLocation();
				Vector v = l.toVector().subtract(p.getLocation().toVector());
				v.normalize();
				v = v.multiply(1.5);
				v.setY(v.getY() + 0.5);
				p.setVelocity(v);

			}
		}
	}
}