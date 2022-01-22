package com.inceris.runecraftutilities;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

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
		if (label.equalsIgnoreCase("runecraftutilities") || label.equalsIgnoreCase("rcu")) {
			try {
				if (args.length == 0) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&6R&5C&bU&8] &6Rune&5Craft&bUtilities &fdeveloped by &cInceris &ffor &9RuneCraft.us"));
					return true;
				}
			}
			catch(Exception e) {
				sender.sendMessage(ChatColor.RED + "There was a problem!");
				e.printStackTrace();
			}
		}
		return false;
	}
}
