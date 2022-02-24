package com.inceris.lockout.listeners;

import java.util.List;

import org.bukkit.entity.Blaze;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.ElderGuardian;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Illager;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Player;
import org.bukkit.entity.Silverfish;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Wither;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.entity.ZombieVillager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.inceris.lockout.Lockout;
import com.inceris.lockout.util.GameInstance;
import com.inceris.lockout.util.Objective;

public class EntityDamageByEntityListener implements Listener {

	public static Lockout pl = Lockout.getPlugin(Lockout.class);
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
		
		Entity damager = e.getDamager();
		Entity entity = e.getEntity();
		if (damager instanceof Player && entity instanceof Damageable && entity instanceof LivingEntity) {
			Damageable d = (Damageable) entity;
			if (d.getHealth() <= e.getDamage()) {
				Player p = (Player) damager;
				for (GameInstance gi : pl.gameInstances) {
					if (gi.isActive() && gi.getPlayerScores().keySet().contains(p)) {
						List<Objective> objectives = gi.getObjectives();

						if (d instanceof Zombie && objectives.contains(Objective.killZombie)) {
							Objective.complete(gi, Objective.killZombie, p);

						} else if (d instanceof Spider && objectives.contains(Objective.killSpider)) {
							Objective.complete(gi, Objective.killSpider, p);

						} else if (d instanceof Creeper && objectives.contains(Objective.killCreeper)) {
							Objective.complete(gi, Objective.killCreeper, p);

						} else if (d instanceof ZombieVillager && objectives.contains(Objective.killZombieVillager)) {
							Objective.complete(gi, Objective.killZombieVillager, p);

						} else if (d instanceof Skeleton && objectives.contains(Objective.killSkeleton)) {
							Objective.complete(gi, Objective.killSkeleton, p);

						} else if (d instanceof Silverfish && objectives.contains(Objective.killSilverfish)) {
							Objective.complete(gi, Objective.killSilverfish, p);

						} else if (d instanceof IronGolem && objectives.contains(Objective.killIronGolem)) {
							Objective.complete(gi, Objective.killIronGolem, p);

						} else if (d instanceof Witch && objectives.contains(Objective.killWitch)) {
							Objective.complete(gi, Objective.killWitch, p);

						} else if (d instanceof WitherSkeleton && objectives.contains(Objective.killWitherSkeleton)) {
							Objective.complete(gi, Objective.killWitherSkeleton, p);

						} else if (d instanceof Blaze && objectives.contains(Objective.killBlaze)) {
							Objective.complete(gi, Objective.killBlaze, p);

						} else if (d instanceof Phantom && objectives.contains(Objective.killPhantom)) {
							Objective.complete(gi, Objective.killPhantom, p);

						} else if (d instanceof Illager && objectives.contains(Objective.killIllager)) {
							Objective.complete(gi, Objective.killIllager, p);

						} else if (d instanceof EnderDragon && objectives.contains(Objective.killEnderDragon)) {
							Objective.complete(gi, Objective.killEnderDragon, p);

						} else if (d instanceof Wither && objectives.contains(Objective.killWither)) {
							Objective.complete(gi, Objective.killWither, p);

						} else if (d instanceof ElderGuardian && objectives.contains(Objective.killElderGuardian)) {
							Objective.complete(gi, Objective.killElderGuardian, p);

						}
					}
				}
			}
		}
	}
}
