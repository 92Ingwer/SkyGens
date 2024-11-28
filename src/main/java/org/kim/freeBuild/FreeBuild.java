package org.kim.freeBuild;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.kim.freeBuild.commands.*;
import org.kim.freeBuild.guis.EnergyGenGUI;
import org.kim.freeBuild.guis.GenGUI;
import org.kim.freeBuild.guis.GetGenGUI;
import org.kim.freeBuild.listeners.*;
import org.kim.freeBuild.recipes.GenMechanicsRecipes;
import org.kim.freeBuild.schedulers.TenSecond;
import org.kim.freeBuild.sql.SQL;
import org.kim.freeBuild.sql.SQLCreate;

public final class FreeBuild extends JavaPlugin {
    public static FreeBuild instance;
    public static SQL sql;

    @Override
    public void onEnable() {
        instance = this;
        sql = new SQL("localhost","FreeBuild","FreeBuild","/oq)yRWLXXVM/4as","true");
        initdb();

        //listeners
        Bukkit.getPluginManager().registerEvents(new BreakGenListener(), this);
        Bukkit.getPluginManager().registerEvents(new OnJoinListener(),this);
        Bukkit.getPluginManager().registerEvents(new OnQuitListener(),this);
        Bukkit.getPluginManager().registerEvents(new OnMoveListener(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(),this);
        Bukkit.getPluginManager().registerEvents(new GetGenGUI(),this);
        Bukkit.getPluginManager().registerEvents(new PlaceGenEvent(),this);
        Bukkit.getPluginManager().registerEvents(new GenGUI(),this);
        Bukkit.getPluginManager().registerEvents(new EnergyGenGUI(),this);
        Bukkit.getPluginManager().registerEvents(new PlaceUpgraderListener(),this);
        Bukkit.getPluginManager().registerEvents(new OpenAutomaticChestListener(), this);

        //Commands
        this.getCommand("createisland").setExecutor(new CreateIslandCommand());
        this.getCommand("istp").setExecutor(new IslandTPCommand());
        this.getCommand("getgen").setExecutor(new GetGenCommand());
        this.getCommand("sell").setExecutor(new SellCommand());
        this.getCommand("money").setExecutor(new MoneyCommand());


        //Recipes
        GenMechanicsRecipes.ChestRecipe();

        //schedulers
        TenSecond.tenSecondTimer();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static FreeBuild getInstance() {
        return instance;
    }

    private void initdb() {
        sql.update("USE FreeBuild");
        SQLCreate.create();
    }
    public static SQL getSql() {
        return sql;
    }
}
