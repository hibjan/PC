package servidor;

import java.io.IOException;
import java.net.*;

import conc.Entero;

public class Servidor {
	
	private final static int NUM_PRODS = 4;
	private final static int NUM_IDS = 100;
	
	public static void main(String[] args) {
		ServerSocket ss = null;
		Entero done = new Entero(0);
		try {
			ss = new ServerSocket(32010, 50, InetAddress.getByName("0.0.0.0"));
			
			Biblioteca library = new Biblioteca();
			BancoUsuarios users = new BancoUsuarios();
			ServerInfo server = new ServerInfo(NUM_IDS);
			AlmacenIDs almacen = new AlmacenIDs(NUM_IDS);
			RegistroID registro = new RegistroID();
			
			
			for(int i = 0; i < NUM_PRODS; i++) {
				Thread p = new ProdID(almacen, registro, done);
				p.start();
			}
			
			System.out.println("Server started");
			
			while(true) {
				Socket s = ss.accept();
				
				OyenteCliente c = new OyenteCliente(s, library, users, server, almacen, registro);
				c.start();
			}
		} 
		catch (Exception e) { e.printStackTrace();}
		finally {
			done.set(1);
			try {
				if(ss != null && !ss.isClosed()) ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
