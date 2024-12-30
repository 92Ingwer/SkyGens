package org.kim.SkyGens.objects;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Chest;

import java.util.HashMap;
import java.util.UUID;

public class AutomaticChestObject {

    @Getter
    private double x;
    @Getter
    private double y;
    @Getter
    private double z;
    @Getter
    private final Chest chest;
    private boolean setting;

    public static HashMap<UUID, AutomaticChestObject> automaticChestObjectMap = new HashMap<>();
    public AutomaticChestObject(Double x, Double y, Double z, Chest chest, boolean setting) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.chest = chest;
        this.setting = setting;
    }

    public Location getLocation() {
        return new Location(Bukkit.getWorld("InselWelt"),getX(),getY(),getZ());
    }

    public void setLocation(Location loc) {
        this.x = loc.getX();
        this.y = loc.getY();
        this.z = loc.getZ();
    }

    public boolean getSetting() {
        return setting;
    }
    public void changeSetting() {
        this.setting = !setting;
    }
}
