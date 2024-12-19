package org.kim.freeBuild.objects;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;

@Getter
@Setter
public class GenerationBaseObject {
    private double x;
    private double y;
    private double z;
    private int level;
    private int upgrade;
    private double fuel;

    public static final HashMap<Player,GenerationBaseObject> generationBaseObjectMap = new HashMap<>();
    public GenerationBaseObject(double x, double y, double z, int level, int upgrade, double fuel) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.level = level;
        this.upgrade = upgrade;
        this.fuel = fuel;
    }
    public Location getLocation() {
        return new Location(Bukkit.getWorld("InselWelt"),getX(),getY(),getZ());
    }

}
