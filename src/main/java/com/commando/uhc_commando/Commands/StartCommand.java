package com.commando.uhc_commando.Commands;

import com.commando.uhc_commando.UHC_Commando;
import com.commando.uhc_commando.Tasks.TimerTask;
import com.commando.uhc_commando.Teams.Team;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class StartCommand implements CommandExecutor {

    private UHC_Commando main;

    public StartCommand(UHC_Commando uhc){
        this.main = uhc;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if ("start".equalsIgnoreCase(command.getName()) && sender.isOp()) {
            return start(sender);
        }

        return false;
    }

    public boolean start(CommandSender sender) {
        Bukkit.broadcastMessage("§l> [SERVEUR] §cPréparation au lancement du §a§Commando UHC ... ");

        if(Bukkit.getOnlinePlayers().size() > Team.teams.size()) {
            sender.sendMessage("There is more players than teams available, the game can't start!");
            return false;
        }
        int durationEffect = this.main.CONFIG.getInt("Invicibility");

        World world = Bukkit.getWorld("world");
        world.setDifficulty(Difficulty.PEACEFUL);
        world.setTime(0);
        // TODO alternative ?
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spreadplayers 0 0 10 200 false @a"); //x z DistanceEntreChaquePlayer MaxRangeSurLaTp team?
        int indexOfTeam = 0;
        for(Player player : Bukkit.getOnlinePlayers()) {
            // reset potion effects
            for(PotionEffect effect : player.getActivePotionEffects()) {
                player.removePotionEffect(effect.getType());
            }

            // reset achievements
            for(Achievement a : Achievement.values()) {
                if(player.hasAchievement(a)) player.removeAchievement(a);
            }

            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20*durationEffect, 255));
            player.setGameMode(GameMode.SURVIVAL);
            player.setHealth(20);
            player.setFoodLevel(20);
            player.setExp(0);
            Inventory inv = player.getInventory();
            inv.clear();
            inv.addItem(new ItemStack(Material.COOKED_BEEF, 64));
            
            Team.teams.get(indexOfTeam).join(player.getUniqueId());
            this.main.playersInTheParty.add(player.getUniqueId());
            indexOfTeam++;
        }

        world.setDifficulty(Difficulty.HARD);

        TimerTask timer = new TimerTask(this.main);
        timer.runTaskTimer(this.main, 0, 20);
        TimerTask.setRunning(true);

        return true;
    }
}
