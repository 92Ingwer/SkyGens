package org.kim.freeBuild.services;

import org.bukkit.Location;
import org.kim.freeBuild.objects.BlockBreakObject;

import java.util.HashMap;

public class BlockService {
    public static final HashMap<Location, BlockBreakObject> blockObjectHashMap = new HashMap<>(); //Wenn Block abgebaut wird, wird es erstellt
}
