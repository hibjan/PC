package cliente;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import mensaje.Mensaje;
import mensaje.MensajeConsulta;
import mensaje.MensajeDescarga;
import mensaje.MensajeFin;
import mensaje.MensajeRegistroLibro;
import mensaje.MensajeRegistroUsuario;
import mensaje.MensajeUsuarios;

public class OyenteServidor extends Thread {
	
	private Socket s;
	private String username;
	private String path;
	private int port;
	private Mensaje request;
	private Mensaje reply;
	private ObjectOutputStream outc;
	private ObjectInputStream inc;
	
	public OyenteServidor(Socket s, String username, String path, int port) {
        this.s = s;
        this.username = username;
        this.path = path;
        this.port = port;
        this.request = null;
		this.reply = null;
		this.outc = null;
		this.inc = null;
    }
	
	public void run() {
		try {
			outc = new ObjectOutputStream(s.getOutputStream());
			inc = new ObjectInputStream(s.getInputStream());
			
			// request MensajePuerto
			// reply MensajePuertoRespuesta
			// puerto.escribir(reply.getPuerto()) -> Sem / Mon -> release
			
	        request = new MensajeRegistroUsuario(username, port);
	        outc.writeObject(request);
	        outc.flush();
	        
	        reply = (Mensaje) inc.readObject();

            refreshDirectory();
            
            Scanner sc = new Scanner(System.in);
            System.out.print("Introduce el comando: ");
	        String command = sc.nextLine();
	        
	        while(!command.equals("end")) {
	        	switch(command) {
	        		case "query":
	        			request = new MensajeConsulta();
		    	        outc.writeObject(request);
		    	        outc.flush();
		    	        
		    	        reply = (Mensaje) inc.readObject();
		    	        
		    	        String[] books = reply.getBooks();
		    	        for(String book : books) {
		    	        	System.out.println(book);
		    	        }
	        		break;
	        		case "user":
	        			System.out.print("Introduce el libro: ");
		    	        String bookName = sc.nextLine();
		    	        
		    	        request = new MensajeUsuarios(bookName);
		    	        outc.writeObject(request);
		    	        outc.flush();
		    	        
		    	        reply = (Mensaje) inc.readObject();
		    	        
		    	        String[] users = reply.getUsers();
		    	        for(String user : users) {
		    	        	System.out.println(user);
		    	        }
	        		break;
	        		case "request":
	        			System.out.print("Introduce el usuario: ");
		    	        String user = sc.nextLine();
		    	        
		    	        request = new MensajeDescarga(user);
		    	        outc.writeObject(request);
		    	        outc.flush();
		    	        
		    	        reply = (Mensaje) inc.readObject();
		    	        
		    	        System.out.print("Introduce el libro: ");
		    	        String book = sc.nextLine();
		    	        
		    	        Socket p2p = new Socket(reply.getIP(), reply.getPort());
		    	        ClienteDescargaP2P cdp2p = new ClienteDescargaP2P(p2p, book, path, username);
		    			cdp2p.start();
	        		break;
	        		case "refresh":
	        			// TODO if file is erased?
	        			refreshDirectory();
	        		break;
	        		default:
	        		break;
	        	}
	        		
	        	System.out.print("Introduce el comando: ");
		        command = sc.nextLine();
	        }
	        
	        request = new MensajeFin(username);
	        outc.writeObject(request);
	        outc.flush();
	        
	        reply = (Mensaje) inc.readObject();
            
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
	
	private void refreshDirectory() {
		//TODO recursive search of directory
		
		try {
			File directory = new File(path);

	        if (directory.isDirectory()) {
	            File[] files = directory.listFiles();
	            if (files != null) {
	                for (File file : files) {
	                	ClienteFile cd = new ClienteFile(username, path, file.getName());
             	        cd.run();
	                }
	            } 
	            else {
	                System.out.println("The directory is empty or cannot be read.");
	            }
	        } 
	        else {
	            System.out.println("The path is not a valid directory.");
	        }
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
}

