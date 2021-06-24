package com.commando.uhc_commando.Tasks;

import com.commando.uhc_commando.UHC_Commando;
import com.commando.uhc_commando.Teams.Team;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

import fr.mrmicky.fastboard.FastBoard;

public class TimerTask extends BukkitRunnable {

	public static boolean RUN = false;
	private static int time = 0;

	public static int WBtime = 0;
	private int WBstate = 0;

	public static int PvPtime = 0;

	private UHC_Commando main;
	
	public TimerTask(UHC_Commando uhc) {
		this.main = uhc;
		time = 0;
	}
	
	@Override
	public void run() {
		this.updateBoards();
		
		if(RUN) {
			time ++;
			if(WBstate < 2) WBtime --; // changing wb timer only if not finished
			if(PvPtime > 0) PvPtime --;

			if(WBtime == 0 && WBstate == 0) { // worldborder starts moving
				this.moveWorldBorder();
				WBstate ++;
			}
			if(WBtime == 0 && WBstate == 1) { // worldborder ends moving
				WBstate ++;
			}

			if(PvPtime == 0) { // turn pvp on
				this.main.PVP = true;
				Bukkit.broadcastMessage("PvP is now enable!");
				PvPtime --; // avoid entering this "if" infinitely
			}
		}
		
	}
	
	public static void setRunning(boolean state) {
		RUN = state;
	}

	public static void setWordborderTimer(int minutes) {
		WBtime = minutes * 60;
	}

	public static void setPVPtimer(int minutes) {
		PvPtime = minutes * 60;
	}
	
	public static String formatTime(int secs, boolean printHour) {
		ChatColor color = RUN ? ChatColor.YELLOW : ChatColor.RED;
		String ret = "";
		if(!printHour) ret = String.format("%02d:%02d", secs / 60, secs % 60);
		else ret = String.format("%02d:%02d:%02d", secs / 3600, (secs % 3600) / 60, secs % 60);

		return color + ret.replace(":", ChatColor.RESET + ":" + color);
	}

	public static String formatLine(String key, Object value, ChatColor cVal) {
		ChatColor cKey = ChatColor.GOLD;
		ChatColor cRst = ChatColor.RESET;
		return cKey + key + cRst + " - " + cVal + value;
	}

	public static String formatLine(String key, Object value) {
		return formatLine(key, value, ChatColor.YELLOW);
	}

    private void updateBoards() {
        for(FastBoard board : this.main.boards) {
			board.updateLine(1, formatTime(time, true));
			board.updateLine(3, formatLine("Teams", Team.getLeadingTeamsAmount()));
			board.updateLine(4, formatLine("Your team", Team.getTeamOf(board.getPlayer()).getPlayerAmount()));
			if(PvPtime > 0) board.updateLine(6, formatLine("PvP", formatTime(PvPtime, false)));
			else board.updateLine(6, formatLine("PvP", "ON", ChatColor.GREEN));
			board.updateLine(7, formatLine("Border", formatTime(WBtime, false)));
			board.updateLine(8, formatLine("Size", (int)this.main.WORLD.getWorldBorder().getSize()));
        }
	}

	private void moveWorldBorder() { 
		int endSize = this.main.CONFIG.getInt("Border.EndSize");
		int duration = this.main.CONFIG.getInt("Border.MovingDuration");
		WBtime = duration;
		this.main.WORLD.getWorldBorder().setSize(endSize, duration);
		Bukkit.broadcastMessage("Border is now moving");
	}
}
 