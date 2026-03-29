package cliente;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Files;

import org.json.simple.JSONObject;

import mensaje.Mensaje;
import mensaje.MensajeEnvioRespuesta;
import mensaje.MensajeFinRespuesta;
import mensaje.MensajeRegistroLibroRespuesta;
import mensaje.MensajeRegistroUsuarioRespuesta;

public class OyenteClienteP2P extends Thread {
	
	private Socket sc;
	private String path;
	private Mensaje request;
	private Mensaje reply;
	private ObjectInputStream inc;
	private ObjectOutputStream outc;

	public OyenteClienteP2P(Socket sc, String path) {
		this.sc = sc;
		this.path = path;
		this.request = null;
		this.reply = null;
		this.inc = null;
		this.outc = null;
	}
	
	public void run() {
		try {
			inc = new ObjectInputStream(sc.getInputStream());
			outc = new ObjectOutputStream(sc.getOutputStream());
			
			// Wait for request
			request = (Mensaje) inc.readObject();
						
			File f = new File(path + "\\" + request.getFilename());
			if(f.exists() && f.isFile()) {
				
				byte[] fileBytes = Files.readAllBytes(f.toPath());

                reply = new MensajeEnvioRespuesta("OK", fileBytes);
    			
			}
			else {
				reply = new MensajeEnvioRespuesta("ERROR", null);
			}
			outc.writeObject(reply);
			outc.flush();
		} 
		catch (Exception e) {e.printStackTrace();}
		finally {
			try {
				if(inc != null) inc.close();
				if(outc != null) outc.close();
				if(sc != null && !sc.isClosed()) sc.close();
			}
			catch(Exception e) {e.printStackTrace();}
		}
	}

}
