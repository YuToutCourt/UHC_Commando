package com.commando.uhc_commando.Events;

import com.commando.uhc_commando.UHC_Commando;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvents implements Listener {
    
    private UHC_Commando main;

    public ChatEvents(UHC_Commando uhc){
        this.main = uhc;
    }

    @EventHandler
    public void onMessage(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        String message = event.getMessage();
        if(this.main.CONFIG.getBoolean("Teams.TeamChat") && message.charAt(0) != '!') { // team message
            event.setCancelled(true);
            // TODO team chat message
        } else { // general message
            event.setFormat("<- "+player.getDisplayName()+" -> "+message);
        }
    }

}
