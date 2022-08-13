package com.inceris.atsutilities.commands;

import com.inceris.atsutilities.ATSUtilities;
import com.inceris.atsutilities.util.Util;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WB {

	private static ATSUtilities pl = ATSUtilities.getPlugin(ATSUtilities.class);

	public static boolean cmd(CommandSender sender) {

		if (pl.latestJoin == null) {
			sender.sendMessage(Util.colours(
					"&cNobody has joined this server recently enough to welcome! Please note, if somebody has joined the network but not the server you're on, this command will not work."));
			return true;
		}
		
		if (!(sender instanceof Player)) {
			return true;
		}
		
		Player p = (Player) sender;
		
		String name = pl.latestJoin;
		
		String message = "";
		switch (Util.randomNumberBetween(1, 6)) {
		case 1:
			message = "welcome back " + name;
			break;
		case 2:
			message = "wb " + name;
			break;
		case 3:
			message = "weba " + name;
			break;
		case 4:
			message = "hey " + name + "! welcome back";
			break;
		case 5:
			message = "hey " + name + "! Welcome back";
			break;
		case 6:
			message = "welcome back, " + name;
			break;
		}
		
		if (Util.randomNumberBetween(1, 2) == 2) {
			message = Character.toUpperCase(message.charAt(0)) + message.substring(1);
		}
		
		if (Util.randomNumberBetween(1, 2) == 2) {
			message += "!";
		}
		
		p.chat(message);

		return true;

	}

}
