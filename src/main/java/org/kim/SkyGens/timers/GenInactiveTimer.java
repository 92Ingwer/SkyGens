package org.kim.SkyGens.timers;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Display;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.scheduler.BukkitRunnable;
import org.kim.SkyGens.SkyGens;
import org.kim.SkyGens.objects.GenerationBaseObject;
import org.kim.SkyGens.services.GenerationService;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

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
        GenerationService.inactiveGenList.add(p.getUniqueId());
        GenerationBaseObject generationBaseObject = GenerationBaseObject.generationBaseObjectMap.get(p.getUniqueId());
        Location location = generationBaseObject.getLocation().clone().add(0.5,1.5,0.5);
        new BukkitRunnable() {
            int millis = 90*1000;
            final TextDisplay textDisplayTimer = Objects.requireNonNull(Bukkit.getWorld("InselWelt")).spawn(location, TextDisplay.class);
            final TextDisplay textDisplayInactive = Objects.requireNonNull(Bukkit.getWorld("InselWelt")).spawn(location.clone().add(0,0.3,0), TextDisplay.class);
            @Override
            public void run() {
                if(millis <= 0) {
                    GenerationService.inactiveGenList.remove(p.getUniqueId());
                    textDisplayTimer.remove();
                    textDisplayInactive.remove();
                    this.cancel();
                    return;
                }
                textDisplayInactive.text(Component.text("§cInactive!"));
                textDisplayInactive.setBillboard(Display.Billboard.CENTER);
                String timeFormated = String.format("%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(millis),
                        TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
                );
                textDisplayTimer.text(Component.text("§c"+timeFormated + " ⌛"));
                textDisplayTimer.setBillboard(Display.Billboard.CENTER);
                millis = millis-1000;
            }
        }.runTaskTimer(SkyGens.getInstance(),0,20L);
    }
}
