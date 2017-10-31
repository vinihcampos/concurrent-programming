package view;

import java.util.Scanner;

import business.controller.BathroomManager;
import business.controller.BathroomManagerSemaphore;
import business.threads.JobIn;

public class MainSemaphore {
	public static void main(String args[]){
		BathroomManager bathroomManager;
		Thread jobIn;
		int maxCapacity;
		int timeLimit;
		int maxFlow;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Inform the max capacity of bathroom: ");
		maxCapacity = sc.nextInt();
		
		System.out.println("Inform the number of people to be used in experiment: ");
		maxFlow = sc.nextInt();
		
		System.out.println("Inform the time limit of each person in bathroom: ");
		timeLimit = sc.nextInt();
		
		sc.close();
		
		bathroomManager = new BathroomManagerSemaphore(maxCapacity);
		jobIn = new JobIn(bathroomManager, timeLimit, maxFlow);
		jobIn.start();		
	}
}
