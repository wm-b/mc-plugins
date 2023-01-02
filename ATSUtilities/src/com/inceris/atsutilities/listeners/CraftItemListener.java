package com.inceris.atsutilities.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

public class CraftItemListener implements Listener {

    @EventHandler
    public void onCraftItem(CraftItemEvent e) {

        ItemStack result = e.getRecipe().getResult();
        if (result.getType().name().toLowerCase().contains("boat with chest")) {

            e.setCancelled(true);

            for (HumanEntity h : e.getInventory().getViewers()) {
                h.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&4Server&8]&c You can't craft this item due to the boat with chest"
                        + "item duplication glitch!"));
            }
        }

    }
}
