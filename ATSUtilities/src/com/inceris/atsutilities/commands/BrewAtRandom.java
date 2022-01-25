package com.inceris.atsutilities.commands;

import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.inceris.atsutilities.ATSUtilities;

public class BrewAtRandom {
	
	private static ATSUtilities atsu = ATSUtilities.getPlugin(ATSUtilities.class);

	public static void BrewChoices(int choice, String name) {
		String command = "minecraft:give " + name + " ";

		switch (choice) {
		case 1:
			command += "splash_potion{CustomPotionEffects:[{Id:11,Duration:300,Amplifier:1},{Id:12,Duration:300,Amplifier:1}],CustomPotionColor:16765266,"
					+ "display:{Name:'{\"text\":\"Protective Concoction\",\"italic\":\"false\"}'}} 1";
			break;
		case 2:
			command += "splash_potion{CustomPotionEffects:[{Id:17,Duration:750,Amplifier:3},{Id:2,Duration:500,Amplifier:1},{Id:7,Duration:20,Amplifier:1}],"
					+ "CustomPotionColor:2697513,display:{Name:'{\"text\":\"Alchemist\\\'s Plague\",\"italic\":\"false\"}'}} 1";
			break;
		case 3:
			command += "splash_potion{CustomPotionEffects:[{Id:6,Duration:20,Amplifier:1},{Id:10,Duration:200,Amplifier:1}],CustomPotionColor:16712985,"
					+ "display:{Name:'{\"text\":\"Healing Concoction\",\"italic\":\"false\"}'}} 1";
			break;
		case 4:
			command += "splash_potion{CustomPotionEffects:[{Id:1,Duration:400,Amplifier:1},{Id:8,Duration:400,Amplifier:1}],CustomPotionColor:3063039,"
					+ "display:{Name:'{\"text\":\"Agile Concoction\",\"italic\":\"false\"}'}} 1";
			break;
		}
		atsu.SendCommand(command);
	}
	
	public static void cmd(String name) {
		Bukkit.getScheduler().runTask(atsu, new Runnable() {
			@Override
			public void run() {
				int amount = 0;
				Player p = atsu.getServer().getPlayer(name);
				for (ItemStack item : p.getInventory().getContents()) {
					if (item != null) {
						if (item.getType() == Material.SPLASH_POTION) {
							amount += item.getAmount();
						}
					}
				}
				if (amount <= 10) {
					int r = ThreadLocalRandom.current().nextInt(1, 4 + 1);
					// int r2;
					// do {
					// r2 = ThreadLocalRandom.current().nextInt(1, 4 + 1);
					// } while (r1 == r2);
					BrewChoices(r, p.getName());
					atsu.SendCommand("minecraft:give " + p.getName()
							+ " splash_potion{CustomPotionEffects:[{Id:7,Duration:20,Amplifier:0},{Id:19,Duration:100,Amplifier:1}],CustomPotionColor:8257720,"
							+ "display:{Name:'{\"text\":\"Harming Concoction\",\"italic\":\"false\"}'}} 1");
					// BrewChoices(r2, args[0]);
				} else
					p.sendMessage(ChatColor.RED + "You can't carry any more potions!");
			}
		});
	}
}
