package org.kim.freeBuild.commands;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.jetbrains.annotations.NotNull;
import org.kim.freeBuild.methods.CreateIslandMethods;
import org.kim.freeBuild.objects.PlayerBaseObject;
import org.kim.freeBuild.services.IslandService;


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
        if(!CreateIslandMethods.hasPlayerIsland(p)) {
            p.sendMessage("Hast keine Insel bro");
            return false;
        }
        if(strings.length == 0) {
            PlayerBaseObject playerBaseObject = PlayerBaseObject.playerBaseObjectMap.get(p);
            Location loc = new Location(Bukkit.getWorld(playerBaseObject.getWorld()),playerBaseObject.getX(),playerBaseObject.getY(),playerBaseObject.getZ());
            p.teleport(loc);
            IslandService.islandBeAllowedToMove.remove(p);
            return true;
        }
        Player t = Bukkit.getPlayer(strings[0]);
        if(t == null) {
            p.sendMessage("Spieler nicht on!");
            return false;
        }
        PlayerBaseObject playerBaseObject = PlayerBaseObject.playerBaseObjectMap.get(t);
        Location loc = new Location(Bukkit.getWorld(playerBaseObject.getWorld()),playerBaseObject.getX(),playerBaseObject.getY(),playerBaseObject.getZ());
        p.teleport(loc);
        IslandService.islandBeAllowedToMove.remove(p);
        IslandService.islandBeAllowedToMove.put(p,t);
        return false;
    }
}
