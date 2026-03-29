package mensaje;

public class MensajeRegistroUsuarioRespuesta extends Mensaje {
	
	private String status;

	public MensajeRegistroUsuarioRespuesta(String status) {
		super("reply_register");
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
