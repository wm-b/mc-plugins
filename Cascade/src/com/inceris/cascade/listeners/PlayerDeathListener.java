package com.inceris.cascade.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        if (e.getDeathMessage() == null) return;
        if (e.getDeathMessage().endsWith(e.getEntity().getName() + " died")) e.setDeathMessage(null);
    }

}
