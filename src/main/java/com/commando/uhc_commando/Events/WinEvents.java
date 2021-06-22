package com.commando.uhc_commando.Events;


import com.commando.uhc_commando.Teams.Team;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;


public class WinEvents implements Listener {

    public void onDeath(PlayerDeathEvent event){
        if (Team.leadingTeams == 1){
            Bukkit.broadcastMessage("Fin de la partie la team "+ Team.getWinner() +" gagne !");
        }
    }
}