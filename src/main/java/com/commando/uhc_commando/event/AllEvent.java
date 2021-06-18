package com.commando.uhc_commando.event;

import com.commando.uhc_commando.UHC_Commando;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;


public class AllEvent implements Listener {

    private UHC_Commando main;

    public AllEvent(UHC_Commando uhc){
        this.main = uhc;
    }

    // ############ Events that are related with the CHAT in game #################

    @EventHandler
    public void playerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        event.setJoinMessage("§7[§3+§7] " + player.getDisplayName());
        if(!this.main.START) {
            player.teleport(Bukkit.getWorld("world").getSpawnLocation());
        } else {
            if(!this.main.players.contains(player.getUniqueId()))
                player.setGameMode(GameMode.SPECTATOR);
        }
    }

    @EventHandler
    public void playerLeave(PlayerQuitEvent event){
        Player player = event.getPlayer();
        event.setQuitMessage("§7[§4-§7] " + player.getDisplayName());
    }

    @EventHandler
    public void onMessage(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        String message = event.getMessage();
        event.setFormat("<- "+player.getDisplayName()+" -> "+message);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) throws InterruptedException {
        World world = Bukkit.getWorld("world");
        Player victim = event.getEntity();
        Player attacker = victim.getKiller();

        world.playSound(victim.getLocation(), Sound.ZOMBIE_REMEDY, 1000.0F, 1.0F);
        victim.setGameMode(GameMode.SPECTATOR);

        if (!(attacker instanceof Player)) {
            event.setDeathMessage("§c§l† " + victim.getDisplayName()+ " died from PVE §c§l†");
            victim.teleport(world.getSpawnLocation());

        } else {
            event.setDeathMessage("§c§l† " + victim.getDisplayName()+ " was killed by " + attacker.getPlayerListName() + " §c§l†");

            // waiting 30 seconds
            Bukkit.getScheduler().scheduleSyncRepeatingTask(this.main, new Runnable() {
                int timer = 30;
                public void run() {
                    timer --;
                    if(timer == 0) Thread.currentThread().interrupt();
                }
            }, 0, 20);
            
            victim.teleport(attacker.getLocation());
            victim.setGameMode(GameMode.SURVIVAL);
            //TODO Victim join the team of the killer

        }
    }
}
