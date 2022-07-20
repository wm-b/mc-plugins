package com.inceris.atstcg;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Commands {
	
	private static ATSTCG tcg = ATSTCG.getPlugin(ATSTCG.class);
	
	public static boolean atstcg(CommandSender sender, String[] args) {
		try {
			// /tradingcards
			if (args.length == 0) {
				sender.sendMessage(ChatColor.BLUE + "TradingCards by Inceris");
			} else if (!sender.hasPermission("tradingcards.admin")) {
				sender.sendMessage(ChatColor.RED + "You don't have permission for this!");
				return true;

				// /tradingcards reload
			} else if (args[0].equalsIgnoreCase("reload")) {
				tcg.reloadConfig();
				tcg.countCards();
				sender.sendMessage(ChatColor.GREEN + "TradingCards config reloaded!");
				
				// /tradingcards give
			} else if (args[0].equalsIgnoreCase("give")) {
				if (args[2].equals("random")) tcg.getServer().getPlayer(args[1]).getInventory().addItem(tcg.randomCardOfEdFromSet(Integer.parseInt(args[3]), 1));
				else tcg.getServer().getPlayer(args[1]).getInventory().addItem(tcg.card(args[2], Integer.parseInt(args[3])));

				// /tradingcards pack
			} else if (args[0].equalsIgnoreCase("pack")) {
				ItemStack pack = new ItemStack(Material.NETHERITE_SCRAP);
				pack.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 10);
				ItemMeta meta = pack.getItemMeta();
				if (args[2].equalsIgnoreCase("2")) meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aSet 2 &6Second Edition &dCard Pack"));
				else meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aSet 1 &6Second Edition &dCard Pack"));
				List<String> lore = new ArrayList<String>();
				lore.add("");
				lore.add(ChatColor.GRAY + "Contains 4 random trading cards!");
				meta.setLore(lore);
				pack.setItemMeta(meta);
				tcg.getServer().getPlayer(args[1]).getInventory().addItem(pack);
			} else if (args[0].equalsIgnoreCase("board")) {
				GameInv gameinv = new GameInv();
				if (sender instanceof HumanEntity) {
					gameinv.openInventory((HumanEntity) sender);
				}
			}
		}
		catch(Exception e) {
			sender.sendMessage(ChatColor.RED + "There was a problem!");
			e.printStackTrace();
		}
		return false;
	}
}
