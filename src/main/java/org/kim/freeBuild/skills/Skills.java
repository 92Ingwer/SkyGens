package org.kim.freeBuild.skills;

import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.audience.Audiences;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.kim.freeBuild.FreeBuild;
import org.kim.freeBuild.services.PlayerService;

import java.awt.*;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public abstract class Skills implements Listener {
    private String name;
    private int level;
    private double xp;
    private UUID uuid;
    private int rebirth;

    public Skills(UUID uuid, String name, int level, double xp, int rebirth) {
        this.uuid = uuid;
        this.name = name;
        this.level = level;
        this.xp = xp;
        this.rebirth = rebirth;
    }

    public abstract void applyXP(double xp);

    public abstract double calcXP();

    public void isLevelUpReady() {
        if(getXp() >= calcXP()) {
            setLevel(getLevel()+1);
            setXp(0);
        }
    }

    public void showBossbar(double xp, BossBar.Color color) {
        if(PlayerService.hasPlayerBossBars.containsKey(uuid)) {
            BossBar bossBar = PlayerService.hasPlayerBossBars.get(uuid);
            Objects.requireNonNull(Bukkit.getPlayer(uuid)).hideBossBar(bossBar);
            PlayerService.hasPlayerBossBars.remove(uuid);
        }
        float skillPercent = (float) (getXp()/calcXP());
        BossBar bossBar = BossBar.bossBar(MiniMessage.miniMessage().deserialize("<b><gradient:#06FF00:#06FF00>"+ name + ": (" + getXp() + "/" + calcXP() + ") + " + xp + " EXP </gradient></b>"),skillPercent,color,BossBar.Overlay.PROGRESS);
        Objects.requireNonNull(Bukkit.getPlayer(uuid)).showBossBar(bossBar);
        PlayerService.hasPlayerBossBars.put(uuid, bossBar);
        new BukkitRunnable() {
            @Override
            public void run() {
                Objects.requireNonNull(Bukkit.getPlayer(uuid)).hideBossBar(bossBar);
                this.cancel();
            }
        }.runTaskTimer(FreeBuild.getInstance(),2*20,0L);
    }
}
