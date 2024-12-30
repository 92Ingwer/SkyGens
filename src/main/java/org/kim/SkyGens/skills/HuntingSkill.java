package org.kim.SkyGens.skills;

import net.kyori.adventure.bossbar.BossBar;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;

import java.util.UUID;

public class HuntingSkill extends Skills {

    public HuntingSkill(UUID uuid, String name, int level, double xp, int rebirths) {
        super(uuid, name, level, xp, rebirths);
    }

    @Override
    public void applyXP(double xp) {
        setXp(getXp() + xp);
        isLevelUpReady();
        showBossbar(xp, BossBar.Color.RED);
    }

    @Override
    public double calcXP() {
        return 4*Math.pow(getLevel()+1,4);
    }
}
