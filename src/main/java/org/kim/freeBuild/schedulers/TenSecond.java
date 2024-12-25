package org.kim.freeBuild.schedulers;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.TextDisplay;
import org.bukkit.scheduler.BukkitRunnable;
import org.kim.freeBuild.FreeBuild;

public class TenSecond {

    public static void tenSecondTimer() {
        new BukkitRunnable() {
            @Override
            public void run() {
                World world = Bukkit.getWorld("InselWelt");
                assert world != null;
                for(Entity entity : world.getEntities()) {
                    if(entity instanceof TextDisplay && ((TextDisplay) entity).text().contains(Component.text("❤"))) {
                        entity.remove();
                    }
                }
            }
        }.runTaskTimer(FreeBuild.getInstance(),0L,200L);
    }
}
