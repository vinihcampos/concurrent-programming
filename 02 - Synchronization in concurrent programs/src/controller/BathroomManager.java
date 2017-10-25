package controller;

import model.Person;

public interface BathroomManager {
	
	public Boolean isEmpty();
	
	public Integer getTimeSleep();
	
	public void add(Person p);
	
	public void remove();
	
}
