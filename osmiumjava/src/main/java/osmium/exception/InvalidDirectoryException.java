package osmium.exception;

import java.io.File;
import java.io.IOException;

public class InvalidDirectoryException extends IOException {

	private static final long serialVersionUID = 4286548479841592641L;

	public InvalidDirectoryException(String path) {
		super("Error: Path \"" + path + "\" is invalid.");
	}
	
	public InvalidDirectoryException(File path) {
		super("Error: Path \"" + path + "\" is invalid.");
	}

	public InvalidDirectoryException() {
		super("Error: Path is invalid.");
	}

}
