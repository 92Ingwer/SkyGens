package org.kim.SkyGens;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.kim.SkyGens.recipes.GenMechanicsRecipes;
import org.kim.SkyGens.schedulers.TenSecond;
import org.kim.SkyGens.skills.BreakBlockListener;
import org.kim.SkyGens.sql.SQL;
import org.kim.SkyGens.sql.SQLCreate;

public final class SkyGens extends JavaPlugin {
    @Getter
    public static SkyGens instance;
    @Getter
    public static SQL sql;

    @Override
    public void onEnable() {
        instance = this;
         // Neu ausgefüllt werden sql = new SQL();
        initdb();

        //listeners
        Bukkit.getPluginManager().registerEvents(new org.kim.SkyGens.listeners.BreakGenListener(), this);
        Bukkit.getPluginManager().registerEvents(new org.kim.SkyGens.listeners.OnJoinListener(),this);
        Bukkit.getPluginManager().registerEvents(new org.kim.SkyGens.listeners.OnQuitListener(),this);
        Bukkit.getPluginManager().registerEvents(new org.kim.SkyGens.listeners.OnMoveListener(),this);
        Bukkit.getPluginManager().registerEvents(new org.kim.SkyGens.listeners.PlayerInteractListener(),this);
        Bukkit.getPluginManager().registerEvents(new org.kim.SkyGens.guis.GetGenGUI(),this);
        Bukkit.getPluginManager().registerEvents(new org.kim.SkyGens.listeners.PlaceGenEvent(),this);
        Bukkit.getPluginManager().registerEvents(new org.kim.SkyGens.guis.GenGUI(),this);
        Bukkit.getPluginManager().registerEvents(new org.kim.SkyGens.guis.EnergyGenGUI(),this);
        Bukkit.getPluginManager().registerEvents(new org.kim.SkyGens.listeners.PlaceUpgraderListener(),this);
        Bukkit.getPluginManager().registerEvents(new org.kim.SkyGens.listeners.OpenAutomaticChestListener(), this);
        Bukkit.getPluginManager().registerEvents(new org.kim.SkyGens.guis.GenSettingsGUI(), this);
        Bukkit.getPluginManager().registerEvents(new org.kim.SkyGens.listeners.BreakChestListener(), this);
        Bukkit.getPluginManager().registerEvents(new org.kim.SkyGens.listeners.CollectItemListener(),this );
        Bukkit.getPluginManager().registerEvents(new org.kim.SkyGens.listeners.OnIslandPvPEvent(), this);
        Bukkit.getPluginManager().registerEvents(new org.kim.SkyGens.listeners.BreakDrillListener(),this);
        Bukkit.getPluginManager().registerEvents(new org.kim.SkyGens.listeners.BlockExplosiveEvent(),this);
        Bukkit.getPluginManager().registerEvents(new org.kim.SkyGens.guis.DrillGui(),this);
        Bukkit.getPluginManager().registerEvents(new BreakBlockListener(), this);
        Bukkit.getPluginManager().registerEvents(new org.kim.SkyGens.guis.SkillsGUI(), this);
        Bukkit.getPluginManager().registerEvents(new org.kim.SkyGens.guis.SpecificSkillGUI(), this);
        Bukkit.getPluginManager().registerEvents(new org.kim.SkyGens.guis.SkillLevelGUI(), this);

        //Commands
        this.getCommand("createisland").setExecutor(new org.kim.SkyGens.commands.CreateIslandCommand());
        this.getCommand("istp").setExecutor(new org.kim.SkyGens.commands.IslandTPCommand());
        this.getCommand("getgen").setExecutor(new org.kim.SkyGens.commands.GetGenCommand());
        this.getCommand("sell").setExecutor(new org.kim.SkyGens.commands.SellCommand());
        this.getCommand("money").setExecutor(new org.kim.SkyGens.commands.MoneyCommand());
        this.getCommand("giveitems").setExecutor(new org.kim.SkyGens.commands.GiveItemsCommand());
        this.getCommand("test").setExecutor(new org.kim.SkyGens.commands.TestCommand());
        this.getCommand("skills").setExecutor(new org.kim.SkyGens.commands.SkillsCommand());


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
        sql.update("USE ");
        SQLCreate.create();
    }
}
