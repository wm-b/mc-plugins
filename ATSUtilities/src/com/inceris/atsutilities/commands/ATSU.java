package com.inceris.atsutilities.commands;

import com.inceris.atsutilities.ATSUtilities;
import com.inceris.atsutilities.util.Items;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class ATSU {

    private static final ATSUtilities atsu = ATSUtilities.getPlugin(ATSUtilities.class);

    public static boolean cmd(CommandSender sender, String[] args) {

        if (args.length == 0) {
            sender.sendMessage("ATSUtilities developed for play.atownyserver.com by Inceris");
            return true;

        } else if (args[0].equalsIgnoreCase("denysin") && sender.hasPermission("atsutilities.admin")) {
            atsu.denyTallGrass = !atsu.denyTallGrass;
            sender.sendMessage("[ATSUtilities] DenyTallGrass mode set to " + atsu.denyTallGrass);
            return true;

        } else if (args[0].equalsIgnoreCase("denyinfested") && sender.hasPermission("atsutilities.admin")) {
            atsu.denyInfested = !atsu.denyInfested;
            sender.sendMessage("[ATSUtilities] DenyInfested mode set to " + atsu.denyInfested);
            return true;

        } else if (args[0].equalsIgnoreCase("debug") && sender.hasPermission("atsutilities.admin")) {
            atsu.debug = !atsu.debug;
            sender.sendMessage("[ATSUtilities] Debug mode set to " + atsu.debug);
            return true;

        } else if (args[0].equalsIgnoreCase("checktransformmobs") && sender.hasPermission("atsutilities.admin")) {
            for (UUID uuid : atsu.transformMobs) {
                sender.sendMessage(uuid.toString());
            }
            return true;

        } else if (args[0].equalsIgnoreCase("give") && sender.hasPermission("atsutilities.admin")) {

            Player p = atsu.getServer().getPlayer(args[1]);

            ItemStack item = Items.getItem(args[2]);

            if (item == null) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        "&8[&4Server&8] &cItem called &e" + args[2] + "&c does not exist!"));
                return true;
            } else {
                assert p != null;
                p.getInventory().addItem(item);
                assert item.getItemMeta() != null;
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        "&8[&4Server&8] &fGiven " + p.getName() + " " + item.getItemMeta().getDisplayName()));
                return true;
            }
        } else if (args[0].equalsIgnoreCase("giveperm")) {
            if (atsu.getServer().getPlayer(args[1]) == null) {
                sender.sendMessage("That doesn't look like a player!");
                return false;
            }

            if (args[2].contains("gadgetsmenu.suits.") || args[2].contains("gadgetsmenu.gadgets.")) {
                Bukkit.dispatchCommand(sender, "lp user " + args[1] + " permission set " + args[2] + " true world="
                        + atsu.getConfig().getString("limit-gadget-and-suit-to-world"));
            } else {
                Bukkit.dispatchCommand(sender, "lp user " + args[1] + " permission set " + args[2]);
            }
            return true;
        }
        return false;
    }

}
