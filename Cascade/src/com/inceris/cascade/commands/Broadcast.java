package com.inceris.cascade.commands;

import com.inceris.cascade.Cascade;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Broadcast {

    private static final Cascade cascade = Cascade.getPlugin(Cascade.class);

    public static boolean cmd(String[] args) {
        Bukkit.getScheduler().runTask(cascade, () -> {
            StringBuilder message = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                if (i != 0)
                    message.append(" ");
                message.append(args[i]);
            }
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message.toString()));
        });

        return true;
    }
}
