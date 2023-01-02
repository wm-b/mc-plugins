package com.inceris.atsutilities.listeners;

import com.inceris.atsutilities.ATSUtilities;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerJoinListener implements Listener {

    private static final ATSUtilities pl = ATSUtilities.getPlugin(ATSUtilities.class);

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        Player p = e.getPlayer();

        if (p.hasPermission("pv.joinvanished")) {
            return;
        }

        String name = p.getName();

        pl.latestJoin = name;

        new BukkitRunnable() {
            @Override
            public void run() {
                if (pl.latestJoin.equals(name)) {
                    pl.latestJoin = null;
                }
            }
        }.runTaskLater(pl, 1200);
    }

}
