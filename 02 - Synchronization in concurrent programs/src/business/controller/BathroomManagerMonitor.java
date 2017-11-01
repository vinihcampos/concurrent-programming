package business.controller;

import model.Person;

public class BathroomManagerMonitor extends BathroomManager{
	
	public BathroomManagerMonitor(Integer capacity) {
		super(capacity);
	}
	
	@Override
	public synchronized Boolean isEmpty() {
		return insidePeople.isEmpty();
	}

	@Override
	public synchronized Integer getTimeSleep() {
		return insidePeople.isEmpty() ? null : insidePeople.get(0).getTime();
	}

	@Override
	public synchronized void add(Person p) {
		auxiliarAdd(p);	
	}

	@Override
	public synchronized void remove() {
		auxiliarRemove();
	}
}
