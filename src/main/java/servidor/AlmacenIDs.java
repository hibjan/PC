package servidor;

import conc.PCSem;

public class AlmacenIDs {
		
	private int SIZE;
	private int buf[];
	private int ini;
	private int fin;
	
	private PCSem mutex;
	
	public AlmacenIDs(int SIZE) {
		this.SIZE = SIZE;
		this.buf = new int[SIZE];
		this.ini = 0;
		this.fin = 0;
		this.mutex = new PCSem(SIZE);
	}

	public void almacenar(int producto) {
		mutex.requestDeposit();
		
		try {
			this.buf[fin] = producto;
			fin = (fin + 1) % SIZE;
		}
		finally {
			mutex.releaseDeposit();
		}
	}
	
	public int extraer() {
		mutex.requestExtract();
		
		int resul = -1;
		try {
			resul = this.buf[ini];
			ini = (ini + 1) % SIZE;
		}
		finally {
			mutex.releaseExtract();
		}
		
		return resul;
	}

	
}
