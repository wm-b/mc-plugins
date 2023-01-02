package com.inceris.atsutilities.listeners;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

public class DurabilityLossListener implements Listener {

    @EventHandler
    public void onPlayerItemDamageEvent(PlayerItemDamageEvent event) {

        Player p = event.getPlayer();
        ItemStack item = event.getItem();
        Damageable meta = (Damageable) item.getItemMeta();
        assert meta != null;
        int durability = item.getType().getMaxDurability() - (meta.getDamage() + 1);

        if (durability <= 10) {

            StringBuilder name_ = new StringBuilder(item.getType().name().toLowerCase());

            String[] nameArray = name_.toString().split("_");
            name_ = new StringBuilder();
            for (String s : nameArray) {
                name_.append(s.charAt(0)).append(s.substring(1)).append(" ");
            }
            String name = name_.toString().trim();

            String hasHave = name.charAt(name.length() - 1) == 's' ? "have" : "has";

            String message = "";
            if (durability <= 0)
                message = ChatColor.RED + "Uh-oh! Your " + name + " " + hasHave + " broke!";
            else if (durability == 1)
                message = ChatColor.RED + "Your " + name + " only " + hasHave + " " + durability + " use left!";
            else
                message = ChatColor.RED + "Your " + name + " only " + hasHave + " " + durability + " uses left!";

            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));

        }
    }
}
