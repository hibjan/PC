package servidor;

import conc.Entero;

public class ProdID extends Thread {
	
	private AlmacenIDs almacen;
	private RegistroID registro;
	private Entero done;
	
	public ProdID(AlmacenIDs almacen, RegistroID registro, Entero done) {
		this.almacen = almacen;
		this.registro = registro;
		this.done = done;
	}
	
	public void run() {
		int count = 0;
		while(done.get() != 1) {
			if(!registro.checkID(count)) {
				almacen.almacenar(count);
			}
			else {
				count++;
			}
		}
	}

}
