package com.inceris.atstcg;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

public class ATSTCG extends JavaPlugin {
	
	public int[] cardsUpToSet = new int[5];
	
	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PackListener(), this);

		countCards();
	}
	
	public void countCards() {
		//index 0 must equal 0 for being a minimum value for random number generation later
		cardsUpToSet[0] = 0;
		int count = 0;
		for (int i = 1; i < cardsUpToSet.length; i++) {
			for (int j = 1; j < ((getConfig().getKeys(true).size() - 1)/11) + 2; j++) {
				if (getConfig().getInt("cards." + j + ".set") == i) count++;
			}
			if (i == 1) count++;
			cardsUpToSet[i] = count + cardsUpToSet[i - 1];
			count = 0;
		}
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public String edition(int ed) {
		String edition;
		if (ed == 1) edition = " &8First Edition";
		else if (ed == 2) edition = " &8Second Edition";
		else if (ed == 3) edition = " &8Third Edition";
		else edition = " &8Worthless";
		return edition;
	}
	
	public ItemStack card(String id, int ed) {
		ItemStack card = new ItemStack(Material.PAPER);
		card.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 10);
		ItemMeta meta = card.getItemMeta();
		//pull name from config
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&e&l" + this.getConfig().getString("cards." + id + ".name") + " &dTrading Card"));
		
		//pull lore from config and save in array
		List<String> lore = this.getConfig().getStringList("cards." + id + ".lore");
		
		//pull rarity from config and apply to lore
		String rarity = null;
		switch (this.getConfig().getString("cards." + id + ".rarity").toLowerCase()) {
			case "normal":
				rarity = "&fNormal";
				break;
			case "special":
				rarity = "&bSpecial";
				break;
			case "epic":
				rarity = "&5Epic";
				break;
			case "legendary":
				rarity = "&6Legendary";
				break;
			case "mythic":
				rarity = "&9Mythic";
				break;
			case "supreme":
				rarity = "&4Supreme";
				break;
		}
		lore.add(0, rarity + edition(ed));
		lore.add(1, "");
		
		//pull TCG info from config and apply to lore
		List<String> effect = this.getConfig().getStringList("cards." + id + ".effect");
		if (effect.size() != 0) {
			for (int i = effect.size() - 1; i > -1; i--) lore.add(1, ChatColor.WHITE + effect.get(i));
			lore.add(1, ChatColor.GRAY + "Effect" + ChatColor.DARK_GRAY + ": ");
			lore.add(1, "");
		}
		int power = this.getConfig().getInt("cards." + id + ".power");
		int defense = this.getConfig().getInt("cards." + id + ".defense");
		int cost = this.getConfig().getInt("cards." + id + ".cost");
		String type = this.getConfig().getString("cards." + id + ".type");
		String subtype = this.getConfig().getString("cards." + id + ".subtype");
		if (cost != -1 && type != "[]" && type != null) {
			String costAndTypes = ChatColor.YELLOW + type;
			if (subtype != "[]" && subtype != null) costAndTypes += ChatColor.translateAlternateColorCodes('&', "&8 | &f" + subtype);
			costAndTypes += ChatColor.translateAlternateColorCodes('&', "&8 | &7Cost&8: &a");
			for (int i = 0; i < cost; i++) costAndTypes += "•";
			if (power != -1 && defense != -1) {
				lore.add(1, ChatColor.translateAlternateColorCodes('&', "&7Power&8: &c&l" + power + "&8 | &7Defense&8: &b&l" + defense));
			}
			lore.add(0, costAndTypes);
		}
		
		//pull subset from config and apply to lore
		String subset = this.getConfig().getString("cards." + id + ".subset");
		if (subset != "[]" && subset != null) lore.add(1, ChatColor.DARK_GRAY + subset);
		
		//format lore from config
		for (int i = 0; i < lore.size(); i++) {
			lore.set(i, ChatColor.translateAlternateColorCodes('&', "&7" + lore.get(i)));
		}
		
		//pull set and card ID from config and apply to lore
		lore.add(ChatColor.translateAlternateColorCodes('&', "&8Set " + this.getConfig().getInt("cards." + id + ".set") + " | #" + id));
		
		//remove blank lore
		if (lore.get(3) == "[]") lore.remove(3);
		
		meta.setLore(lore);
		card.setItemMeta(meta);
		return card;
	}
	
	public int getNumCardsInSet(int set) {
		int count = 0;
		for (short j = 0; j > ((getConfig().getKeys(true).size() - 1)/6); j++) {
			if (getConfig().getInt("cards." + j + ".set") == 1) count++;
		}
		return count;
	}
	
	public ItemStack justRandomCardOfEdFromSet(int ed, int set) {
		return card("" + ThreadLocalRandom.current().nextInt(cardsUpToSet[set - 1] + 1, cardsUpToSet[set] + 1), ed);
	}
	
	public ItemStack randomCardOfEdFromSet(int ed, int set) {
		int r = ThreadLocalRandom.current().nextInt(1, 100 + 1);
		int count = 0;
		ItemStack card = null;
		if (r == 1) {
			do {
				count++;
				if (count>100) break;
				card = justRandomCardOfEdFromSet(ed, set);
			} while (!card.getItemMeta().getLore().contains(ChatColor.translateAlternateColorCodes('&', "&4Supreme" + edition(ed))));
		}
		else if (r <= 4) {
			do {
				count++;
				if (count>100) break;
				card = justRandomCardOfEdFromSet(ed, set);
			} while (!card.getItemMeta().getLore().contains(ChatColor.translateAlternateColorCodes('&', "&9Mythic" + edition(ed))));
		}
		else if (r <= 11) {
			do {
				count++;
				if (count>100) break;
				card = justRandomCardOfEdFromSet(ed, set);
			} while (!card.getItemMeta().getLore().contains(ChatColor.translateAlternateColorCodes('&', "&6Legendary" + edition(ed))));
		}
		else if (r <= 25) {
			do {
				count++;
				if (count>100) break;
				card = justRandomCardOfEdFromSet(ed, set);
			} while (!card.getItemMeta().getLore().contains(ChatColor.translateAlternateColorCodes('&', "&5Epic" + edition(ed))));
		}
		else if (r <= 55) {
			do {
				count++;
				if (count>100) break;
				card = justRandomCardOfEdFromSet(ed, set);
			} while (!card.getItemMeta().getLore().contains(ChatColor.translateAlternateColorCodes('&', "&bSpecial" + edition(ed))));
		}
		else {
			do {
				count++;
				if (count>100) break;
				card = justRandomCardOfEdFromSet(ed, set);
			} while (!card.getItemMeta().getLore().contains(ChatColor.translateAlternateColorCodes('&', "&fNormal" + edition(ed))));
		}
		
		return card;
	}
	
	public int getEmptySlots(Player p) {
        PlayerInventory inventory = p.getInventory();
        ItemStack[] cont = inventory.getContents();
        int i = 0;
        for (ItemStack item : cont)
          if (item != null && item.getType() != Material.AIR) {
            i++;
          }
        return 36 - i;
    }
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("atstcg")) {
			try {
				// /tradingcards
				if (args.length == 0) {
					sender.sendMessage(ChatColor.BLUE + "TradingCards by Inceris");
				} else if (!sender.hasPermission("tradingcards.admin")) {
					sender.sendMessage(ChatColor.RED + "You don't have permission for this!");
					return true;

					// /tradingcards reload
				} else if (args[0].equalsIgnoreCase("reload")) {
					this.reloadConfig();
					countCards();
					sender.sendMessage(ChatColor.GREEN + "TradingCards config reloaded!");
					
					// /tradingcards give
				} else if (args[0].equalsIgnoreCase("give")) {
					if (args[2].equals("random")) this.getServer().getPlayer(args[1]).getInventory().addItem(randomCardOfEdFromSet(Integer.parseInt(args[3]), 1));
					else this.getServer().getPlayer(args[1]).getInventory().addItem(card(args[2], Integer.parseInt(args[3])));

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
					this.getServer().getPlayer(args[1]).getInventory().addItem(pack);
				}
			}
			catch(Exception e) {
				sender.sendMessage(ChatColor.RED + "There was a problem!");
				e.printStackTrace();
			}
		}
		return false;
	}
}
