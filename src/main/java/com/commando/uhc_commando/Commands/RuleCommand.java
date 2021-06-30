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
                sender.sendMessage("> §3Infinite day : "+ DAY_NIGHT_CYCLE);
                sender.sendMessage("> §3Fire spreading : "+ FIRE_SPREADING);
                sender.sendMessage("> §3Achivements are show during the game : "+ ACHIVEMENTS);
                sender.sendMessage("> §3Team chat : "+ TEAM_CHAT);
                sender.sendMessage("> §3Invicibility time : "+ INVICIBILITY + " second(s)");
                return true;
            case 3:
                sender.sendMessage("§9-----------Page 3/?------------");
                sender.sendMessage("> §3Time before the Pvp : "+ TIME_BEFORE_PVP + " minute(s)");
                sender.sendMessage("> §3Border size : "+ BORDER_START_SIZE);
                sender.sendMessage("> §3Time before the border starts moving : "+ TIME_BEFORE_BORDER_MOVE + "minute(s)");
                sender.sendMessage("> §3Moving time of the border: "+ BORDER_MOVE_DURATION + "second(s)");
                sender.sendMessage("> §3Final size of the border : "+ BORDER_END_SIZE);
                return true;
            default:
                return false;
        }
    }
    
}
