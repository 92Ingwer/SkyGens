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
    private int amountofGens;
    public static final HashMap<Player, PlayerBaseObject> playerBaseObjectMap = new HashMap<>();
    public PlayerBaseObject( int bankmoney, String islandname, int islandid,String world, double x, double y, double z, int amountofGens) {
        this.bankmoney = bankmoney;
        this.islandname = islandname;
        this.islandid = islandid;
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.amountofGens = amountofGens;
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
    public int getAmountofGens() {
        return amountofGens;
    }
    public void setAmountofGens(int amountofGens) {
        this.amountofGens = amountofGens;
    }
    public void setBankmoney(int bankmoney) {
        this.bankmoney = bankmoney;
    }
}
