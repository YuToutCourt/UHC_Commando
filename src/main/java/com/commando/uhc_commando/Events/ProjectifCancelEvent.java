package com.commando.uhc_commando.Events;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class ProjectifCancelEvent implements Listener {

    @EventHandler
    public void projectileHit(ProjectileLaunchEvent event){
        String projectile = event.getEntity().getType().toString();
        if(projectile.equals("EGG") || projectile.equals("SNOWBALL") || projectile.equals("FISHING_HOOK")){
            event.setCancelled(true);
        }
    }
    
}
