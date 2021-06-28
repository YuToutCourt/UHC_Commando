package com.commando.uhc_commando.Events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.BrewEvent;

public class AntiCraftStrenghtPotionEvent implements Listener{
    @EventHandler
    public void onBrewEvent(BrewEvent event) {
        if(event.getContents().getIngredient().getType().equals(Material.BLAZE_POWDER)){
            event.setCancelled(true);
        }
    }

}
    
