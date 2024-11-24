package org.kim.freeBuild.schedulers;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.TextDisplay;
import org.kim.freeBuild.FreeBuild;

public class TenSecond {

    public static void tenSecondTimer() {
        Bukkit.getScheduler().runTaskTimer(FreeBuild.getInstance(),
                new Runnable() {
                    @Override
                    public void run() {
                        World world = Bukkit.getWorld("InselWelt");
                        for(Entity entity : world.getEntities()) {
                            if(entity instanceof TextDisplay) {
                                entity.remove();
                            }
                        }
                    }
                },0L, 200L);
    }
}
