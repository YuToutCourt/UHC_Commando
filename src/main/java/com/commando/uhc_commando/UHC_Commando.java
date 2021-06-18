package com.commando.uhc_commando;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.commando.uhc_commando.Commands.AllCommands;
import com.commando.uhc_commando.event.AllEvent;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class UHC_Commando extends JavaPlugin {

    public FileConfiguration CONFIG;
    public boolean START = false;
    public final List<UUID> players = new ArrayList<UUID>();

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        CONFIG = this.getConfig();

        Bukkit.broadcastMessage("§a-------- Commando are ready ! ---------");
        System.out.println("-------- Commando are ready ! ---------");
        this.getCommand("alert").setExecutor(new AllCommands(this));
        this.getCommand("rule").setExecutor(new AllCommands(this));
        this.getCommand("start").setExecutor(new AllCommands(this));
        this.getCommand("createSpawn").setExecutor(new AllCommands(this));
        this.getServer().getPluginManager().registerEvents(new AllEvent(this), this);

        this.resetGame();
    }

    @Override
    public void onDisable() {
        Bukkit.broadcastMessage("§c------- Commando going to bed ! --------");
        System.out.println("------- Commando going to bed ! --------");
    }

    public void resetGame() {
        // Reset worldborder
        World world = Bukkit.getWorld("world");
        world.setSpawnLocation(CONFIG.getInt("Spawn.x"), CONFIG.getInt("Spawn.y"), CONFIG.getInt("Spawn.z"));
        world.getWorldBorder().setCenter(world.getSpawnLocation());
        world.getWorldBorder().setSize(CONFIG.getInt("Border.StartSize"));

        /*
         * TODO
         * reset timers
         * reset teams
         * 
         */
    }
}
