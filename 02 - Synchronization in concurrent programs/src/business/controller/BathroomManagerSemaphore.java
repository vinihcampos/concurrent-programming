package business.controller;

import java.util.concurrent.Semaphore;

import model.Person;

public class BathroomManagerSemaphore extends BathroomManager{
	private Semaphore semaphore;
	
	public BathroomManagerSemaphore(Integer capacity) {
		super(capacity);
		this.semaphore = new Semaphore(1, true);
	}
	
	public Boolean isEmpty() {
		boolean empty = true;
		try {
			semaphore.acquire();
			empty = insidePeople.isEmpty();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaphore.release();
		}
		
		return empty;
	}
	
	public Integer getTimeSleep() {
		int sleep = 1000;
		try {
			semaphore.acquire();
			sleep = insidePeople.get(0).getTime();
			semaphore.release();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sleep;
	}
	
	public void add(Person p) {
		try {
			semaphore.acquire();
			auxiliarAdd(p);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaphore.release();
		}
	}
	
	public void remove() {
		try {
			semaphore.acquire();
			auxiliarRemove();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			semaphore.release();
		}
	}
}
