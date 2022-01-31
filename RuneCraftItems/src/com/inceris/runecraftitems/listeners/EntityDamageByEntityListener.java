package com.inceris.runecraftitems.listeners;

import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.inceris.runecraftitems.util.Items;
import com.inceris.runecraftitems.util.Util;

public class EntityDamageByEntityListener implements Listener {

	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
		Entity entity = e.getEntity();
		Entity damager = e.getDamager();
		
		if (damager instanceof Player && entity instanceof LivingEntity) {
			
			LivingEntity l = (LivingEntity) entity;
			Player p = (Player) damager;
			ItemStack item = p.getInventory().getItemInMainHand();
			
			if (Util.checkItem(item, Items.loveIsBlind)) {
				l.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 0));
				l.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 2));
				
			} else if (Util.checkItem(item, Items.brokenHeart)) {
				l.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60, 1));
				
			} else if (Util.checkItem(item, Items.incsSword) && p.getName().equalsIgnoreCase("Inceris")) {
				if (l instanceof Damageable) {
					Damageable d = (Damageable) l;
					d.setHealth(0);
				}
			}
			
		}
	}
	
}
