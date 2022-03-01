package com.inceris.lockout.listeners;

import java.util.List;

import org.bukkit.entity.Axolotl;
import org.bukkit.entity.Bee;
import org.bukkit.entity.Cat;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fox;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Panda;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.entity.PolarBear;
import org.bukkit.entity.Rabbit;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;

import com.inceris.lockout.Lockout;
import com.inceris.lockout.util.GameInstance;
import com.inceris.lockout.util.Objective;

public class EntityBreedListener implements Listener {

	public static Lockout pl = Lockout.getPlugin(Lockout.class);

	@EventHandler
	public void onEntityBreed(EntityBreedEvent e) {
		
		if (e.getBreeder() instanceof Player) {
			Player p = (Player) e.getBreeder();
			for (GameInstance gi : pl.getGameInstances()) {
				if (gi.isActive() && gi.getPlayers().contains(p)) {
					List<Objective> objectives = gi.getObjectives();
					Entity father = e.getFather();
					Entity mother = e.getMother();
					
					if (mother instanceof Sheep && objectives.contains(Objective.breedSheep)) {
						Objective.complete(gi, Objective.breedSheep, p);
						
					} else if (mother instanceof Cow && objectives.contains(Objective.breedCow)) {
						Objective.complete(gi, Objective.breedCow, p);
						
					} else if (mother instanceof Pig && objectives.contains(Objective.breedPig)) {
						Objective.complete(gi, Objective.breedPig, p);
						
					} else if (mother instanceof Rabbit && objectives.contains(Objective.breedRabbit)) {
						Objective.complete(gi, Objective.breedRabbit, p);
						
					} else if (mother instanceof Wolf && objectives.contains(Objective.breedWolf)) {
						Objective.complete(gi, Objective.breedWolf, p);
						
					} else if (mother instanceof Chicken && objectives.contains(Objective.breedChicken)) {
						Objective.complete(gi, Objective.breedChicken, p);
						
					} else if (mother instanceof Bee && objectives.contains(Objective.breedBee)) {
						Objective.complete(gi, Objective.breedBee, p);
						
					} else if (mother instanceof Cat && objectives.contains(Objective.breedCat)) {
						Objective.complete(gi, Objective.breedCat, p);
						
					} else if (mother instanceof Axolotl && objectives.contains(Objective.breedAxolotl)) {
						Objective.complete(gi, Objective.breedAxolotl, p);
						
					} else if (mother instanceof Fox && objectives.contains(Objective.breedFox)) {
						Objective.complete(gi, Objective.breedFox, p);
						
					} else if ((mother instanceof Horse || father instanceof Horse) && objectives.contains(Objective.breedHorse)) {
						Objective.complete(gi, Objective.breedHorse, p);
						
					} else if (mother instanceof PolarBear && objectives.contains(Objective.breedPolarBear)) {
						Objective.complete(gi, Objective.breedPolarBear, p);
						
					} else if (mother instanceof Panda && objectives.contains(Objective.breedPanda)) {
						Objective.complete(gi, Objective.breedPanda, p);
						
					}
				}
			}
		}
		
	}
	
}
