package com.inceris.joinmessage;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class JoinMessage extends JavaPlugin {
	
	@Override
	public void onEnable() {
		Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new FirstJoinListener(), this);
	}
	
	@Override
	public void onDisable() {
		
	}
}
