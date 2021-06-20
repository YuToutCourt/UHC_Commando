package com.commando.uhc_commando.Tasks;

import com.commando.uhc_commando.UHC_Commando;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import fr.mrmicky.fastboard.FastBoard;

public class TimerTask extends BukkitRunnable {

	public static boolean RUN = false;
	private static int time;

	public static int WBtime;

	private UHC_Commando main;
	
	public TimerTask(UHC_Commando main) {
		this.main = main;
		time = 0;
	}
	
	@Override
	public void run() {
		this.updateBoard();
		
		if(RUN) {
			time ++;

			if(time == WBtime*60) { // worldborder starts moving
				this.moveWorldBorder();
			}
		}
		
	}
	
	public static void setRunning(boolean state) {
		RUN = state;
	}
	
	public String formatTime(int secs) {
		return String.format("%02d:%02d:%02d", secs / 3600, (secs % 3600) / 60, secs % 60);
	}

    private void updateBoard() { // TODO Board
        for(FastBoard board : this.main.boards) {
            board.updateLines();
        }
	}

	private void moveWorldBorder() { 
		int endSize = this.main.CONFIG.getInt("Border.EndSize");
		int duration = this.main.CONFIG.getInt("Border.MovingDuration");
		Bukkit.getWorld("world").getWorldBorder().setSize(endSize, duration);
	}
}
 