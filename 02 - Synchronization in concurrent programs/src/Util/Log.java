package Util;

import model.Person;

public class Log {
	
	public static void entrance(Person p, Integer amount, Integer timeElapsed) {
		System.out.println("Time: " + timeElapsed + "s.");
		System.out.println("The " + p + " entered into bathroom.");
		printAmountOfPeople(amount);
		System.out.println("---------------------------");
	}
	
	public static void exit(Person p, Integer amount, Integer timeElapsed) {
		System.out.println("Time: " + timeElapsed + "s.");
		System.out.println("The " + p + " left the bathroom.");
		printAmountOfPeople(amount);
		System.out.println("---------------------------");
	}
	
	private static void printAmountOfPeople(Integer amount) {
		if(amount == 0) {
			System.out.println("There is nobody in the bathroom.");
		}else if(amount == 1) {
			System.out.println("There is " + amount + " person in the bathroom.");
		}else {
			System.out.println("There are " + amount + " people in the bathroom.");
		}
	}
}
