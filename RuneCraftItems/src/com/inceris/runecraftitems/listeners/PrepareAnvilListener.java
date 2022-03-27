package com.inceris.runecraftitems.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

import com.inceris.runecraftitems.RuneCraftItems;
import com.inceris.runecraftitems.util.Items;
import com.inceris.runecraftitems.util.Util;

public class PrepareAnvilListener implements Listener {

	private static RuneCraftItems pl = (RuneCraftItems) RuneCraftItems.getPlugin(RuneCraftItems.class);

	@EventHandler
	public void onPrepareAnvil(PrepareAnvilEvent e) {

		if (e.getResult() != null) {
			
			ItemStack result = e.getResult();

			if (Util.checkItem(result, Items.getItem("grailsword"))) {

				Damageable meta = (Damageable) result.getItemMeta();
				int maxDurability = pl.getConfig().getInt("items.grailsword.durability");
				int originalMaxDurability = result.getType().getMaxDurability();

				if ((originalMaxDurability - meta.getDamage()) > maxDurability) {
					meta.setDamage(originalMaxDurability - maxDurability);
				}

				result.setItemMeta(meta);
			}
		}
	}

}
