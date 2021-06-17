package com.commando.uhc_commando.event;

import com.commando.uhc_commando.UHC_Commando;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class AllEvent implements Listener {

    private UHC_Commando main;

    public AllEvent(UHC_Commando uhc){
        this.main = uhc;
    }

    // ############ Events that are related with the CHAT in game #################

    @EventHandler
    public void playerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        event.setJoinMessage("§7[§3+§7] " + player.getDisplayName());
        if(!this.main.START) {
            Location spawn = new Location(Bukkit.getWorld("world"), this.main.CONFIG.getInt("Spawn.x"), this.main.CONFIG.getInt("Spawn.y"), this.main.CONFIG.getInt("Spawn.z"));
            player.teleport(spawn);
        } else {
            if(!this.main.players.contains(player.getUniqueId()))
                player.setGameMode(GameMode.SPECTATOR);
        }
    }

    @EventHandler
    public void playerLeave(PlayerQuitEvent event){
        Player player = event.getPlayer();
        event.setQuitMessage("§7[§4-§7] " + player.getDisplayName());
    }

    @EventHandler
    public void onMessage(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        String message = event.getMessage();
        event.setFormat("<- "+player.getDisplayName()+" -> "+message);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) throws InterruptedException {
        World world = Bukkit.getWorld("world");
        Player playerDeath = event.getEntity();
        Player playerKiller = playerDeath.getKiller();
        if (!(playerKiller instanceof Player)) {
            event.setDeathMessage("§c§l† " + playerDeath.getDisplayName()+ " died from PVE §c§l†");
            world.playSound(playerDeath.getLocation(), Sound.ZOMBIE_REMEDY, 1000.0F, 1.0F);
            playerDeath.setGameMode(GameMode.SPECTATOR);
        } else {
            event.setDeathMessage("§c§l† " + playerDeath.getDisplayName()+ " was killed by " + playerKiller.getPlayerListName() + " §c§l†");
            world.playSound(playerDeath.getLocation(), Sound.ZOMBIE_REMEDY, 1000.0F, 1.0F);
            //TODO
            /*
            Faire en sorte que quand le joueur meurt il soit 30 secondes en Spec puis tp en survie a son killer. Rejoin ça team en même temps
             */
            playerDeath.setGameMode(GameMode.SPECTATOR);
            //playerDeath.setGameMode(GameMode.SURVIVAL);
            //playerDeath.teleport(playerKiller.getLocation());

        }
    }


    //TODO A modifier
    @EventHandler
    public void onClick(InventoryClickEvent event){
        Inventory inv = event.getInventory();
        Player player = (Player) event.getWhoClicked();
        ItemStack current = event.getCurrentItem();
        if(current == null) return;
        if(inv.getName().equalsIgnoreCase("§6Configureur")){
            event.setCancelled(true);
        }
    }
}
