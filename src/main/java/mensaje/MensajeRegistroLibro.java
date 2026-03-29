package mensaje;

public class MensajeRegistroLibro extends Mensaje {
	
	private String book;
	private String user;

	public MensajeRegistroLibro(String book, String user) {
		super("request_register_book");
		this.book = book;
		this.user = user;
	}

	public String getBook() {
		return book;
	}

	public String getUser() {
		return user;
	}
}
