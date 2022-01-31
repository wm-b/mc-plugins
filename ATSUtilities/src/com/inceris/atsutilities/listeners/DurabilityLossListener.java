package com.inceris.atsutilities.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

import com.inceris.atsutilities.ATSUtilities;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class DurabilityLossListener implements Listener {
	
	private static ATSUtilities atsu = ATSUtilities.getPlugin(ATSUtilities.class);

	@EventHandler
	public void onPlayerItemDamageEvent(PlayerItemDamageEvent event) {

		Player p = event.getPlayer();
		ItemStack item = event.getItem();
		Damageable meta = (Damageable) item.getItemMeta();
		int durability = item.getType().getMaxDurability() - (meta.getDamage() + 1);

		if (atsu.debug) {
			atsu.getLogger().info("PlayerItemDamageEvent firing");
			atsu.getLogger().info("Player: " + p.getName());
			atsu.getLogger().info("Durability: " + durability);
		}

		if (durability <= 10) {

			String name = item.getType().name().toLowerCase();
			
			String nameArray[] = name.split("_");
			name = "";
			for (int i = 0; i < nameArray.length; i++) {
				name += nameArray[i].substring(0, 1) + nameArray[i].substring(1, nameArray[i].length()) + " ";
			}
			name.trim();

			String hasHave = "has";
			if (name.substring(name.length() - 1, name.length()) == "s")
				hasHave = "have";

			String message = "";
			if (durability == 0)
				message = ChatColor.RED + "Uh-oh! Your " + name + " " + hasHave + " broke!";
			else if (durability == 1)
				message = ChatColor.RED + "Your " + name + " only " + hasHave + " " + durability + " use left!";
			else
				message = ChatColor.RED + "Your " + name + " only " + hasHave + " " + durability + " uses left!";

			if (atsu.debug) {
				atsu.getLogger().info("Attempting to send the following Actionbar Message to " + p.getName());
				atsu.getLogger().info(message);
			}

			p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));

		}
	}
}
