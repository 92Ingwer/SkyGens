package org.kim.freeBuild.listeners;

import com.destroystokyo.paper.ParticleBuilder;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;
import org.joml.AxisAngle4f;
import org.joml.Vector3f;
import org.kim.freeBuild.FreeBuild;
import org.kim.freeBuild.items.GeneratorItems;
import org.kim.freeBuild.objects.AutomaticChestObject;
import org.kim.freeBuild.objects.AutomaticDrillObject;
import org.kim.freeBuild.objects.BrokenGenObject;
import org.kim.freeBuild.objects.GenerationBaseObject;
import org.kim.freeBuild.services.GenerationService;
import org.kim.freeBuild.utils.ItemBuilder;

import java.util.ArrayList;
import java.util.List;

public class PlaceUpgraderListener implements Listener {
    static int time = 0;

    @EventHandler
    public void onPlaceUpgrader(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        ItemStack item = e.getItemInHand();
        Block b = e.getBlockPlaced();
        AutomaticChestObject automaticChestObject = AutomaticChestObject.automaticChestObjectMap.get(p);
        GenerationBaseObject generationBaseObject = GenerationBaseObject.generationBaseObjectMap.get(p);
        AutomaticDrillObject automaticDrillObject = AutomaticDrillObject.automaticDrillObject.get(p);

        //Chest placen
        if (item.equals(GeneratorItems.getChest())) {
            if (automaticChestObject.getX() != -1.0) {
                e.setCancelled(true);
                p.sendMessage("Du hast bereits eine Automatische Chest geplaced!");
                return;
            }
            Location loc = b.getLocation();
            if ((generationBaseObject.getLocation().distance(loc) > 10) || generationBaseObject.getLocation().distance(loc) < 2) {
                e.setCancelled(true);
                p.sendMessage("Zu weit weg/nah von/an deinem Generator!");
                return;
            }
            Chest chest = (Chest) b.getState();
            setItemSlots(chest);
            AutomaticChestObject automaticChestObjectNew = new AutomaticChestObject(loc.getX(), loc.getY(), loc.getZ(), chest, true);
            AutomaticChestObject.automaticChestObjectMap.put(p, automaticChestObjectNew);
            List<Location> locationList = new ArrayList<>();
            locationList.add(loc.add(0.5, 0.5, 0.5));
            locationList.add(generationBaseObject.getLocation().add(0.5, 0.5, 0.5));
            GenerationService.locationForChestParticle.put(p, locationList);
            drawParticleLine(p);
        }

        //DRill placen
        if (item.equals(GeneratorItems.getDrill())) {
            if (automaticDrillObject.getX() != -1.0) {
                e.setCancelled(true);
                p.sendMessage("Du hast bereits einen Drill geplaced!");
                return;
            }
            Location loc = b.getLocation();
            if (generationBaseObject.getLocation().distance(loc) >= 10) {
                e.setCancelled(true);
                p.sendMessage("Dein Drill muss 2 blöcke entfernt sein!");
                return;
            }
            AutomaticDrillObject automaticDrillObjectNew = new AutomaticDrillObject(loc.getX(), loc.getY(), loc.getZ(), true, 0);
            AutomaticDrillObject.automaticDrillObject.put(p, automaticDrillObjectNew);
            List<Location> locationList = new ArrayList<>();
            locationList.add(loc.add(1, 1, 1));
            locationList.add(generationBaseObject.getLocation().add(0.5, 0.5, 0.5));
            GenerationService.locationForDrillParticle.put(p, locationList);
            drawParticleDrill(p, locationList.getFirst(), locationList.getLast());
        }

    }


    public static void drawParticleLine(Player p) {
        Bukkit.getScheduler().runTaskTimer(FreeBuild.getInstance(), () -> {
            List<Location> locationList = GenerationService.locationForChestParticle.get(p);
            Location start = locationList.get(0);
            Location end = locationList.get(1);
            AutomaticChestObject automaticChestObject = AutomaticChestObject.automaticChestObjectMap.get(p);

            if (!Bukkit.getOnlinePlayers().contains(p) || automaticChestObject.getChest() == null || !automaticChestObject.getSetting()) {
                return;
            }
            spawnParticle(start, end, Color.RED);
        }, 0L, 5L);
    }

    /**
     * Draws a particle drill effect between two locations and handles the animation of a block display moving
     * from the start location towards the end location. It also manages interactions with automatic drill,
     * generation, and chest systems.
     *
     * @param p     The player for whom the particle drill is being created.
     * @param start The starting location of the particle drill.
     * @param end   The ending location of the particle drill.
     */
    public static void drawParticleDrill(Player p, Location start, Location end) {
        AutomaticDrillObject automaticDrillObject = AutomaticDrillObject.automaticDrillObject.get(p);
        Location endNew = end.clone().add(-0.5, -0.5, -1);
        // BlockDisplay erzeugen
        BlockDisplay blockDisplay = p.getWorld().spawn(start.add(-1, -1, 0), BlockDisplay.class, entity -> {
            entity.setBlock(Material.STONECUTTER.createBlockData());
            entity.setTransformation(new Transformation(new Vector3f(0, 1, 0), new AxisAngle4f(), new Vector3f(1, 1, 1), new AxisAngle4f((float) Math.toRadians(90), 1, 0, 0) // Rotation
            ));
        });

        // Partikelanzeige-Timer
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!Bukkit.getOnlinePlayers().contains(p) || automaticDrillObject.getX() == -1 || !automaticDrillObject.isSetting()) {
                    return;
                }
                spawnParticle(start.clone().add(0.5, 0.5, 0), end, Color.AQUA);
            }
        }.runTaskTimer(FreeBuild.getInstance(), 0L, 5L);

        // Bewegungs-Logik
        new BukkitRunnable() {
            private int currentStep = 0;

            @Override
            public void run() {
                AutomaticDrillObject automaticDrillObject = AutomaticDrillObject.automaticDrillObject.get(p);
                GenerationBaseObject generationBaseObject = GenerationBaseObject.generationBaseObjectMap.get(p);
                AutomaticChestObject automaticChestObject = AutomaticChestObject.automaticChestObjectMap.get(p);

                if (!Bukkit.getOnlinePlayers().contains(p) || automaticDrillObject.getX() == -1 || !automaticDrillObject.isSetting()) {
                    blockDisplay.setVisibleByDefault(false);
                    return;
                }
               /* if (!p.isOnline() || automaticDrillObject.getX() == -1 || !automaticDrillObject.isSetting()) {
                    blockDisplay.remove();
                    this.cancel();
                    return;
                }*/
                blockDisplay.setVisibleByDefault(true);

                // Bewegung zum Ziel
                double stepSize = 0.2;
                Vector direction = endNew.toVector().subtract(start.toVector()).normalize().multiply(stepSize);
                Location currentLocation = start.clone().add(direction.clone().multiply(currentStep));

                if (blockDisplay.getLocation().distance(endNew) > 0.66) {
                    blockDisplay.teleport(currentLocation);
                    currentStep++;
                } else {
                    // Ziel erreicht
                    //TODO: Wenn man ausschaltet, während es am abbauen ist, ist alles gut. Schaltet man es ein, so ist es unsichtbar, aber baut weiter ab.
                    //Fix: Es soll wieder zum Anfang tpt werden, wenn man es anschaltet
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                                if (!automaticDrillObject.isSetting()) {
                                    blockDisplay.teleport(start);
                                    blockDisplay.setVisibleByDefault(false);
                                    return;
                                }
                                BreakGenListener.breakGen(generationBaseObject, automaticChestObject, p);
                                if (GenerationService.brokedGenList.contains(p)) {
                                    blockDisplay.remove();
                                    currentStep = 0;
                                    GenerationService.brokedGenList.remove(p);
                                    start.add(1, 1, 0);
                                    drawParticleDrill(p, start, end);
                                    this.cancel();
                                }
                            }
                    }.runTaskTimer(FreeBuild.getInstance(), 0L, 20L);
                    this.cancel(); // Beendet den Bewegungstask
                }
            }
        }.runTaskTimer(FreeBuild.getInstance(), 0L, 1L);
    }

    public static void spawnParticle(Location start, Location end, org.bukkit.Color color) {
        double distanz = start.distance(end);
        double schritt = 0.2;
        Vector direction = end.toVector().subtract(start.toVector()).normalize().multiply(schritt);
        Location current = start.clone();
        for (double d = 0; d < distanz; d += schritt) {
            new ParticleBuilder(Particle.DUST).location(current).color(color).count(1).spawn();
            current.add(direction);
        }
    }

    public void setItemSlots(Chest chest) {
        int[] slots = {0, 1, 2, 3, 4, 5, 6, 7, 8, 18, 19, 20, 21, 22, 23, 24, 25};
        for (int slot : slots) {
            chest.getInventory().setItem(slot, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        }
        ItemStack item = new ItemBuilder(Material.BEDROCK).name(Component.text("§cKiste abbauen")).build();
        chest.getInventory().setItem(26, item);
    }
}
