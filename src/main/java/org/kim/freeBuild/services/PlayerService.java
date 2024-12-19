package org.kim.freeBuild.services;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.kim.freeBuild.objects.PlayerBaseObject;

import java.util.HashMap;
import java.util.Map;

public class PlayerService {
    public static Map<Player, Boolean> wasPlayerOn = new HashMap<>();

    public static boolean isPlayerOnOtherIsland(Player p) {
        Location l = p.getLocation();
        PlayerBaseObject playerBaseObject = PlayerBaseObject.playerBaseObjectMap.get(p);
        return (l.getX() >= (playerBaseObject.getX() + 1000) || l.getZ() >= (playerBaseObject.getZ() + 1000) || l.getX() <= (playerBaseObject.getX() - 1000) || l.getZ() <= (playerBaseObject.getZ() - 1000)) && p.getWorld().equals(Bukkit.getWorld("InselWelt"));
    }
    public static boolean isPlayerOnOwnIsland(Player p) {
        Location l = p.getLocation();
        PlayerBaseObject playerBaseObject = PlayerBaseObject.playerBaseObjectMap.get(p);
        return !(l.getX() >= (playerBaseObject.getX() + 1000) || l.getZ() >= (playerBaseObject.getZ() + 1000) || l.getX() <= (playerBaseObject.getX() - 1000) || l.getZ() <= (playerBaseObject.getZ() - 1000)) && p.getWorld().equals(Bukkit.getWorld("InselWelt"));
    }
    public static boolean isPlayerInIslandWorld(Player p) {
        Location l = p.getLocation();
        PlayerBaseObject playerBaseObject = PlayerBaseObject.playerBaseObjectMap.get(p);
        return (playerBaseObject.getWorld().equals(Bukkit.getWorld("InselWelt")));
    }
}
