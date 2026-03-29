package servidor;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import conc.RWMon;

public class BancoUsuarios {
	
	//User -> IP Address
	private Map<String, Usuario> users;
	private RWMon mutex;
	
	public BancoUsuarios() {
		this.users = new HashMap<String, Usuario>();
		this.mutex = new RWMon();
	}
	
	public void escribir(String user, Socket socket, int port) {
		mutex.requestWrite();
		
		users.put(user, new Usuario(socket, port));
		
		mutex.releaseWrite();
	}
	
	public void borrar(String username) {
		mutex.requestWrite();
		
		users.remove(username);
		
		mutex.releaseWrite();
	}
	
	public String leer_ip(String user) {
		mutex.requestRead();
		
		String IP = users.get(user).getSocket().getInetAddress().getHostAddress();
		
		mutex.releaseRead();
		
		return IP;
	}
	
	public int leer_port(String user) {
		mutex.requestRead();
		
		int port = users.get(user).getPort();
		
		mutex.releaseRead();
		
		return port;
	}

}
