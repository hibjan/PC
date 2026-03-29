package mensaje;

public class MensajeRegistroLibroRespuesta extends Mensaje {
	private String status;

	public MensajeRegistroLibroRespuesta(String status) {
		super("reply_register_book");
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
