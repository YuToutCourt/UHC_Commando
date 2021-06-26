package com.commando.uhc_commando.Events;

import com.commando.uhc_commando.UHC_Commando;
import com.commando.uhc_commando.Tasks.TimerTask;
import com.commando.uhc_commando.Teams.Team;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

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

        if(!TimerTask.RUN) {
            player.teleport(this.main.WORLD.getSpawnLocation());
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
        if(!(event.getDamager() instanceof Player)) return;
        Player victim = (Player) event.getEntity();
        Player attacker = (Player) event.getDamager();
        // Condition ne marche pas car personne n'est vraiment dans la même team
        if(!Team.friendlyFire && Team.getTeamOf(victim).equals(Team.getTeamOf(attacker))){
            attacker.sendMessage("§cYou can't hit your teammate !");
            event.setCancelled(true);
            return;
        }
    }


}
