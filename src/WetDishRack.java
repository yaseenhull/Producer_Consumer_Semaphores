//package os_part2;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class WetDishRack {
	// add variables
	int rackSize;
	int count;
	private int rack[];
	LinkedList buffer = new LinkedList(); //rack structure: finite buffer
	int top = -1;
	
	//Semaphore mutexCount = new Semaphore(1); //count:provides exclusive access to threads by decrementing (acquiring:0 (blocking)) and incrementing (releasing:1 (unblocking))
	//Semaphore mutexUser = new Semaphore(1);
	//Semaphore barrier = new Semaphore(0); // <= 0 blocks threads, > 0 unblocks threads allowing them to pass through
	
	//Semaphore washer = new Semaphore(0);
	//Semaphore dryer = new Semaphore(1);
	Semaphore mutex = new Semaphore(1); // provides exclusive access to threads by decrementing (acquiring:0 (blocking)) and incrementing (releasing:1 (unblocking))
	Semaphore dishCount = new Semaphore(0); // keeps track of number of dishes in rack
	Semaphore openRackSlots; // keeps track of available spaces in rack
	
	
	WetDishRack(int rackSize) {
	    // add correct code here 
	    this.rackSize = rackSize;
	    //this.rack =  new int[rackSize];
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
			//count = rack[top--];
			count = (Integer)buffer.remove(0); // remove dish from rack
		mutex.release();
			
		openRackSlots.release();	//increments no. of available slots: free up space on rack
		
		return count; // replace with correct code here
		// if dishes are clean dry and remove
		// if dishes != rack size; wait - barrier implementation
		// when dishes == rack size; release()
		// if barrier.Semaphore <= 0 block (acquire)
		// when the rack is empty dryer must wait translates to if barrier !> 0
	
	}
	
}
