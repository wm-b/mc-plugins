package com.inceris.runecraftitems;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.inceris.runecraftitems.commands.RCI;
import com.inceris.runecraftitems.listeners.BlockBreakListener;
import com.inceris.runecraftitems.listeners.EntityDamageByEntityListener;
import com.inceris.runecraftitems.listeners.InventoryClickListener;
import com.inceris.runecraftitems.listeners.ItemUseListener;
import com.inceris.runecraftitems.listeners.PlayerItemHeldListener;
import com.inceris.runecraftitems.listeners.PlayerJoinListener;
import com.inceris.runecraftitems.listeners.PlayerQuitListener;
import com.inceris.runecraftitems.listeners.ProjectileHitListener;

public class RuneCraftItems extends JavaPlugin {

	public boolean debug = false;

	@Override
	public void onEnable() {

		this.saveDefaultConfig();
		PluginManager pm = getServer().getPluginManager();
		Util.registerPlayers();
		pm.registerEvents(new BlockBreakListener(), this);
		pm.registerEvents(new ProjectileHitListener(), this);
		pm.registerEvents(new InventoryClickListener(), this);
		pm.registerEvents(new ItemUseListener(), this);
		pm.registerEvents(new PlayerItemHeldListener(), this);
		pm.registerEvents(new PlayerJoinListener(), this);
		pm.registerEvents(new PlayerQuitListener(), this);
		pm.registerEvents(new EntityDamageByEntityListener(), this);

	}

	@Override
	public void onDisable() {

	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		try {
			return RCI.cmd(sender, label, args);
		} catch (Exception e) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&6R&5C&9I&8] &cThere was a problem!"));
			e.printStackTrace();
		}
		return false;
	}
}
