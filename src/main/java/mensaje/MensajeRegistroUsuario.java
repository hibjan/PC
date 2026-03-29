package mensaje;

public class MensajeRegistroUsuario extends Mensaje {
	
	private String username;
    private String IP;
    private int port;

	public MensajeRegistroUsuario(String username, int port) {
		super("request_register_user");
		this.username = username;
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public int getPort() {
		return port;
	}

}
