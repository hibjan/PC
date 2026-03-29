package mensaje;

import java.io.Serializable;
import org.json.simple.JSONObject;

public abstract class Mensaje implements Serializable {

    private String tipo;
    
    public Mensaje(String tipo) {
    	this.tipo = tipo;
    }
    
    public String getTipo() {
    	return this.tipo;
    }

	public String getUsername() {
		throw new UnsupportedOperationException("Not valid");
	}

	public String getIP() {
		throw new UnsupportedOperationException("Not valid");
	}

	public String getBook() {
		throw new UnsupportedOperationException("Not valid");
	}

	public String getUser() {
		throw new UnsupportedOperationException("Not valid");
	}

	public String getStatus() {
		throw new UnsupportedOperationException("Not valid");
	}

	public String[] getBooks() {
		throw new UnsupportedOperationException("Not valid");
	}

	public String[] getUsers() {
		throw new UnsupportedOperationException("Not valid");
	}

	public byte[] getFileBytes() {
		throw new UnsupportedOperationException("Not valid");
	}

	public String getFilename() {
		throw new UnsupportedOperationException("Not valid");
	}

	public int getPort() {
		throw new UnsupportedOperationException("Not valid");
	}
    
}