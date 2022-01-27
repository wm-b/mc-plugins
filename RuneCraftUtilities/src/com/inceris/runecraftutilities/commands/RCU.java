package com.inceris.runecraftutilities.commands;

import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import com.inceris.runecraftutilities.RuneCraftUtilities;

public class RCU {
	
	private static RuneCraftUtilities rcu = RuneCraftUtilities.getPlugin(RuneCraftUtilities.class);
	
	public static boolean cmd(CommandSender sender, String[] args) {

		if (args.length == 0) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
					"&8[&6R&5C&bU&8] &6Rune&5Craft&bUtilities &fdeveloped by &cInceris &ffor &9RuneCraft.us"));
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
			return true;
			
		} else if (args[0].equalsIgnoreCase("heal") && sender.hasPermission("runecraftitems.heal")) {
			Player p = rcu.getServer().getPlayer(args[1]);
			p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
		}
		return false;
	}
	
}
