package thread;

import java.util.Random;

import controller.BathroomManager;
import model.Person;

public class JobIn extends Thread{
	private BathroomManager baManager;
	private Integer id;
	private Random randomGenerator;
	private Integer timeLimit;
	private Thread jobOut;
	
	public JobIn(BathroomManager baManager, Integer timeLimit) {
		super();
		this.baManager = baManager;
		this.id = 0;
		this.timeLimit = timeLimit;
		this.randomGenerator = new Random();
		this.jobOut = new JobOut(baManager);
	}
	
	@Override
	public void run() {
		while(true) {
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
	}
	
	private Person generatePerson() {
		int time = randomGenerator.nextInt(timeLimit) + 1;
		boolean gender = randomGenerator.nextBoolean();		
		return new Person(id++, gender, time);
	}
}
