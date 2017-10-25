package thread;

import controller.BathroomManager;
import controller.BathroomManagerSemaphore;

public class JobOut extends Thread{
	private BathroomManager baManager;
	
	public JobOut(BathroomManager bathroomManager) {
		this.baManager = bathroomManager;
	}
	
	@Override
	public void run() {
		try {
			while(!baManager.isEmpty()) {
				Thread.sleep(baManager.getTimeSleep() * 1000);
				baManager.remove();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
