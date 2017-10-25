package view;

import java.util.Scanner;

import controller.BathroomManager;
import model.Person;
import thread.JobIn;

public class Main {
	public static void main(String args[]){
		BathroomManager bathroomManager;
		Thread jobIn;
		int maxCapacity;
		int timeLimit;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Inform the max capacity of bathroom: ");
		maxCapacity = sc.nextInt();
		
		System.out.println("Inform the time limit of each person in bathroom: ");
		timeLimit = sc.nextInt();
		
		sc.close();
		
		bathroomManager = new BathroomManager(maxCapacity);
		jobIn = new JobIn(bathroomManager, timeLimit);
		jobIn.start();		
	}
}
