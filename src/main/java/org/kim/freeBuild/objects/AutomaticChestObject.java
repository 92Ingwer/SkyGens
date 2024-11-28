package org.kim.freeBuild.objects;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class AutomaticChestObject {

    private double x;
    private double y;
    private double z;
    private Chest chest;

    public static HashMap<Player, AutomaticChestObject> automaticChestObjectMap = new HashMap<Player, AutomaticChestObject>();
    public AutomaticChestObject(Double x, Double y, Double z, Chest chest) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.chest = chest;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getZ() {
        return z;
    }

    public Location getLocation() {
        Location loc = new Location(Bukkit.getWorld("InselWelt"),getX(),getY(),getZ());
        return loc;
    }

    public void setLocation(Location loc) {
        this.x = loc.getX();
        this.y = loc.getY();
        this.z = loc.getZ();
    }
    public Chest getChest() {
        return chest;
    }
}
