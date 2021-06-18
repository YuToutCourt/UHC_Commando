package com.commando.uhc_commando.Commands;

import com.commando.uhc_commando.Tasks.TimerTask;
import com.commando.uhc_commando.Teams.Team;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JoinCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if("join".equalsIgnoreCase(command.getName()) && sender instanceof Player) {
            return join(sender, args);
        }

        return false;
    }

    public boolean join(CommandSender sender, String[] args) {
        if(TimerTask.RUN) {
            sender.sendMessage("You can't join a team when the game has started!");
            return false;
        }

        if(args.length < 1) {
            sender.sendMessage("Please specify a team!");
            return false;
        }

        for(Team t : Team.teams) {
            if(t.getName().equalsIgnoreCase(args[1]) || String.valueOf(t.getId()).equalsIgnoreCase(args[1])) {
                t.join(((Player) sender).getUniqueId());
                break;
            }
            sender.sendMessage("No teams found with name nor id \"" + args[1] + "\"");
            return false;
        }
        
        return true;
    }
    
}
