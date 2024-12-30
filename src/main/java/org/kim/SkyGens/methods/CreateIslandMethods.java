package org.kim.SkyGens.methods;


import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.*;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.kim.SkyGens.objects.PlayerBaseObject;

import java.io.File;
import java.io.FileInputStream;

public class CreateIslandMethods {

    public static void paste(Location to, File schematicFile, Player p) {
        // Überprüfen, ob die Datei existiert
        if (!schematicFile.exists()) {
            p.sendMessage("Die Schematic-Datei wurde nicht gefunden!");
            return;
        }

        // Versuch, die Schematic einzufügen
        try (EditSession session = WorldEdit.getInstance().newEditSession(BukkitAdapter.adapt(to.getWorld()))) {
            ClipboardFormat format = ClipboardFormats.findByFile(schematicFile);
            if (format == null) {
                p.sendMessage("Ungültiges Schematic-Format!");
                return;
            }

            try (ClipboardReader reader = format.getReader(new FileInputStream(schematicFile))) {
                Clipboard schematic = reader.read();
                Operation operation = new ClipboardHolder(schematic)
                        .createPaste(session)
                        .to(BlockVector3.at(to.getBlockX(), to.getBlockY(), to.getBlockZ()))
                        .ignoreAirBlocks(false)
                        .build();

                Operations.complete(operation);
                p.sendMessage("Schematic erfolgreich eingefügt!");
            }
        } catch (Exception e) {
            p.sendMessage("Es gab einen Fehler beim Einfügen der Schematic.");
        }
    }
    public static boolean hasPlayerIsland(Player p) {
        PlayerBaseObject playerBaseObject = PlayerBaseObject.playerBaseObjectMap.get(p.getUniqueId());
        return !(playerBaseObject.getX() < 0);
    }
}
