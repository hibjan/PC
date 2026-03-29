package conc;

import java.util.concurrent.Semaphore;

public class PCSem {
	
	private Semaphore empty;
	private Semaphore full;
	private Semaphore mutexP;
	private Semaphore mutexC;
	
	public PCSem(int SIZE) {
		this.empty = new Semaphore(SIZE);
		this.full = new Semaphore(0);
		this.mutexP = new Semaphore(1);
		this.mutexC = new Semaphore(1);
	}
	
	public void requestDeposit() {
		try {
			empty.acquire();
			mutexP.acquire();
		} 
		catch (InterruptedException e) {e.printStackTrace();}
	}
	
	public void releaseDeposit() {
		mutexP.release();
		full.release();
	}

	
	public void requestExtract() {
		try {
			full.acquire();
			mutexC.acquire();
		} 
		catch (InterruptedException e) {e.printStackTrace();}
	}
	
	public void releaseExtract() {
		mutexC.release();
		empty.release();
	}
}
