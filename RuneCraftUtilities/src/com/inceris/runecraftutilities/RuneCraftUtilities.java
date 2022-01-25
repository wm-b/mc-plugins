package com.inceris.runecraftutilities;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.inceris.runecraftutilities.listeners.ItemUseListener;

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
		try {
			if (label.equalsIgnoreCase("runecraftutilities") || label.equalsIgnoreCase("rcu")) {
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
				}
			}
		} catch (Exception e) {
			sender.sendMessage(ChatColor.RED + "There was a problem!");
			e.printStackTrace();
		}
		return false;
	}
}