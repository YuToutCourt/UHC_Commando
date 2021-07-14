package com.commando.uhc_commando.Events;

import com.commando.uhc_commando.UHC_Commando;
import com.commando.uhc_commando.Tasks.TimerTasks;
import com.commando.uhc_commando.Teams.Team;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerEvents implements Listener {

    private UHC_Commando main;

    public PlayerEvents(UHC_Commando uhc){
        this.main = uhc;
    }

    @EventHandler
    public void playerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        Team team = Team.getLeadingTeamOf(player);
        if(team != null) {
            team.setPlayerName(team.getColor() + "[" + team.getSymbol() +"]");
        }
        event.setJoinMessage("§7[§3+§7] " + player.getDisplayName());
        this.main.boards.add(this.main.createBoard(player));

        if(!TimerTasks.RUN) {
            Material blockAtSpawn = this.main.WORLD.getBlockAt(this.main.WORLD.getSpawnLocation().getBlockX(),this.main.WORLD.getSpawnLocation().getBlockY()-1,this.main.WORLD.getSpawnLocation().getBlockZ()).getType();
            if(!(blockAtSpawn.equals(Material.GLASS))){
                // WAIT A BIT FOR THE CHUNCK TO BE LOADED
                BukkitRunnable task = new BukkitRunnable() {
                    @Override
                    public void run() {
                        createSpawn();
                    }
                };
                task.runTaskLater(this.main, 20*1);
            }
            player.teleport(this.main.WORLD.getSpawnLocation());
            player.setGameMode(GameMode.ADVENTURE);
            
        } else {
            if(!this.main.playersInTheParty.contains(player.getUniqueId()))
                player.setGameMode(GameMode.SPECTATOR);
        }
    }

    @EventHandler
    public void playerLeave(PlayerQuitEvent event){
        Player player = event.getPlayer();
        event.setQuitMessage("§7[§4-§7] " + player.getDisplayName());
        this.main.removeBoardOf(player);
    }
    
    @EventHandler
    public void damageEvent(EntityDamageByEntityEvent event) {
        if(!(event.getEntity() instanceof Player))  return;
        Player victim = (Player) event.getEntity();
        Player attacker = null;
        if(event.getDamager() instanceof Player) attacker = (Player) event.getDamager();
        else if(event.getDamager() instanceof Projectile) attacker = (Player) ((Projectile) event.getDamager()).getShooter();
        if(attacker == null) return;

        if(Team.getLeadingTeamOf(victim).equals(Team.getLeadingTeamOf(attacker)) || victim.equals(attacker)){
            attacker.sendMessage("§cYou can't hit your teammate !");
            event.setCancelled(true);
            return;
        }
    }



    private void createSpawn(){

        Location spawn = this.main.WORLD.getSpawnLocation();

        String createCube = "fill " + (spawn.getBlockX() - 10) + " " + (spawn.getBlockY() - 1) + " " + (spawn.getBlockZ() - 10);
        createCube += " " + (spawn.getBlockX() + 10) + " " + (spawn.getBlockY() + 4) + " " + (spawn.getBlockZ() + 10);
        createCube += " minecraft:glass";

        String carveCube = "fill " + (spawn.getBlockX() - 9) + " " + (spawn.getBlockY()) + " " + (spawn.getBlockZ() - 9);
        carveCube += " " + (spawn.getBlockX() + 9) + " " + (spawn.getBlockY() + 3) + " " + (spawn.getBlockZ() + 9);
        carveCube += " minecraft:air";

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), createCube);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), carveCube);

    }
}