package com.commando.uhc_commando.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;

public class NetherCancelEvent implements Listener {

    @EventHandler
    public void onPortalTravel(PlayerPortalEvent event){
        Player player = event.getPlayer();
        if(event.getCause() == PlayerPortalEvent.TeleportCause.NETHER_PORTAL){
            event.setCancelled(true);
            player.sendMessage("§4§l[SERVER] > Nether as been disable");
        }
    }
    
}
