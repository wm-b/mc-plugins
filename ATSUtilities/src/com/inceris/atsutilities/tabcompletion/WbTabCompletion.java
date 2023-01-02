package com.inceris.atsutilities.tabcompletion;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class WbTabCompletion implements TabCompleter {

    @Override
    public List<String> onTabComplete(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String alias, @Nonnull String[] args) {

        if (args.length == 1) {
            List<String> list = new ArrayList<String>();
            list.add("welcome");
            list.add("workbench");
            return list;
        }

        return null;
    }

}
