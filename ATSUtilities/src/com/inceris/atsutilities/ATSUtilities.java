package com.inceris.atsutilities;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.inceris.atsutilities.listeners.PrepareAnvilListener;
import com.inceris.atsutilities.commands.ATSRTP;
import com.inceris.atsutilities.commands.Blink;
import com.inceris.atsutilities.commands.BrewAtRandom;
import com.inceris.atsutilities.commands.Broadcast;
import com.inceris.atsutilities.commands.Leap;
import com.inceris.atsutilities.listeners.DurabilityLossListener;
import com.inceris.atsutilities.listeners.IronGolemDeathListener;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

public class ATSUtilities extends JavaPlugin {
	
	public static boolean debug = false;

	@Override
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new IronGolemDeathListener(), this);
		pm.registerEvents(new DurabilityLossListener(), this);
		pm.registerEvents(new PrepareAnvilListener(), this);
	}

	@Override
	public void onDisable() {

	}

	public void SendCommand(String command) {
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		Bukkit.dispatchCommand(console, command);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		try {
			if (label.equalsIgnoreCase("atsutilities")) {
				
				if (args.length == 0) {
					sender.sendMessage("ATSUtilities developed for play.atownyserver.com by Inceris");
				}
				
				if (args[0].equalsIgnoreCase("debug")) {
					debug = !debug;
					sender.sendMessage("[ATSUtilities] Debug mode set to " + debug);
				}

				return true;
			}
			
			if (label.equalsIgnoreCase("atsrtp")) {
				ATSRTP.cmd(args);
				return true;
			}

			if (label.equalsIgnoreCase("bc") || label.equalsIgnoreCase("broadcast")) {
				Broadcast.cmd(args);
				return true;
			}

			if (label.equalsIgnoreCase("blink")) {
				Blink.cmd(args);
				return true;
			}

			if (label.equalsIgnoreCase("leap")) {
				Leap.cmd(args);
				return true;
			}

			if (label.equalsIgnoreCase("brewatrandom")) {
				BrewAtRandom.cmd(args[0]);
				return true;
			}

		} catch (Exception e) {
			getLogger().info(e.fillInStackTrace().getMessage());
		}

		return false;
	}
}