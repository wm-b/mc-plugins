package com.inceris.lockout;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.inceris.lockout.commands.LockoutCommand;
import com.inceris.lockout.listeners.EntityBreedListener;
import com.inceris.lockout.listeners.EntityDamageByEntityListener;
import com.inceris.lockout.listeners.EntityPickupItemListener;
import com.inceris.lockout.listeners.InventoryClickListener;
import com.inceris.lockout.listeners.PlayerInteractListener;
import com.inceris.lockout.listeners.PlayerItemConsumeListener;
import com.inceris.lockout.listeners.PlayerPortalListener;
import com.inceris.lockout.listeners.PlayerQuitListener;
import com.inceris.lockout.util.GameInstance;

public class Lockout extends JavaPlugin {
	
	public List<GameInstance> gameInstances = new ArrayList<GameInstance>();
	
	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new EntityDamageByEntityListener(), this);
		pm.registerEvents(new PlayerItemConsumeListener(), this);
		pm.registerEvents(new PlayerPortalListener(), this);
		pm.registerEvents(new PlayerQuitListener(), this);
		pm.registerEvents(new PlayerInteractListener(), this);
		pm.registerEvents(new InventoryClickListener(), this);
		pm.registerEvents(new EntityPickupItemListener(), this);
		pm.registerEvents(new EntityBreedListener(), this);
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
