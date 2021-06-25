package com.commando.uhc_commando.Events;



import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.event.inventory.CraftItemEvent;

import org.bukkit.inventory.ItemStack;


public class AntiCraftEventNotch implements Listener {

    
    @EventHandler
    public void notchAppleCraft(CraftItemEvent event)
    {
        Player player = (Player) event.getWhoClicked();
        ItemStack itemCrafted = event.getRecipe().getResult();
        if(itemCrafted.getType() == Material.GOLDEN_APPLE){
            if (itemCrafted.getDurability() == 1) {
                player.sendMessage(ChatColor.DARK_RED + "§l[SERVER] > Notch apples as been disable");
                event.setCancelled(true);
            }
        }
    }
}
