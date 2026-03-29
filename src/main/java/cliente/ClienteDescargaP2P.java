package cliente;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import mensaje.Mensaje;
import mensaje.MensajeEnvio;
import mensaje.MensajeFin;
import mensaje.MensajeRegistroLibro;
import mensaje.MensajeRegistroUsuario;

public class ClienteDescargaP2P extends Thread {
	
	private Socket s;
	private String filename;
	private String path;
	private String username;
	private Mensaje request;
	private Mensaje reply;
	private ObjectOutputStream outc;
	private ObjectInputStream inc;
	
	public ClienteDescargaP2P(Socket s, String filename, String path, String username){
		this.s = s;
		this.filename = filename;
		this.path = path;
		this.username = username;
        this.request = null;
		this.reply = null;
		this.outc = null;
		this.inc = null;
	}
	
	public void run() {
		try {
			outc = new ObjectOutputStream(s.getOutputStream());
			inc = new ObjectInputStream(s.getInputStream());
	                    
	        request = new MensajeEnvio(filename);
	        outc.writeObject(request);
	        outc.flush();
	        
	        reply = (Mensaje) inc.readObject();
	        
	        if(reply.getStatus().equals("OK")) {
	        	byte[] fileBytes = reply.getFileBytes();
	 	        
	 		    Files.write(Paths.get(path + "\\" + filename), fileBytes);
	 		    
	 		    ClienteFile cd = new ClienteFile(username, path, filename);
	 	        cd.run();
	        }
		    
		} 
		catch (Exception e) {e.printStackTrace();}
		finally {
			try {
				if (outc != null) outc.close();
				if(inc != null) inc.close();
				if(s != null && !s.isClosed()) s.close();
			}
			catch(Exception e) {e.printStackTrace();}
		}
	}
}
