package mensaje;

public class MensajeEnvio extends Mensaje {
	private String filename;

	public MensajeEnvio(String filename) {
		super("request_file");
		this.filename = filename;
	}

	public String getFilename() {
		return filename;
	}

}
