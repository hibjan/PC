package conc;
import java.util.concurrent.atomic.AtomicInteger;

public class LockTicket {
	
	private AtomicInteger number;
	private Entero next;
	
	public LockTicket(){
		this.number = new AtomicInteger(0);
		this.next = new Entero(0);
	}
	
	public void takeLock() {
		int turn = number.getAndAdd(1); 
		while(turn != this.next.get()) {}
	}
	public void releaseLock() {
		this.next.incrementar();
	}
}
