package com.inceris.atsutilities.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.inceris.atsutilities.ATSUtilities;
import com.inceris.atsutilities.util.Util;

public class Transform {

	private static ATSUtilities atsu = ATSUtilities.getPlugin(ATSUtilities.class);

	public static boolean cmd(String[] args) {

		if (!(Bukkit.getServer().getPlayer(args[0]) instanceof Player)) {
			return false;
		}

		EntityType entityType = null;
		switch (args[1].toUpperCase()) {
		case "ALLAY":
			entityType = EntityType.ALLAY;
			break;
		case "AXOLOTL":
			entityType = EntityType.AXOLOTL;
			break;
		case "BAT":
			entityType = EntityType.BAT;
			break;
		case "CAT":
			entityType = EntityType.CAT;
			break;
		case "CHICKEN":
			entityType = EntityType.CHICKEN;
			break;
		case "COD":
			entityType = EntityType.COD;
			break;
		case "COW":
			entityType = EntityType.COW;
			break;
		case "DONKEY":
			entityType = EntityType.DONKEY;
			break;
		case "FOX":
			entityType = EntityType.FOX;
			break;
		case "FROG":
			entityType = EntityType.FROG;
			break;
		case "GLOW_SQUID":
			entityType = EntityType.GLOW_SQUID;
			break;
		case "HORSE":
			entityType = EntityType.HORSE;
			break;
		case "MULE":
			entityType = EntityType.MULE;
			break;
		case "OCELOT":
			entityType = EntityType.OCELOT;
			break;
		case "PIG":
			entityType = EntityType.PIG;
			break;
		case "PUFFERFISH":
			entityType = EntityType.PUFFERFISH;
			break;
		case "RABBIT":
			entityType = EntityType.RABBIT;
			break;
		case "SALMON":
			entityType = EntityType.SALMON;
			break;
		case "SHEEP":
			entityType = EntityType.SHEEP;
			break;
		case "SNOWMAN":
			entityType = EntityType.SNOWMAN;
			break;
		case "SQUID":
			entityType = EntityType.SQUID;
			break;
		case "STRIDER":
			entityType = EntityType.STRIDER;
			break;
		case "TADPOLE":
			entityType = EntityType.TADPOLE;
			break;
		case "TROPICAL_FISH":
			entityType = EntityType.TROPICAL_FISH;
			break;
		case "TURTLE":
			entityType = EntityType.TURTLE;
			break;
		case "VILLAGER":
			entityType = EntityType.VILLAGER;
			break;
		case "WANDERING_TRADER":
			entityType = EntityType.WANDERING_TRADER;
			break;
		case "ZOMBIE_HORSE":
			entityType = EntityType.ZOMBIE_HORSE;
			break;
		case "SKELETON_HORSE":
			entityType = EntityType.SKELETON_HORSE;
			break;
		case "LLAMA":
			entityType = EntityType.LLAMA;
			break;
		case "BEE":
			entityType = EntityType.BEE;
			break;
		case "WOLF":
			entityType = EntityType.WOLF;
			break;
		}
		
		if (entityType == null) {
			return false;
		}

		int transformForSeconds = 5;
		if (args.length >= 3 && Util.regex(args[2], "\\A[0-9]+\\Z")) {
			transformForSeconds = Integer.parseInt(args[2]);
		}

		Player p = Bukkit.getServer().getPlayer(args[0]);
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		Bukkit.dispatchCommand(console,
				"execute at " + p.getName() + " run particle minecraft:smoke ~ ~1 ~ 0.25 0.5 0.25 0 100");
		Bukkit.dispatchCommand(console,
				"execute at " + p.getName() + " run particle minecraft:large_smoke ~ ~1 ~ 0.25 0.5 0.25 0 20");
		Bukkit.dispatchCommand(console,
				"execute at " + p.getName() + " run playsound minecraft:entity.enderman.teleport master @a ~ ~ ~ 1 1");
		LivingEntity entity = (LivingEntity) p.getWorld().spawnEntity(p.getLocation(), entityType);
		atsu.transformMobs.add(entity.getUniqueId());
		entity.setCustomName(p.getName());
		entity.setCustomNameVisible(true);
		entity.setRemoveWhenFarAway(false);
		p.setGameMode(GameMode.SPECTATOR);
		p.setSpectatorTarget(entity);
		if (p.getSpectatorTarget() == null || !(p.getSpectatorTarget().getUniqueId().equals(entity.getUniqueId()))) {
			p.setGameMode(GameMode.SURVIVAL);
			atsu.transformMobs.remove(entity.getUniqueId());
			entity.remove();
		} else {
			transformRecursion(p, entity, 0, transformForSeconds * 5);
		}
		return true;
	}

	public static void transformRecursion(Player p, LivingEntity entity, int count, int countTo) {

		final int pass = count + 1;

		if (pass >= countTo) {
			atsu.transformMobs.remove(entity.getUniqueId());
			entity.remove();
			if (p.getGameMode().equals(GameMode.SPECTATOR)) {
				ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
				Bukkit.dispatchCommand(console,
						"execute at " + p.getName() + " run particle minecraft:smoke ~ ~1 ~ 0.25 0.5 0.25 0 100");
				Bukkit.dispatchCommand(console,
						"execute at " + p.getName() + " run particle minecraft:large_smoke ~ ~1 ~ 0.25 0.5 0.25 0 20");
				Bukkit.dispatchCommand(console, "execute at " + p.getName()
						+ " run playsound minecraft:entity.enderman.teleport master @a ~ ~ ~ 1 1");
				p.setGameMode(GameMode.SURVIVAL);
			}
			return;
		}

		if (!p.getGameMode().equals(GameMode.SPECTATOR)) {
			ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
			Bukkit.dispatchCommand(console,
					"execute at " + p.getName() + " run particle minecraft:smoke ~ ~1 ~ 0.25 0.5 0.25 0 100");
			Bukkit.dispatchCommand(console,
					"execute at " + p.getName() + " run particle minecraft:large_smoke ~ ~1 ~ 0.25 0.5 0.25 0 20");
			Bukkit.dispatchCommand(console,
					"execute at " + p.getName() + " run playsound minecraft:entity.enderman.teleport master @a ~ ~ ~ 1 1");
			atsu.transformMobs.remove(entity.getUniqueId());
			entity.remove();
			return;
		}

		if (entity.isDead()) {
			atsu.transformMobs.remove(entity.getUniqueId());
			p.setGameMode(GameMode.SURVIVAL);
			ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
			Bukkit.dispatchCommand(console,
					"execute at " + p.getName() + " run particle minecraft:smoke ~ ~1 ~ 0.25 0.5 0.25 0 100");
			Bukkit.dispatchCommand(console,
					"execute at " + p.getName() + " run particle minecraft:large_smoke ~ ~1 ~ 0.25 0.5 0.25 0 20");
			Bukkit.dispatchCommand(console, "execute at " + p.getName()
					+ " run playsound minecraft:entity.enderman.teleport master @a ~ ~ ~ 1 1");
			entity.remove();
			return;
		}

		if (p.getSpectatorTarget() == null || !(p.getSpectatorTarget().getUniqueId().equals(entity.getUniqueId()))) {
			p.setSpectatorTarget(entity);
		}

		Bukkit.getScheduler().runTaskLater(atsu, new Runnable() {
			@Override
			public void run() {
				transformRecursion(p, entity, pass, countTo);
			}
		}, 4);
	}
}
