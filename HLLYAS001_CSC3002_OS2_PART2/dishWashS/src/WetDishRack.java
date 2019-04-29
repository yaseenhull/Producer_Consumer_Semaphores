//package os_part2;
//Author:		Yaseen Hull
//Assignment: 	OS2 part 2 producer-consumer problem using semaphores
//Date:			29/04/2019

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class WetDishRack {
	// add variables
	int rackSize;
	int count;
	LinkedList buffer = new LinkedList(); //rack structure: finite buffer
	
	Semaphore mutex = new Semaphore(1); // provides exclusive access to threads by decrementing (acquiring:0 (blocking)) and incrementing (releasing:1 (unblocking))
	Semaphore dishCount = new Semaphore(0); // keeps track of number of dishes in rack
	Semaphore openRackSlots; // keeps track of available spaces in rack
	
	
	WetDishRack(int rackSize) {
	    // add correct code here 
	    this.rackSize = rackSize;
	
	    this.openRackSlots = new Semaphore(rackSize); // creates a semaphore with rackSize permits
	}		
	
	public void addDish(int dish_id)  throws InterruptedException {
		openRackSlots.acquire(); // decrements rack space: since dish fills space
		
		mutex.acquire();
			//rack[++top] = dish_id;
			buffer.add(dish_id); // add dish to rack
			
		mutex.release();
		
		dishCount.release(); // increments number items on rack: put dish on rack

		// add correct code here
		// if dish rack empty add dishes
	
		
		
	
		
	}
	
	public int removeDish() throws InterruptedException {
		dishCount.acquire(); // decrements number items on rack: take dish off rack
		
		mutex.acquire();
		
			count = (Integer)buffer.remove(0); // remove dish from rack
		mutex.release();
			
		openRackSlots.release();	//increments no. of available slots: free up space on rack
		
		return count; 
		// if dishes are clean dry and remove
		
	
	}
	
}
