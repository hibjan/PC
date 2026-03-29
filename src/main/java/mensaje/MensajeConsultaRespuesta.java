package mensaje;

import java.util.List;

public class MensajeConsultaRespuesta extends Mensaje {
	private String[] books;
	private String status;

	public MensajeConsultaRespuesta(String status, String[] books) {
		super("reply_query");
		this.status = status;
		this.books = books;
	}

	public String[] getBooks() {
		return books;
	}

	public String getStatus() {
		return status;
	}

}
