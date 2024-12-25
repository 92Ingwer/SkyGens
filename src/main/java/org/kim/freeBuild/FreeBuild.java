package org.kim.freeBuild;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.kim.freeBuild.commands.*;
import org.kim.freeBuild.guis.*;
import org.kim.freeBuild.listeners.*;
import org.kim.freeBuild.recipes.GenMechanicsRecipes;
import org.kim.freeBuild.schedulers.TenSecond;
import org.kim.freeBuild.sql.SQL;
import org.kim.freeBuild.sql.SQLCreate;

import java.security.cert.Extension;

public final class FreeBuild extends JavaPlugin {
    @Getter
    public static FreeBuild instance;
    @Getter
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
        Bukkit.getPluginManager().registerEvents(new GenSettingsGUI(), this);
        Bukkit.getPluginManager().registerEvents(new BreakChestListener(), this);
        Bukkit.getPluginManager().registerEvents(new CollectItemListener(),this );
        Bukkit.getPluginManager().registerEvents(new OnIslandPvPEvent(), this);
        Bukkit.getPluginManager().registerEvents(new BreakDrillListener(),this);
        Bukkit.getPluginManager().registerEvents(new BlockExplosiveEvent(),this);
        Bukkit.getPluginManager().registerEvents(new DrillGui(),this);

        //Commands
        this.getCommand("createisland").setExecutor(new CreateIslandCommand());
        this.getCommand("istp").setExecutor(new IslandTPCommand());
        this.getCommand("getgen").setExecutor(new GetGenCommand());
        this.getCommand("sell").setExecutor(new SellCommand());
        this.getCommand("money").setExecutor(new MoneyCommand());
        this.getCommand("giveitems").setExecutor(new GiveItemsCommand());


        //Recipes
        GenMechanicsRecipes.ChestRecipe();
        GenMechanicsRecipes.DrillRecipe();

        //schedulers
        TenSecond.tenSecondTimer();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void initdb() {
        sql.update("USE FreeBuild");
        SQLCreate.create();
    }
}
