package org.kim.freeBuild.sql;

import org.kim.freeBuild.FreeBuild;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SQLCreate {
    public static void create() {
        FreeBuild.getSql().update("CREATE TABLE IF NOT EXISTS playerbase (uuid VARCHAR(36), bankmoney BIGINT, islandname VARCHAR(36),islandid BIGINT, world VARCHAR(36), x DOUBLE, y DOUBLE, z DOUBLE, amountofgens BIGINT)");
        FreeBuild.getSql().update("CREATE TABLE IF NOT EXISTS genbase (uuid VARCHAR(36), x DOUBLE, y DOUBLE,z DOUBLE, level BIGINT, upgrade BIGINT, fuel DOUBLE)");
        FreeBuild.getSql().update("CREATE TABLE IF NOT EXISTS upgrades (uuid VARCHAR(36), x DOUBLE, y DOUBLE,z DOUBLE, upgradetype VARCHAR(36))");
    }


    public static void insertUser(UUID uuid) {
        if (!userExists(uuid)) {
            FreeBuild.getSql().update("INSERT INTO playerbase (uuid, bankmoney, islandname, islandid, world, x, y, z, amountofgens) VALUES ('" + uuid + "', '0', NULL, NULL, NULL, '-1', '-1', '-1','0')");
            FreeBuild.getSql().update("INSERT INTO genbase (uuid, x, y, z, level, upgrade, fuel) VALUES ('" + uuid + "', NULL, NULL, NULL, '0','-1','0')");
            FreeBuild.getSql().update("INSERT INTO upgrades (uuid, x, y, z, upgradetype) VALUES ('" + uuid + "', NULL, NULL, NULL, 'chest')");
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

    public static Double getIslandX(UUID uuid) {
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

    public static Double getIslandY(UUID uuid) {
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

    public static Double getIslandZ(UUID uuid) {
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

    public static Double getGenX(UUID uuid) {
        try {
            ResultSet rs = FreeBuild.getSql().getResult("SELECT x FROM genbase WHERE uuid = '" + uuid + "'");
            if (rs.next()) {
                return rs.getDouble("x");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1.0;
    }

    public static Double getGenY(UUID uuid) {
        try {
            ResultSet rs = FreeBuild.getSql().getResult("SELECT y FROM genbase WHERE uuid = '" + uuid + "'");
            if (rs.next()) {
                return rs.getDouble("y");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1.0;
    }

    public static Double getGenZ(UUID uuid) {
        try {
            ResultSet rs = FreeBuild.getSql().getResult("SELECT z FROM genbase WHERE uuid = '" + uuid + "'");
            if (rs.next()) {
                return rs.getDouble("z");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1.0;
    }

    public static Integer getGenLevel(UUID uuid) {
        try {
            ResultSet rs = FreeBuild.getSql().getResult("SELECT level FROM genbase WHERE uuid = '" + uuid + "'");
            if (rs.next()) {
                return rs.getInt("level");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static Integer getGenUpgrade(UUID uuid) {
        try {
            ResultSet rs = FreeBuild.getSql().getResult("SELECT upgrade FROM genbase WHERE uuid = '" + uuid + "'");
            if (rs.next()) {
                return rs.getInt("upgrade");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static Double getGenFuel(UUID uuid) {
        try {
            ResultSet rs = FreeBuild.getSql().getResult("SELECT fuel FROM genbase WHERE uuid = '" + uuid + "'");
            if (rs.next()) {
                return rs.getDouble("fuel");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1.0;
    }

    public static Double getChestX(UUID uuid) {
        try {
            ResultSet rs = FreeBuild.getSql().getResult("SELECT x FROM upgrades WHERE uuid = '" + uuid + "' AND upgradetype = 'chest'");
            if (rs.next()) {
                return rs.getDouble("x");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1.0;
    }
    public static Double getChestY(UUID uuid) {
        try {
            ResultSet rs = FreeBuild.getSql().getResult("SELECT y FROM upgrades WHERE uuid = '" + uuid + "' AND upgradetype = 'chest'");
            if (rs.next()) {
                return rs.getDouble("y");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1.0;
    }
    public static Double getChestZ(UUID uuid) {
        try {
            ResultSet rs = FreeBuild.getSql().getResult("SELECT z FROM upgrades WHERE uuid = '" + uuid + "' AND upgradetype = 'chest'");
            if (rs.next()) {
                return rs.getDouble("z");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1.0;
    }
}
