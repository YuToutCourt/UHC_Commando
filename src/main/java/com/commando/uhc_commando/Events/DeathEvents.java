package com.commando.uhc_commando.Events;

import com.commando.uhc_commando.UHC_Commando;
import com.commando.uhc_commando.Teams.Team;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class DeathEvents implements Listener {

    private UHC_Commando main;

    public DeathEvents(UHC_Commando uhc){
        this.main = uhc;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) throws InterruptedException {
        Player victim = event.getEntity();
        Player attacker = victim.getKiller();
        Location deathLocation = victim.getLocation();

        this.main.WORLD.playSound(deathLocation, Sound.ZOMBIE_REMEDY, 1000.0F, 1.0F);
        victim.spigot().respawn();
        victim.setGameMode(GameMode.SPECTATOR);
        victim.teleport(deathLocation);

        if (!(attacker instanceof Player)) {
            event.setDeathMessage("§c§l† §r" + victim.getDisplayName() + " §c§ldied from PVE §c§l†");
        } else {
            event.setDeathMessage("§c§l† §r" + victim.getDisplayName()+ " §c§lwas killed by " + attacker.getPlayerListName() + " §c§l†");

            // waiting 30 seconds
            BukkitRunnable task = new BukkitRunnable() {
                @Override
                public void run() {
                    victim.teleport(attacker.getLocation());
                    victim.setGameMode(GameMode.SURVIVAL);
                    Team.getTeamOf(victim).join(Team.getTeamOf(attacker));
                }
            };
            task.runTaskLater(this.main, 20 * 30);
            
        }
    }
}
