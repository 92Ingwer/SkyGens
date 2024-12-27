package org.kim.freeBuild.guis;


import net.kyori.adventure.text.Component;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.kim.freeBuild.skills.Skills;
import org.kim.freeBuild.utils.InventoryBuilder;

import java.util.List;

public class SpecificSkillGUI implements Listener {


    public static <T extends Skills> void openInventory(Player p, T skills) {
        Inventory inventory = new InventoryBuilder(skills.getName() + "-GUI von " + p.getName(), 3 * 9, 1)
                .aItem(13, Material.PAPER, Component.text(Color.AQUA + "Übersicht"), List.of(Component.text("- Skill: " + skills.getName()), Component.text("- Level: " + skills.getLevel()), Component.text("- EXP: " + skills.getXp() + "/" + skills.calcXP() + " EXP"), Component.text("- Rebirths: " + skills.getRebirth())))
                .aItem(19, Material.EXPERIENCE_BOTTLE, Component.text("§eLevel - Übersicht"), List.of(Component.text(Color.GRAY + "Öffne die Level Übersicht")))
                .build();
        p.openInventory(inventory);
    }
}



