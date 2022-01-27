package com.inceris.runecraftitems.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.inceris.runecraftitems.Items;
import com.inceris.runecraftitems.Util;

public class EntityDamageByEntityListener implements Listener {

	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
		Entity entity = e.getEntity();
		Entity damager = e.getDamager();
		
		if (entity instanceof Player && damager instanceof Player) {
			
			Player p = (Player) entity;
			Player d = (Player) damager;
			ItemStack item = d.getInventory().getItemInMainHand();
			
			if (Util.checkItem(item, Items.loveIsBlind)) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 0));
				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 2));
				
			} else if (Util.checkItem(item, Items.brokenHeart)) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60, 1));
			}
			
		}
	}
	
}
