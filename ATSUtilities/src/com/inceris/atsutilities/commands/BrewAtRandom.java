package com.inceris.atsutilities.commands;

import com.inceris.atsutilities.ATSUtilities;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.ThreadLocalRandom;

public class BrewAtRandom {

    private static final ATSUtilities atsu = ATSUtilities.getPlugin(ATSUtilities.class);

    public static void BrewChoices(int choice, String name) {
        String command = "minecraft:give " + name + " ";

        switch (choice) {
            case 1 ->
                    command += "splash_potion{CustomPotionEffects:[{Id:11,Duration:300,Amplifier:1},{Id:12,Duration:300,Amplifier:1}],CustomPotionColor:16765266,"
                            + "display:{Name:'{\"text\":\"Protective Concoction\",\"italic\":\"false\"}'}} 1";
            case 2 ->
                    command += "splash_potion{CustomPotionEffects:[{Id:17,Duration:750,Amplifier:3},{Id:2,Duration:500,Amplifier:1},{Id:7,Duration:20,Amplifier:1}],"
                            + "CustomPotionColor:2697513,display:{Name:'{\"text\":\"Alchemist\\'s Plague\",\"italic\":\"false\"}'}} 1";
            case 3 ->
                    command += "splash_potion{CustomPotionEffects:[{Id:6,Duration:20,Amplifier:1},{Id:10,Duration:200,Amplifier:1}],CustomPotionColor:16712985,"
                            + "display:{Name:'{\"text\":\"Healing Concoction\",\"italic\":\"false\"}'}} 1";
            case 4 ->
                    command += "splash_potion{CustomPotionEffects:[{Id:1,Duration:400,Amplifier:1},{Id:8,Duration:400,Amplifier:1}],CustomPotionColor:3063039,"
                            + "display:{Name:'{\"text\":\"Agile Concoction\",\"italic\":\"false\"}'}} 1";
        }
        atsu.SendCommand(command);
    }

    public static boolean cmd(String name) {
        Bukkit.getScheduler().runTask(atsu, () -> {
            int amount = 0;
            Player p = atsu.getServer().getPlayer(name);
            if (p == null) return;
            for (ItemStack item : p.getInventory().getContents()) {
                if (item != null) {
                    if (item.getType() == Material.SPLASH_POTION) {
                        amount += item.getAmount();
                    }
                }
            }
            if (amount <= 10) {
                int r = ThreadLocalRandom.current().nextInt(1, 4 + 1);

                BrewChoices(r, p.getName());
                atsu.SendCommand("minecraft:give " + p.getName()
                        + " splash_potion{CustomPotionEffects:[{Id:7,Duration:20,Amplifier:0},{Id:19,Duration:100,Amplifier:1}],CustomPotionColor:8257720,"
                        + "display:{Name:'{\"text\":\"Harming Concoction\",\"italic\":\"false\"}'}} 1");
            } else
                p.sendMessage(ChatColor.RED + "You can't carry any more potions!");
        });

        return true;
    }
}
