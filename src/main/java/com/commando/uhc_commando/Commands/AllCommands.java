package com.commando.uhc_commando.Commands;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ItemMergeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class AllCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

        if ("alert".equals(command.getName().toLowerCase()) && commandSender instanceof Player) {
            if (args.length == 0) return false;
            String arg = "§c§l> [SERVEUR]";
            for (String part : args)
                arg += " " + part;
            if (commandSender.isOp()) Bukkit.broadcastMessage(arg);
            else commandSender.sendMessage("§c§l> [SERVEUR] Vous n'avez pas la permission");
            return true;
        }
        else if ("rule".equals(command.getName().toLowerCase()) && commandSender instanceof Player) {
            if (args.length == 0) return false;
            switch (args[0]){
                case "1":
                    commandSender.sendMessage("§9-----------Page 1/?------------");
                    commandSender.sendMessage("> §3Quand un joueur vous tue vous rejoinez son équipe ");
                    commandSender.sendMessage("> §3Si le Commando de votre équipe meurt l'équipe et donc dissoute. Tout les joueurs de l'équipe son donc Tp aléatoirement sur la map");
                    return true;

            }
        }
        else if ("config".equals(command.getName().toLowerCase()) && commandSender instanceof Player && commandSender.isOp()) {
            if (args.length == 0){
                ItemStack chestteam = new ItemStack(Material.CHEST,1);
                ItemMeta chest = chestteam.getItemMeta();
                chest.setDisplayName("§6Configureur");
                chestteam.setItemMeta(chest);
                for(Player p : Bukkit.getOnlinePlayers()){
                    if (p.isOp()){
                        Inventory inventory = p.getInventory();
                        inventory.clear();
                        p.getInventory().addItem(chestteam);
                    }
                }
                return true;

            }
        }
        else if ("start".equals(command.getName().toLowerCase()) && commandSender instanceof Player) {
            World world = Bukkit.getWorld("world");

            world.setDifficulty(Difficulty.PEACEFUL);
            world.setTime(0);
            Bukkit.broadcastMessage("§l> [SERVEUR] §cPréparation au lancement du §a§Commando UHC ... ");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"spreadplayers 0 0 10 200 false @a"); //x z DistanceEntreChaquePlayer MaxRangeSurLaTp team?
            for(Player player : Bukkit.getOnlinePlayers()){
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"effect @a clear");
                player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20*62, 255));
                player.setGameMode(GameMode.SURVIVAL);
                player.setHealth(20);
                player.setFoodLevel(20);
                player.setExp(0);
                Inventory inv = player.getInventory();
                inv.clear();
                Inventory inventory = player.getInventory();
                inventory.clear();
                inventory.addItem(new ItemStack(Material.COOKED_BEEF, 64));
            }
            world.setDifficulty(Difficulty.HARD);
            return true;

        }
        return false;
    }
}
