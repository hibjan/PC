package mensaje;

public class MensajeDescarga extends Mensaje {
	private String user;

	public MensajeDescarga(String user) {
		super("request_ip");
		this.user = user;
	}

	public String getUser() {
		return user;
	}

}
