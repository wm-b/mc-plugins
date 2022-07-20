package com.inceris.runecraftutilities.listeners;

import java.io.IOException;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.mineacademy.boss.api.event.BossDeathEvent;

import com.inceris.runecraftutilities.RuneCraftUtilities;

public class BossDeathListener implements Listener {

	private static RuneCraftUtilities pl = RuneCraftUtilities.getPlugin(RuneCraftUtilities.class);

	@EventHandler
	public void onBossDeath(BossDeathEvent e) {
		String path =  "TotalKills." + e.getEntity().getKiller().getName();
		int numKills = pl.getBosskillsConfig().getInt(path);
		pl.getBosskillsConfig().set(path, numKills + 1);
		
		path = e.getBoss().getName() + "." + e.getEntity().getKiller().getName();
		numKills = pl.getBosskillsConfig().getInt(path);
		pl.getBosskillsConfig().set(path, numKills + 1);
		
		try {
			pl.getBosskillsConfig().save(pl.getBosskills());
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	
	}
	
}
