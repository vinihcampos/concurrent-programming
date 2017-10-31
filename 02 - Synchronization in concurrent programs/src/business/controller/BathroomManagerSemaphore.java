package business.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;

import business.util.Log;
import model.Person;

public class BathroomManagerSemaphore implements BathroomManager{
	private Semaphore semaphore;
	private Integer capacity;
	private List<Person> insidePeople;
	private List<Person> waitingPeople;
	private Integer timeElapsed;
	private Integer outsideTimeElapsed;
	
	public BathroomManagerSemaphore(Integer capacity) {
		this.semaphore = new Semaphore(1, true);
		this.capacity = capacity;
		this.insidePeople = new ArrayList<>();
		this.waitingPeople = new ArrayList<>();
		this.timeElapsed = 0;
		this.outsideTimeElapsed = 0;
	}
	
	@Override
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
	
	@Override
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
	
	@Override
	public void add(Person p) {
		try {
			semaphore.acquire();
			if(insidePeople.isEmpty()) {
				insidePeople.add(p);
				Log.entrance(p, insidePeople.size(), Math.max(timeElapsed, outsideTimeElapsed));
				Collections.sort(insidePeople);
			}else {
				if(insidePeople.get(0).getGender() == p.getGender() && 
				   insidePeople.size() < capacity && waitingPeople.isEmpty()) {
					insidePeople.add(p);
					Log.entrance(p, insidePeople.size(), Math.max(timeElapsed, outsideTimeElapsed));
					Collections.sort(insidePeople);
				}else {
					waitingPeople.add(p);
				}
			}
			timeElapsed++;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaphore.release();
		}
	}
	
	@Override
	public void remove() {
		try {
			semaphore.acquire();
			if(!insidePeople.isEmpty()) {
				Integer time = insidePeople.get(0).getTime();
				outsideTimeElapsed += time;
				Person auxP;
				while(!insidePeople.isEmpty() && insidePeople.get(0).getTime() == time) {
					auxP = insidePeople.remove(0);
					Log.exit(auxP, insidePeople.size(),outsideTimeElapsed);					
				}
				
				for(int i = 0; i < insidePeople.size(); ++i) {
					insidePeople.get(i).setTime( insidePeople.get(i).getTime() - time );
				}
				
				if(insidePeople.size() < capacity && !waitingPeople.isEmpty()) {
					
					Boolean gender = !insidePeople.isEmpty() ? insidePeople.get(0).getGender() : waitingPeople.get(0).getGender();
					
					while(!waitingPeople.isEmpty() && 
						  waitingPeople.get(0).getGender() == gender &&
						  insidePeople.size() < capacity) {
						insidePeople.add(waitingPeople.get(0));
						auxP = waitingPeople.remove(0);
						Log.entrance(auxP, insidePeople.size(),outsideTimeElapsed);
					}
					Collections.sort(insidePeople);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			semaphore.release();
		}
	}
}
