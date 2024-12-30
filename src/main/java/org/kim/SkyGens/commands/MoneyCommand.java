package org.kim.SkyGens.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.kim.SkyGens.objects.PlayerBaseObject;

public class MoneyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player p)) {
            return false;
        }
        if(strings.length != 0) {
            p.sendMessage("§cUsage: Verwende /money");
            return false;
        }
        PlayerBaseObject playerBaseObject = PlayerBaseObject.playerBaseObjectMap.get(p.getUniqueId());
        int bankmoney = playerBaseObject.getBankmoney();
        p.sendMessage("Geld von " + p.getName() + ": " + bankmoney);
        return false;
    }
}
