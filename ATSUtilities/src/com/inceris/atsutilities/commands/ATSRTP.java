package com.inceris.atsutilities.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class ATSRTP {

    public static void TPPlayer(Plugin plugin, Player player, Location l) {
        Bukkit.getScheduler().runTask(plugin, () -> {
            l.setX(l.getX() + 0.5);
            l.setZ(l.getZ() + 0.5);
            player.teleport(l);
        });
    }

}
