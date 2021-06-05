package com.commando.uhc_commando.event;

import com.commando.uhc_commando.UHC_Commando;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.TimeUnit;

public class AllEvent implements Listener {

    private UHC_Commando main;

    public AllEvent(UHC_Commando uhc){
        this.main = uhc;
    }

    // ############ Event that is realated with the CHAT in game #################

    @EventHandler
    public void PlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        event.setJoinMessage("§7[§3+§7] " + player.getDisplayName());
    }

    @EventHandler
    public void PlayerLeave(PlayerQuitEvent event){
        Player player = event.getPlayer();
        event.setQuitMessage("§7[§4-§7] " + player.getDisplayName());
    }
    @SuppressWarnings("deprecated")
    @EventHandler
    public void OnMessage(PlayerChatEvent event){
        Player player = event.getPlayer();
        String message = event.getMessage();
        event.setFormat("<- "+player.getDisplayName()+" -> "+message);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) throws InterruptedException {
        World world = Bukkit.getWorld("world");
        Player playerKiller = event.getEntity().getKiller();
        Player playerDeath = event.getEntity();
        if (!(playerKiller instanceof Player)){
            event.setDeathMessage("§c§l† " + playerDeath.getDisplayName()+ " est mort PVE comme une merde §c§l†");
            world.playSound(playerDeath.getLocation(), Sound.ZOMBIE_REMEDY, 1000.0F, 1.0F);
            playerDeath.setGameMode(GameMode.SPECTATOR);

        }
        else{
            event.setDeathMessage("§c§l† " + playerDeath.getDisplayName()+ " à est tomber au combat contre "+ playerKiller.getPlayerListName() +" §c§l†");
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

    // ############# Event for the configure commande ###############

    @EventHandler
    public void onClickChest(PlayerInteractEvent event){

        Player player = event.getPlayer();
        ItemStack it = event.getItem();
        if(it == null){
            return;
        }
        if (it.getType() == Material.CHEST && it.hasItemMeta() && it.getItemMeta().hasDisplayName() && it.getItemMeta().getDisplayName().equalsIgnoreCase("§6Configureur")){
            Inventory inv = Bukkit.createInventory(null, 45, "§6Configureur");
            ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE,1, (byte)8);
            for(int i=0;i<10;i++)
                inv.setItem(i,glass);
            for(int i=35;i<45;i++)
                inv.setItem(i,glass);
            for(int i=17;i<=26;i+=9)
                inv.setItem(i,glass);
            for(int i=18;i<=27;i+=9)
                inv.setItem(i,glass);
            player.openInventory(inv);
        }

    }
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
