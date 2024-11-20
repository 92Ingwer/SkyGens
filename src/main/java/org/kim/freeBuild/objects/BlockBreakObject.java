package org.kim.freeBuild.objects;

import net.kyori.adventure.text.Component;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.kim.freeBuild.enums.MineralsEnum;

public class BlockBreakObject {
    private int hearts;
    private TextDisplay display;
    Location loc;

    public BlockBreakObject(Material material, Location loc) {
        this.hearts = MineralsEnum.getHeart(material);
        this.loc = loc;
        spawnDisplay();
    }

    public int getHearts() {
        return hearts;
    }

    public void setHearts(int hearts) {
        this.hearts = hearts;
        display.remove();
        setDisplayName(Component.text("§c" + hearts + " ❤"));
        if(hearts > 0) {
            spawnDisplay();
        }
    }

    public void setDisplayName(Component text) {
        display.customName(text);
    }
    public void spawnDisplay() {
        World world = loc.getWorld();
        Location newLocation = new Location(world, loc.getX() + 0.5, loc.getY() + 1.5, loc.getZ() + 0.5);
        display = world.spawn(newLocation, TextDisplay.class);
        display.text(Component.text("§c" + hearts + " ❤"));
        display.setBillboard(Display.Billboard.CENTER);
    }
}
