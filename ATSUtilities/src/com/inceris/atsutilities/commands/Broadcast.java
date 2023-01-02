package com.inceris.atsutilities.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import com.inceris.atsutilities.ATSUtilities;

public class Broadcast {
	
	private static final ATSUtilities atsu = ATSUtilities.getPlugin(ATSUtilities.class);

	public static boolean cmd(String[] args) {
		Bukkit.getScheduler().runTask(atsu, () -> {
			StringBuilder message = new StringBuilder();
			for (int i = 0; i < args.length; i++) {
				if (i != 0)
					message.append(" ");
				message.append(args[i]);
			}
			Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message.toString()));
		});
		
		return true;
	}
}
