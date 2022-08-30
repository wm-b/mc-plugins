package com.inceris.atsutilities;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.inceris.atsutilities.listeners.PrepareAnvilListener;
import com.inceris.atsutilities.tabcompletion.WbTabCompletion;

import net.luckperms.api.LuckPerms;

import com.inceris.atsutilities.commands.ATSRTP;
import com.inceris.atsutilities.commands.ATSU;
import com.inceris.atsutilities.commands.Blink;
import com.inceris.atsutilities.commands.BrewAtRandom;
import com.inceris.atsutilities.commands.Broadcast;
import com.inceris.atsutilities.commands.CountdownCmd;
import com.inceris.atsutilities.commands.Leap;
import com.inceris.atsutilities.commands.Transform;
import com.inceris.atsutilities.commands.WB;
import com.inceris.atsutilities.listeners.AsyncPlayerChatListener;
import com.inceris.atsutilities.listeners.DurabilityLossListener;
import com.inceris.atsutilities.listeners.EntityDamageByEntityListener;
import com.inceris.atsutilities.listeners.EntityDeathListener;
import com.inceris.atsutilities.listeners.InventoryClickListener;
import com.inceris.atsutilities.listeners.IronGolemDeathListener;
import com.inceris.atsutilities.listeners.PlayerCommandPreprocessListener;
import com.inceris.atsutilities.listeners.PlayerDeathListener;
import com.inceris.atsutilities.listeners.PlayerInteractListener;
import com.inceris.atsutilities.listeners.PlayerJoinListener;
import com.inceris.atsutilities.listeners.PlayerQuitListener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;

public class ATSUtilities extends JavaPlugin {

	public YamlConfiguration wbData = null;
	public File wbDataFile = new File("plugins/ATSUtilities/wbData.yml");
	public static LuckPerms lp = null;
	public String latestJoin = null;
	public boolean denyTallGrass = false;
	public boolean denyInfested = true;
	public boolean debug = false;
	public List<UUID> transformMobs = new ArrayList<UUID>();
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
		
        if(!wbDataFile.exists()){
            try {
            	wbDataFile.createNewFile();
                wbData = YamlConfiguration.loadConfiguration(wbDataFile);
                wbData.addDefault("WorkbenchPreferred", new ArrayList<String>());
                wbData.options().copyDefaults(true);
                wbData.save(wbDataFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
        	wbData = YamlConfiguration.loadConfiguration(wbDataFile);
        }
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new IronGolemDeathListener(), this);
		pm.registerEvents(new DurabilityLossListener(), this);
		pm.registerEvents(new PrepareAnvilListener(), this);
		pm.registerEvents(new InventoryClickListener(), this);
		pm.registerEvents(new AsyncPlayerChatListener(), this);
		pm.registerEvents(new PlayerInteractListener(), this);
		pm.registerEvents(new PlayerCommandPreprocessListener(), this);
		pm.registerEvents(new PlayerDeathListener(), this);
		pm.registerEvents(new EntityDamageByEntityListener(), this);
		pm.registerEvents(new PlayerJoinListener(), this);
		pm.registerEvents(new PlayerQuitListener(), this);
		pm.registerEvents(new EntityDeathListener(), this);
		
		getCommand("wb").setTabCompleter(new WbTabCompletion());
		getCommand("welcome").setTabCompleter(new WbTabCompletion());
		
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
				return ATSU.cmd(sender, args);
			}
			
			if (label.equalsIgnoreCase("atsrtp")) {
				return ATSRTP.cmd(args);
			}

			if (label.equalsIgnoreCase("bc") || label.equalsIgnoreCase("broadcast")) {
				return Broadcast.cmd(args);
			}

			if (label.equalsIgnoreCase("blink")) {
				return Blink.cmd(args);
			}

			if (label.equalsIgnoreCase("leap")) {
				return Leap.cmd(args);
			}

			if (label.equalsIgnoreCase("brewatrandom")) {
				return BrewAtRandom.cmd(args[0]);
			}

			if (label.equalsIgnoreCase("wb") || label.equalsIgnoreCase("welcome")) {
				return WB.cmd(sender, args);
			}

			if (label.equalsIgnoreCase("countdown") || label.equalsIgnoreCase("cd")) {
				return CountdownCmd.cmd(sender, args);
			}
			
			if ((label.equalsIgnoreCase("transform") || label.equalsIgnoreCase("tf")) && sender.hasPermission("atsutilities.transform")) {
				return Transform.cmd(args);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
}