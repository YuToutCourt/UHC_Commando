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
        Player playerSender = event.getPlayer();
        String message = event.getMessage();
        if(Team.chatEnable && message.charAt(0) == '!') { // team message
            event.setCancelled(true);
            String newMessage  ="";
            String[] toLoop = message.split("");
            for(int i=1;i<toLoop.length;i++){
                newMessage += toLoop[i];
            }
            for(Player p : Bukkit.getOnlinePlayers()){
                if(Team.getLeadingTeamOf(playerSender).equals(Team.getLeadingTeamOf(p))){
                    p.sendMessage(Team.getLeadingTeamOf(playerSender).getColor()+ "["+Team.getLeadingTeamOf(playerSender).getName()+"] " + playerSender.getName() +" >§r "+ newMessage);
                }
            }
        } else { // general message
            event.setFormat(playerSender.getDisplayName() + "§r§7" + " > §r" + message);
        }
    }

}
