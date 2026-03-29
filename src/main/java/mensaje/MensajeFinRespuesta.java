package mensaje;

public class MensajeFinRespuesta extends Mensaje {
	private String status;

	public MensajeFinRespuesta(String status) {
		super("reply_end");
		
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
