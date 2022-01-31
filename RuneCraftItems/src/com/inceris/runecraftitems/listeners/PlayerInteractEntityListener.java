package com.inceris.runecraftitems.listeners;

import com.inceris.runecraftitems.RuneCraftItems;
import com.inceris.runecraftitems.util.Items;
import com.inceris.runecraftitems.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerInteractEntityListener implements Listener {
	private static RuneCraftItems rci = (RuneCraftItems) RuneCraftItems.getPlugin(RuneCraftItems.class);

	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		Entity entity = e.getRightClicked();
		if (entity instanceof Player) {
			Player target = (Player) entity;
			if (Util.checkItem(target.getInventory().getItemInMainHand(), Items.ricksFlowers)) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 0, 10));
				p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 0, 10));
				p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 0, 10));
				p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 0, 10));
				Util.sendCommand("trailsid HEART " + p.getName());
				final Player playerPass = p;
				Bukkit.getScheduler().runTaskLater((Plugin) rci, new Runnable() {
					public void run() {
						Util.sendCommand("trailsid NONE " + playerPass.getName());
					}
				}, 200L);
			}
		}
	}
}