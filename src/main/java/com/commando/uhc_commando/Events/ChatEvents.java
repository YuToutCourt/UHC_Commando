package com.commando.uhc_commando.Events;

import com.commando.uhc_commando.Teams.Team;

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
        } else { // general message
            event.setFormat("<- "+player.getDisplayName()+" -> "+message);
        }
    }

}
