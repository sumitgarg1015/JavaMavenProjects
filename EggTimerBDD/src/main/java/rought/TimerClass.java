package rought;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class TimerClass{

	private static LocalDateTime now;
	
	private static int seconds, counter=0;
	private static int maxSeconds=25, newCount, targetSeconds;
	
	public static void main(String[] args) {
		/*
		 * Timer timer = new Timer();
		 * 
		 * timer.scheduleAtFixedRate(new TimerTask() { int i = 0; public void run() {
		 * if(timeCount==i) { i++; maxCount--; timeCount++; System.out.println(i); }
		 * 
		 * 
		 * if (i > 25) { timer.cancel(); } } }, 0, 1000);
		 */
		
		now = LocalDateTime.now();
//		targetSeconds = now.getSecond() + maxSeconds; 
		
		seconds = now.getSecond();
		
		
		while(counter!=25) {
			int newSeconds = now.getSecond() - seconds; 
			int webSecondCount = maxSeconds - 25;
			
			
		}
		
		
    }

}
