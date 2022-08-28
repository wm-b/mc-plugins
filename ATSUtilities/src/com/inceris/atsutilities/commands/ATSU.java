package com.inceris.atsutilities.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.inceris.atsutilities.ATSUtilities;
import com.inceris.atsutilities.util.Items;

public class ATSU {

	private static ATSUtilities atsu = ATSUtilities.getPlugin(ATSUtilities.class);

	public static boolean cmd(CommandSender sender, String[] args) {

		if (args.length == 0) {
			sender.sendMessage("ATSUtilities developed for play.atownyserver.com by Inceris");

		} else if (args[0].equalsIgnoreCase("denysin") && sender.hasPermission("atsutilities.admin")) {
			atsu.denyTallGrass = !atsu.denyTallGrass;
			sender.sendMessage("[ATSUtilities] DenyTallGrass mode set to " + atsu.denyTallGrass);

		} else if (args[0].equalsIgnoreCase("denyinfested") && sender.hasPermission("atsutilities.admin")) {
			atsu.denyInfested = !atsu.denyInfested;
			sender.sendMessage("[ATSUtilities] DenyInfested mode set to " + atsu.denyInfested);

		} else if (args[0].equalsIgnoreCase("debug") && sender.hasPermission("atsutilities.admin")) {
			atsu.debug = !atsu.debug;
			sender.sendMessage("[ATSUtilities] Debug mode set to " + atsu.debug);

		} else if (args[0].equalsIgnoreCase("vampiriccocktail") && sender.hasPermission("atsutilities.admin")) {
			
			if (!(Bukkit.getServer().getPlayer(args[1]) instanceof Player)) {
				return false;
			}
			
			Player p = Bukkit.getServer().getPlayer(args[1]);
			ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
			Bukkit.dispatchCommand(console, "execute at " + p.getName() + " run particle minecraft:smoke ~ ~1 ~ 0.25 0.5 0.25 0 100");
			Bukkit.dispatchCommand(console, "execute at " + p.getName() + " run particle minecraft:large_smoke ~ ~1 ~ 0.25 0.5 0.25 0 20");
			Bukkit.dispatchCommand(console, "execute at " + p.getName() + " run playsound minecraft:entity.enderman.teleport master @a ~ ~ ~ 1 1");
			LivingEntity entity = (LivingEntity) p.getWorld().spawnEntity(p.getLocation(), EntityType.BAT);
			entity.setCustomName(p.getName());
			entity.setCustomNameVisible(true);
			entity.setRemoveWhenFarAway(false);
			p.setGameMode(GameMode.SPECTATOR);
			p.setSpectatorTarget(entity);
			if(p.getSpectatorTarget() == null || !(p.getSpectatorTarget().getUniqueId().equals(entity.getUniqueId()))) {
				p.setGameMode(GameMode.SURVIVAL);
			}
			Bukkit.getScheduler().runTaskLater(atsu, new Runnable() {
				@Override
				public void run() {
					if (p.getGameMode().equals(GameMode.SPECTATOR)) {
						Bukkit.dispatchCommand(console, "execute at " + p.getName() + " run particle minecraft:smoke ~ ~1 ~ 0.25 0.5 0.25 0 100");
						Bukkit.dispatchCommand(console, "execute at " + p.getName() + " run particle minecraft:large_smoke ~ ~1 ~ 0.25 0.5 0.25 0 20");
						Bukkit.dispatchCommand(console, "execute at " + p.getName() + " run playsound minecraft:entity.enderman.teleport master @a ~ ~ ~ 1 1");
						entity.remove();
						p.setGameMode(GameMode.SURVIVAL);
					}
				}
			}, Integer.parseInt(args[2]) * 20);
			checkInPOV(p, entity, 0, Integer.parseInt(args[2]) * 5);
			return true;

		} else if (args[0].equalsIgnoreCase("give") && sender.hasPermission("atsutilities.admin")) {

			Player p = atsu.getServer().getPlayer(args[1]);

			ItemStack item = Items.getItem(args[2]);

			if (item == null) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
						"&8[&4Server&8] &cItem called &e" + args[2] + "&c does not exist!"));
				return true;
			} else {
				p.getInventory().addItem(item);
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
						"&8[&4Server&8] &fGiven " + p.getName() + " " + item.getItemMeta().getDisplayName()));
				return true;
			}
		} else if (args[0].equalsIgnoreCase("giveperm")) {
			if (!(atsu.getServer().getPlayer(args[1]) instanceof Player)) {
				sender.sendMessage("That doesn't look like a player!");
				return false;
			}

			if (args[2].contains("gadgetsmenu.suits.") || args[2].contains("gadgetsmenu.gadgets.")) {
				Bukkit.dispatchCommand(sender, "lp user " + args[1] + " permission set " + args[2] + " true world="
						+ atsu.getConfig().getString("limit-gadget-and-suit-to-world"));
			} else {
				Bukkit.dispatchCommand(sender, "lp user " + args[1] + " permission set " + args[2]);
			}
			return true;
		}
		return false;
	}
	
	public static void checkInPOV(Player p, LivingEntity entity, int count, int countTo) {
		
		final int pass = count++;
		
		if (count >= countTo) {
			return;
		}
		
		if (!p.getGameMode().equals(GameMode.SPECTATOR)) {
			return;
		}
		
		if (entity.isDead()) {
			p.setGameMode(GameMode.SURVIVAL);
			ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
			Bukkit.dispatchCommand(console, "execute at " + p.getName() + " run particle minecraft:smoke ~ ~1 ~ 0.25 0.5 0.25 0 100");
			Bukkit.dispatchCommand(console, "execute at " + p.getName() + " run particle minecraft:large_smoke ~ ~1 ~ 0.25 0.5 0.25 0 20");
			Bukkit.dispatchCommand(console, "execute at " + p.getName() + " run playsound minecraft:entity.enderman.teleport master @a ~ ~ ~ 1 1");
			entity.remove();
			return;
		}
		
		if (p.getSpectatorTarget() == null || !(p.getSpectatorTarget().getUniqueId().equals(entity.getUniqueId()))) {
			p.setSpectatorTarget(entity);
		}
		
		Bukkit.getScheduler().runTaskLater(atsu, new Runnable() {
			@Override
			public void run() {
				checkInPOV(p, entity, pass, countTo);
			}
		}, 4);
	}

}
