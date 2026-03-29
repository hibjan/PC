package mensaje;

public class MensajeEnvioRespuesta extends Mensaje {
	private byte[] fileBytes;
	private String status;

	public MensajeEnvioRespuesta(String status, byte[] fileBytes) {
		super("reply_file");
		this.status = status;
		this.fileBytes = fileBytes;
	}

	public byte[] getFileBytes() {
		return fileBytes;
	}

	public String getStatus() {
		return status;
	}

}
