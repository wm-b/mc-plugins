package com.inceris.cascade.commands;

import com.inceris.cascade.Cascade;
import com.inceris.cascade.util.Items;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class CascadeCmd {

    private static final Cascade cascade = Cascade.getPlugin(Cascade.class);

    public static boolean cmd(CommandSender sender, String[] args) {

        if (args.length == 0) {
            sender.sendMessage("Cascade developed for cascade.theprisma.xyz by Inceris");
            return true;

        } else if (args[0].equalsIgnoreCase("debug") && sender.hasPermission("cascade.admin")) {
            cascade.debug = !cascade.debug;
            sender.sendMessage("[Cascade] Debug mode set to " + cascade.debug);
            return true;

        } else if (args[0].equalsIgnoreCase("give") && sender.hasPermission("cascade.admin")) {

            Player p = cascade.getServer().getPlayer(args[1]);

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
        }
        return false;
    }

}
