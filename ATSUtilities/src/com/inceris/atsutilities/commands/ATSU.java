package com.inceris.atsutilities.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.inceris.atsutilities.ATSUtilities;
import com.inceris.atsutilities.util.Items;

public class ATSU {

	private static ATSUtilities atsu = ATSUtilities.getPlugin(ATSUtilities.class);

	public static boolean cmd(CommandSender sender, String[] args) {

		if (args.length == 0) {
			sender.sendMessage("ATSUtilities developed for play.atownyserver.com by Inceris");
			
		} else if (args[0].equalsIgnoreCase("denysin") && sender.hasPermission("atsutilities.admin")) {
			atsu.denyTallGrass = !atsu.denyTallGrass;
			sender.sendMessage("[ATSUtilities] DenyTallGrass mode set to " + atsu.denyTallGrass);
			
		} else if (args[0].equalsIgnoreCase("debug") && sender.hasPermission("atsutilities.admin")) {
			atsu.debug = !atsu.debug;
			sender.sendMessage("[ATSUtilities] Debug mode set to " + atsu.debug);
			
		} else if (args[0].equalsIgnoreCase("give") && sender.hasPermission("atsutilities.admin")) {

			Player p = atsu.getServer().getPlayer(args[1]);

			ItemStack item = Items.getItem(args[2]);

			if (item == null) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
						"&8[&4Server&8] &cItem called &e" + args[2] + "&c does not exist!"));
				return true;
			} else {
				p.getInventory().addItem(item);
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
						"&8[&4Server&8] &fGiven " + p.getName() + " " + item.getItemMeta().getDisplayName()));
				return true;
			}
		}
		return false;
	}

}
