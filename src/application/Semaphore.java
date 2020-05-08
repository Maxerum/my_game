package application;

public class Semaphore {
	public static boolean isReady = false;

	public static synchronized void set() {

		isReady = true;

	}
	
	public static synchronized void reset() {

		isReady = false;

	}
}
