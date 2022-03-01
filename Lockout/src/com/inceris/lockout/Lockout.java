package com.inceris.lockout;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.inceris.lockout.commands.LockoutCommand;
import com.inceris.lockout.listeners.BrewEventListener;
import com.inceris.lockout.listeners.CreatureSpawnListener;
import com.inceris.lockout.listeners.EnchantItemListener;
import com.inceris.lockout.listeners.EntityBreedListener;
import com.inceris.lockout.listeners.EntityDamageByEntityListener;
import com.inceris.lockout.listeners.EntityDamageListener;
import com.inceris.lockout.listeners.EntityPickupItemListener;
import com.inceris.lockout.listeners.EntityPotionEffectListener;
import com.inceris.lockout.listeners.EntityResurrectListener;
import com.inceris.lockout.listeners.EntityTameListener;
import com.inceris.lockout.listeners.InventoryClickListener;
import com.inceris.lockout.listeners.PiglinBarterListener;
import com.inceris.lockout.listeners.PlayerBedEnterListener;
import com.inceris.lockout.listeners.PlayerDeathListener;
import com.inceris.lockout.listeners.PlayerFishListener;
import com.inceris.lockout.listeners.PlayerInteractEntityListener;
import com.inceris.lockout.listeners.PlayerInteractListener;
import com.inceris.lockout.listeners.PlayerItemConsumeListener;
import com.inceris.lockout.listeners.PlayerMoveListener;
import com.inceris.lockout.listeners.PlayerQuitListener;
import com.inceris.lockout.listeners.PlayerRespawnListener;
import com.inceris.lockout.listeners.VillagerAcquireTradeListener;
import com.inceris.lockout.util.GameInstance;

public class Lockout extends JavaPlugin {
	
	private List<GameInstance> gameInstances = new ArrayList<GameInstance>();
	private List<Player> prepTeamB = new ArrayList<>();
	private List<Player> prepTeamE = new ArrayList<>();
	private boolean gameStarting = false;
	private boolean preventJoiningTeams = false;

	public List<GameInstance> getGameInstances() {
		return gameInstances;
	}

	public void setGameInstances(List<GameInstance> gameInstances) {
		this.gameInstances = gameInstances;
	}

	public List<Player> getPrepTeamB() {
		return prepTeamB;
	}

	public void setPrepTeamB(List<Player> prepTeamB) {
		this.prepTeamB = prepTeamB;
	}

	public List<Player> getPrepTeamE() {
		return prepTeamE;
	}

	public void setPrepTeamE(List<Player> prepTeamE) {
		this.prepTeamE = prepTeamE;
	}

	public boolean isGameStarting() {
		return gameStarting;
	}

	public void setGameStarting(boolean gameStarting) {
		this.gameStarting = gameStarting;
	}
	
	public boolean isPreventJoiningTeams() {
		return preventJoiningTeams;
	}

	public void setPreventJoiningTeams(boolean preventJoiningTeams) {
		this.preventJoiningTeams = preventJoiningTeams;
	}

	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new EntityDamageByEntityListener(), this);
		pm.registerEvents(new EntityDamageListener(), this);
		pm.registerEvents(new PlayerItemConsumeListener(), this);
		pm.registerEvents(new PlayerQuitListener(), this);
		pm.registerEvents(new PlayerInteractListener(), this);
		pm.registerEvents(new InventoryClickListener(), this);
		pm.registerEvents(new EntityPickupItemListener(), this);
		pm.registerEvents(new EntityBreedListener(), this);
		pm.registerEvents(new EntityPotionEffectListener(), this);
		pm.registerEvents(new PlayerDeathListener(), this);
		pm.registerEvents(new PlayerInteractEntityListener(), this);
		pm.registerEvents(new BrewEventListener(), this);
		pm.registerEvents(new EnchantItemListener(), this);
		pm.registerEvents(new PlayerFishListener(), this);
		pm.registerEvents(new PlayerBedEnterListener(), this);
		pm.registerEvents(new CreatureSpawnListener(), this);
		pm.registerEvents(new PiglinBarterListener(), this);
		pm.registerEvents(new EntityResurrectListener(), this);
		pm.registerEvents(new VillagerAcquireTradeListener(), this);
		pm.registerEvents(new PlayerMoveListener(), this);
		pm.registerEvents(new PlayerRespawnListener(), this);
		pm.registerEvents(new EntityTameListener(), this);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("lockout")) {
			try {
				return LockoutCommand.cmd(sender, args);
			}
			catch(Exception e) {
				sender.sendMessage(ChatColor.RED + "There was a problem!");
				e.printStackTrace();
			}
		}
		return false;
	}
}
