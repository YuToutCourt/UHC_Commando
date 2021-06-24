package com.commando.uhc_commando.Events;

import com.commando.uhc_commando.Teams.Team;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvents implements Listener {

    @EventHandler
    public void onMessage(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        String message = event.getMessage();
        if(Team.chatEnable && message.charAt(0) == '!') { // team message
            event.setCancelled(true);
            // TODO team chat message
            // Un enfer cette chose aled
            Team teamOfTheSender = Team.getTeamOf(player);
            for(Player p : Bukkit.getOnlinePlayers()){
                System.out.println("Nom Team = :"+teamOfTheSender.getName()+","+teamOfTheSender+" | "+Team.getTeamOf(p).getName()+","+Team.getTeamOf(p));
                System.out.println("Team Leader = "+ teamOfTheSender.getLeader()+" "+Team.getTeamOf(p).getLeader());
                System.out.println("GetOwner = "+teamOfTheSender.getOwner()+" "+Team.getTeamOf(p).getOwner());
                System.out.println("GetOwnTeam = "+teamOfTheSender.getOwnTeams()+" "+Team.getTeamOf(p).getOwnTeams());
                if(teamOfTheSender.getOwner().equals(Team.getTeamOf(player).getOwner())){
                    p.sendMessage(teamOfTheSender.getColor()+ "["+teamOfTheSender.getName()+"] " + player.getName() +" >§r "+ message);
                }
            }
        } else { // general message
            event.setFormat(player.getDisplayName() + "§r§7" + " > §r" + message);
        }
    }

}
