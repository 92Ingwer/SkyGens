package org.kim.freeBuild.objects;


import org.bukkit.entity.Player;

import java.util.HashMap;

public class GenerationBaseObject {
    private double x;
    private double y;
    private double z;
    private int level;

    public static final HashMap<Player,GenerationBaseObject> generationBaseObjectMap = new HashMap<>();
    public GenerationBaseObject(double x, double y, double z, int level) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.level = level;
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

}
