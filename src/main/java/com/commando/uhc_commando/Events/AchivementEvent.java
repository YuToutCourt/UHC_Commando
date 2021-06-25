package com.commando.uhc_commando.Events;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;

public class AchivementEvent implements Listener {

    @EventHandler
    public void onAchivement(PlayerAchievementAwardedEvent event){
        event.setCancelled(true);
    }
    
}
