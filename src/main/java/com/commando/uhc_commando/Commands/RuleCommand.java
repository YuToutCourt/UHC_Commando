package com.commando.uhc_commando.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RuleCommand implements CommandExecutor {
    
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
    
}
