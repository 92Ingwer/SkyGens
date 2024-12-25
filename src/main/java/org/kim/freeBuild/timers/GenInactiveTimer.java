package org.kim.freeBuild.timers;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Display;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.scheduler.BukkitRunnable;
import org.kim.freeBuild.FreeBuild;
import org.kim.freeBuild.objects.GenerationBaseObject;
import org.kim.freeBuild.services.GenerationService;

import java.util.Objects;

public class GenInactiveTimer {
    /**
     * Creates an inactive timer for the given player. The timer displays a countdown
     * and an "Inactive!" label near the corresponding location of the player's
     * associated generation base object. Once the timer ends, the player is
     * removed from the inactive generator list, and the displays are removed.
     *
     * @param p The player for whom the inactive timer is created. This player must
     *          have an associated GenerationBaseObject mapped in the system.
     */
    public static void createInactiveTimer(Player p) {
        GenerationService.inactiveGenList.add(p);
        GenerationBaseObject generationBaseObject = GenerationBaseObject.generationBaseObjectMap.get(p);
        Location location = generationBaseObject.getLocation().clone().add(0.5,1.5,0.5);
        new BukkitRunnable() {
            int time = 90;
            final TextDisplay textDisplayTimer = Objects.requireNonNull(Bukkit.getWorld("InselWelt")).spawn(location, TextDisplay.class);
            final TextDisplay textDisplayInactive = Objects.requireNonNull(Bukkit.getWorld("InselWelt")).spawn(location.clone().add(0,0.3,0), TextDisplay.class);
            @Override
            public void run() {
                if(time <= 0) {
                    GenerationService.inactiveGenList.remove(p);
                    textDisplayTimer.remove();
                    textDisplayInactive.remove();
                    this.cancel();
                    return;
                }
                textDisplayInactive.text(Component.text("§cInactive!"));
                textDisplayInactive.setBillboard(Display.Billboard.CENTER);
                textDisplayTimer.text(Component.text("§c"+time/60+":"+time%60+" ⌛"));
                textDisplayTimer.setBillboard(Display.Billboard.CENTER);
                time --;
            }
        }.runTaskTimer(FreeBuild.getInstance(),0,20L);
    }
}
