package com.inceris.atsutilities.listeners;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.ThreadLocalRandom;

public class IronGolemDeathListener implements Listener {
    @EventHandler
    public void onEntityDeathEvent(EntityDeathEvent event) {
        if (event.getEntityType() == EntityType.IRON_GOLEM) {
            assert event.getEntity().getLocation().getWorld() != null;
            event.getDrops().clear();
            event.getEntity().getLocation().getWorld().dropItem(event.getEntity().getLocation(), new ItemStack(Material.IRON_INGOT, ThreadLocalRandom.current().nextInt(1, 4)));
            if (ThreadLocalRandom.current().nextInt(0, 2) == 1)
                event.getEntity().getLocation().getWorld().dropItem(event.getEntity().getLocation(), new ItemStack(Material.POPPY));
        }
    }
}
