package com.inceris.cascade.listeners;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.potion.PotionEffectType;

public class DurabilityLossListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerItemDamageEvent(PlayerItemDamageEvent event) {
        Player p = event.getPlayer();
        ItemStack item = event.getItem();
        Damageable meta = (Damageable) item.getItemMeta();
        if (meta == null) return;
        int durability = item.getType().getMaxDurability() - (meta.getDamage() + 1);

        int durabilityThreshold = (int) Math.max(10, item.getType().getMaxDurability() * 0.01);
        durabilityThreshold *= (item.getEnchantmentLevel(Enchantment.DIG_SPEED) * 0.3) + 1;
        if (p.hasPotionEffect(PotionEffectType.FAST_DIGGING)) durabilityThreshold *= 3;

        if (durability <= durabilityThreshold) {

            StringBuilder name_ = new StringBuilder(item.getType().name().toLowerCase());

            String[] nameArray = name_.toString().split("_");
            name_ = new StringBuilder();
            for (String s : nameArray) {
                name_.append(s.charAt(0)).append(s.substring(1)).append(" ");
            }
            String name = name_.toString().trim();

            String hasHave = name.charAt(name.length() - 1) == 's' ? "have" : "has";

            String message;
            if (durability <= 0) {
                message = ChatColor.RED + "Uh-oh! Your " + name + " " + hasHave + " broken!";
            } else {
                message = ChatColor.RED + "Your " + name + " only " + hasHave + " " + durability + " use";
                if (durability > 1) message += "s";
                message += " left!";

                if (durability < 5 || durability % 5 == 0) {
                    float v = (1f - durability / (float) durabilityThreshold) * 1.5f + 0.5f;
                    p.playSound(p.getEyeLocation(), Sound.BLOCK_ANVIL_LAND, v, v);
                }
            }

            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));

            p.sendTitle("", message, 0, 20, 10);

        }
    }
}
