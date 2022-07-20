package com.inceris.runecraftitems;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.inceris.runecraftitems.commands.RCI;
import com.inceris.runecraftitems.listeners.BlockBreakListener;
import com.inceris.runecraftitems.listeners.CraftItemListener;
import com.inceris.runecraftitems.listeners.EntityDamageByEntityListener;
import com.inceris.runecraftitems.listeners.InventoryClickListener;
import com.inceris.runecraftitems.listeners.PlayerInteractListener;
import com.inceris.runecraftitems.listeners.PlayerItemConsumeListener;
import com.inceris.runecraftitems.listeners.PlayerItemDamageListener;
import com.inceris.runecraftitems.listeners.PlayerDropItemListener;
import com.inceris.runecraftitems.listeners.PlayerFishListener;
import com.inceris.runecraftitems.listeners.PlayerInteractEntityListener;
import com.inceris.runecraftitems.listeners.PlayerItemHeldListener;
import com.inceris.runecraftitems.listeners.PlayerJoinListener;
import com.inceris.runecraftitems.listeners.PlayerMoveListener;
import com.inceris.runecraftitems.listeners.PlayerQuitListener;
import com.inceris.runecraftitems.listeners.PrepareAnvilListener;
import com.inceris.runecraftitems.listeners.ProjectileHitListener;
import com.inceris.runecraftitems.util.Items;
import com.inceris.runecraftitems.util.Util;

public class RuneCraftItems extends JavaPlugin {

	public boolean debug = false;
	public List<Player> wearingDevilsGreenCoat = new ArrayList<Player>();

	@Override
	public void onEnable() {

		this.saveDefaultConfig();
		PluginManager pm = getServer().getPluginManager();
		Util.registerPlayers();
		pm.registerEvents(new BlockBreakListener(), this);
		pm.registerEvents(new ProjectileHitListener(), this);
		pm.registerEvents(new InventoryClickListener(), this);
		pm.registerEvents(new PlayerInteractListener(), this);
		pm.registerEvents(new PlayerItemHeldListener(), this);
		pm.registerEvents(new PlayerJoinListener(), this);
		pm.registerEvents(new PlayerQuitListener(), this);
		pm.registerEvents(new EntityDamageByEntityListener(), this);
		pm.registerEvents(new PlayerDropItemListener(), this);
		pm.registerEvents(new PlayerInteractEntityListener(), this);
		pm.registerEvents(new PlayerItemConsumeListener(), this);
		pm.registerEvents(new CraftItemListener(), this);
		pm.registerEvents(new PlayerFishListener(), this);
		pm.registerEvents(new PlayerMoveListener(), this);
		pm.registerEvents(new PrepareAnvilListener(), this);
		pm.registerEvents(new PlayerItemDamageListener(), this);
		Util.setupEconomy();

		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				if (wearingDevilsGreenCoat != null) {
					for (Player p : wearingDevilsGreenCoat) {
						if (Util.checkItem(p.getInventory().getItem(38), Items.getItem("devilsgreencoat"))) {
							p.sendMessage(Util.colours("&a&l+ $50"));
							Util.getEcon().depositPlayer(p, 50);
						} else {
							wearingDevilsGreenCoat.remove(p);
						}
					}
				}
			}
		}, 0, 2400);
	}

	@Override
	public void onDisable() {

	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		try {
			if (label.equalsIgnoreCase("runecraftitems") || label.equalsIgnoreCase("rci")) {
				return RCI.cmd(sender, args);
			}
		} catch (Exception e) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&6R&5C&9I&8] &cThere was a problem!"));
			e.printStackTrace();
		}
		return false;
	}
}
