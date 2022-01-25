package com.inceris.atsutilities.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.inceris.atsutilities.ATSUtilities;

public class Blink {
	
	private static ATSUtilities atsu = ATSUtilities.getPlugin(ATSUtilities.class);
	
	public static void cmd(String[] args) {

		Bukkit.getScheduler().runTask(atsu, new Runnable() {
			@Override
			public void run() {
				Location destination = null;
				Block check = null;
				Player p = atsu.getServer().getPlayer(args[0]);
				for (int i = 1; i < Integer.parseInt(args[1]); i++) {
					check = p.getTargetBlock(null, i);
					if (!check.getType().isSolid())
						destination = check.getLocation();
					else
						break;
				}
				if (destination != null) {
					destination.setPitch(p.getLocation().getPitch());
					destination.setYaw(p.getLocation().getYaw());
					ATSRTP.TPPlayer(atsu, p, destination);
				} else
					p.sendMessage(ChatColor.RED + "There is a wall in the way!");
			}
		});
	}

}
