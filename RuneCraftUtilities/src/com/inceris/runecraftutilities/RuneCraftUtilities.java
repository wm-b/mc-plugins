package com.inceris.runecraftutilities;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.inceris.runecraftutilities.commands.RCU;
import com.inceris.runecraftutilities.listeners.BossDeathListener;
import com.inceris.runecraftutilities.listeners.ItemUseListener;


public class RuneCraftUtilities extends JavaPlugin {

	private File bosskills;
	
	public File getBosskills() {
		return bosskills;
	}

	private FileConfiguration bosskillsConfig;

	public FileConfiguration getBosskillsConfig() {
		return bosskillsConfig;
	}

	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new ItemUseListener(), this);
		pm.registerEvents(new BossDeathListener(), this);
		
		bosskills = new File(getDataFolder()+File.separator+"bosskills.yml");
		if (!bosskills.exists()) {
		    try {
		    	bosskills.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		bosskillsConfig = YamlConfiguration.loadConfiguration(bosskills);
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