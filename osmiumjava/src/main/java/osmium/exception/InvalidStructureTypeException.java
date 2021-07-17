package osmium.exception;

import java.io.File;
import java.io.IOException;

public class InvalidStructureTypeException extends IOException {

	private static final long serialVersionUID = 4286548479841592641L;

	public InvalidStructureTypeException() {
		super("Error: Invalid structure type.");
	}

}
