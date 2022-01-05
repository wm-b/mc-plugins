package com.inceris.atsutilities;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import com.inceris.atsutilities.listeners.DurabilityLossListener;
import com.inceris.atsutilities.listeners.IronGolemDeathListener;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ATSUtilities extends JavaPlugin {
	
	public static boolean debug = false;

	@Override
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new IronGolemDeathListener(), this);
		pm.registerEvents(new DurabilityLossListener(), this);
	}

	@Override
	public void onDisable() {

	}

	public int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}

	public Location GetHighestBlock(Location l) {
		for (int y = 255; y >= -64; y--) {
			l.setY(y);

			if (l.getBlock().getType().isSolid()) {
				l.setY(y + 1);
				return l;
			}
		}
		return l;
	}

	public void TPPlayer(Plugin plugin, Player player, Location l) {
		Bukkit.getScheduler().runTask(plugin, new Runnable() {
			@Override
			public void run() {
				l.setX(l.getX() + 0.5);
				l.setZ(l.getZ() + 0.5);
				player.teleport(l);
			}
		});
	}

	public boolean CheckLocation(Location l) {
		if (!l.getBlock().getType().toString().toLowerCase().equals("water")
				&& !l.getBlock().getType().toString().toLowerCase().equals("lava")
				&& !l.getBlock().getType().toString().toLowerCase().equals("kelp")
				&& !l.getBlock().getType().toString().toLowerCase().equals("kelp_plant")
				&& !l.getBlock().getType().toString().toLowerCase().equals("seagrass")
				&& !l.getBlock().getType().toString().toLowerCase().equals("tall_seagrass")) {
			return true;
		}
		return false;
	}

	public void SendCommand(String command) {
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		Bukkit.dispatchCommand(console, command);
	}

	public void BrewChoices(int choice, String name) {
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
		SendCommand(command);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Plugin plugin = this;
		try {
			if (label.equalsIgnoreCase("atsutilities")) {
				
				if (args.length == 0) {
					sender.sendMessage("ATSUtilities developed for play.atownyserver.com by Inceris");
				}
				
				if (args[0].equalsIgnoreCase("debug")) {
					debug = !debug;
					sender.sendMessage("[ATSUtilities] Debug mode set to " + debug);
				}

				return true;
			}
			
			if (label.equalsIgnoreCase("atsrtp")) {
				Bukkit.getScheduler().runTaskAsynchronously(plugin, new Runnable() {
					@Override
					public void run() {
						int x = 0;
						int z = 0;
						short i;
						Location destination;
						World world = getServer().getWorld(args[1]);
						Player player = getServer().getPlayer(args[0]);

						if (args[3].toLowerCase().equals("random")) {
							x = getRandomNumber(-Integer.parseInt(args[2]), Integer.parseInt(args[2]));
							z = getRandomNumber(-Integer.parseInt(args[2]), Integer.parseInt(args[2]));

							for (i = 0; i < 10000; i++) {

								x = getRandomNumber(-Integer.parseInt(args[2]), Integer.parseInt(args[2]));
								z = getRandomNumber(-Integer.parseInt(args[2]), Integer.parseInt(args[2]));

								destination = GetHighestBlock(new Location(world, x, 255, z, 0, 0));
								if (CheckLocation(destination)) {
									TPPlayer(plugin, player, destination);
									break;
								}
							}
							if (i == 10000)
								player.sendMessage("[ATSRTP] Could not find a spot.");
						} else {
							
							String biomes[] = args[3].toLowerCase().split(",");
							
							for (i = 0; i < 10000; i++) {
								x = getRandomNumber(-Integer.parseInt(args[2]), Integer.parseInt(args[2]));
								z = getRandomNumber(-Integer.parseInt(args[2]), Integer.parseInt(args[2]));
								destination = new Location(world, x, 255, z, 0, 0);
								if (Arrays.asList(biomes).contains(destination.getBlock().getBiome().toString().toLowerCase())) {
									destination = GetHighestBlock(destination);
									if (CheckLocation(destination)) {
										TPPlayer(plugin, player, destination);
										break;
									}
								}
							}
							if (i == 10000)
								player.sendMessage("[ATSRTP] Could not find the biome.");
						}
					}
				});

				return true;
			}

			if (label.equalsIgnoreCase("bc") || label.equalsIgnoreCase("broadcast")) {
				Bukkit.getScheduler().runTask(plugin, new Runnable() {
					@Override
					public void run() {
						String message = "";
						for (int i = 0; i < args.length; i++) {
							if (i != 0)
								message += " ";
							message += args[i];
						}
						Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message));
					}
				});
			}

			if (label.equalsIgnoreCase("blink")) {
				Bukkit.getScheduler().runTask(plugin, new Runnable() {
					@Override
					public void run() {
						Location destination = null;
						Block check = null;
						Player p = getServer().getPlayer(args[0]);
						for (int i = 1; i < Integer.parseInt(args[1]); i++) {
							check = p.getTargetBlock(null, i);
							if (!check.getType().isSolid())
								destination = check.getLocation();
							else
								break;
						}
						if (destination != null) {
							destination.setPitch(p.getLocation().getPitch());
							destination.setYaw(p.getLocation().getYaw());
							TPPlayer(plugin, p, destination);
						} else
							p.sendMessage(ChatColor.RED + "There is a wall in the way!");
					}
				});

				return true;
			}

			if (label.equalsIgnoreCase("leap")) {
				Bukkit.getScheduler().runTask(plugin, new Runnable() {
					@Override
					public void run() {
						Player p = getServer().getPlayer(args[0]);
						Vector v = p.getLocation().getDirection().multiply(1.15);
						v.setY(0.75);
						p.setVelocity(v);
					}
				});

				return true;
			}

			if (label.equalsIgnoreCase("brewatrandom")) {
				Bukkit.getScheduler().runTask(plugin, new Runnable() {
					@Override
					public void run() {
						int amount = 0;
						Player p = getServer().getPlayer(args[0]);
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
							BrewChoices(r, args[0]);
							SendCommand("minecraft:give " + args[0]
									+ " splash_potion{CustomPotionEffects:[{Id:7,Duration:20,Amplifier:0},{Id:19,Duration:100,Amplifier:1}],CustomPotionColor:8257720,"
									+ "display:{Name:'{\"text\":\"Harming Concoction\",\"italic\":\"false\"}'}} 1");
							// BrewChoices(r2, args[0]);
						} else
							p.sendMessage(ChatColor.RED + "You can't carry any more potions!");
					}
				});

				return true;
			}

		} catch (Exception e) {
			getLogger().info(e.fillInStackTrace().getMessage());
		}

		return false;
	}
}