package com.inceris.runecraftitems.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.inceris.runecraftitems.RuneCraftItems;
import com.inceris.runecraftitems.util.Items;

public class RCI {

	private static RuneCraftItems rci = RuneCraftItems.getPlugin(RuneCraftItems.class);

	public static boolean cmd(CommandSender sender, String[] args) {
		try {
			if (args.length == 0) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
						"&8[&6R&5C&9I&8] &6Rune&5Craft&9Items &fdeveloped by &cInceris &ffor &9RuneCraft.us"));
				return true;
			}

			if (args[0].equalsIgnoreCase("give") && sender.hasPermission("runecraftitems.admin")) {

				try {

					Player p = rci.getServer().getPlayer(args[1]);

					ItemStack item = Items.getItem(args[2]);

					if (item == null) {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
								"&8[&6R&5C&9I&8] &cItem called &e" + args[2] + "&c does not exist!"));
						return true;
					} else {
						p.getInventory().addItem(item);
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
								"&8[&6R&5C&9I&8] &fGiven " + p.getName() + " " + item.getItemMeta().getDisplayName()));
						return true;
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			} else if (args[0].equalsIgnoreCase("debug") && sender.hasPermission("runecraftitems.admin")) {

				rci.debug = !rci.debug;
				sender.sendMessage(
						ChatColor.translateAlternateColorCodes('&', "&8[&6R&5C&9I&8] &fDebug set to " + rci.debug));
				return true;

			} else if (args[0].equalsIgnoreCase("reload") && sender.hasPermission("runecraftitems.admin")) {
				rci.reloadConfig();
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&6R&5C&9I&8] &aConfig reloaded."));
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
