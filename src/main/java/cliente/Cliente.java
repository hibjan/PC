package cliente;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) {
		Socket s = null;
		ServerSocket sp2p = null;
		Scanner scanner = null;
		try {
			s = new Socket("localhost", 32010);
			
			// TODO cambiar a request to server
			int port = 32011;
			boolean found = false;
			while(!found) {
				try {
					sp2p = new ServerSocket(port);
					found = true;
				}
				catch(Exception e) {
					port++;
				}
			}
			
			scanner = new Scanner(System.in);
	        System.out.print("Introduce el nombre de usuario: ");
	        String username = scanner.nextLine();
	        
	        System.out.print("Introduce la ruta del directorio: ");
            String path = scanner.nextLine();
			
			OyenteServidor os = new OyenteServidor(s, username, path, port);
			os.start();
			
			//sp2p = new ServerSocket(puerto.leer())
			
			sp2p.setSoTimeout(5000);
			boolean online = true;
			while(online) {
				try {
					Socket sc = sp2p.accept();
					
					OyenteClienteP2P ocp2p = new OyenteClienteP2P(sc, path);
					ocp2p.start();
				}
				catch(Exception e){
					if(!os.isAlive()) {
						online = false;
					}
				}
			}
		} 
		catch (Exception e) { e.printStackTrace();}
		finally {
			try {
				if(sp2p != null && !sp2p.isClosed()) sp2p.close();
				if(s != null && !s.isClosed()) s.close();
				if(scanner != null) scanner.close();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
