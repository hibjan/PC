package mensaje;

public class MensajeUsuarios extends Mensaje {
	private String book_name;

	public MensajeUsuarios(String book_name) {
		super("request_users");
		this.book_name = book_name;
	}

	public String getBook() {
		return book_name;
	}

}
