package org.kim.SkyGens.schedulers;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.TextDisplay;
import org.bukkit.scheduler.BukkitRunnable;
import org.kim.SkyGens.SkyGens;

public class TenSecond {

    public static void tenSecondTimer() {
        new BukkitRunnable() {
            @Override
            public void run() {
                World world = Bukkit.getWorld("InselWelt");
                for(Entity entity : world.getEntities()) {
                    if(entity instanceof TextDisplay textDisplay) {
                        Component componentText = textDisplay.text();
                        String text = componentText.toString();
                        if(text.contains("§cInactive!") || text.contains("⌛")) {
                            return;
                        }
                        textDisplay.remove();
                    }
                }
            }
        }.runTaskTimer(SkyGens.getInstance(),0L,200L);
    }
}
