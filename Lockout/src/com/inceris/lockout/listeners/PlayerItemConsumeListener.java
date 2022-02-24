package com.inceris.lockout.listeners;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import com.inceris.lockout.Lockout;
import com.inceris.lockout.util.GameInstance;
import com.inceris.lockout.util.Objective;

public class PlayerItemConsumeListener implements Listener {

	public static Lockout pl = Lockout.getPlugin(Lockout.class);
	
	@EventHandler
	public void onPlayerItemConsume(PlayerItemConsumeEvent e) {
		
		Player p = e.getPlayer();
		
		for(GameInstance gi : pl.gameInstances) {
			if (gi.isActive() && gi.getPlayerScores().keySet().contains(p)) {
				Material type = e.getItem().getType();
				List<Objective> objectives = gi.getObjectives();
				
				if (type.equals(Material.APPLE) && objectives.contains(Objective.eatApple)) {
					Objective.complete(gi, Objective.eatApple, p);
					
				} else if (type.equals(Material.MUSHROOM_STEW) && objectives.contains(Objective.eatMushroomSoup)) {
					Objective.complete(gi, Objective.eatMushroomSoup, p);
					
				} else if (type.equals(Material.BREAD) && objectives.contains(Objective.eatBread)) {
					Objective.complete(gi, Objective.eatBread, p);
					
				} else if ((type.equals(Material.COOKED_PORKCHOP) || type.equals(Material.PORKCHOP))
						&& objectives.contains(Objective.eatPork)) {
					Objective.complete(gi, Objective.eatPork, p);

				} else if ((type.equals(Material.COOKED_CHICKEN) || type.equals(Material.CHICKEN))
						&& objectives.contains(Objective.eatChicken)) {
					Objective.complete(gi, Objective.eatChicken, p);

				} else if ((type.equals(Material.COOKED_BEEF) || type.equals(Material.BEEF))
						&& objectives.contains(Objective.eatBeef)) {
					Objective.complete(gi, Objective.eatBeef, p);

				} else if ((type.equals(Material.COOKED_MUTTON) || type.equals(Material.MUTTON))
						&& objectives.contains(Objective.eatMutton)) {
					Objective.complete(gi, Objective.eatMutton, p);

				} else if ((type.equals(Material.COOKED_RABBIT) || type.equals(Material.RABBIT))
						&& objectives.contains(Objective.eatRabbit)) {
					Objective.complete(gi, Objective.eatRabbit, p);

				} else if ((type.equals(Material.BAKED_POTATO) || type.equals(Material.POTATO))
						&& objectives.contains(Objective.eatPotato)) {
					Objective.complete(gi, Objective.eatPotato, p);

				} else if ((type.equals(Material.COOKED_COD) || type.equals(Material.COD))
						&& objectives.contains(Objective.eatCod)) {
					Objective.complete(gi, Objective.eatCod, p);

				} else if ((type.equals(Material.COOKED_SALMON) || type.equals(Material.SALMON))
						&& objectives.contains(Objective.eatSalmon)) {
					Objective.complete(gi, Objective.eatSalmon, p);

				} else if (type.equals(Material.COOKIE) && objectives.contains(Objective.eatCookie)) {
					Objective.complete(gi, Objective.eatCookie, p);
					
				} else if (type.equals(Material.RABBIT_STEW) && objectives.contains(Objective.eatRabbitStew)) {
					Objective.complete(gi, Objective.eatRabbitStew, p);
					
				} else if (type.equals(Material.GLOW_BERRIES) && objectives.contains(Objective.eatGlowBerries)) {
					Objective.complete(gi, Objective.eatGlowBerries, p);
					
				} else if (type.equals(Material.BEETROOT_SOUP) && objectives.contains(Objective.eatBeetrootSoup)) {
					Objective.complete(gi, Objective.eatBeetrootSoup, p);
					
				} else if (type.equals(Material.CARROT) && objectives.contains(Objective.eatCarrot)) {
					Objective.complete(gi, Objective.eatCarrot, p);
					
				} else if (type.equals(Material.PUFFERFISH) && objectives.contains(Objective.eatPufferfish)) {
					Objective.complete(gi, Objective.eatPufferfish, p);
					
				} else if (type.equals(Material.SPIDER_EYE) && objectives.contains(Objective.eatSpiderEye)) {
					Objective.complete(gi, Objective.eatSpiderEye, p);
					
				} else if (type.equals(Material.MELON_SLICE) && objectives.contains(Objective.eatMelonSlice)) {
					Objective.complete(gi, Objective.eatMelonSlice, p);
					
				} else if (type.equals(Material.DRIED_KELP) && objectives.contains(Objective.eatDriedKelp)) {
					Objective.complete(gi, Objective.eatDriedKelp, p);
					
				} else if (type.equals(Material.ROTTEN_FLESH) && objectives.contains(Objective.eatRottenFlesh)) {
					Objective.complete(gi, Objective.eatRottenFlesh, p);
					
				} else if (type.equals(Material.PUMPKIN_PIE) && objectives.contains(Objective.eatPumpkinPie)) {
					Objective.complete(gi, Objective.eatPumpkinPie, p);
					
				} else if (type.equals(Material.BEETROOT) && objectives.contains(Objective.eatBeetroot)) {
					Objective.complete(gi, Objective.eatBeetroot, p);
					
				} else if (type.equals(Material.POISONOUS_POTATO) && objectives.contains(Objective.eatPoisonousPotato)) {
					Objective.complete(gi, Objective.eatPoisonousPotato, p);
					
				} else if (type.equals(Material.TROPICAL_FISH) && objectives.contains(Objective.eatTropicalFish)) {
					Objective.complete(gi, Objective.eatTropicalFish, p);
					
				} else if (type.equals(Material.ENCHANTED_GOLDEN_APPLE) && objectives.contains(Objective.eatEnchantedGoldenApple)) {
					Objective.complete(gi, Objective.eatEnchantedGoldenApple, p);
					
				} else if (type.equals(Material.SWEET_BERRIES) && objectives.contains(Objective.eatSweetBerries)) {
					Objective.complete(gi, Objective.eatSweetBerries, p);
					
				}
			}
		}
		
	}
	
}
