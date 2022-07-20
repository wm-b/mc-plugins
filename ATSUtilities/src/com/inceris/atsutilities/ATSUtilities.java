package com.inceris.atsutilities;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.inceris.atsutilities.listeners.PrepareAnvilListener;

import net.luckperms.api.LuckPerms;

import com.inceris.atsutilities.commands.ATSRTP;
import com.inceris.atsutilities.commands.ATSU;
import com.inceris.atsutilities.commands.Blink;
import com.inceris.atsutilities.commands.BrewAtRandom;
import com.inceris.atsutilities.commands.Broadcast;
import com.inceris.atsutilities.commands.CountdownCmd;
import com.inceris.atsutilities.commands.Leap;
import com.inceris.atsutilities.listeners.AsyncPlayerChatListener;
import com.inceris.atsutilities.listeners.DurabilityLossListener;
import com.inceris.atsutilities.listeners.InventoryClickListener;
import com.inceris.atsutilities.listeners.IronGolemDeathListener;
import com.inceris.atsutilities.listeners.PlayerCommandPreprocessListener;
import com.inceris.atsutilities.listeners.PlayerDeathListener;
import com.inceris.atsutilities.listeners.PlayerInteractListener;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.enchantments.Enchantment;

public class ATSUtilities extends JavaPlugin {

	public static LuckPerms lp;
	public boolean denyTallGrass = false;
	public boolean denyInfested = true;
	public boolean debug = false;
	public final Enchantment[] allowedEnchantments = new Enchantment[] {
			Enchantment.DIG_SPEED,
			Enchantment.DAMAGE_ALL,
			Enchantment.DAMAGE_ARTHROPODS,
			Enchantment.DAMAGE_UNDEAD,
			Enchantment.ARROW_DAMAGE,
			Enchantment.IMPALING,
			Enchantment.PROTECTION_ENVIRONMENTAL,
			Enchantment.PROTECTION_FIRE,
			Enchantment.PROTECTION_EXPLOSIONS,
			Enchantment.PROTECTION_PROJECTILE,
			Enchantment.DURABILITY
			};

	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new IronGolemDeathListener(), this);
		pm.registerEvents(new DurabilityLossListener(), this);
		pm.registerEvents(new PrepareAnvilListener(), this);
		pm.registerEvents(new InventoryClickListener(), this);
		pm.registerEvents(new AsyncPlayerChatListener(), this);
		pm.registerEvents(new PlayerInteractListener(), this);
		pm.registerEvents(new PlayerCommandPreprocessListener(), this);
		pm.registerEvents(new PlayerDeathListener(), this);
		
		RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
		if (provider != null) {
		    lp = provider.getProvider();
		}
	}

	@Override
	public void onDisable() {

	}

	public void SendCommand(String command) {
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		Bukkit.dispatchCommand(console, command);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		try {
			if (label.equalsIgnoreCase("atsutilities") || label.equalsIgnoreCase("atsu")) {
				ATSU.cmd(sender, args);
				return true;
			}
			
			if (label.equalsIgnoreCase("atsrtp")) {
				ATSRTP.cmd(args);
				return true;
			}

			if (label.equalsIgnoreCase("bc") || label.equalsIgnoreCase("broadcast")) {
				Broadcast.cmd(args);
				return true;
			}

			if (label.equalsIgnoreCase("blink")) {
				Blink.cmd(args);
				return true;
			}

			if (label.equalsIgnoreCase("leap")) {
				Leap.cmd(args);
				return true;
			}

			if (label.equalsIgnoreCase("brewatrandom")) {
				BrewAtRandom.cmd(args[0]);
				return true;
			}

			if (label.equalsIgnoreCase("countdown") || label.equalsIgnoreCase("cd")) {
				return CountdownCmd.cmd(sender, args);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
}