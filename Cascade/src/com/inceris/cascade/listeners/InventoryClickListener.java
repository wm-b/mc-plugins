package com.inceris.cascade.listeners;

import com.inceris.cascade.Cascade;
import com.inceris.cascade.util.Util;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.PlayerInventory;

public class InventoryClickListener implements Listener {

    private static final Cascade pl = Cascade.getPlugin(Cascade.class);

    @EventHandler
    private void onInventoryClick(InventoryClickEvent e) {
        if (e.getCurrentItem() == null) return;

        if (pl.cascGearData.getString("serialisedInventories." + Util.convertToTitleCase(e.getView().getTitle().replace("CascGear: ", ""))) != null) {
            if (e.getClickedInventory() instanceof PlayerInventory) {
                return;
            }

            if (e.isRightClick() && e.getWhoClicked().hasPermission("cascade.cascgear.use")) {
                e.setCancelled(true);
                e.getWhoClicked().getInventory().addItem(e.getCurrentItem());
                return;
            }

            if (!e.getWhoClicked().hasPermission("cascade.cascgear.edit")) {
                e.setCancelled(true);
            }
        }
    }
}
