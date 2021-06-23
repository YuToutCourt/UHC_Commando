package com.commando.uhc_commando.Events;


import com.commando.uhc_commando.Teams.Team;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;


public class WinEvents implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        Bukkit.broadcastMessage("Il reste "+ Team.leadingTeams + " team(s) dans la partie");
        if (Team.leadingTeams == 1){
            Bukkit.broadcastMessage("End of the game ! The team "+ Team.getWinner() +" win !");
        }
    }
}