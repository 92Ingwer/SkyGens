package org.kim.freeBuild.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.kim.freeBuild.objects.PlayerBaseObject;

public class MoneyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player)) {
            return false;
        }
        Player p = (Player) commandSender;
        if(strings.length != 0) {
            p.sendMessage("§cUsage: Verwende /money");
            return false;
        }
        PlayerBaseObject playerBaseObject = PlayerBaseObject.playerBaseObjectMap.get(p);
        int bankmoney = playerBaseObject.getBankmoney();
        p.sendMessage("Geld von " + p.getName() + ": " + bankmoney);
        return false;
    }
}
