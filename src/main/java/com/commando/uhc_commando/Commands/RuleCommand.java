package com.commando.uhc_commando.Commands;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RuleCommand implements CommandExecutor {

    public static String DAY_NIGHT_CYCLE;
    public static String FIRE_SPREADING;
    public static String ACHIVEMENTS;
    public static String TEAM_CHAT;
    public static String INVICIBILITY;
    public static String TIME_BEFORE_PVP;
    public static String BORDER_START_SIZE;
    public static String TIME_BEFORE_BORDER_MOVE;
    public static String BORDER_END_SIZE;
    public static String BORDER_MOVE_DURATION;

    public static String NOTCH_APPLE;
    public static String LEVEL_TWO_POTION;
    public static String STRENGHT_POTIONS;
    public static String PROJECTILES;
    public static String NETHER;
    public static String HORSE;
    public static String FIRE_ENCHANTS;
    public static String CUT_CLEAN;


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if ("rule".equalsIgnoreCase(command.getName())) {
            return rule(sender, args);
        }
        
        return false;
    }

    public boolean rule(CommandSender sender, String[] args) {
        int page;
        try {
            page = Integer.parseInt(args[0]);
        } catch (Exception e) {
            return false;
        }
        switch (page){
            case 1:
                sender.sendMessage("§9-----------Page 1/5------------");
                sender.sendMessage("> §3When you §lkill §r§3a §eplayer§3, all their §ateams §3join yours");
                sender.sendMessage("> §3If you §lkill §r§3a §eplayer§3, you will have access to their §finventory");
                sender.sendMessage("> §3The §l§ncommando§r §3of a §ateam §3have access to all the §finventories §3of the §ateam");
                sender.sendMessage("> §3The game ends when there is only §c1 §ateam §3remaining");

                return true;
            case 2:
                sender.sendMessage("§9-----------Page 2/5------------");
                sender.sendMessage("> §3Day Cycle: "+ DAY_NIGHT_CYCLE);
                sender.sendMessage("> §3Fire spreading: "+ FIRE_SPREADING);
                sender.sendMessage("> §3Achivements are show during the game: "+ ACHIVEMENTS);
                sender.sendMessage("> §3Team chat: "+ TEAM_CHAT);
                sender.sendMessage("> §3Invicibility time: §e"+ INVICIBILITY + " second(s)");
                return true;
            case 3:
                sender.sendMessage("§9-----------Page 3/5------------");
                sender.sendMessage("> §3Time before PvP: §e"+ TIME_BEFORE_PVP + " §3minute(s)");
                sender.sendMessage("> §3Border size: §e"+ BORDER_START_SIZE);
                sender.sendMessage("> §3Time before border moving: §e"+ TIME_BEFORE_BORDER_MOVE + " §3minute(s)");
                sender.sendMessage("> §3Moving time of the border: §e"+ BORDER_MOVE_DURATION + " §3second(s)");
                sender.sendMessage("> §3Final size of the border: §e"+ BORDER_END_SIZE);
                return true;
            case 4:
                sender.sendMessage("§9-----------Page 4/5------------");
                sender.sendMessage("> §3Notch Apple: "+ NOTCH_APPLE);
                sender.sendMessage("> §3Level 2 Potions: "+ LEVEL_TWO_POTION);
                sender.sendMessage("> §3Strenght Potions: "+ STRENGHT_POTIONS);
                sender.sendMessage("> §3Projectiles Knockback: "+ PROJECTILES);
                return true;
            case 5:
                sender.sendMessage("§9-----------Page 5/5------------");
                sender.sendMessage("> §3Nether: "+ NETHER);
                sender.sendMessage("> §3Horses: "+ HORSE);
                sender.sendMessage("> §3Fire Enchants: "+ FIRE_ENCHANTS);
                sender.sendMessage("> §3Cut Clean: "+ CUT_CLEAN);
                return true;
            default:
                return false;
        }
    }
    
}
