package com.inceris.runecraftutilities.util;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.mineacademy.boss.BossPlugin;

import com.inceris.runecraftutilities.RuneCraftUtilities;

public class Util {
	
	public static BossPlugin boss = BossPlugin.getPlugin(BossPlugin.class);

	private static RuneCraftUtilities pl = RuneCraftUtilities.getPlugin(RuneCraftUtilities.class);

	public static void sendCommand(String command) {
		ConsoleCommandSender console = pl.getServer().getConsoleSender();
		Bukkit.dispatchCommand((CommandSender) console, command);
	}
}
