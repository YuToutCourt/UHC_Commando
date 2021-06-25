package com.commando.uhc_commando.Events;

import java.util.Random;


import org.bukkit.event.Listener;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class CutCleanEvents implements Listener {
    
    @EventHandler
    public void breakOre(BlockBreakEvent event){
        Player player = event.getPlayer();
        Block block = event.getBlock();
        if(block.getType() == Material.GOLD_ORE){
            block.getDrops().clear();
            event.setCancelled(true);
            block.setType(Material.AIR);
            block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.GOLD_INGOT));
            player.giveExp(4);
            return;
        }
        if(block.getType() == Material.IRON_ORE){
            block.getDrops().clear();
            event.setCancelled(true);
            block.setType(Material.AIR);
            block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.IRON_INGOT));
            player.giveExp(2);
            return;
        }
    }
    
    @EventHandler
    public void EntityD(EntityDeathEvent event) {
        Random rand = new Random();

        int getExtratItem = rand.nextInt(2);

        switch (event.getEntityType()){
            case COW:
            case MUSHROOM_COW:
                event.getDrops().clear();
                event.getDrops().add(new ItemStack(Material.COOKED_BEEF, 3));
                if(getExtratItem == 1) 
                    event.getDrops().add(new ItemStack(Material.LEATHER,2)); 
                return;

            case CHICKEN:
                event.getDrops().clear();
                event.getDrops().add(new ItemStack(Material.COOKED_CHICKEN, 3));
                if(getExtratItem == 1) 
                    event.getDrops().add(new ItemStack(Material.FEATHER, 2));
                return;
            
            case PIG:
                event.getDrops().clear();
                event.getDrops().add(new ItemStack(Material.GRILLED_PORK, 3));
                return;
            
            case SHEEP:
                event.getDrops().clear();
                event.getDrops().add(new ItemStack(Material.COOKED_MUTTON, 2));
                return;
            
            case RABBIT:
                event.getDrops().clear();
                event.getDrops().add(new ItemStack(Material.COOKED_RABBIT, 2));
                return;
            
            default:
                return;
        }
    }


}
