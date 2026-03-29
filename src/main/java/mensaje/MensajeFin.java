package mensaje;

public class MensajeFin extends Mensaje {
	private String username;

	public MensajeFin(String username) {
		super("request_end");
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

}
