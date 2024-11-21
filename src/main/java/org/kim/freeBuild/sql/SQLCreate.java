package org.kim.freeBuild.sql;

import org.kim.freeBuild.FreeBuild;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SQLCreate {
    public static void create() {
        FreeBuild.getSql().update("CREATE TABLE IF NOT EXISTS playerbase (uuid VARCHAR(36), bankmoney BIGINT, islandname VARCHAR(36),islandid BIGINT, world VARCHAR(36), x DOUBLE, y DOUBLE, z DOUBLE, amountofgens BIGINT)");
    }


    public static void insertUser(UUID uuid) {
        if (!userExists(uuid)) {
            FreeBuild.getSql().update("INSERT INTO playerbase (uuid, bankmoney, islandname, islandid, world, x, y, z, amountofgens) VALUES ('" + uuid + "', '0', NULL, NULL, NULL, '-1', '-1', '-1','0')");
        }
    }

    public static boolean userExists(UUID uuid) {
        try {
            ResultSet rs = FreeBuild.getSql().getResult("SELECT uuid FROM playerbase WHERE uuid = '" + uuid + "'");
            if (rs.next()) {
                return rs.getString("uuid") != null;
            }
        } catch (SQLException e) {

        }
        return false;
    }
    public static Integer getBankMoney(UUID uuid) {
        try {
            ResultSet rs = FreeBuild.getSql().getResult("SELECT bankmoney FROM playerbase WHERE uuid = '" + uuid + "'");
            if (rs.next()) {
                return rs.getInt("bankmoney");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    public static String getIslandname(UUID uuid) {
        try {
            ResultSet rs = FreeBuild.getSql().getResult("SELECT islandname FROM playerbase WHERE uuid = '" + uuid + "'");
            if (rs.next()) {
                return rs.getString("islandname");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Integer getIslandid(UUID uuid) {
        try {
            ResultSet rs = FreeBuild.getSql().getResult("SELECT islandid FROM playerbase WHERE uuid = '" + uuid + "'");
            if (rs.next()) {
                return rs.getInt("islandid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    public static String getWorldName(UUID uuid) {
        try {
            ResultSet rs = FreeBuild.getSql().getResult("SELECT world FROM playerbase WHERE uuid = '" + uuid + "'");
            if (rs.next()) {
                return rs.getString("world");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Double getX(UUID uuid) {
        try {
            ResultSet rs = FreeBuild.getSql().getResult("SELECT x FROM playerbase WHERE uuid = '" + uuid + "'");
            if (rs.next()) {
                return rs.getDouble("x");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1.0;
    }
    public static Double getY(UUID uuid) {
        try {
            ResultSet rs = FreeBuild.getSql().getResult("SELECT y FROM playerbase WHERE uuid = '" + uuid + "'");
            if (rs.next()) {
                return rs.getDouble("y");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1.0;
    }

    public static Double getZ(UUID uuid) {
        try {
            ResultSet rs = FreeBuild.getSql().getResult("SELECT z FROM playerbase WHERE uuid = '" + uuid + "'");
            if (rs.next()) {
                return rs.getDouble("z");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1.0;
    }
    public static Integer getAmountofGens(UUID uuid) {
        try {
            ResultSet rs = FreeBuild.getSql().getResult("SELECT amountofgens FROM playerbase WHERE uuid = '" + uuid + "'");
            if (rs.next()) {
                return rs.getInt("amountofgens");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
