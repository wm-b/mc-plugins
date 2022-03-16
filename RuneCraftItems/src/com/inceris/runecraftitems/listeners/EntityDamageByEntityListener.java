package com.inceris.runecraftitems.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.ElderGuardian;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wither;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.inceris.runecraftitems.RuneCraftItems;
import com.inceris.runecraftitems.util.Items;
import com.inceris.runecraftitems.util.RCIPlayer;
import com.inceris.runecraftitems.util.Util;

public class EntityDamageByEntityListener implements Listener {

	private static RuneCraftItems rci = RuneCraftItems.getPlugin(RuneCraftItems.class);

	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
		Entity entity = e.getEntity();
		Entity damager = e.getDamager();

		if (damager instanceof Player && entity instanceof LivingEntity) {

			LivingEntity l = (LivingEntity) entity;
			Player p = (Player) damager;
			ItemStack item = p.getInventory().getItemInMainHand();
			
			if (l instanceof Player && l.getHealth() < e.getDamage()) {
				l.getWorld().strikeLightning(l.getLocation());
				p.setHealth(20);
			}

			if (Util.checkItem(item, Items.getItem("loveIsBlind"))) {
				l.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 0));
				l.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 2));

			} else if (Util.checkItem(item, Items.getItem("brokenHeart"))) {
				l.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60, 1));

			} else if (Util.checkItem(item, Items.getItem("theflamingsword"))) {
				if (l instanceof Damageable && !(l instanceof Player || l instanceof EnderDragon
						|| l instanceof ElderGuardian || l instanceof Wither)) {
					e.setDamage(1000);
				}

			} else if (Util.checkItem(item, Items.getItem("doubleEdgedSword"))) {
				if (l instanceof Damageable) {
					p.setHealth(p.getHealth() / 2);
				}

			} else if (Util.checkItem(item, Items.getItem("mjolnir"))) {
				RCIPlayer rcip = RCIPlayer.getRCIPlayer(p);
				if (l instanceof Damageable) {
					if (!rcip.mjolnirOnCooldown) {
						p.getWorld().strikeLightning(l.getLocation());
						rcip.mjolnirOnCooldown = true;
						rcip.healOnCooldown = true;
						Bukkit.getScheduler().runTaskLater(rci, new Runnable() {
							@Override
							public void run() {
								rcip.mjolnirOnCooldown = false;
							}
						}, 300);
					}
				}
			}

			if (rci.getConfig().getBoolean("stones.enabled")) {
				if (entity instanceof Damageable && !(entity instanceof Player)) {
					Damageable damaged = (Damageable) entity;
					if (e.getDamage() >= damaged.getHealth()) {
						if (Util.percentChance(rci.getConfig().getDouble("stones.droprates.kill-mob"))) {
							p.getInventory().addItem(Items.getItem("stoneThree"));
						}
					}
				}
			}
		}
	}

}
