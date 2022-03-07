package com.peepersoak.specialarrows.commands;

import com.peepersoak.specialarrows.SpecialArrows;
import com.peepersoak.specialarrows.arrows.ArrowFactory;
import com.peepersoak.specialarrows.arrows.Types;
import com.peepersoak.specialarrows.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            SpecialArrows.getInstance().getLogger().warning(Utils.color("&cYou are not allowed to use this!!!"));
            return false;
        }
        if (!player.isOp()) {
            player.sendMessage(Utils.color("&cYou don't have enough permission to use this command!"));
            return false;
        }

        ArrowFactory arrowFactory = new ArrowFactory();

        if (args.length == 1) {
            String type = args[0];
            for (Types types : Types.values()) {
                if (types.name().equalsIgnoreCase(type)) {
                    player.getInventory().addItem(arrowFactory.createArrow(types));
                }
            }
        }

        else if (args.length == 2) {
            String type = args[0];
            int ammount;
            try {
                ammount = Integer.parseInt(args[1]);
            } catch (NumberFormatException ex) {
                player.sendMessage(Utils.color("&cPlease enter a valid number!!"));
                return false;
            }
            for (Types types : Types.values()) {
                if (types.name().equalsIgnoreCase(type)) {
                    player.getInventory().addItem(arrowFactory.createArrow(types, ammount));
                }
            }
        } else {
            player.sendMessage(Utils.color("&eUsage: /specialarrow [type] [ammount]"));
        }

        return false;
    }
}
