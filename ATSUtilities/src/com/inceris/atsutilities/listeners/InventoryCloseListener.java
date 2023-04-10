package com.inceris.atsutilities.listeners;

import com.inceris.atsutilities.ATSUtilities;
import com.inceris.atsutilities.util.InvSerialisation;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.io.IOException;

public class InventoryCloseListener implements Listener {


    private static final ATSUtilities pl = ATSUtilities.getPlugin(ATSUtilities.class);

    @EventHandler
    private void onInventoryClose(InventoryCloseEvent e) {
        if (e.getPlayer().hasPermission("atsutil.lostandfound.edit")) {
            pl.lostandfoundData.set("SerialisedInventory", InvSerialisation.toBase64(pl.lostandfoundInv));
            try {
                pl.lostandfoundData.save(pl.lostandfoundFile);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
