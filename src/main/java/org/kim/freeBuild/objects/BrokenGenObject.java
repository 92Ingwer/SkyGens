package org.kim.freeBuild.objects;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Display;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.kim.freeBuild.enums.GenItemEnum;
import org.kim.freeBuild.enums.MineralsEnum;
import org.kim.freeBuild.listeners.BreakGenListener;
import org.kim.freeBuild.methods.GenMethods;
import org.kim.freeBuild.services.GenerationService;

public class BrokenGenObject {


    @Getter
    private int hearts;
    @Getter
    private Location loc;
    private Player p;
    private GenerationBaseObject generationBaseObject;
    private TextDisplay textDisplay;
    private AutomaticChestObject automaticChestObject;
    
    public BrokenGenObject(int hearts, Location loc, Player p, GenerationBaseObject generationBaseObject, AutomaticChestObject automaticChestObject) {
        this.hearts = (hearts * (100 - 5 * generationBaseObject.getUpgrade())) / 100;
        this.loc = new Location(loc.getWorld(),loc.getX()+0.5,loc.getY()+1.25,loc.getZ()+0.5);
        this.p = p;
        this.generationBaseObject = generationBaseObject;
        this.automaticChestObject = automaticChestObject;
    }

    public void setHearts(int hearts) {
        this.hearts = hearts;
        try {
            textDisplay.remove();
        } catch (Exception e) {};
        textDisplay = loc.getWorld().spawn(loc, TextDisplay.class);
        textDisplay.text(Component.text("§c" + hearts + "❤"));
        textDisplay.setBillboard(Display.Billboard.CENTER);
        if(hearts <= 0) {
            textDisplay.remove();
            GenerationService.brokenGenObjects.remove(p);
            GenerationService.brokedGenList.add(p);
            generationBaseObject = GenerationBaseObject.generationBaseObjectMap.get(p);
            int level = generationBaseObject.getLevel();
            double fuel = generationBaseObject.getFuel();
            double usage = MineralsEnum.getUsage(level);
            Block b = loc.getBlock();
            if(usage > fuel ) {
                GenMethods.createExplosion(b);
                return;
            }
            ItemStack item = GenItemEnum.getItem(level);
            if(automaticChestObject.getX() == -1 || !automaticChestObject.getSetting()) {
                loc.getWorld().dropItemNaturally(loc, item);
            } else {
                Location chestLoc = automaticChestObject.getChest().getLocation();
                Chest chest = (Chest) chestLoc.getBlock().getState();
                chest.getInventory().addItem(item);
                GenMethods.moveItem(p);
            }
            BreakGenListener.useFuel(generationBaseObject,p);
        }
    }
}
