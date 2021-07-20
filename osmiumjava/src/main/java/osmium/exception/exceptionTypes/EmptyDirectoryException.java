package osmium.exception.exceptionTypes;

import java.io.File;
import java.io.IOException;

public class EmptyDirectoryException extends Exception {
	
	private static final long serialVersionUID = 5307008388022749132L;

	public EmptyDirectoryException(File path) {
		super("Error: Path \"" + path + "\" is empty.");
	}

}
