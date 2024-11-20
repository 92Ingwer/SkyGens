package org.kim.freeBuild.objects;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class PlayerBaseObject {


    private int bankmoney;
    private String islandname;
    private int islandid;
    private String world;
    private double x;
    private double y;
    private double z;

    public static final HashMap<Player,PlayerBaseObject> playerBaseObjectMap = new HashMap<Player,PlayerBaseObject>();
    public PlayerBaseObject( int bankmoney, String islandname, int islandid,String world, double x, double y, double z) {
        this.bankmoney = bankmoney;
        this.islandname = islandname;
        this.islandid = islandid;
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public int getBankmoney() {
        return bankmoney;
    }
    public String getIslandname() {
        return islandname;
    }
    public int getIslandid() {
        return islandid;
    }
    public String getWorld() {
        return world;
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
}
