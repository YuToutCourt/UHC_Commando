package com.commando.uhc_commando;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.commando.uhc_commando.Commands.AlertCommand;
import com.commando.uhc_commando.Commands.CreateSpawnCommand;
import com.commando.uhc_commando.Commands.InventoryCommand;
import com.commando.uhc_commando.Commands.RuleCommand;
import com.commando.uhc_commando.Commands.StartCommand;
import com.commando.uhc_commando.Events.AchivementEvent;
import com.commando.uhc_commando.Events.RulesEvents;
import com.commando.uhc_commando.Events.ChatEvents;
import com.commando.uhc_commando.Events.CutCleanEvents;
import com.commando.uhc_commando.Events.DeathEvents;
import com.commando.uhc_commando.Events.WinEvents;
import com.commando.uhc_commando.Events.PlayerEvents;
import com.commando.uhc_commando.Events.WeatherEvent;
import com.commando.uhc_commando.Tasks.TimerTasks;
import com.commando.uhc_commando.Teams.Team;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.mrmicky.fastboard.FastBoard;

public final class UHC_Commando extends JavaPlugin {

    public FileConfiguration CONFIG;
    public final List<UUID> playersInTheParty = new ArrayList<UUID>();
	public final List<FastBoard> boards = new ArrayList<FastBoard>();
    public World WORLD;

    public boolean PVP = false;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        CONFIG = this.getConfig();
        Bukkit.broadcastMessage("§a-------- Commando are ready ! ---------");

        this.getCommand("alert").setExecutor(new AlertCommand());
        this.getCommand("rule").setExecutor(new RuleCommand());
        this.getCommand("start").setExecutor(new StartCommand(this));
        this.getCommand("createSpawn").setExecutor(new CreateSpawnCommand(this));
        this.getCommand("inv").setExecutor(new InventoryCommand());

        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new ChatEvents(), this);
        pm.registerEvents(new DeathEvents(this), this);
        pm.registerEvents(new PlayerEvents(this), this);
        pm.registerEvents(new WinEvents(), this);
        pm.registerEvents(new RulesEvents(), this);
        if(CONFIG.getBoolean("CutClean")) pm.registerEvents(new CutCleanEvents(), this);
        if(!CONFIG.getBoolean("World.BadWeather")) pm.registerEvents(new WeatherEvent(), this);
        if(CONFIG.getBoolean("DisableAchivements")) pm.registerEvents(new AchivementEvent(), this);

        this.resetGame();
    }

    @Override
    public void onDisable() {
        Bukkit.broadcastMessage("§c------- Commando going to bed ! --------");
    }

    public void resetGame() {
        WORLD = Bukkit.getWorld(CONFIG.getString("World.WorldName"));
        WORLD.setPVP(false);

        // Reset worldborder
        WORLD.setSpawnLocation(CONFIG.getInt("Spawn.x"), CONFIG.getInt("Spawn.y"), CONFIG.getInt("Spawn.z"));
        WORLD.getWorldBorder().setCenter(WORLD.getSpawnLocation());
        WORLD.getWorldBorder().setSize(CONFIG.getInt("Border.StartSize"));

        // Reset teams
        List<String> teams = CONFIG.getStringList("Teams.Teams");
        Team.chatEnable = CONFIG.getBoolean("Teams.TeamChat");
        Team.teams.clear();
        Team.teamsColorCode.clear();
        Team.teamsPrefix.clear();
        Team.teamsName.clear();
        for(int i = 0; i < teams.size(); i++) {
            String currentTeam = teams.get(i);
            String[] splitTeam = currentTeam.split(",");
            Team.teamsColorCode.add(splitTeam[0]);
            Team.teamsPrefix.add(splitTeam[1]);
            Team.teamsName.add(splitTeam[2]);
            Team.teams.add(new Team(splitTeam[2], splitTeam[1], splitTeam[0]));
        }

        // Reset timers
        TimerTasks.setRunning(false);
        TimerTasks.setPVPtimer(CONFIG.getInt("TimeBeforePvp"));
        TimerTasks.setWordborderTimer(CONFIG.getInt("Border.TimeBeforeMoving"));

        // Reset objectives & gamerules
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"scoreboard objectives remove vie");
        RulesEvents.NOTCH_APPLE = CONFIG.getBoolean("NotchApple");
        RulesEvents.STRENGHT_POTIONS = CONFIG.getBoolean("StrenghtPotions");
        RulesEvents.LEVEL_TWO_POTION = CONFIG.getBoolean("LevelTwoPotions");
        RulesEvents.PROJECTILES = CONFIG.getBoolean("ProjectilesKnockback");
        RulesEvents.HORSE = CONFIG.getBoolean("AllowHorse");
        RulesEvents.NETHER = CONFIG.getBoolean("AllowNether");
        RulesEvents.FIRE_ENCHANTS = CONFIG.getBoolean("FireAndFlame");

        RuleCommand.DAY_NIGHT_CYCLE = CONFIG.getBoolean("World.EnableDayNightCycle") ? "§aOn" : "§cOff";
        RuleCommand.FIRE_SPREADING = CONFIG.getBoolean("World.EnableFireSpreading") ? "§aOn" : "§cOff";
        RuleCommand.ACHIVEMENTS = CONFIG.getBoolean("DisableAchivements") ? "§cOff" : "§aOff";
        RuleCommand.TEAM_CHAT = CONFIG.getBoolean("Teams.TeamChat") ? "§aOn" : "§cOff";
        RuleCommand.INVICIBILITY = CONFIG.getString("Invicibility");
        RuleCommand.TIME_BEFORE_PVP = CONFIG.getString("TimeBeforePvp"); 
        RuleCommand.BORDER_START_SIZE = CONFIG.getString("Border.StartSize");
        RuleCommand.TIME_BEFORE_BORDER_MOVE = CONFIG.getString("Border.TimeBeforeMoving");
        RuleCommand.BORDER_END_SIZE = CONFIG.getString("Border.EndSize");
        RuleCommand.BORDER_MOVE_DURATION = CONFIG.getString("Border.MovingDuration");

        RuleCommand.NOTCH_APPLE = RulesEvents.NOTCH_APPLE ? "§aOn" : "§cOff";
        RuleCommand.STRENGHT_POTIONS = RulesEvents.STRENGHT_POTIONS ? "§aOn" : "§cOff";
        RuleCommand.LEVEL_TWO_POTION = RulesEvents.LEVEL_TWO_POTION ? "§aOn" : "§cOff";
        RuleCommand.PROJECTILES = RulesEvents.PROJECTILES ? "§aOn" : "§cOff";
        RuleCommand.HORSE = RulesEvents.HORSE ? "§aOn" : "§cOff";
        RuleCommand.NETHER = RulesEvents.NETHER ? "§aOn" : "§cOff";
        RuleCommand.FIRE_ENCHANTS = RulesEvents.FIRE_ENCHANTS ? "§aOn" : "§cOff";
        RuleCommand.CUT_CLEAN = CONFIG.getBoolean("CutClean") ? "§aOn" : "§cOff";

        // Reset scoreboard
        for(FastBoard board : this.boards) {
            board.delete();
        }
        this.boards.clear();
        for(Player p : Bukkit.getOnlinePlayers()) {
            this.boards.add(this.createBoard(p));
        }
    }

    public FastBoard createBoard(Player player) {
	    String SEPARATOR = ChatColor.RED + "--------------";
		FastBoard board = new FastBoard(player);
        board.updateTitle(CONFIG.getString("ScoreboardTitle").replace("&", "§"));

		List<String> lines = new ArrayList<String>();
		lines.add(SEPARATOR);
		lines.add(TimerTasks.formatTime(0, true));
		lines.add(SEPARATOR);
		lines.add(TimerTasks.formatLine("Teams", 0));
		lines.add(TimerTasks.formatLine("Your team", 0));
		lines.add(SEPARATOR);
		lines.add(TimerTasks.formatLine("PvP", TimerTasks.formatTime(0, false)));
		lines.add(TimerTasks.formatLine("Border", TimerTasks.formatTime(0, false)));
		lines.add(TimerTasks.formatLine("Size", 0));
		lines.add(SEPARATOR);
		board.updateLines(lines);

		return board;
	}

    public void removeBoardOf(Player player) {
        for(FastBoard board : this.boards) {
            if(board.getPlayer().getUniqueId() == player.getUniqueId()) {
                board.delete();
                this.boards.remove(board);
                return;
            }
        }
    }
}
