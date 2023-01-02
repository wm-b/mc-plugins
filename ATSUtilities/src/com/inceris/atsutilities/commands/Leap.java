package com.inceris.atsutilities.commands;

import com.inceris.atsutilities.ATSUtilities;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Leap {

    private static final ATSUtilities atsu = ATSUtilities.getPlugin(ATSUtilities.class);

    public static boolean cmd(String[] args) {
        Bukkit.getScheduler().runTask(atsu, () -> {
            Player p = atsu.getServer().getPlayer(args[0]);
            if (p == null) return;
            Vector v = p.getLocation().getDirection().multiply(1.15);
            v.setY(0.75);
            p.setVelocity(v);
        });

        return true;
    }

}
