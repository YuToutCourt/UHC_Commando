package com.commando.uhc_commando;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.commando.uhc_commando.Commands.AlertCommand;
import com.commando.uhc_commando.Commands.CreateSpawnCommand;
import com.commando.uhc_commando.Commands.RuleCommand;
import com.commando.uhc_commando.Commands.StartCommand;
import com.commando.uhc_commando.Events.ChatEvents;
import com.commando.uhc_commando.Events.DeathEvents;
import com.commando.uhc_commando.Events.PlayerEvents;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.mrmicky.fastboard.FastBoard;

public final class UHC_Commando extends JavaPlugin {

    public FileConfiguration CONFIG;
    public final List<UUID> players = new ArrayList<UUID>();
	public final List<FastBoard> boards = new ArrayList<FastBoard>();

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        CONFIG = this.getConfig();

        Bukkit.broadcastMessage("§a-------- Commando are ready ! ---------");
        System.out.println("-------- Commando are ready ! ---------");
        
        this.getCommand("alert").setExecutor(new AlertCommand());
        this.getCommand("rule").setExecutor(new RuleCommand());
        this.getCommand("start").setExecutor(new StartCommand(this));
        this.getCommand("createSpawn").setExecutor(new CreateSpawnCommand());

        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new ChatEvents(this), this);
        pm.registerEvents(new DeathEvents(this), this);
        pm.registerEvents(new PlayerEvents(this), this);

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

    public void removeBoardOf(Player player) {
        for(FastBoard board : this.boards) {
            if(board.getPlayer().getUniqueId() == player.getUniqueId()) {
                board.delete();
                this.boards.remove(board);
                return;
            }
        }
    }
}
