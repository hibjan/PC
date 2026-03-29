package mensaje;

public class MensajeDescargaRespuesta extends Mensaje {
	private String status;
	private String IP;
	private int port;

	public MensajeDescargaRespuesta(String status, String IP, int port) {
		super("reply_ip");
		this.status = status;
		this.IP = IP;
		this.port = port;
	}

	public String getStatus() {
		return status;
	}

	public String getIP() {
		return IP;
	}

	public int getPort() {
		return port;
	}

}
