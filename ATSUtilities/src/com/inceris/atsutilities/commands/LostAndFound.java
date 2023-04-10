package com.inceris.atsutilities.commands;

import com.inceris.atsutilities.ATSUtilities;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LostAndFound {

    private static final ATSUtilities pl = ATSUtilities.getPlugin(ATSUtilities.class);

    public static boolean cmd(CommandSender sender) {
        if (!(sender instanceof Player p)) return false;

        p.openInventory(pl.lostandfoundInv);

        return true;
    }

}
