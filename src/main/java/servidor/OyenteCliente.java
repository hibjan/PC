package servidor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import mensaje.Mensaje;
import mensaje.MensajeConsultaRespuesta;
import mensaje.MensajeDescargaRespuesta;
import mensaje.MensajeFinRespuesta;
import mensaje.MensajeRegistroLibroRespuesta;
import mensaje.MensajeRegistroUsuarioRespuesta;
import mensaje.MensajeUsuariosRespuesta;

public class OyenteCliente extends Thread {
	
	private Socket s;
	private Biblioteca library;
	private BancoUsuarios users;
	private ServerInfo server;
	private RegistroID registro;
	private int id;
	private Mensaje request;
	private Mensaje reply;
	private ObjectInputStream ins;
	private ObjectOutputStream outs;
	
	public OyenteCliente(Socket s, Biblioteca library, BancoUsuarios users, ServerInfo server, AlmacenIDs almacen, RegistroID registro) {
        this.s = s;
        this.library = library;
        this.users = users;
        this.server = server;
        this.registro = registro;
        request = null;
        reply = null;
        ins = null;
        outs = null;
        this.id = almacen.extraer();
        this.registro.takeID(this.id);
    }
	
	public void run() {
		try {
			server.connected();
			
			ins = new ObjectInputStream(s.getInputStream());
			outs = new ObjectOutputStream(s.getOutputStream());
			
			// Wait for request
			request = (Mensaje) ins.readObject();
			
			while(!request.getTipo().equals("request_end")) {
				
				switch(request.getTipo()) {
					case "request_register_user": //register a user in DB
						users.escribir(request.getUsername(), s, request.getPort());
						
						System.out.println("Client connected: " + request.getUsername());
						
						reply = new MensajeRegistroUsuarioRespuesta("OK");
						break;
					case "request_register_book": //register a user in DB
						library.escribir(request.getBook(), request.getUser());
						
						reply = new MensajeRegistroLibroRespuesta("OK");
						break;
					case "request_query": //which books are available
						String[] books = library.read_books();
						server.query(this.id);
						
						reply = new MensajeConsultaRespuesta("OK", books);
						break;
					case "request_users": //which users have a particular book
						String[] users_book = library.read_users(request.getBook());
						
						reply = new MensajeUsuariosRespuesta("OK", users_book);
						break;
					case "request_ip":
						String IP = users.leer_ip(request.getUser());
						Integer port = users.leer_port(request.getUser());
						server.transfer(this.id);
						
						reply = new MensajeDescargaRespuesta("OK", IP, port);
						break;
					default:
						break;
				}
				
				outs.writeObject(reply);
	            outs.flush();
	            
				// Wait for request
				request = (Mensaje) ins.readObject();
			}
			
			//deregister a user in DB
			server.disconnected();
			users.borrar(request.getUsername());
			library.borrar(request.getUsername());
			registro.releaseID(this.id);
			
			System.out.println("Client disconnected: " + request.getUsername());
			
			reply = new MensajeFinRespuesta("OK");
			outs.writeObject(reply);
            outs.flush();
			
		} 
		catch (Exception e) {e.printStackTrace();}
		finally {
			try {
				if(ins != null) ins.close();
				if(outs != null) outs.close();
				if(s != null && !s.isClosed()) s.close();
			}
			catch(Exception e) {e.printStackTrace();}
		}
	}
}
