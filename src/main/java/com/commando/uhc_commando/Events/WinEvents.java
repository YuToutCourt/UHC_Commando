package com.commando.uhc_commando.Events;

import com.commando.uhc_commando.Tasks.TimerTasks;
import com.commando.uhc_commando.Teams.Team;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class WinEvents implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        if (Team.getLeadingTeamsAmount() == 1){
            Bukkit.broadcastMessage("§aEnd of the game! The "+ Team.getWinner().getColor() + Team.getWinner().getName() +" §ateam won!");
            TimerTasks.setRunning(false);
        }
    }
}