package com.inceris.cascade;

import com.inceris.cascade.commands.*;
import com.inceris.cascade.listeners.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;

public class Cascade extends JavaPlugin {
    public boolean debug = false;
    private static Cascade instance;

    public static Cascade getInstance() {
        return instance;
    }
    public YamlConfiguration cascGearData = null;
    public File cascGearFile = new File("plugins/Cascade/cascGear.yml");

    @Override
    public void onEnable() {
        instance = this;

        this.saveDefaultConfig();

        if(!cascGearFile.exists()){
            try {
                boolean ignored = cascGearFile.createNewFile();
                cascGearData = YamlConfiguration.loadConfiguration(cascGearFile);
                cascGearData.options().copyDefaults(true);
                cascGearData.save(cascGearFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            cascGearData = YamlConfiguration.loadConfiguration(cascGearFile);
        }

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new DurabilityLossListener(), this);
        pm.registerEvents(new InventoryClickListener(), this);
        pm.registerEvents(new InventoryCloseListener(), this);
        pm.registerEvents(new PlayerInteractListener(), this);
        pm.registerEvents(new PlayerDeathListener(), this);
        pm.registerEvents(new EntityDamageByEntityListener(), this);
        pm.registerEvents(new CountdownCmd(), this);
    }

    @Override
    public void onDisable() {

    }

    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command cmd, @Nonnull String label, @Nonnull String[] args) {
        try {
            if (label.equalsIgnoreCase("cascade")) {
                return CascadeCmd.cmd(sender, args);
            }

            if (label.equalsIgnoreCase("bc") || label.equalsIgnoreCase("broadcast")) {
                return Broadcast.cmd(args);
            }

            if (label.equalsIgnoreCase("countdown") || label.equalsIgnoreCase("cd")) {
                return CountdownCmd.cmd(sender, args);
            }

            if (label.equalsIgnoreCase("cascgear") && (sender.hasPermission("cascade.cascgear.use") || sender.hasPermission("cascade.cascgear.edit"))) {
                return CascGear.cmd(sender, args);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}