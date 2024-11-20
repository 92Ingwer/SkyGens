package org.kim.freeBuild.services;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class IslandService {
    public static final HashMap<Player,Player> islandBeAllowedToMove = new HashMap<>(); // Dafür da, um zu wissen,. ob der Spieler auf der Insel sich bewegen darf.
}
