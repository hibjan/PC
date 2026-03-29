package cliente;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import mensaje.Mensaje;
import mensaje.MensajeRegistroLibro;

public class ClienteFile extends Thread {

	private Socket s;
	private String username;
	private String path;
	private String filename;
	private Mensaje request;
	private Mensaje reply;
	private ObjectOutputStream outc;
	private ObjectInputStream inc;
	
	public ClienteFile(String username, String path, String filename) {
        this.username = username;
        this.path = path;
        this.filename = filename;
        this.request = null;
		this.reply = null;
		this.outc = null;
		this.inc = null;
		this.s = null;
	}
	
	public void run() {
		try {
			s = new Socket("localhost", 32010);
			outc = new ObjectOutputStream(s.getOutputStream());
			inc = new ObjectInputStream(s.getInputStream());
			
			File file = new File(path + "\\" + filename);
			
            if (file.isFile()) {
                request = new MensajeRegistroLibro(file.getName(), username);
    	        outc.writeObject(request);
    	        outc.flush();
    	        
    	        reply = (Mensaje) inc.readObject();
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
