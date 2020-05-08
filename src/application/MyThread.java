package application;

import application.Algorithms;

public class MyThread implements Runnable {

	// write to the file last information
//	public void loadLastGame() {
//
//	}
//
//	public void read_conservation_from_file() {
//
//	}
	String conservationString;

	Thread thread;
	boolean running = false;

	public MyThread() {
		this.thread = new Thread(this, "Server");
		this.thread.start();
	}

	@Override
	public void run() {
		while (!this.thread.isInterrupted()) {
			if (this.running == true) {
				try {
					Thread.sleep(5000);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				this.conservationString = Algorithms.getSaveInfo();
				
				//System.out.println(conservationString );
				// this.conservationString =
				Semaphore.set();
				this.running = false;
			}
		}

	}
	
	public String getString() {
		return this.conservationString;
	}
	
	public void generateInfo() {
		this.running = true;
	}
	
	public Thread getThread() {
		return this.thread;
	}

}
