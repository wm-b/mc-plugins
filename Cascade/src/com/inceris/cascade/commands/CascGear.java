package com.inceris.cascade.commands;

import com.inceris.cascade.Cascade;
import com.inceris.cascade.util.InvSerialisation;
import com.inceris.cascade.util.Util;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.io.File;
import java.io.IOException;

import static org.bukkit.Bukkit.getServer;

public class CascGear {

    private static final Cascade pl = Cascade.getPlugin(Cascade.class);

    public static boolean cmd(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("Please provide a name for the inventory you're trying to access!");
            return false;
        }

        if (!(sender instanceof Player p)) return false;

        if (pl.cascGearData == null) return false;

        String invName = Util.convertToTitleCase(args[0]);

        String serialisedInventory = pl.cascGearData.getString("serialisedInventories." + invName);
        Inventory inv = null;

        if (serialisedInventory == null) {
            inv = getServer().createInventory(null, 54, "CascGear: " + invName);
            pl.cascGearData.set("serialisedInventories." + invName, InvSerialisation.toBase64(inv));
            try {
                pl.cascGearData.save(pl.cascGearFile);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            inv = InvSerialisation.toInventory(serialisedInventory, null, "CascGear: " + invName);
        }

        if (inv == null) return false;
        ((Player) sender).openInventory(inv);

        return true;
    }

}
