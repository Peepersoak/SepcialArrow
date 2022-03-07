package com.peepersoak.specialarrows.commands;

import com.peepersoak.specialarrows.arrows.Types;
import com.peepersoak.specialarrows.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) return null;
        if (!player.isOp()) return null;


        if (args.length == 1) {
            return types();
        }

        if (args.length == 2) {
            List<String> ammount = new ArrayList<>();
            ammount.add(Utils.color("&8[Ammount]"));
            return ammount;
        }

        return null;
    }

    public List<String> types() {
        List<String> types = new ArrayList<>();
        for (Types type : Types.values()) {
            types.add(type.name());
        }
        return types;
    }
}
