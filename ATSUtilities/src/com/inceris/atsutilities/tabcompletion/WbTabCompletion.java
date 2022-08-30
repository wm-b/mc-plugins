package com.inceris.atsutilities.tabcompletion;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class WbTabCompletion implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		
		if (args.length == 1) {
			List<String> list = new ArrayList<String>();
			list.add("welcome");
			list.add("workbench");
			return list;
		}
		
		return null;
	}
	
}
