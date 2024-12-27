package org.kim.freeBuild.services;

import java.util.HashMap;
import java.util.UUID;

public class IslandService {
    public static final HashMap<UUID, UUID> islandBeAllowedToMove = new HashMap<>(); // Dafür da, um zu wissen,. ob der Spieler auf der Insel sich bewegen darf.
}
