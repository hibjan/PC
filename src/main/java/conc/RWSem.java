package conc;

import java.util.concurrent.Semaphore;

public class RWSem {
	
	//Concurrent
	private int nr;
	private int nw;
	private Semaphore e;
	private Semaphore r;
	private Semaphore w;
	private int dr;
	private int dw;
	
	public RWSem() {
		this.nr = 0;
		this.nw = 0;
		this.e = new Semaphore(1);
		this.r = new Semaphore(0);
		this.w = new Semaphore(0);
		this.dr = 0;
		this.dw = 0;
		
	}
	
	public void requestWrite() {
		try {
			e.acquire();
			if(this.nw > 0 || this.nr > 0) {
				this.dw++;
				try {
					e.release();
					w.acquire();
				}
				catch (InterruptedException e) {e.printStackTrace();}
			}
			this.nw++;
			e.release();
		}
		catch (InterruptedException e) {e.printStackTrace();}
	}
	
	public void releaseWrite() {
		try {
			e.acquire();
			this.nw--;
			
			if(this.dw > 0) {
				this.dw--;
				w.release();
			}
			else if(this.dr > 0) {
				this.dr--;
				r.release();
			}
			else {
				e.release();
			}
		}
		catch (InterruptedException e) {e.printStackTrace();}
	}
	
	public void requestRead() {
		try {
			e.acquire();
			if(this.nw > 0) {
				this.dr++;
				try {
					e.release();
					r.acquire();
				}
				catch (InterruptedException e) {e.printStackTrace();}
			}
			this.nr++;
			if(this.dr > 0) {
				this.dr--;
				r.release();
			}
			else {
				e.release();
			}
			
		}
		catch (InterruptedException e) {e.printStackTrace();}
	}
	
	public void releaseRead() {
		try {
			e.acquire();
			this.nr--;
			if(this.nr == 0 && this.dw > 0) {
				this.dw--;
				w.release();
			}
			else {
				e.release();
			}
		}
		catch (InterruptedException e) {e.printStackTrace();}
	}
}
