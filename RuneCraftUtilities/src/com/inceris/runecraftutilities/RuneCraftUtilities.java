package com.inceris.runecraftutilities;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.inceris.runecraftutilities.commands.RCU;
import com.inceris.runecraftutilities.listeners.ItemUseListener;

public class RuneCraftUtilities extends JavaPlugin {

	@Override
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new ItemUseListener(), this);
	}

	@Override
	public void onDisable() {

	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		try {
			if (label.equalsIgnoreCase("runecraftutilities") || label.equalsIgnoreCase("rcu")) {
				return RCU.cmd(sender, args);
			}
		} catch (Exception e) {
			sender.sendMessage(ChatColor.RED + "There was a problem!");
			e.printStackTrace();
		}
		return false;
	}
}