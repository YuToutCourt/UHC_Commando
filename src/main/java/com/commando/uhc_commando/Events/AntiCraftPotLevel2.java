package com.commando.uhc_commando.Events;

import org.bukkit.event.Listener;

import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;

public class AntiCraftPotLevel2 implements Listener{

    @EventHandler
    public void onBrewEvent(BrewEvent event) {
        if(event.getContents().getIngredient().getType().equals(Material.GLOWSTONE_DUST)){
            event.setCancelled(true);
        }
    }
    
}
