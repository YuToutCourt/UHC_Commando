package com.commando.uhc_commando.Commands;

import com.commando.uhc_commando.Tasks.TimerTask;
import com.commando.uhc_commando.Teams.Team;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class InventoryCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if ("inv".equalsIgnoreCase(command.getName()) && sender instanceof Player) {
            return inventory(sender, args);
        }
        return false;
    }

    public boolean inventory(CommandSender sender, String[] args) {
        if (args.length != 1) return false;
        Player playerSender = (Player) sender;
        Player target = Bukkit.getServer().getPlayer(args[0]);
        if(!TimerTask.RUN) {
            playerSender.sendMessage("§cThe game has not started yet");
            return true;
        }
        if(target == null) {
            playerSender.sendMessage("§c§lThe player "+ ChatColor.WHITE + args[0] + " §c§lis not connected");
            return true;
        }
        if(playerSender.getName().equals(target.getName())){
            playerSender.sendMessage("§eYou can't open your own inventory");
            return true;
        } 
        if(!Team.isPlayerLeading(playerSender, target)) {
            playerSender.sendMessage("§cYou don't lead this player");
            return true;
        }
        // TODO Armor in Inventory
        ItemStack[] test = target.getEquipment().getArmorContents();
        for(ItemStack item : test)
            System.out.println(item);
        playerSender.openInventory(target.getInventory());
        return true;
    }
}