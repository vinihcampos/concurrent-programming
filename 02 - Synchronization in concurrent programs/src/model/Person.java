package model;

public class Person implements Comparable<Person>{
	private Integer id;
	private Boolean gender;
	private Integer enterTime;
	private Integer time;
	
	public Person(Integer id, Boolean gender, Integer time) {
		this.id = id;
		this.gender = gender;
		this.time = time;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Boolean getGender() {
		return gender;
	}
	public void setGender(Boolean gender) {
		this.gender = gender;
	}
	public Integer getEnterTime() {
		return enterTime;
	}
	public void setEnterTime(Integer enterTime) {
		this.enterTime = enterTime;
	}

	public Integer getTime() {
		return time;
	}
	public void setTime(Integer time) {
		this.time = time;
	}
	
	@Override
	public String toString() {
		if(gender != null) {
			return gender ? "man-" + String.valueOf(id) : "woman-" + String.valueOf(id);
		}else {
			return "";
		}
	}

	@Override
	public int compareTo(Person o) {
		return (this.enterTime + this.time)  - (o.getEnterTime() + o.getTime());
	}
}
