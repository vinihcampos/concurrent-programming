package business.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import business.util.Log;
import model.Person;

public abstract class BathroomManager {
	
	protected volatile Integer capacity;
	protected volatile List<Person> insidePeople;
	protected volatile List<Person> waitingPeople;
	protected volatile Integer enterTime;
	protected volatile Integer exitTime;
	
	public BathroomManager(Integer capacity) {
		this.capacity = capacity;
		this.insidePeople = new ArrayList<>();
		this.waitingPeople = new ArrayList<>();
		this.enterTime = 0;
		this.exitTime = 0;
	}
	
	public Boolean isEmpty() { return null; }
	
	public Integer getTimeSleep() { return null; }
	
	public void add(Person p) { }
	
	public void remove() { }
	
	protected void auxiliarAdd(Person p) {
		if(insidePeople.isEmpty()) {
			p.setEnterTime(enterTime);
			insidePeople.add(p);
			Log.entrance(p, insidePeople.size(), Math.max(enterTime, exitTime));
		}else {
			if(insidePeople.get(0).getGender() == p.getGender() && 
			   insidePeople.size() < capacity && waitingPeople.isEmpty()) {
				p.setEnterTime(enterTime);
				insidePeople.add(p);
				Log.entrance(p, insidePeople.size(), Math.max(enterTime, exitTime));
			}else {
				waitingPeople.add(p);
			}
		}
		enterTime++;
	}
	
	protected void auxiliarRemove() {
		if(!insidePeople.isEmpty()) {

			Collections.sort(insidePeople);
			
			exitTime++;
			Person auxP;
			
			while(!insidePeople.isEmpty() && (insidePeople.get(0).getEnterTime() + insidePeople.get(0).getTime()) == exitTime) {
				auxP = insidePeople.remove(0);
				Log.exit(auxP, insidePeople.size(),exitTime);					
			}
			
			if(insidePeople.size() < capacity && !waitingPeople.isEmpty()) {
				
				Boolean gender = !insidePeople.isEmpty() ? insidePeople.get(0).getGender() : waitingPeople.get(0).getGender();
				
				while(!waitingPeople.isEmpty() && 
					  waitingPeople.get(0).getGender() == gender &&
					  insidePeople.size() < capacity) {
					insidePeople.add(waitingPeople.get(0));
					insidePeople.get(insidePeople.size() - 1).setEnterTime(exitTime);
					auxP = waitingPeople.remove(0);
					Log.entrance(auxP, insidePeople.size(),exitTime);
				}
			}
		}
	}
	
}
