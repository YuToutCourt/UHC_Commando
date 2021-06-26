package com.commando.uhc_commando.Events;

import org.bukkit.event.Listener;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.block.Block;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

public class AntiCraftPotLevel2 implements Listener{

    @EventHandler
    public void glowStone(BlockBreakEvent event)
    {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        if (block.getType() == Material.GLOWSTONE)
        {
            block.getDrops().clear();
            event.setCancelled(true);
            block.setType(Material.AIR);
            player.sendMessage("§4§l[SERVER] > Potion level 2 as been disable");
        }
    }
    
}
