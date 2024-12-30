package org.kim.SkyGens.guis;


import net.kyori.adventure.text.Component;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.kim.SkyGens.services.PlayerService;
import org.kim.SkyGens.skills.Skills;
import org.kim.SkyGens.utils.InventoryBuilder;

import java.util.List;

public class SpecificSkillGUI implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player p = (Player) event.getWhoClicked();
        Skills skill = PlayerService.skillGUIWhichSkill.get(p.getUniqueId());
        if(skill == null) {
            return;
        }
        if(event.getView().title().equals(Component.text(skill.getName() + " Skill - GUI von " + p.getName()))) {
            event.setCancelled(true);
            switch(event.getSlot()) {
                case 19:
                    SkillLevelGUI.openInventory(p, PlayerService.skillGUIWhichSkill.get(p.getUniqueId()),0);
                    PlayerService.skillGUIWhichSite.put(p.getUniqueId(),0);
                    break;
            }
        }
    }

    public static void openInventory(Player p) {
        Skills skills = PlayerService.skillGUIWhichSkill.get(p.getUniqueId());
        Inventory inventory = new InventoryBuilder(skills.getName() + " Skill - GUI von " + p.getName(), 4 * 9, 1)
                .aItem(13, Material.PAPER, Component.text("§bÜbersicht"), List.of(Component.text("- Skill: " + skills.getName()), Component.text("- Level: " + skills.getLevel()), Component.text("- EXP: " + skills.getXp() + "/" + skills.calcXP() + " EXP"), Component.text("- Rebirths: " + skills.getRebirth())))
                .aItem(19, Material.EXPERIENCE_BOTTLE, Component.text("§eLevel - Übersicht"), List.of(Component.text( "§7Öffne die Level Übersicht")))
                .build();
        p.openInventory(inventory);
    }
}



