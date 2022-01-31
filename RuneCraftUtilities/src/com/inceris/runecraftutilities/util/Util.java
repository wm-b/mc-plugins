package com.inceris.runecraftutilities.util;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import com.inceris.runecraftutilities.RuneCraftUtilities;

public class Util {

	private static RuneCraftUtilities rcu = RuneCraftUtilities.getPlugin(RuneCraftUtilities.class);

	public static void sendCommand(String command) {
		ConsoleCommandSender console = rcu.getServer().getConsoleSender();
		Bukkit.dispatchCommand((CommandSender) console, command);
	}
}
