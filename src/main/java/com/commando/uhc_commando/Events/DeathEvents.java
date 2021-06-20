package com.commando.uhc_commando.Events;

import com.commando.uhc_commando.UHC_Commando;
import com.commando.uhc_commando.Teams.Team;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathEvents implements Listener {

    private UHC_Commando main;

    public DeathEvents(UHC_Commando uhc){
        this.main = uhc;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) throws InterruptedException {
        Player victim = event.getEntity();
        Player attacker = victim.getKiller();

        this.main.WORLD.playSound(victim.getLocation(), Sound.ZOMBIE_REMEDY, 1000.0F, 1.0F);
        victim.setGameMode(GameMode.SPECTATOR);

        if (!(attacker instanceof Player)) {
            event.setDeathMessage("§c§l† " + victim.getDisplayName()+ " died from PVE §c§l†");
            victim.teleport(this.main.WORLD.getSpawnLocation());

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
            Team.getTeamOf(attacker).join(victim);



        }
    }
}
