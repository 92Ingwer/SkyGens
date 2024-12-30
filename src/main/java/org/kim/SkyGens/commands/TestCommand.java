package org.kim.SkyGens.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.kim.SkyGens.services.GenerationService;

import java.util.List;
import java.util.UUID;

public class TestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player p)) {
            return false;
        }

        List<UUID> uuidList = GenerationService.inactiveGenList;
        for(UUID uuid : uuidList) {
            p.sendMessage(uuid.toString());
        }


        return false;
    }
}
