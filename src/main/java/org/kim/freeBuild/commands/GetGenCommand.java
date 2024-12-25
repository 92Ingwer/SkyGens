package org.kim.freeBuild.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.kim.freeBuild.guis.GetGenGUI;
import org.kim.freeBuild.methods.CreateIslandMethods;

public class GetGenCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player p)) {
            return false;
        }
        if(strings.length != 0) {
            return false;
        }
        if(!CreateIslandMethods.hasPlayerIsland(p)) {
            p.sendMessage("Erstelle zuerst eine Insel!");
            return false;
        }
        GetGenGUI.openInventory(p);

        return false;
    }
}
