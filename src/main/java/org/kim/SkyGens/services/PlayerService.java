package org.kim.SkyGens.services;

import net.kyori.adventure.bossbar.BossBar;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.kim.SkyGens.objects.PlayerBaseObject;
import org.kim.SkyGens.skills.Skills;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerService {
    public static Map<UUID, Boolean> wasPlayerOn = new HashMap<>();
    public static Map<UUID, BossBar> hasPlayerBossBars = new HashMap<>();
    public static Map<UUID, Skills> skillGUIWhichSkill = new HashMap<>();
    public static Map<UUID, Integer> skillGUIWhichSite = new HashMap<>();

    public static boolean isPlayerOnOtherIsland(Player p) {
        Location l = p.getLocation();
        PlayerBaseObject playerBaseObject = PlayerBaseObject.playerBaseObjectMap.get(p.getUniqueId());
        return (isPlayerInIslandWorld(p) && (l.getX() >= (playerBaseObject.getX() + 1000) || l.getZ() >= (playerBaseObject.getZ() + 1000) || l.getX() <= (playerBaseObject.getX() - 1000) || l.getZ() <= (playerBaseObject.getZ() - 1000)));
    }
    public static boolean isPlayerOnOwnIsland(Player p) {
        Location l = p.getLocation();
        PlayerBaseObject playerBaseObject = PlayerBaseObject.playerBaseObjectMap.get(p.getUniqueId());
        return !(isPlayerInIslandWorld(p) && (l.getX() >= (playerBaseObject.getX() + 1000) || l.getZ() >= (playerBaseObject.getZ() + 1000) || l.getX() <= (playerBaseObject.getX() - 1000) || l.getZ() <= (playerBaseObject.getZ() - 1000)));
    }
    public static boolean isPlayerInIslandWorld(Player p) {
        Location l = p.getLocation();
        PlayerBaseObject playerBaseObject = PlayerBaseObject.playerBaseObjectMap.get(p.getUniqueId());
        World playerWorld = Bukkit.getWorld(playerBaseObject.getWorld());
        return(l.getWorld().equals(playerWorld));
    }
}
