package com.commando.uhc_commando.Events;

import com.commando.uhc_commando.UHC_Commando;
import com.commando.uhc_commando.Teams.Team;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathEvents implements Listener {

    private UHC_Commando main;
    private Team teams;

    public DeathEvents(UHC_Commando uhc){
        this.main = uhc;
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
            //Problème comment je l'ajoute/remove d'une team précise
            teams.leave(victim.getUniqueId());
            teams.join(victim.getUniqueId());



        }
    }
}
