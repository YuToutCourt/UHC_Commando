package com.commando.uhc_commando.tasks;

import com.commando.uhc_commando.UHC_Commando;

import org.bukkit.scheduler.BukkitRunnable;

import fr.mrmicky.fastboard.FastBoard;

public class TimerTask extends BukkitRunnable {

	private UHC_Commando main;
	public static boolean RUN = false;
	private static int time;
	
	public TimerTask(UHC_Commando main) {
		this.main = main;
		time = 0;
	}
	
	@Override
	public void run() {
		this.updateBoard();
		
		if(RUN) {
			time ++;
		}
		
	}
	
	private void updateBoard() { // TODO Board
        for(FastBoard board : this.main.boards.values()) {
            board.updateLines();
        }
	}
	
	public static void setRunning(boolean state) {
		RUN = state;
	}
	
	public String formatTime(int secs) {
		return String.format("%02d:%02d:%02d", secs / 3600, (secs % 3600) / 60, secs % 60);
	}
}
