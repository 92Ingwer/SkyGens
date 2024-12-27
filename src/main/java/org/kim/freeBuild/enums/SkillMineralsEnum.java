package org.kim.freeBuild.enums;

import lombok.Getter;
import org.bukkit.Material;

@Getter
public enum SkillMineralsEnum {
    CARROTS("Farming",Material.CARROTS,1, true),
    WHEAT("Farming",Material.WHEAT,1, true),
    POTATOES("Farming",Material.POTATOES,1, true),
    BEETROOTS("Farming",Material.BEETROOTS,1, true),
    MELON("Farming",Material.MELON,1, false),
    PUMPKIN("Farming",Material.PUMPKIN,1, false);

    private final String skill;
    private final Material material;
    private final double xp;
    private final boolean isAgeable;

    SkillMineralsEnum(String skill, Material material, double xp, boolean isAgeable) {
        this.skill = skill;
        this.material = material;
        this.xp = xp;
        this.isAgeable = isAgeable;
    }

    public static String getName(Material material) {
        for(SkillMineralsEnum s : SkillMineralsEnum.values()) {
            if(s.getMaterial().equals(material)) {
                return s.getSkill();
            }
        }
        return null;
    }


    public static double getXP(Material material) {
        for(SkillMineralsEnum s : SkillMineralsEnum.values()) {
            if(s.getMaterial().equals(material)) {
                return s.getXp();
            }
        }
        return 0;
    }

    private static boolean isItemValid(Material material) {
        for(SkillMineralsEnum s : SkillMineralsEnum.values()) {
            if(s.getMaterial().equals(material)) {
                return true;
            }
        }
        return false;
    }


    public static boolean isItemFarming(Material material) {
        if(!isItemValid(material)) {
            return false;
        }
        for(SkillMineralsEnum s : SkillMineralsEnum.values()) {
            if(s.getMaterial().equals(material) && s.getSkill().equals("Farming")) {
                return true;
            }
        }
        return false;
    }
    public static boolean isAgeable(Material material) {
        if(!isItemValid(material)) {
            return false;
        }
        for(SkillMineralsEnum s : SkillMineralsEnum.values()) {
            if(s.getMaterial().equals(material) && s.isAgeable) {
                return true;
            }
        }
        return false;
    }
}
