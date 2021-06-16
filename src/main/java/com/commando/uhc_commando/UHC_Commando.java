package com.commando.uhc_commando;

import com.commando.uhc_commando.Commands.AllCommands;
import com.commando.uhc_commando.event.AllEvent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class UHC_Commando extends JavaPlugin {

    public FileConfiguration CONFIG;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        CONFIG = this.getConfig();
        
        Bukkit.broadcastMessage("§a-------- Commando are ready ! ---------");
        System.out.println("-------- Commando are ready ! ---------");
        this.getCommand("Alert").setExecutor(new AllCommands());
        this.getCommand("Rule").setExecutor(new AllCommands());
        this.getCommand("Start").setExecutor(new AllCommands());
        this.getServer().getPluginManager().registerEvents(new AllEvent(this),this);
    }

    @Override
    public void onDisable() {
        Bukkit.broadcastMessage("§c------- Commando going to bed ! --------");
        System.out.println("------- Commando going to bed ! --------");
    }
}
