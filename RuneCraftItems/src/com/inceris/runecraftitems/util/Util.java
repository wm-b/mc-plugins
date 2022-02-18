package com.inceris.runecraftitems.util;

import com.inceris.runecraftitems.RuneCraftItems;
import java.util.Collection;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;

public class Util {
	private static RuneCraftItems rci = (RuneCraftItems) RuneCraftItems.getPlugin(RuneCraftItems.class);

	public static void sendCommand(String command) {
		ConsoleCommandSender console = rci.getServer().getConsoleSender();
		Bukkit.dispatchCommand((CommandSender) console, command);
	}

	public static ItemStack constructItem(Material material, String name) {
		ItemStack item = new ItemStack(material);
		item.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 10);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(
				ChatColor.translateAlternateColorCodes('&', rci.getConfig().getString("items." + name + ".name")));
		List<String> lore = rci.getConfig().getStringList("items." + name + ".lore");
		for (int i = 0; i < lore.size(); i++)
			lore.set(i, ChatColor.translateAlternateColorCodes('&', lore.get(i)));
		lore.add(0, "");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public static boolean checkItem(ItemStack item, ItemStack against) {
		if (item.getEnchantmentLevel(Enchantment.ARROW_INFINITE) == 10
				&& item.getItemMeta().getDisplayName().equals(against.getItemMeta().getDisplayName()))
			return true;
		return false;
	}

	public static void registerPlayers() {
		Collection<? extends Player> players = rci.getServer().getOnlinePlayers();
		for (Player p : players)
			RCIPlayer.players.add(new RCIPlayer(p));
	}
	
	public static void checkRemoveItemEffects(Player p, ItemStack item) {
		
		if (Util.checkItem(item, Items.pulsingHeart)) {
		Util.sendCommand("trailsid NONE " + p.getName());
		
		} else if (Util.checkItem(item, Items.stargazer)) {
			p.removePotionEffect(PotionEffectType.GLOWING);
			
		} else if (Util.checkItem(item, Items.doubleEdgedSword)) {
			p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
			
		} else if (Util.checkItem(item, Items.runecraftEssence)) {
			p.removePotionEffect(PotionEffectType.SPEED);
			
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
}
