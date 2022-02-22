package com.inceris.lockout;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.inceris.lockout.commands.LockoutCommand;
import com.inceris.lockout.util.GameInstance;
import com.inceris.lockout.util.Util;

public class Lockout extends JavaPlugin {
	
	public List<GameInstance> gameInstances = new ArrayList<GameInstance>();
	
	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		Util.initialise();
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("lockout")) {
			try {
				return LockoutCommand.cmd(sender, args);
			}
			catch(Exception e) {
				sender.sendMessage(ChatColor.RED + "There was a problem!");
				e.printStackTrace();
			}
		}
		return false;
	}
}
