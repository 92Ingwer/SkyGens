package org.kim.SkyGens.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.jetbrains.annotations.NotNull;
import org.kim.SkyGens.methods.CreateIslandMethods;
import org.kim.SkyGens.objects.PlayerBaseObject;
import org.kim.SkyGens.services.IslandService;


public class IslandTPCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player p)) {
            return false;
        }

        if(strings.length > 1) {
            p.sendMessage("§cUsage: /istp (Spieler)");
            return false;
        }

        if(strings.length == 0) {
            if(!CreateIslandMethods.hasPlayerIsland(p)) {
                p.sendMessage("Hast keine Insel bro");
                return false;
            }
            PlayerBaseObject playerBaseObject = PlayerBaseObject.playerBaseObjectMap.get(p.getUniqueId());
            Location loc = new Location(Bukkit.getWorld(playerBaseObject.getWorld()),playerBaseObject.getX(),playerBaseObject.getY(),playerBaseObject.getZ());
            p.teleport(loc);
            IslandService.islandBeAllowedToMove.remove(p.getUniqueId());
            return true;
        }
        Player t = Bukkit.getPlayer(strings[0]);
        if(t == null) {
            p.sendMessage("Spieler nicht on!");
            return false;
        }
        PlayerBaseObject playerBaseObject = PlayerBaseObject.playerBaseObjectMap.get(t.getUniqueId());
        Location loc = new Location(Bukkit.getWorld(playerBaseObject.getWorld()),playerBaseObject.getX(),playerBaseObject.getY(),playerBaseObject.getZ());
        p.teleport(loc);
        IslandService.islandBeAllowedToMove.remove(p.getUniqueId());
        IslandService.islandBeAllowedToMove.put(p.getUniqueId(),t.getUniqueId());
        return false;
    }
}
