package com.inceris.atsutilities.commands;

import org.bukkit.entity.Player;

import com.inceris.atsutilities.ATSUtilities;
import com.inceris.atsutilities.util.Util;

import org.bukkit.command.CommandSender;

public class WB {
	
	private static ATSUtilities pl = ATSUtilities.getPlugin(ATSUtilities.class);

	public static boolean cmd(CommandSender sender) {
		
		if (pl.latestJoin == null) {
			sender.sendMessage(Util.colours("&cNobody has joined this server recently enough to welcome! Please note, if somebody has joined the network but not the server you're on, this command will not work."));
			return true;
		}
		
		if (!(sender instanceof Player)) {
			return true;
		}
		
		Player p = (Player) sender;
		
		p.chat("Welcome back, " + pl.latestJoin + "!");
		
		return true;
		
	}
	
}
