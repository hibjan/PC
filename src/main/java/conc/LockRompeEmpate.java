package conc;

public class LockRompeEmpate {
	
	private Entero in[];
	private Entero last[];
	
	public LockRompeEmpate(int N){
		this.in = new Entero[N];
		this.last = new Entero[N];
		for(int i = 0; i < N; i++) {
			this.in[i] = new Entero(-1);
			this.last[i] = new Entero(-1);
		}
	}
	
	public void takeLock(int id) {
		for(int j = 0; j < this.in.length; j++) {
			this.in[id].set(j); this.last[j].set(id);
			for(int k = 0; k < this.in.length; k++) {
				if(id != k) {
					while(this.in[k].get() >= this.in[id].get() 
							&& this.last[j].get() == id) {}
				}
			}
		}
		
	}
	public void releaseLock(int id) {
		in[id].set(-1);
	}
}
