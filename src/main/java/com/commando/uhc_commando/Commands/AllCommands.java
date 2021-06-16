package com.commando.uhc_commando.Commands;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class AllCommands implements CommandExecutor {
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if ("alert".equalsIgnoreCase(command.getName()) && sender.isOp()) {
            return alert(sender, args);
        }
        
        if ("rule".equalsIgnoreCase(command.getName())) {
            return rule(sender, args);
        }
        if ("config".equalsIgnoreCase(command.getName()) && sender instanceof Player && sender.isOp()) {
            return config(sender);
        }

        if ("start".equalsIgnoreCase(command.getName()) && sender.isOp()) {
            return start();
        }

        return false;
    }

    public boolean alert(CommandSender sender, String[] args) {
        if (args.length == 0) return false;
        String arg = "§c§l> [SERVEUR] ";
        for (String part : args) arg += " " + part;
        Bukkit.broadcastMessage(arg);
        return true;
    }

    // TODO To complete
    public boolean rule(CommandSender sender, String[] args) {
        int page;
        try {
            page = Integer.parseInt(args[0]);
        } catch (Exception e) {
            page = 1;
        }
        switch (page){
            case 1:
                sender.sendMessage("§9-----------Page 1/?------------");
                sender.sendMessage("> §3Quand un joueur vous tue vous rejoinez son équipe ");
                sender.sendMessage("> §3Si le Commando de votre équipe meurt l'équipe et donc dissoute. Tout les joueurs de l'équipe son donc Tp aléatoirement sur la map");
                return true;
            default:
                return false;
        }
    }

    // TODO to edit
    public boolean config(CommandSender sender) {
        ItemStack chestteam = new ItemStack(Material.CHEST, 1);
        ItemMeta chest = chestteam.getItemMeta();
        chest.setDisplayName("§6Configureur");
        chestteam.setItemMeta(chest);
        for(Player p : Bukkit.getOnlinePlayers()) {
            if (p.isOp()){
                Inventory inventory = p.getInventory();
                inventory.clear();
                p.getInventory().addItem(chestteam);
            }
        }
        return true;
    }
    
    public boolean start() {
        World world = Bukkit.getWorld("world");
        world.setDifficulty(Difficulty.PEACEFUL);
        world.setTime(0);
        Bukkit.broadcastMessage("§l> [SERVEUR] §cPréparation au lancement du §a§Commando UHC ... ");
        // TODO alternative ?
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"spreadplayers 0 0 10 200 false @a"); //x z DistanceEntreChaquePlayer MaxRangeSurLaTp team?
        for(Player player : Bukkit.getOnlinePlayers()) {
            for(PotionEffect effect : player.getActivePotionEffects()) {
                player.removePotionEffect(effect.getType());
            }
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20*62, 255));
            player.setGameMode(GameMode.SURVIVAL);
            player.setHealth(20);
            player.setFoodLevel(20);
            player.setExp(0);
            Inventory inv = player.getInventory();
            inv.clear();
            inv.addItem(new ItemStack(Material.COOKED_BEEF, 64));
        }
        world.setDifficulty(Difficulty.HARD);
        return true;
    }
}
