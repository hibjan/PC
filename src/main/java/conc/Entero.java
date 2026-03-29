package conc;

public class Entero {
	private volatile int num;
	
	public Entero(int num) {
		this.num = num;
	}

	public int get() {
		return num;
	}
	public void set(int n) {
		this.num = n;
	}

	public void incrementar() {
		this.num++;
	}
	public void decrementar() {
		this.num--;
	}
	
}
