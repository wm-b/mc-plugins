package com.inceris.atsutilities.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.inceris.atsutilities.util.Countdown;
import com.inceris.atsutilities.util.Util;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class CountdownCmd {
	
	public static boolean cmd(CommandSender sender, String[] args) {
		
		if (!(sender instanceof Player p)) {
			return false;
		}

		if (args[0].equalsIgnoreCase("stop")) {
			Countdown.stop(p);
			return true;
		}
		
		int cd = Integer.parseInt(args[0]);
		
		if (cd > 3600) {
			p.sendMessage(Util.colours("&8[&aATS&9Timer&8]&c Please put at most 3600 seconds!"));
			return false;
		}

		p.sendMessage(Util.colours("&8[&aATS&9Timer&8]&c " + cd + "&f seconds on the clock!"));
		p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(Util.colours("&c" + cd + "&f seconds left")));
		
		new Countdown(p, cd);

		return true;
		
	}

}
