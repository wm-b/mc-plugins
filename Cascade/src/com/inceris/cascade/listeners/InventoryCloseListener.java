package com.inceris.cascade.listeners;

import com.inceris.cascade.Cascade;
import com.inceris.cascade.util.InvSerialisation;
import com.inceris.cascade.util.Util;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.io.IOException;

public class InventoryCloseListener implements Listener {


    private static final Cascade pl = Cascade.getPlugin(Cascade.class);

    @EventHandler
    private void onInventoryClose(InventoryCloseEvent e) {
        if (e.getPlayer().hasPermission("cascade.cascgear.edit")) {
            String invName = Util.convertToTitleCase(e.getView().getTitle().replace("CascGear: ", ""));
            if (pl.cascGearData.getString("serialisedInventories." + invName) == null) {
                return;
            }

            if (e.getInventory().isEmpty()) {
                pl.cascGearData.set("serialisedInventories." + invName, null);
                try {
                    pl.cascGearData.save(pl.cascGearFile);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                return;
            }

            pl.cascGearData.set("serialisedInventories." + invName, InvSerialisation.toBase64(e.getInventory()));
            try {
                pl.cascGearData.save(pl.cascGearFile);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
