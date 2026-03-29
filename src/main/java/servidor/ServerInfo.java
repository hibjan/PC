package servidor;

import conc.LockBakery;
import conc.LockRompeEmpate;
import conc.LockTicket;

public class ServerInfo {

	private int count_query;
	private int count_connected;
	private int count_transfer;
	private LockBakery lock1;
	private LockRompeEmpate lock2;
	private LockTicket lock3;
	
	public ServerInfo(int NUM_IDS){
		this.count_query = 0;
		this.count_connected = 0;
		this.count_transfer = 0;
		
		this.lock1 = new LockBakery(NUM_IDS);
		this.lock2 = new LockRompeEmpate(NUM_IDS);
		this.lock3 = new LockTicket();
	}
	public void query(int id) {
		this.lock1.takeLock(id);
		
		this.count_query++;
		
		this.lock1.releaseLock(id);
	}
	
	public void connected() {
		lock3.takeLock();
		
		this.count_connected++;
		
		lock3.releaseLock();
	}
	
	public void disconnected() {
		lock3.takeLock();
		
		this.count_connected--;
		
		lock3.releaseLock();
	}
	
	public void transfer(int id) {
		this.lock2.takeLock(id);
		
		this.count_transfer++;
		
		this.lock2.releaseLock(id);
	}
}
