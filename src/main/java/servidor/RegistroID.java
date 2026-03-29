package servidor;

import java.util.HashSet;
import java.util.Set;

import conc.RWMon;

public class RegistroID {
	
	private Set<Integer> ids;
	private RWMon mutex;
	
	public RegistroID() {
		this.ids = new HashSet<Integer>();
		this.mutex = new RWMon();
	}
	
	public void takeID(int id) {
		mutex.requestWrite();
		
		ids.add(id);
	
		mutex.releaseWrite();
	}
	
	public void releaseID(int id) {
		mutex.requestWrite();
		
		ids.remove(id);
	
		mutex.releaseWrite();
	}
	
	public boolean checkID(int id) {
		mutex.requestRead();
		
		boolean isUsed = ids.contains(id);
		
		mutex.releaseRead();
		
		return isUsed;
	}

}
