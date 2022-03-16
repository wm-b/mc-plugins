package com.inceris.runecraftitems.util;

import com.inceris.runecraftitems.RuneCraftItems;

import net.milkbowl.vault.economy.Economy;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.potion.PotionEffectType;

public class Util {

	private static RuneCraftItems pl = RuneCraftItems.getPlugin(RuneCraftItems.class);
	private static Economy econ = null;
	public static Logger l = Bukkit.getLogger();

	public static Economy getEcon() {
		return econ;
	}

	public static void setupEconomy() {
		if (pl.getServer().getPluginManager().getPlugin("Vault") != null) {
			RegisteredServiceProvider<Economy> rsp = pl.getServer().getServicesManager().getRegistration(Economy.class);
			econ = rsp.getProvider();
		}
	}

	public static void sendCommand(String command) {
		ConsoleCommandSender console = pl.getServer().getConsoleSender();
		Bukkit.dispatchCommand((CommandSender) console, command);
	}

	public static String colours(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}

	public static ItemStack constructItem(Material material, String name) {
		if (pl.getConfig().getString("items." + name + ".name") != null) {
			ItemStack item = new ItemStack(material);
			item.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 10);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(
					ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("items." + name + ".name")));
			List<String> lore = pl.getConfig().getStringList("items." + name + ".lore");
			for (int i = 0; i < lore.size(); i++)
				lore.set(i, ChatColor.translateAlternateColorCodes('&', lore.get(i)));
			lore.add(0, "");
			meta.setLore(lore);
			item.setItemMeta(meta);
			return item;
		} else {
			l.info("Couldn't find config information for item: " + name);
			return null;
		}
	}

	public static boolean checkItem(ItemStack item, ItemStack against) {
		if (item.getEnchantmentLevel(Enchantment.ARROW_INFINITE) == 10
				&& item.getItemMeta().getDisplayName().equals(against.getItemMeta().getDisplayName())) {
			return true;
		} else {
			return false;
		}
	}

	public static void registerPlayers() {
		Collection<? extends Player> players = pl.getServer().getOnlinePlayers();
		for (Player p : players)
			RCIPlayer.players.add(new RCIPlayer(p));
	}

	public static void checkRemoveItemEffects(Player p, ItemStack item) {

		if (Util.checkItem(item, Items.getItem("pulsingheart"))) {
			Util.sendCommand("trailsid NONE " + p.getName());

		} else if (Util.checkItem(item, Items.getItem("stargazer"))) {
			p.removePotionEffect(PotionEffectType.GLOWING);

		} else if (Util.checkItem(item, Items.getItem("doubleedgedsword"))) {
			p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);

		} else if (Util.checkItem(item, Items.getItem("runecraftessence"))) {
			p.removePotionEffect(PotionEffectType.SPEED);

		} else if (Util.checkItem(item, Items.getItem("hermesboots"))) {
			p.removePotionEffect(PotionEffectType.SPEED);

		} else if (Util.checkItem(item, Items.getItem("poseidonstrident"))) {
			p.removePotionEffect(PotionEffectType.DOLPHINS_GRACE);
			p.removePotionEffect(PotionEffectType.CONDUIT_POWER);
			Util.sendCommand("trailsid NONE " + p.getName());

		} else if (Util.checkItem(item, Items.getItem("mjolnir"))) {
			p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
			p.removePotionEffect(PotionEffectType.JUMP);

		} else if (Util.checkItem(item, Items.getItem("devilsgreencoat"))) {
			pl.wearingDevilsGreenCoat.remove(p);

		} else if (Util.checkItem(item, Items.getItem("crownoftwelvestars"))) {
			Util.sendCommand("trailsid NONE " + p.getName());

		} else if (Util.checkItem(item, Items.getItem("disguisecap"))) {
			Util.sendCommand("undisguiseplayer " + p.getName());

		} else if (Util.checkItem(item, Items.getItem("brolysrage"))) {
			p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
			p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);

		} else if (Util.checkItem(item, Items.getItem("neverletgo"))) {
			Util.sendCommand("trailsid NONE " + p.getName());

		} else if (Util.checkItem(item, Items.getItem("invisibilitycloak"))) {
			p.removePotionEffect(PotionEffectType.INVISIBILITY);

		} else if (Util.checkItem(item, Items.getItem("areschestplate"))) {
			p.removePotionEffect(PotionEffectType.REGENERATION);
			p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
			Util.sendCommand("trailsid NONE " + p.getName());

		} else if (Util.checkItem(item, Items.getItem("cupidswings"))) {
			Util.sendCommand("trailsid NONE " + p.getName());

		} else if (Util.checkItem(item, Items.getItem("sunwukongsheadband"))) {
			Util.sendCommand("lp user " + p.getName() + " parent remove sunwukongsheadband");

		}
	}

	public static boolean percentChance(double percent) {
		double r = Math.random();
		if (r <= (percent / 100)) {
			return true;
		} else {
			return false;
		}
	}

	public static void TPEffects(String name) {
		Util.sendCommand("execute at " + name + " run particle minecraft:reverse_portal ~ ~1 ~ 0.5 0.5 0.5 0.1 50");
		Util.sendCommand("execute at " + name + " run playsound minecraft:entity.enderman.teleport master " + name
				+ " ~ ~ ~ 1 1 1");
	}

	public static void blink(Player p, int radius) {
		Util.TPEffects(p.getName());
		Bukkit.getScheduler().runTaskAsynchronously(pl, new Runnable() {
			@Override
			public void run() {
				RTP.rtp(p, 32);
				Location destination = null;
				Block check = null;
				for (int i = 1; i < radius; i++) {
					check = p.getTargetBlock(null, i);
					if (!check.getType().isSolid())
						destination = check.getLocation();
					else {
						break;
					}
				}
				if (destination != null) {
					destination.setPitch(p.getLocation().getPitch());
					destination.setYaw(p.getLocation().getYaw());
					RTP.TPPlayer(pl, p, destination);
				} else {
					p.sendMessage(ChatColor.RED + "There is a wall in the way!");
				}
			}
		});
		Bukkit.getScheduler().runTaskLater(pl, new Runnable() {
			@Override
			public void run() {
				Util.TPEffects(p.getName());
			}
		}, 1);
	}
}
