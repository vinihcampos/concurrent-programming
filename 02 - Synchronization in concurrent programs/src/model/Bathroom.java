package model;

public class Bathroom {
	private Integer capacity;
	private Integer amountOfPeople;
	
	public Bathroom(Integer capacity) {
		this.capacity = capacity;
		amountOfPeople = 0;
	}
	
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	public Integer getAmountOfPeople() {
		return amountOfPeople;
	}
	public void setAmountOfPeople(Integer amountOfPeople) {
		this.amountOfPeople = amountOfPeople;
	}
}
