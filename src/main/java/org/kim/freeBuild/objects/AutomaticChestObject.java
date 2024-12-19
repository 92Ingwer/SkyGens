package org.kim.freeBuild.objects;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class AutomaticChestObject {

    @Getter
    private double x;
    @Getter
    private double y;
    @Getter
    private double z;
    @Getter
    private Chest chest;
    private boolean setting;

    public static HashMap<Player, AutomaticChestObject> automaticChestObjectMap = new HashMap<Player, AutomaticChestObject>();
    public AutomaticChestObject(Double x, Double y, Double z, Chest chest, boolean setting) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.chest = chest;
        this.setting = setting;
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

    public boolean getSetting() {
        return setting;
    }
    public void changeSetting() {
        this.setting = !setting;
    }
}
