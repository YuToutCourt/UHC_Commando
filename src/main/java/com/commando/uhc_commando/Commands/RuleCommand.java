package com.commando.uhc_commando.Commands;

import com.commando.uhc_commando.UHC_Commando;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RuleCommand implements CommandExecutor {

    private UHC_Commando main;
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if ("rule".equalsIgnoreCase(command.getName())) {
            return rule(sender, args);
        }
        
        return false;
    }

    // TODO To complete
    public boolean rule(CommandSender sender, String[] args) {
        int page;
        try {
            page = Integer.parseInt(args[0]);
        } catch (Exception e) {
            return false;
        }
        switch (page){
            case 1:
                sender.sendMessage("§9-----------Page 1/?------------");
                sender.sendMessage("> §3When you §lkill §r§3a §eplayer §3all his §ateam §3join yours");
                sender.sendMessage("> §3If you §lkill §r§3a §eplayer §3you will have access to his §finventory");
                sender.sendMessage("> §3The §l§ncommando §r§3of a §ateam §3have access to all the §finventory §3of the §ateam");
                sender.sendMessage("> §3The game end when they are only §c1 §ateam §3remaining");
                return true;
            case 2:
                sender.sendMessage("§9-----------Page 2/?------------");
                sender.sendMessage("> §3Infinite day : §l"+ this.main.CONFIG.getString("World.EnableDayNightCycle"));
                sender.sendMessage("> §3If you §lkill §r§3a §eplayer §3you will have access to his §finventory");
                sender.sendMessage("> §3The §l§ncommando §r§3of a §ateam §3have access to all the §finventory §3of the §ateam");
                sender.sendMessage("> §3The game end when they are only §c1 §ateam §3remaining");
            default:
                return false;
        }
    }
    
}
