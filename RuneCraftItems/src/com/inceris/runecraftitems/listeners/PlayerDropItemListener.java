package com.inceris.runecraftitems.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import com.inceris.runecraftitems.util.Util;

public class PlayerDropItemListener implements Listener {
	
	@EventHandler
	public void onPlayerDropItemEvent(PlayerDropItemEvent e) {
		Util.checkRemoveItemEffects(e.getPlayer(), e.getItemDrop().getItemStack());
	}
}
