package org.kim.freeBuild.services;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.kim.freeBuild.objects.BrokenGenObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenerationService {

    public static HashMap<Player, BrokenGenObject> brokenGenObjects = new HashMap<>(); //Wenn Gen abgebaut wird
    public static HashMap<Player, List<Location>> locationForChestParticle = new HashMap<>(); // Für Chest Partikeln und so
    public static HashMap<Player,List<Location>> locationForDrillParticle = new HashMap<>(); // Für Drill Partikeln
    public static List<Player> brokedGenList = new ArrayList<>(); // Wenn Gen abgebaut wird (für den Drill)
}
