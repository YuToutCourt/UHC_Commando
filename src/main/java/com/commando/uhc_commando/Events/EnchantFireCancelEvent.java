package com.commando.uhc_commando.Events;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;

public class EnchantFireCancelEvent implements Listener {

    @EventHandler
    public void onEnchantItem(EnchantItemEvent event){
        Map<Enchantment,Integer> a = new HashMap<Enchantment,Integer>();
        Player player = event.getEnchanter();
        a.putAll(event.getEnchantsToAdd());
        if(a.containsKey(Enchantment.ARROW_FIRE) || a.containsKey(Enchantment.FIRE_ASPECT)){
            player.sendMessage("§4§l[SERVER] > Fire enchant as been disable");
            event.setCancelled(true);
        }
    }
    
}
