package org.kim.freeBuild.objects;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.UUID;

@Getter
@Setter
public class PlayerBaseObject {


    private int bankmoney;
    private String islandname;
    private int islandid;
    private String world;
    private double x;
    private double y;
    private double z;
    private int amountofGens;
    public static final HashMap<UUID, PlayerBaseObject> playerBaseObjectMap = new HashMap<>();

    public PlayerBaseObject(int bankmoney, String islandname, int islandid, String world, double x, double y, double z, int amountofGens) {
        this.bankmoney = bankmoney;
        this.islandname = islandname;
        this.islandid = islandid;
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.amountofGens = amountofGens;
    }

    public Location getLocation() {

        return new Location(Bukkit.getWorld(world), getX(), getY(), getZ());
    }
}
