package org.kim.freeBuild.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.kim.freeBuild.FreeBuild;
import org.kim.freeBuild.listeners.OnQuitListener;
import org.kim.freeBuild.methods.CreateIslandMethods;
import org.kim.freeBuild.objects.PlayerBaseObject;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateIslandCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(commandSender instanceof Player)) {
            return false;
        }
        Player p = (Player) commandSender;
        if (strings.length != 1) {
            p.sendMessage("§cUsage: /createisland <Inselname>");
            return false;
        }
        if (CreateIslandMethods.hasPlayerIsland(p)) {
            p.sendMessage("Du hast schon eine Insel, diggi!");
            return false;
        }

        // Asynchrone Datenbankabfrage
        Bukkit.getScheduler().runTaskAsynchronously(FreeBuild.getInstance(), () -> {
            int islandId = 0;

            try {
                ResultSet rs = FreeBuild.getSql().getResult("SELECT MAX(islandid) FROM playerbase");
                if (rs != null && rs.next()) {
                    islandId = rs.getInt(1) + 1;
                }
                if (rs != null) rs.close();
            } catch (SQLException e) {
                Bukkit.getLogger().severe("Fehler bei der Datenbankabfrage: " + e.getMessage());
            }
            int finalIslandId = islandId;
            Bukkit.getScheduler().runTask(FreeBuild.getInstance(), () -> {
                // Insel erstellen
                PlayerBaseObject playerBaseObject = new PlayerBaseObject(0, strings[0], finalIslandId, "InselWelt",finalIslandId * 4000, 170, finalIslandId * 4000, 1);
                OnQuitListener.updateDB(0, strings[0], finalIslandId, "InselWelt", finalIslandId * 4000, 170, finalIslandId * 4000, p.getUniqueId().toString(), 1);
                PlayerBaseObject.playerBaseObjectMap.put(p, playerBaseObject);
                Location islandCenter = new Location(Bukkit.getWorld("InselWelt"), (finalIslandId * 4000) + 9, 179, (finalIslandId * 4000) + 8);

                // Schematic laden
                File schematicFile = new File(Bukkit.getServer().getPluginManager().getPlugin("WorldEdit").getDataFolder(), "schematics/inselperfect.schem");
                CreateIslandMethods.paste(islandCenter, schematicFile, p);
                Bukkit.getLogger().info("Schematic erfolgreich eingefügt!");
                p.sendMessage("Alles geht diggi");
            });
        });

        return false;
    }
}