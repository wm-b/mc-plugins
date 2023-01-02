package com.inceris.atsutilities.listeners;

import com.inceris.atsutilities.ATSUtilities;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDeathListener implements Listener {

    private static final ATSUtilities atsu = ATSUtilities.getPlugin(ATSUtilities.class);

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {

        if (atsu.transformMobs.contains(e.getEntity().getUniqueId())) {
            e.getDrops().clear();
        }

    }

}
