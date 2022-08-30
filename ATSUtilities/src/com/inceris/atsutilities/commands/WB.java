package com.inceris.atsutilities.commands;

import com.inceris.atsutilities.ATSUtilities;
import com.inceris.atsutilities.util.Util;

import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WB {

	private static ATSUtilities pl = ATSUtilities.getPlugin(ATSUtilities.class);

	public static boolean cmd(CommandSender sender, String[] args) {
		
		if (!(sender instanceof Player)) {
			return false;
		}
		
		Player p = (Player) sender;
		
		if (!wbData(p, args)) {
			return true;
		}
		
		if (pl.wbData.getStringList("WorkbenchPreferred").contains(p.getUniqueId().toString())) {
			Bukkit.dispatchCommand(sender, "craft");
			return true;
		}

		if (pl.latestJoin == null) {
			sender.sendMessage(Util.colours(
					"&cNobody has joined this server recently enough to welcome! Please note, if somebody has joined the network but not the server you're on, this command will not work."));
			return true;
		}
		
		String name = pl.latestJoin;
		
		String message = "";
		switch (Util.randomNumberBetween(1, 6)) {
		case 1:
			message = "welcome back " + name;
			break;
		case 2:
			message = "wb " + name;
			break;
		case 3:
			message = "weba " + name;
			break;
		case 4:
			message = "hey " + name + "! welcome back";
			break;
		case 5:
			message = "hey " + name + "! Welcome back";
			break;
		case 6:
			message = "welcome back, " + name;
			break;
		}
		
		if (Util.randomNumberBetween(1, 2) == 2) {
			message = Character.toUpperCase(message.charAt(0)) + message.substring(1);
		}
		
		if (Util.randomNumberBetween(1, 2) == 2) {
			message += "!";
		}
		
		p.chat(message);

		return true;

	}
	
	public static boolean wbData(Player p, String[] args) {
		
		if (args.length < 1) {
			return true;
		}
		
		if (args[0].equalsIgnoreCase("workbench")) {
			if (!pl.wbData.getStringList("WorkbenchPreferred").contains(p.getUniqueId().toString())) {
				List<String> put = pl.wbData.getStringList("WorkbenchPreferred");
				put.add(p.getUniqueId().toString());
				pl.wbData.set("WorkbenchPreferred", put);
				try {
					pl.wbData.save(pl.wbDataFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
				p.sendMessage(Util.colours("&8[&4Server&8] &fYour &9/wb &fpreference has been set to 'Workbench'."));
			} else {
				p.sendMessage(Util.colours("&8[&4Server&8] &fYour &9/wb &fpreference is already set to 'Workbench'."));
			}
			return false;
		}
		
		if (args[0].equalsIgnoreCase("welcome")) {
			if (pl.wbData.getStringList("WorkbenchPreferred").contains(p.getUniqueId().toString())) {
				List<String> put = pl.wbData.getStringList("WorkbenchPreferred");
				put.remove(p.getUniqueId().toString());
				pl.wbData.set("WorkbenchPreferred", put);
				try {
					pl.wbData.save(pl.wbDataFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
				p.sendMessage(Util.colours("&8[&4Server&8] &fYour &9/wb &fpreference has been set to 'Welcome'."));
			} else {
				p.sendMessage(Util.colours("&8[&4Server&8] &fYour &9/wb &fpreference is already set to 'Welcome'."));
			}
			return false;
		}
		
		return true;
	}

}
