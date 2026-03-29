package conc;

public class LockBakery {
	
	private Entero turn[];
	
	public LockBakery(int N){
		this.turn = new Entero[N];
		for(int i = 0; i < N; i++) {
			this.turn[i] = new Entero(-1);
		}
	}
	
	public void takeLock(int id) {
		this.turn[id].set(0); 
		this.turn[id].set(getMax() + 1); 
		for(int j = 0; j < this.turn.length; j++) {
			if(id != j) {
				while(this.turn[j].get() != -1 
						&& (this.turn[id].get() > this.turn[j].get() 
							|| 	(this.turn[id].get() == this.turn[j].get()
								&& id > j))) {}
			}
		}
	}
	public void releaseLock(int id) {
		this.turn[id].set(-1);
	}
	
	private int getMax() {
		int max = -1;
		for(int i = 0; i < this.turn.length; i++) {
			if(this.turn[i].get() > max) {
				max = this.turn[i].get();
			}
		}
		return max;
	}
}
