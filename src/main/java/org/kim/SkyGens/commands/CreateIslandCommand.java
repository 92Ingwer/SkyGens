package org.kim.SkyGens.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.kim.SkyGens.SkyGens;
import org.kim.SkyGens.listeners.OnQuitListener;
import org.kim.SkyGens.methods.CreateIslandMethods;
import org.kim.SkyGens.objects.GenerationBaseObject;
import org.kim.SkyGens.objects.PlayerBaseObject;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class CreateIslandCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(commandSender instanceof Player p)) {
            return false;
        }
        if (strings.length != 1) {
            p.sendMessage("§cUsage: /createisland <Inselname>");
            return false;
        }
        if (CreateIslandMethods.hasPlayerIsland(p)) {
            p.sendMessage("Du hast schon eine Insel, diggi!");
            return false;
        }
        OnQuitListener onQuitListener = new OnQuitListener();
        // Asynchrone Datenbankabfrage
        Bukkit.getScheduler().runTaskAsynchronously(SkyGens.getInstance(), () -> {
            int islandId = 0;

            try {
                ResultSet rs = SkyGens.getSql().getResult("SELECT MAX(islandid) FROM playerbase");
                if (rs != null && rs.next()) {
                    islandId = rs.getInt(1) + 1;
                }
                if (rs != null) rs.close();
            } catch (SQLException e) {
                Bukkit.getLogger().severe("Fehler bei der Datenbankabfrage: " + e.getMessage());
            }
            int finalIslandId = islandId;
            Bukkit.getScheduler().runTask(SkyGens.getInstance(), () -> {
                // Insel erstellen
                PlayerBaseObject playerBaseObject = new PlayerBaseObject(0, strings[0], finalIslandId, "InselWelt",finalIslandId * 4000, 170, finalIslandId * 4000, 1);
                onQuitListener.updatePlayerBase(0, strings[0], finalIslandId, "InselWelt", finalIslandId * 4000, 170, finalIslandId * 4000, p.getUniqueId().toString(), 1);
                PlayerBaseObject.playerBaseObjectMap.put(p.getUniqueId(), playerBaseObject);
                Location islandCenter = new Location(Bukkit.getWorld("InselWelt"), (finalIslandId * 4000) + 9, 179, (finalIslandId * 4000) + 8);
                //genbase erstellen
                GenerationBaseObject generationBaseObject = new GenerationBaseObject(-1,-1,-1,1,0,0);
                GenerationBaseObject.generationBaseObjectMap.put(p.getUniqueId(), generationBaseObject);
                // Schematic laden
                File schematicFile = new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin("WorldEdit")).getDataFolder(), "schematics/inselperfect.schem");
                CreateIslandMethods.paste(islandCenter, schematicFile, p);
                Bukkit.getLogger().info("Schematic erfolgreich eingefügt!");
                p.sendMessage("Alles geht diggi");
            });
        });

        return false;
    }
}