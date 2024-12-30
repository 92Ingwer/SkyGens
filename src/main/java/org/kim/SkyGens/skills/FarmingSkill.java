package org.kim.SkyGens.skills;


import net.kyori.adventure.bossbar.BossBar;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class FarmingSkill extends Skills {

    public static HashMap<UUID, FarmingSkill> farmingSkillMap = new HashMap<>();

    public FarmingSkill(UUID uuid, String name, int level, double xp, int rebirths) {
        super(uuid, name, level, xp, rebirths);
    }

    @Override
    public void applyXP(double xp) {
        setXp(getXp()+xp);
        isLevelUpReady();
        showBossbar(xp, BossBar.Color.YELLOW);
    }

    @Override
    public double calcXP() {
        return 3*Math.pow(getLevel()+1,4);
    }
}
