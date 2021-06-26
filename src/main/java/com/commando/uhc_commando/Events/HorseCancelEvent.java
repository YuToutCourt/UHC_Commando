package com.commando.uhc_commando.Events;

import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class HorseCancelEvent implements Listener {

    @EventHandler
    public void playerClickOnHorse(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if(event.getRightClicked() instanceof Horse) {
            player.sendMessage("§4§l[SERVER] > Horse as been disable");
            event.setCancelled(true);
          
        }
      
    }
}
    
