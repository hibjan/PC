package mensaje;

import java.util.List;
import java.util.Map;

public class MensajeUsuariosRespuesta extends Mensaje {
	private String[] users;
	private String status;

	public MensajeUsuariosRespuesta(String status, String[] users) {
		super("reply_users");
		this.status = status;
		this.users = users;
	}

	public String[] getUsers() {
		return users;
	}

	public String getStatus() {
		return status;
	}

}
