package org.kim.freeBuild.services;

import org.bukkit.Location;
import org.kim.freeBuild.objects.BrokenGenObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class GenerationService {

    public static HashMap<UUID, BrokenGenObject> brokenGenObjects = new HashMap<>(); //Wenn Gen abgebaut wird
    public static HashMap<UUID, List<Location>> locationForChestParticle = new HashMap<>(); // Für Chest Partikeln und so
    public static HashMap<UUID,List<Location>> locationForDrillParticle = new HashMap<>(); // Für Drill Partikeln
    public static List<UUID> brokedGenList = new ArrayList<>(); // Wenn Gen abgebaut wird (für den Drill)
    public static List<UUID> inactiveGenList = new ArrayList<>(); // Wenn Gen explodiert und erstmal nicht benutzbar ist
}
