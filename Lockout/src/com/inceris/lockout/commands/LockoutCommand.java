package com.inceris.lockout.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.inceris.lockout.Lockout;
import com.inceris.lockout.util.GameInstance;
import com.inceris.lockout.util.Objective;
import com.inceris.lockout.util.Util;

public class LockoutCommand {

	private static Lockout pl = (Lockout) Lockout.getPlugin(Lockout.class);
	
	public static boolean cmd(CommandSender sender, String[] args) {
		if (args.length == 0) {
			sender.sendMessage(Util.format("Developed by &cInceris &ffor &9play.atownyserver.com"));
			return true;
			
		} else if (args[0].equalsIgnoreCase("start")) {
			if (pl.getServer().getPlayer(args[1]) != null && pl.getServer().getPlayer(args[2]) != null) {
				
				GameInstance gi = Util.chooseWorld();
				Player p1 = pl.getServer().getPlayer(args[1]);
				Player p2 = pl.getServer().getPlayer(args[2]);
				if (GameInstance.get(p1) == null && GameInstance.get(p2) == null) {
					if (gi != null) {
						gi.setActive(true);
						gi.setStartTime(System.currentTimeMillis());
						gi.getPlayerScores().put(p1, 0);
						gi.getPlayerScores().put(p2, 0);
						gi.setObjectives(Objective.chooseObjectives(false));
						return true;
					} else {
						sender.sendMessage(Util.format("All the lockout worlds are full!"));
					}
				} else {
					sender.sendMessage(Util.format("One or both of those players are already in a game of lockout!"));
				}
				
			} else {
				sender.sendMessage(Util.format("&9/lockout start [player 1] [player 2]"));
				return true;
			}
			
		} else if (args[0].equalsIgnoreCase("stop")) {
			if (args.length == 1 && sender instanceof Player) {
				GameInstance.get((Player)sender).reset();
				return true;
			} else if (pl.getServer().getPlayer(args[1]) != null) {
				GameInstance.get(pl.getServer().getPlayer(args[1])).reset();
				return true;
			}
			
		} else if (args[0].equalsIgnoreCase("objectives")) {
			if (sender instanceof Player) {
				GameInstance gi = GameInstance.get((Player) sender);
				if (gi == null) {
					sender.sendMessage(Util.format("You're not in a game of lockout!"));
				} else {
					sender.sendMessage(Util.format(gi.printObjectives()));
				}
			}
		} else if (args[0].equalsIgnoreCase("info")) {
			if (args.length == 1) {
				if (sender instanceof Player) {
					GameInstance gi = GameInstance.get((Player) sender);
					if (gi == null) {
						sender.sendMessage(Util.format("You're not in a game of lockout!"));
					} else {
						sender.sendMessage(Util.format("World: " + gi.getWorld().getName()));
						sender.sendMessage(Util.format("Active: " + gi.isActive()));
						sender.sendMessage(Util.format("Start Time: " + gi.getStartTime()));
					}
				}
			} else if (pl.getServer().getWorld(args[1]) != null) {
				GameInstance gi = GameInstance.get(pl.getServer().getWorld(args[1]));
				sender.sendMessage(Util.format("World: " + gi.getWorld().getName()));
				sender.sendMessage(Util.format("Active: " + gi.isActive()));
				sender.sendMessage(Util.format("Start Time: " + gi.getStartTime()));
			}
		}
		
		return false;
	}
	
}
