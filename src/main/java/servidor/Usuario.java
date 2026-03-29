package servidor;

import java.net.Socket;
import java.util.Set;

public class Usuario {
	private Socket socket;
	private int port;

	public Usuario(Socket socket, int port) {
		this.socket = socket;
		this.port = port;
	}

	public Socket getSocket() {
		return this.socket;
	}

	public int getPort() {
		return port;
	}
	
}
