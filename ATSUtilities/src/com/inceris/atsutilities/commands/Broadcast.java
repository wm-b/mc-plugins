package com.inceris.atsutilities.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import com.inceris.atsutilities.ATSUtilities;

public class Broadcast {
	
	private static ATSUtilities atsu = ATSUtilities.getPlugin(ATSUtilities.class);

	public static boolean cmd(String[] args) {
		Bukkit.getScheduler().runTask(atsu, new Runnable() {
			@Override
			public void run() {
				String message = "";
				for (int i = 0; i < args.length; i++) {
					if (i != 0)
						message += " ";
					message += args[i];
				}
				Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message));
			}
		});
		
		return true;
	}
}
