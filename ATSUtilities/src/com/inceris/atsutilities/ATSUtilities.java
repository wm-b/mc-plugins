package com.inceris.atsutilities;

import com.inceris.atsutilities.commands.*;
import com.inceris.atsutilities.listeners.*;
import com.inceris.atsutilities.tabcompletion.WbTabCompletion;
import com.inceris.atsutilities.util.InvSerialisation;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ATSUtilities extends JavaPlugin {

    public final File wbDataFile = new File("plugins/ATSUtilities/wbData.yml");
    public final List<UUID> transformMobs = new ArrayList<>();
    public final Enchantment[] allowedEnchantments = new Enchantment[]{
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
    public YamlConfiguration wbData = null;
    public String latestJoin = null;
    public boolean denyTallGrass = false;
    public boolean denyInfested = true;
    public boolean debug = false;
    public Inventory lostandfoundInv = null;
    private static ATSUtilities instance;

    public static ATSUtilities getInstance() {
        return instance;
    }
    public YamlConfiguration lostandfoundData = null;
    public File lostandfoundFile = new File("plugins/ATSUtilities/lostandfound.yml");

    @Override
    public void onEnable() {
        instance = this;

        this.saveDefaultConfig();

        if(!lostandfoundFile.exists()){
            try {
                boolean ignored = lostandfoundFile.createNewFile();
                lostandfoundData = YamlConfiguration.loadConfiguration(lostandfoundFile);
                lostandfoundData.addDefault("SerialisedInventory", InvSerialisation.toBase64(getServer().createInventory(null, 27, "Lost and Found")));
                lostandfoundData.options().copyDefaults(true);
                lostandfoundData.save(lostandfoundFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            lostandfoundData = YamlConfiguration.loadConfiguration(lostandfoundFile);
        }

        lostandfoundInv = InvSerialisation.toInventory(lostandfoundData.getString("SerialisedInventory"), null, "Lost and Found");

        if (!wbDataFile.exists()) {
            try {
                boolean ignored = wbDataFile.createNewFile();
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
        pm.registerEvents(new InventoryCloseListener(), this);
        pm.registerEvents(new AsyncPlayerChatListener(), this);
        pm.registerEvents(new PlayerInteractListener(), this);
        pm.registerEvents(new PlayerCommandPreprocessListener(), this);
        pm.registerEvents(new PlayerDeathListener(), this);
        pm.registerEvents(new EntityDamageByEntityListener(), this);
        pm.registerEvents(new PlayerJoinListener(), this);
        pm.registerEvents(new PlayerQuitListener(), this);
        pm.registerEvents(new EntityDeathListener(), this);
        pm.registerEvents(new CountdownCmd(), this);

        Objects.requireNonNull(getCommand("wb")).setTabCompleter(new WbTabCompletion());
        Objects.requireNonNull(getCommand("welcome")).setTabCompleter(new WbTabCompletion());

        Objects.requireNonNull(getCommand("countdown")).setTabCompleter(new CountdownCmd());
    }

    @Override
    public void onDisable() {

    }

    public void SendCommand(String command) {
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        Bukkit.dispatchCommand(console, command);
    }

    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command cmd, @Nonnull String label, @Nonnull String[] args) {
        try {
            if (label.equalsIgnoreCase("atsutilities") || label.equalsIgnoreCase("atsu")) {
                return ATSU.cmd(sender, args);
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

            if ((label.equalsIgnoreCase("lostandfound") || label.equalsIgnoreCase("laf")) && sender.hasPermission("atsutilities.lostandfound")) {
                return LostAndFound.cmd(sender);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}