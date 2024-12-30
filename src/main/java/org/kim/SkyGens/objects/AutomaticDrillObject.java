package org.kim.SkyGens.objects;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Dispenser;

import java.util.HashMap;
import java.util.UUID;

@Getter
@Setter
public class AutomaticDrillObject {

    private Double x;
    private Double y;
    private Double z;
    private boolean setting;
    private int level;
    private Dispenser dispenser;

    public static HashMap<UUID, AutomaticDrillObject> automaticDrillObject = new HashMap<>();
    public AutomaticDrillObject(Double x, Double y, Double z,Dispenser dispenser, boolean setting, int level) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.setting = setting;
        this.level = level;
        this.dispenser = dispenser;
    }
    public void changeSetting() {
        this.setting = !setting;
    }
    public Location getLocation() {
        return new Location(Bukkit.getWorld("InselWelt"),x,y,z);
    }
}
