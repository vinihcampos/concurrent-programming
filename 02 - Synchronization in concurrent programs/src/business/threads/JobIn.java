package business.threads;

import java.util.Random;

import business.controller.BathroomManager;
import model.Person;

public class JobIn extends Thread{
	private BathroomManager baManager;
	private Integer id;
	private Random randomGenerator;
	private Integer timeLimit;
	private Integer maxFlow;
	private Thread jobOut;
	
	public JobIn(BathroomManager bathroomManager, Integer timeLimit, Integer maxFlow) {
		super();
		this.baManager = bathroomManager;
		this.id = 1;
		this.timeLimit = timeLimit;
		this.randomGenerator = new Random();
		this.jobOut = new JobOut(bathroomManager);
		this.maxFlow = maxFlow;
	}
	
	@Override
	public void run() {
		while(maxFlow > 0) {
			maxFlow--;
			try {
				if(baManager.isEmpty()) {
					baManager.add(generatePerson());
					if(!jobOut.isAlive()) {
						jobOut.join();
						jobOut = new JobOut(baManager);
						jobOut.start();
					}
				}else {
					baManager.add(generatePerson());
				}
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		if(jobOut.isAlive()) {
			try {
				jobOut.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private Person generatePerson() {
		int time = randomGenerator.nextInt(timeLimit) + 1;
		boolean gender = randomGenerator.nextBoolean();		
		return new Person(id++, gender, time);
	}
}
