//package os_part2;
//Author:		Yaseen Hull
//Assignment: 	OS2 part 2 producer-consumer problem using semaphores
//Date:			29/04/2019

import java.util.Random;

public class Washer extends Thread {
	private int dirty_dishes;
	private WetDishRack shared_rack;
	private Random washing_time;
	private int sleep;
	
	Washer(int n, WetDishRack the_rack,int sleep) {
		this.dirty_dishes = n;
		this.shared_rack = the_rack;
		this.washing_time = new Random();
		this.sleep=sleep;
	}
	public void run() {
	    try {
	    	int counter=0;
	    	while (dirty_dishes>0) {
	    		counter++;
	    		System.out.println("---Washer is washing dish #"+counter);
	    		sleep(washing_time.nextInt(sleep)); //wait a bit - washing period before adding to the rack
	    		shared_rack.addDish(counter);
	    		System.out.println("---Washer added dish #"+counter+" to rack.");
	    		dirty_dishes--;
	    	}
	    }
	    catch (InterruptedException ex) { /* not handling this  */}
	    System.out.println("---Washer is DONE.");
	}
	
}	
