package com.inceris.runecraftitems;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.inceris.runecraftitems.listeners.BlockBreakListener;
import com.inceris.runecraftitems.listeners.InventoryClickListener;
import com.inceris.runecraftitems.listeners.ProjectileHitListener;

public class RuneCraftItems extends JavaPlugin {

	public static boolean debug = false;

	@Override
	public void onEnable() {

		this.saveDefaultConfig();
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new BlockBreakListener(), this);
		pm.registerEvents(new ProjectileHitListener(), this);
		pm.registerEvents(new InventoryClickListener(), this);

	}

	@Override
	public void onDisable() {

	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("runecraftitems") || label.equalsIgnoreCase("rci")) {
			try {
				if (args.length == 0) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
							"&8[&6R&5C&9I&8] &6Rune&5Craft&9Items &fdeveloped by &cInceris &ffor &9RuneCraft.us"));
					return true;
				}

				if (args[0].equalsIgnoreCase("give") && sender.hasPermission("runecraftitems.admin")) {

					Player p = getServer().getPlayer(args[1]);

					ItemStack item = null;

					if (args[2].equalsIgnoreCase("superpick"))
						item = ItemList.superpick;

					if (args[2].equalsIgnoreCase("grapplinghook"))
						item = ItemList.grapplingHook;

					p.getInventory().addItem(item);
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
							"&8[&6R&5C&9I&8] &fGiven " + p.getName() + " " + item.getItemMeta().getDisplayName()));
					return true;

				} else if (args[0].equalsIgnoreCase("debug") && sender.hasPermission("runecraftitems.admin")) {

					debug = !debug;
					sender.sendMessage(
							ChatColor.translateAlternateColorCodes('&', "&8[&6R&5C&9I&8] &fDebug set to " + debug));
					return true;

				} else if (args[0].equalsIgnoreCase("reload") && sender.hasPermission("runecraftitems.admin")) {
					this.reloadConfig();
					sender.sendMessage(
							ChatColor.translateAlternateColorCodes('&', "&8[&6R&5C&9I&8] &aConfig reloaded."));
					return true;
				} else if (args[0].equalsIgnoreCase("fix") && sender.hasPermission("runecraftitems.fix")) {
					if (sender instanceof Player) {
						Player p = (Player) sender;
						ItemMeta meta = p.getInventory().getItemInMainHand().getItemMeta();
						if (meta instanceof Damageable) {
							Damageable d = (Damageable) meta;
							if (args.length == 1) {
								d.setDamage(0);
							} else {
								int fixTo = d.getDamage() - Integer.parseInt(args[1]);
								if (fixTo < 0)
									fixTo = 0;
								d.setDamage(fixTo);
							}
							p.getInventory().getItemInMainHand().setItemMeta(d);
						}
					}
				}

			} catch (Exception e) {
				sender.sendMessage(
						ChatColor.translateAlternateColorCodes('&', "&8[&6R&5C&9I&8] &cThere was a problem!"));
				e.printStackTrace();
			}
		}
		return false;
	}
}
