package com.inceris.atsutilities.util;

import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Util {
	
	public static Logger l = Bukkit.getLogger();
	
    public static String colours(String message) {
        Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
        Matcher matcher = pattern.matcher(message);
        while (matcher.find()) {
            String hexCode = message.substring(matcher.start(), matcher.end());
            String replaceSharp = hexCode.replace('#', 'x');
           
            char[] ch = replaceSharp.toCharArray();
            StringBuilder builder = new StringBuilder("");
            for (char c : ch) {
                builder.append("&" + c);
            }
           
            message = message.replace(hexCode, builder.toString());
            matcher = pattern.matcher(message);
        }
        return ChatColor.translateAlternateColorCodes('&', message);
    }

	public static boolean percentChance(double percent) {
		double r = Math.random();
		if (r <= (percent / 100)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean regex(String message, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(message);
		if (matcher.find()) {
			return true;
		}
		return false;
	}
	
	public static int randomNumberBetween(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}
	
}
