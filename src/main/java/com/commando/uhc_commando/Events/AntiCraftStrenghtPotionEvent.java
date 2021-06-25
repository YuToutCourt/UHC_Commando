package com.commando.uhc_commando.Events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

import net.md_5.bungee.api.ChatColor;

public class AntiCraftStrenghtPotionEvent implements Listener{

    /*
    Un test pas très bien fait pour la potion de force. Le problème comment je get 
    le type de la potion cliqué dessus ? J'ai donc désactiver les Blazes Power pour le moment
    @EventHandler
    public void strenghtPotionsCraft(InventoryClickEvent event)
    {
        Player player = (Player) event.getWhoClicked();
        if(event.getClickedInventory().getType() != InventoryType.BREWING) return;
        if(event.getCurrentItem().getType() == Material.POTION){
            player.sendMessage(ChatColor.DARK_RED + "§l[SERVER] > Potion of strenght as been disable");
            event.setCancelled(true);
        }
    }
    */
    @EventHandler
    public void blazePowderCraft(CraftItemEvent event)
    {
        Player player = (Player) event.getWhoClicked();
        ItemStack itemStack = event.getRecipe().getResult();
        if (itemStack.getType() == Material.BLAZE_POWDER)
        {
            player.sendMessage(ChatColor.DARK_RED + "§l[SERVER] > Potion of strenght as been disable");
            event.setCancelled(true);
        }
    }

}
    
