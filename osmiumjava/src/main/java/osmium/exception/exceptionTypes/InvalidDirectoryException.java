package osmium.exception.exceptionTypes;

import java.io.File;
import java.io.IOException;

public class InvalidDirectoryException extends Exception {

	private static final long serialVersionUID = 4286548479841592641L;
	
	public InvalidDirectoryException(File path) {
		super("Error: Path \"" + path + "\" is invalid.");
	}

}
