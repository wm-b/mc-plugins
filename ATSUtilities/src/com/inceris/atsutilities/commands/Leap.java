package com.inceris.atsutilities.commands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.inceris.atsutilities.ATSUtilities;

public class Leap {
	
	private static ATSUtilities atsu = ATSUtilities.getPlugin(ATSUtilities.class);
	
	public static void cmd(String[] args) {
		Bukkit.getScheduler().runTask(atsu, new Runnable() {
			@Override
			public void run() {
				Player p = atsu.getServer().getPlayer(args[0]);
				Vector v = p.getLocation().getDirection().multiply(1.15);
				v.setY(0.75);
				p.setVelocity(v);
			}
		});
	}

}
