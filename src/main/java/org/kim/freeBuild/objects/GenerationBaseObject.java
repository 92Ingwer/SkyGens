package org.kim.freeBuild.objects;


import org.bukkit.entity.Player;

import java.util.HashMap;

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
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getZ() {
        return z;
    }
    public int getLevel() {
        return level;
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    public void setZ(double z) {
        this.z = z;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public void setUpgrade(int upgrade) {
        this.upgrade = upgrade;
    }
    public int getUpgrade() {
        return upgrade;
    }
    public void setFuel(double fuel) {
        this.fuel = fuel;
    }
    public double getFuel() {
        return fuel;
    }

}
